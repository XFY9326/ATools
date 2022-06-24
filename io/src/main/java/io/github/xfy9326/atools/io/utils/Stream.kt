@file:Suppress("unused")

package io.github.xfy9326.atools.io.utils

import java.io.Closeable

fun Closeable.closeQuietly() {
    try {
        close()
    } catch (e: Exception) {
        // Ignore
    }
}