package iam5akda.mobilebank.view.launcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import iam5akda.mobilebank.databinding.ActivityLauncherBinding
import iam5akda.mobilebank.view.login.LoginActivity

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding
    private val viewModel: LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().also {
            it.setKeepOnScreenCondition { viewModel.isLoading }
        }
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.appCheck()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.showDeviceNotSecure.observe(this) {
            showDeviceNotSecureDialog()
        }
        viewModel.navigateToLogin.observe(this) {
            LoginActivity.navigate(this)
            finish()
        }
    }

    private fun showDeviceNotSecureDialog() {
        AlertDialog.Builder(this)
            .setMessage("Your device is not secure.")
            .setTitle("Access Denied!")
            .setCancelable(false)
            .setPositiveButton("EXIT") { _,_ ->
                finish()
            }
            .create()
            .show()
    }
}