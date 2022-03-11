@file:Suppress("unused")

package io.github.xfy9326.aio

import android.content.ContentResolver
import android.content.res.AssetManager
import android.content.res.Resources
import io.github.xfy9326.aio.startup.AIOInitializer

object AIOManager {
    internal val appContext by lazy { AIOInitializer.applicationContext }

    val contentResolver: ContentResolver by lazy { appContext.contentResolver }
    val assetManager: AssetManager by lazy { appContext.assets }
    val resources: Resources by lazy { appContext.resources }
}