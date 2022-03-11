@file:Suppress("unused")

package io.github.xfy9326.aio.utils

import android.graphics.Bitmap
import android.os.Build

val WEBPCompat: Bitmap.CompressFormat
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Bitmap.CompressFormat.WEBP_LOSSY
    } else {
        @Suppress("DEPRECATION")
        Bitmap.CompressFormat.WEBP
    }

@Suppress("DEPRECATION")
fun Bitmap.CompressFormat.isWEBP() =
    this == Bitmap.CompressFormat.WEBP || Build.VERSION.SDK_INT >= Build.VERSION_CODES.R &&
            (this == Bitmap.CompressFormat.WEBP_LOSSY || this == Bitmap.CompressFormat.WEBP_LOSSLESS)

fun Bitmap.tryRecycle() {
    try {
        if (!isRecycled) recycle()
    } catch (e: Exception) {
        // Ignore
    }
}