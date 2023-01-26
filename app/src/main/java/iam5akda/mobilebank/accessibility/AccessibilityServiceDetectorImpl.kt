package iam5akda.mobilebank.accessibility

import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityManager
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AccessibilityServiceDetectorImpl @Inject constructor(
    private val accessibilityManager: AccessibilityManager
): AccessibilityServiceDetector {

    override fun getEnabledServiceList(): Flow<List<AccessibilityServiceInfo>> {
        return flow {
            val serviceList = accessibilityManager
                .getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
            emit(serviceList)
        }
    }
}