package io.github.xfy9326.atools.crash

import android.content.Context
import android.os.Build

private const val UNKNOWN_VERSION_NAME = "Unknown"
private const val UNKNOWN_VERSION_CODE = 0L

internal fun Context.appVersion(): Pair<String, Long> {
    try {
        packageManager.getPackageInfo(packageName, 0).let {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.versionName to it.longVersionCode
            } else {
                @Suppress("DEPRECATION")
                it.versionName to it.versionCode.toLong()
            }
        }
    } catch (e: Exception) {
        return UNKNOWN_VERSION_NAME to UNKNOWN_VERSION_CODE
    }
}