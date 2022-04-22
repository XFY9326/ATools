@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.xfy9326.aio.file

import android.content.res.AssetManager
import io.github.xfy9326.aio.AIOManager
import java.io.IOException

fun assetFile(path: String) = AssetFile(path)

class AssetFile(val path: String) {
    @Throws(IOException::class)
    fun open(accessMode: Int = AssetManager.ACCESS_STREAMING) = AIOManager.assetManager.open(path, accessMode)

    @Throws(IOException::class)
    fun openFd() = AIOManager.assetManager.openFd(path)

    @Throws(IOException::class)
    fun openNonAssetFd(cookie: Int = 0) = AIOManager.assetManager.openNonAssetFd(cookie, path)

    @Throws(IOException::class)
    fun openXmlResourceParser(cookie: Int = 0) = AIOManager.assetManager.openXmlResourceParser(cookie, path)
}