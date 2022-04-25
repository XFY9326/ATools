@file:Suppress("unused")

package io.github.xfy9326.atools.ui.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import io.github.xfy9326.atools.ui.packageUri

@RequiresApi(Build.VERSION_CODES.S)
class ScheduleExactAlarmPermissionContract : ActivityResultContract<Unit?, Boolean>() {
    override fun createIntent(context: Context, input: Unit?): Intent =
        Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM, context.packageUri)

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean =
        resultCode == Activity.RESULT_OK
}