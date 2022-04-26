package io.github.xfy9326.atools.crash

data class LastCrashInfo(
    val lastCrashMills: Long,
    val lastCrashLogPath: String?
)
