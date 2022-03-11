@file:Suppress("unused")

package io.github.xfy9326.aio.okio

import android.net.Uri

fun Uri.writeText(text: String, mode: String? = null): Boolean =
    sink(mode)?.useBuffer {
        writeUtf8(text)
        true
    } ?: false

fun Uri.readText(): String? =
    source()?.useBuffer {
        readUtf8()
    }