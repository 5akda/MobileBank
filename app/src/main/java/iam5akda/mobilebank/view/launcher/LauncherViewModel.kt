package iam5akda.mobilebank.view.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor() : ViewModel() {

    private val _showDeviceNotSecure = MutableLiveData<Unit>()
    val showDeviceNotSecure: LiveData<Unit> = _showDeviceNotSecure

    private val _navigateToLogin = MutableLiveData<Unit>()
    val navigateToLogin: LiveData<Unit> = _navigateToLogin

    fun appCheck() {
        viewModelScope.launch {
            delay(2000L)
        }
    }
}