@file:Suppress("unused")

package io.github.xfy9326.aio.okio

import android.net.Uri
import io.github.xfy9326.aio.AIOManager
import okio.Sink
import okio.sink
import java.io.IOException

fun Uri.sink(mode: String? = null): Sink =
    AIOManager.contentResolver.let {
        if (mode == null) {
            it.openOutputStream(this)
        } else {
            it.openOutputStream(this, mode)
        }
    }?.sink() ?: throw IOException("$this: Uri sink open failed.")