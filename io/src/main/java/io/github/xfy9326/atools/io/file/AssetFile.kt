@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.xfy9326.atools.io.file

import android.content.res.AssetManager
import android.net.Uri
import io.github.xfy9326.atools.io.IOManager
import java.io.IOException

fun assetFile(path: String) = AssetFile(path)

class AssetFile(val path: String) {
    val uri: Uri
        get() = Uri.parse("file:///android_asset/$path")

    @Throws(IOException::class)
    fun open(accessMode: Int = AssetManager.ACCESS_STREAMING) = IOManager.assetManager.open(path, accessMode)

    @Throws(IOException::class)
    fun openFd() = IOManager.assetManager.openFd(path)

    @Throws(IOException::class)
    fun openNonAssetFd(cookie: Int = 0) = IOManager.assetManager.openNonAssetFd(cookie, path)

    @Throws(IOException::class)
    fun openXmlResourceParser(cookie: Int = 0) = IOManager.assetManager.openXmlResourceParser(cookie, path)
}