@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package io.github.xfy9326.atools.crash

import android.content.Context
import java.io.File
import kotlin.system.exitProcess

class CrashLogger private constructor(context: Context, private var strategy: CrashLoggerStrategy) {
    companion object {
        @Volatile
        private var instance: CrashLogger? = null

        @Synchronized
        fun getInstance(): CrashLogger =
            instance ?: error("CrashLogger instance hasn't initialized yet!")

        @Synchronized
        fun initInstance(context: Context, enabled: Boolean, strategy: CrashLoggerStrategy): CrashLogger =
            instance ?: CrashLogger(context, strategy).also {
                instance = it
                it.enabled = enabled
                it.exceptionHandler.registerDefaultExceptionHandler()
            }
    }

    private var scene: String? = null
    private var enabled = true
    private val appContext = context.applicationContext
    private var onCrashLogged: ((LastCrashInfo, File) -> Unit)? = null
    private val exceptionHandler = ExceptionHandler()

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    fun setScene(scene: String) {
        this.scene = scene
    }

    fun updateStrategy(strategy: CrashLoggerStrategy) {
        this.strategy = strategy
    }

    fun lastCrashInfo() = CrashLogFileManager.readLastCrashInfo(strategy.crashLogDir)

    fun listLogFiles() = CrashLogFileManager.listLogFiles(strategy.crashLogDir)

    fun setOnCrashLoggedListener(action: (LastCrashInfo, File) -> Unit) {
        onCrashLogged = action
    }

    private inner class ExceptionHandler : Thread.UncaughtExceptionHandler {
        private var upstreamExceptionHandler: Thread.UncaughtExceptionHandler? = null

        @Synchronized
        fun registerDefaultExceptionHandler() {
            Thread.getDefaultUncaughtExceptionHandler().takeIf { it != this }?.let {
                this.upstreamExceptionHandler = it
                Thread.setDefaultUncaughtExceptionHandler(this)
            }
        }

        override fun uncaughtException(t: Thread, e: Throwable) {
            if (enabled) {
                try {
                    onException(t, e)
                } catch (internalException: Exception) {
                    internalException.printStackTrace()
                } finally {
                    if (strategy.useDefaultExceptionHandler) {
                        upstreamExceptionHandler?.uncaughtException(t, e) ?: run {
                            e.printStackTrace()
                            exitProcess(1)
                        }
                    }
                }
            } else {
                upstreamExceptionHandler?.uncaughtException(t, e) ?: run {
                    e.printStackTrace()
                    exitProcess(1)
                }
            }
        }
    }

    private fun onException(t: Thread, e: Throwable) {
        val lastCrashInfo = lastCrashInfo()
        val crashLog = strategy.crashLogGenerator.onGenerateCrashLog(appContext, scene, t, e)
        val logFile = CrashLogFileManager.logCrash(strategy.crashLogDir, crashLog)
        CrashLogFileManager.cleanOldLog(strategy.crashLogDir, strategy.maxLogAmount)
        onCrashLogged?.invoke(lastCrashInfo, logFile)
    }
}