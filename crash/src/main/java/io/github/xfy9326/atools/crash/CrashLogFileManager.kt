package io.github.xfy9326.atools.crash

import java.io.File
import java.util.*

internal object CrashLogFileManager {
    private const val PROPERTIES_LAST_CRASH_MILLS = "last_crash_mills"
    private const val PROPERTIES_LAST_CRASH_LOG_FILE = "last_crash_log_file"
    private const val FILE_LAST_CRASH_PROPERTIES = "last_crash.properties"
    private const val FILE_EXTENSION_LOG = ".log"

    fun listLogFiles(logDir: File): List<File> =
        logDir.takeIf { it.isDirectory }?.listFiles { _, name ->
            name.endsWith(FILE_EXTENSION_LOG)
        }?.sortedByDescending {
            it.lastModified()
        } ?: emptyList()

    fun cleanOldLog(logDir: File, maxLogAmount: Int) {
        logDir.takeIf { it.isDirectory }?.listFiles { _, name ->
            name.endsWith(FILE_EXTENSION_LOG)
        }?.takeIf { it.size - 1 > maxLogAmount + 1 }?.sortedBy {
            it.lastModified()
        }?.let { files ->
            files.take(files.size - maxLogAmount).forEach {
                it.delete()
            }
        }
    }

    fun logCrash(crashLogDir: File, crashLog: String): File {
        val mills = System.currentTimeMillis()
        val crashLogFile = File(crashLogDir, "$mills$FILE_EXTENSION_LOG")
        if (crashLogDir.let { it.exists() || it.mkdirs() }) {
            crashLogFile.writeText(crashLog)
            saveLastCrashInfo(crashLogDir, mills, crashLogFile)
        } else {
            throw FileSystemException(file = crashLogDir, reason = "Failed to create directory.")
        }
        return crashLogFile
    }

    private fun saveLastCrashInfo(crashLogDir: File, crashMills: Long, crashLogFile: File) {
        saveCrashProperties(crashLogDir, Properties().also {
            it[PROPERTIES_LAST_CRASH_MILLS] = crashMills.toString()
            it[PROPERTIES_LAST_CRASH_LOG_FILE] = crashLogFile.absolutePath
        })
    }

    fun readLastCrashInfo(crashLogDir: File): LastCrashInfo {
        return readCrashProperties(crashLogDir).let {
            LastCrashInfo(
                lastCrashMills = it[PROPERTIES_LAST_CRASH_MILLS]?.toString()?.toLongOrNull() ?: 0,
                lastCrashLogPath = it[PROPERTIES_LAST_CRASH_LOG_FILE]?.toString()
            )
        }
    }

    private fun saveCrashProperties(crashLogDir: File, properties: Properties) {
        val crashLogMeta = File(crashLogDir, FILE_LAST_CRASH_PROPERTIES)
        if (crashLogDir.let { it.exists() || it.mkdirs() }) {
            properties.store(crashLogMeta.outputStream(), "Crash Log Properties")
        } else {
            throw FileSystemException(file = crashLogDir, reason = "Failed to create directory.")
        }
    }

    private fun readCrashProperties(crashLogDir: File): Properties {
        val crashLogMeta = File(crashLogDir, FILE_LAST_CRASH_PROPERTIES)
        val properties = Properties()
        if (crashLogMeta.isFile) {
            properties.load(crashLogMeta.inputStream())
        }
        return properties
    }
}