@file:Suppress("unused")

package io.github.xfy9326.atools.io.file

import android.content.res.AssetFileDescriptor
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.RawRes
import io.github.xfy9326.atools.io.IOManager

fun rawResFile(@RawRes resId: Int) = RawResFile(resId)

class RawResFile(@RawRes val resId: Int) {
    @Throws(Resources.NotFoundException::class)
    fun open() = IOManager.resources.openRawResource(resId)

    @Throws(Resources.NotFoundException::class)
    fun open(value: TypedValue) = IOManager.resources.openRawResource(resId, value)

    @Throws(Resources.NotFoundException::class)
    fun openFd(): AssetFileDescriptor? = IOManager.resources.openRawResourceFd(resId)
}