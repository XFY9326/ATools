package io.github.xfy9326.atools.crash

import android.content.Context
import android.os.Build
import java.util.*

class DefaultCrashLogGenerator : CrashLogGenerator {
    companion object {
        const val UNKNOWN_VERSION_NAME = "Unknown"
        const val UNKNOWN_VERSION_CODE = 0L
    }

    override fun onGenerateCrashLog(context: Context, scene: String?, thread: Thread, throwable: Throwable): String {
        val appVersion = readVersion(context)
        return buildString {
            appendLine(Date().toString())
            appendLine()
            appendLine("Version: ${appVersion.first} (${appVersion.second})")
            appendLine("Device Name: ${Build.BRAND}  ${Build.MODEL}")
            appendLine("Device ABI: ${Build.SUPPORTED_ABIS?.joinToString()}")
            appendLine("Android Version: ${Build.VERSION.RELEASE} (${Build.VERSION.SDK_INT})")
            if (scene != null) appendLine("Scene: $scene")
            appendLine()
            appendLine(thread)
            appendLine(throwable.stackTraceToString())
        }
    }

    private fun readVersion(context: Context): Pair<String, Long> {
        try {
            context.packageManager.getPackageInfo(context.packageName, 0).let {
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
}