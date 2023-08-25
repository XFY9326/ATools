package io.github.xfy9326.atools.io.okio

import android.net.Uri
import io.github.xfy9326.atools.io.IOManager
import okio.Sink
import okio.sink
import java.io.IOException

@Throws(IOException::class)
fun Uri.sink(mode: String? = null): Sink =
    IOManager.contentResolver.let {
        if (mode == null) {
            it.openOutputStream(this)
        } else {
            it.openOutputStream(this, mode)
        }
    }?.sink() ?: throw IOException("$this: Uri sink open failed.")