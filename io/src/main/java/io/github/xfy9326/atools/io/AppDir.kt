@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.xfy9326.atools.io

import androidx.core.content.ContextCompat
import io.github.xfy9326.atools.core.AppContext
import io.github.xfy9326.atools.io.utils.joinToFilePath
import java.io.File

object AppDir {
    fun externalFilesDirs(vararg childDirs: String): List<File> =
        ContextCompat.getExternalFilesDirs(AppContext, childDirs.joinToFilePath()).filterNotNull()

    fun externalFilesDir(vararg childDirs: String): File =
        externalFilesDirs(*childDirs).firstOrNull() ?: error("No external files dir available!")

    val externalFilesDir: File
        get() = externalFilesDir()

    val externalFilesDirs: List<File>
        get() = externalFilesDirs()

    val externalCacheDirs: List<File>
        get() = ContextCompat.getExternalCacheDirs(AppContext).filterNotNull()

    val externalCacheDir: File
        get() = externalCacheDirs.firstOrNull() ?: error("No external cache dir available!")

    val obbDirs: List<File>
        get() = ContextCompat.getObbDirs(AppContext).filterNotNull()

    val dataDir: File
        get() = ContextCompat.getDataDir(AppContext) ?: error("No data dir available!")

    val filesDir: File
        get() = AppContext.filesDir

    val cacheDir: File
        get() = AppContext.cacheDir

    val codeCacheDir: File
        get() = ContextCompat.getCodeCacheDir(AppContext)

    val noBackupFilesDir: File?
        get() = ContextCompat.getNoBackupFilesDir(AppContext)
}