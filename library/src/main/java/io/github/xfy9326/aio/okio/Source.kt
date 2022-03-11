@file:Suppress("unused")

package io.github.xfy9326.aio.okio

import android.content.res.AssetManager
import android.content.res.Resources
import android.net.Uri
import android.util.TypedValue
import io.github.xfy9326.aio.AIOManager
import io.github.xfy9326.aio.file.AssetFile
import io.github.xfy9326.aio.file.RawResFile
import okio.Source
import okio.source
import java.io.IOException

@Throws(IOException::class)
fun AssetFile.source(accessMode: Int = AssetManager.ACCESS_STREAMING): Source =
    open(accessMode).source()

@Throws(Resources.NotFoundException::class)
fun RawResFile.source(): Source =
    open().source()

@Throws(Resources.NotFoundException::class)
fun RawResFile.source(typedValue: TypedValue): Source =
    open(typedValue).source()

@Throws(IOException::class)
fun Uri.source(): Source =
    AIOManager.contentResolver.openInputStream(this)?.source() ?: throw IOException("$this: Uri source open failed.")