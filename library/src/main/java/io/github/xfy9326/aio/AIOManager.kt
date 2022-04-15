@file:Suppress("unused")

package io.github.xfy9326.aio

import android.content.ContentResolver
import android.content.res.AssetManager
import android.content.res.Resources
import io.github.xfy9326.aio.startup.AIOInitializer
import io.github.xfy9326.aio.utils.deleteRecursively
import io.github.xfy9326.aio.utils.runIOJob

object AIOManager {
    internal val appContext by lazy { AIOInitializer.applicationContext }

    val contentResolver: ContentResolver by lazy { appContext.contentResolver }
    val assetManager: AssetManager by lazy { appContext.assets }
    val resources: Resources by lazy { appContext.resources }

    suspend fun clearAllCache(): Boolean = runIOJob {
        AppDir.cacheDir.deleteRecursively()
        AppDir.codeCacheDir.deleteRecursively()
        AppDir.externalCacheDirs.deleteRecursively()
    }.isSuccess
}