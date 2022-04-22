@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.xfy9326.aio.file

import android.content.res.AssetFileDescriptor
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.RawRes
import io.github.xfy9326.aio.AIOManager

fun rawResFile(@RawRes resId: Int) = RawResFile(resId)

class RawResFile(@RawRes val resId: Int) {
    @Throws(Resources.NotFoundException::class)
    fun open() = AIOManager.resources.openRawResource(resId)

    @Throws(Resources.NotFoundException::class)
    fun open(value: TypedValue) = AIOManager.resources.openRawResource(resId, value)

    @Throws(Resources.NotFoundException::class)
    fun openFd(): AssetFileDescriptor? = AIOManager.resources.openRawResourceFd(resId)
}