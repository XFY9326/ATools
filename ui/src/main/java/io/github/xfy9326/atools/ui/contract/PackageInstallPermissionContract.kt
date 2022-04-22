@file:Suppress("unused")

package io.github.xfy9326.atools.ui.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContract
import io.github.xfy9326.atools.ui.packageUri

class PackageInstallPermissionContract : ActivityResultContract<Nothing, Boolean>() {
    override fun createIntent(context: Context, input: Nothing): Intent {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, context.packageUri)
        } else {
            Intent(Settings.ACTION_SECURITY_SETTINGS)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean =
        resultCode == Activity.RESULT_OK
}