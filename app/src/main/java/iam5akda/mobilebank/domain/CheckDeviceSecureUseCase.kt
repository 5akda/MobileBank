package iam5akda.mobilebank.domain

import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import iam5akda.mobilebank.accessibility.AccessibilityServiceDetector
import iam5akda.mobilebank.file.FileReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
class CheckDeviceSecureUseCase @Inject constructor(
    private val accessibilityServiceDetector: AccessibilityServiceDetector,
    private val fileReader: FileReader,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun invoke(): Flow<Result<Boolean>> {
        return accessibilityServiceDetector.getEnabledServiceList()
            .flatMapConcat {
                flowOf(checkEnabledServiceWithWhitelist(it))
            }
            .map { Result.success(it) }
            .catch { Result.failure<Boolean>(it) }
            .flowOn(dispatcher)
    }

    private fun checkEnabledServiceWithWhitelist(
        serviceList: List<AccessibilityServiceInfo>
    ): Boolean {
        val whitePackageNameList = fileReader.loadRawData(fileName = "whitelist.txt")
            .split('\n')

        serviceList.forEach { service ->
            val packageName = service.id.substringBefore('/')
            Log.d("CheckDeviceSecure", "Enabled Service: $packageName")
            if (!whitePackageNameList.contains(packageName)) return false
        }
        return true
    }
}