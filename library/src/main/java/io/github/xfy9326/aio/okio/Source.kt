@file:Suppress("unused")

package io.github.xfy9326.aio.okio

import android.content.res.AssetManager
import android.net.Uri
import android.util.TypedValue
import io.github.xfy9326.aio.AIOManager
import io.github.xfy9326.aio.file.AssetFile
import io.github.xfy9326.aio.file.RawResFile
import okio.Sink
import okio.Source
import okio.sink
import okio.source

fun AssetFile.source(accessMode: Int = AssetManager.ACCESS_STREAMING): Source =
    open(accessMode).source()

fun RawResFile.source(): Source =
    open().source()

fun RawResFile.source(typedValue: TypedValue): Source =
    open(typedValue).source()

fun Uri.source(): Source? =
    AIOManager.contentResolver.openInputStream(this)?.source()