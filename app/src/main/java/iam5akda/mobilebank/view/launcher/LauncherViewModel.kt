package iam5akda.mobilebank.view.launcher

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.mobilebank.view.accessibility.AccessibilityServiceDetector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
    private val accessibilityServiceDetector: AccessibilityServiceDetector
) : ViewModel() {

    var isLoading: Boolean = true

    private val _showDeviceNotSecure = MutableLiveData<Unit>()
    val showDeviceNotSecure: LiveData<Unit> = _showDeviceNotSecure

    private val _navigateToLogin = MutableLiveData<Unit>()
    val navigateToLogin: LiveData<Unit> = _navigateToLogin

    fun appCheck() {
        printServiceList()
    }

    private fun printServiceList() {
        viewModelScope.launch {
            accessibilityServiceDetector.getEnabledServiceList()
                .flowOn(Dispatchers.IO)
                .collect {
                    it.forEach { service ->
                        Log.d("AccessibilityService", service.id)
                    }
                    _navigateToLogin.postValue(Unit)
                }
        }
    }

    private fun checkDeviceSecurity() {
        viewModelScope.launch {
            accessibilityServiceDetector.isDeviceSecure()
                .flowOn(Dispatchers.IO)
                .onCompletion {
                    isLoading = false
                }
                .collect { isSecure ->
                    when (isSecure) {
                        true -> _navigateToLogin.postValue(Unit)
                        false -> _showDeviceNotSecure.postValue(Unit)
                    }
                }
        }
    }
}