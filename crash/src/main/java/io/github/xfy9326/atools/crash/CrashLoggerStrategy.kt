package io.github.xfy9326.atools.crash

import java.io.File

data class CrashLoggerStrategy(
    val crashLogDir: File,
    val maxLogAmount: Int = 10,
    val crashLogGenerator: CrashLogGenerator = DefaultCrashLogGenerator(),
    val useDefaultExceptionHandler: Boolean = true
)
