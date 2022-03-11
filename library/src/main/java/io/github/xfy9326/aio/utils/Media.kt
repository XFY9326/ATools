@file:Suppress("unused")

package io.github.xfy9326.aio.utils

import android.content.Intent
import android.net.Uri
import android.os.Build
import io.github.xfy9326.aio.AIOManager

fun Uri.requestScanMediaFile() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        @Suppress("DEPRECATION")
        AIOManager.appContext.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, this))
    }
}