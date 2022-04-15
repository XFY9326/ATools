@file:Suppress("unused")

package io.github.xfy9326.aio.okio

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okio.BufferedSink
import okio.BufferedSource
import java.io.IOException

@Throws(IOException::class)
fun BufferedSource.readBitmap(): Bitmap? =
    BitmapFactory.decodeStream(inputStream())

@Throws(IOException::class)
fun BufferedSource.forceReadBitmap(): Bitmap =
    readBitmap() ?: throw IOException("Can't read this as Bitmap!")

@Throws(IOException::class)
fun BufferedSink.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int = 100, flush: Boolean = true): Boolean =
    bitmap.compress(format, quality, outputStream()).also { if (isOpen && flush) flush() }