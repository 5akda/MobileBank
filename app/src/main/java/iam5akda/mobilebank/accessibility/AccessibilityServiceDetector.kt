package iam5akda.mobilebank.accessibility

import android.accessibilityservice.AccessibilityServiceInfo
import kotlinx.coroutines.flow.Flow

interface AccessibilityServiceDetector {
    fun getEnabledServiceList(): Flow<List<AccessibilityServiceInfo>>
    fun isDeviceSecure(): Flow<Boolean>
}