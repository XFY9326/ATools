@file:Suppress("unused")

package io.github.xfy9326.atools.io

import android.content.ContentResolver
import android.content.res.AssetManager
import android.content.res.Resources
import io.github.xfy9326.atools.core.AppContext
import io.github.xfy9326.atools.io.utils.deleteRecursively
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IOManager {
    val contentResolver: ContentResolver by lazy { AppContext.contentResolver }
    val assetManager: AssetManager by lazy { AppContext.assets }
    val resources: Resources by lazy { AppContext.resources }

    suspend fun clearAllCache(): Boolean = withContext(Dispatchers.IO) {
        runCatching {
            AppDir.cacheDir.deleteRecursively()
            AppDir.codeCacheDir.deleteRecursively()
            AppDir.externalCacheDirs.deleteRecursively()
        }.isSuccess
    }
}