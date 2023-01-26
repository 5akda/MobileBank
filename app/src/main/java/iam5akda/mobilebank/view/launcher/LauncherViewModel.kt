package iam5akda.mobilebank.view.launcher

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.mobilebank.domain.CheckDeviceSecureUseCase
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
    private val checkDeviceSecureUseCase: CheckDeviceSecureUseCase
) : ViewModel() {

    var isLoading: Boolean = true

    private val _showDeviceNotSecure = MutableLiveData<Unit>()
    val showDeviceNotSecure: LiveData<Unit> = _showDeviceNotSecure

    private val _navigateToLogin = MutableLiveData<Unit>()
    val navigateToLogin: LiveData<Unit> = _navigateToLogin

    fun appCheck() {
        viewModelScope.launch {
            checkDeviceSecureUseCase.invoke()
                .onCompletion { isLoading = false }
                .collect { result ->
                    result.onSuccess { processCheckDeviceSecure(it) }
                        .onFailure { Log.d("LauncherViewModel", "AppCheck Error Jaaa") }
                }
        }
    }

    private fun processCheckDeviceSecure(isSecure: Boolean) {
        if (isSecure) {
            _navigateToLogin.postValue(Unit)
        } else {
            _showDeviceNotSecure.postValue(Unit)
        }
    }
}