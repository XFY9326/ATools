@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.xfy9326.aio

import androidx.core.content.ContextCompat
import io.github.xfy9326.aio.utils.joinToFilePath
import java.io.File

object AppDir {
    fun externalFilesDirs(vararg childDirs: String): List<File> =
        ContextCompat.getExternalFilesDirs(AIOManager.appContext, childDirs.joinToFilePath()).filterNotNull()

    fun externalFilesDir(vararg childDirs: String): File =
        externalFilesDirs(*childDirs).firstOrNull() ?: error("No external files dir available!")

    val externalFilesDir: File
        get() = externalFilesDir()

    val externalFilesDirs: List<File>
        get() = externalFilesDirs()

    val externalCacheDirs: List<File>
        get() = ContextCompat.getExternalCacheDirs(AIOManager.appContext).filterNotNull()

    val externalCacheDir: File
        get() = externalCacheDirs.firstOrNull() ?: error("No external cache dir available!")

    val obbDirs: List<File>
        get() = ContextCompat.getObbDirs(AIOManager.appContext).filterNotNull()

    val dataDir: File
        get() = ContextCompat.getDataDir(AIOManager.appContext) ?: error("No data dir available!")

    val filesDir: File
        get() = AIOManager.appContext.filesDir

    val cacheDir: File
        get() = AIOManager.appContext.cacheDir

    val codeCacheDir: File
        get() = ContextCompat.getCodeCacheDir(AIOManager.appContext)

    val noBackupFilesDir: File?
        get() = ContextCompat.getNoBackupFilesDir(AIOManager.appContext)
}