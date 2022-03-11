@file:Suppress("unused")

package io.github.xfy9326.aio.okio

import android.net.Uri
import java.io.IOException

@Throws(IOException::class)
fun Uri.writeText(text: String, mode: String? = null): Boolean =
    sink(mode).useBuffer {
        writeUtf8(text)
        true
    }

@Throws(IOException::class)
fun Uri.readText(): String =
    source().useBuffer {
        readUtf8()
    }