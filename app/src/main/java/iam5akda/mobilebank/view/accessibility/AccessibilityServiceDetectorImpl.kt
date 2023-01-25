package iam5akda.mobilebank.view.accessibility

import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityManager
import iam5akda.mobilebank.view.file.FileReader
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
class AccessibilityServiceDetectorImpl @Inject constructor(
    private val accessibilityManager: AccessibilityManager,
    private val fileReader: FileReader
): AccessibilityServiceDetector {

    override fun getEnabledServiceList(): Flow<List<AccessibilityServiceInfo>> {
        return flow {
            val serviceList = accessibilityManager
                .getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
            emit(serviceList)
        }
    }

    override fun isDeviceSecure(): Flow<Boolean> {
        return getEnabledServiceList()
            .flatMapConcat {
                flowOf(checkEnabledServiceWithWhitelist(it))
            }
    }

    private fun checkEnabledServiceWithWhitelist(
        serviceList: List<AccessibilityServiceInfo>
    ): Boolean {
        val whitePackageNameList = fileReader.loadRawData(fileName = "whitelist.txt")
            .split('\n')

        serviceList.forEach { service ->
            val packageName = service.id.substringBefore('/')
            if (!whitePackageNameList.contains(packageName)) return false
        }
        return true
    }
}