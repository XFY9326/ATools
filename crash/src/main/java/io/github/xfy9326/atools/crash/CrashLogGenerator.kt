package io.github.xfy9326.atools.crash

import android.content.Context

interface CrashLogGenerator {
    fun onGenerateCrashLog(context: Context, scene: String?, thread: Thread, throwable: Throwable): String
}