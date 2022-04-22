@file:Suppress("unused")

package io.github.xfy9326.atools.io.okio

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import okio.BufferedSink
import okio.BufferedSource
import java.io.IOException

@Throws(IOException::class)
fun BufferedSource.readBitmap(outPadding: Rect? = null, opts: BitmapFactory.Options? = null): Bitmap? =
    BitmapFactory.decodeStream(inputStream(), outPadding, opts)

@Throws(IOException::class)
fun BufferedSource.forceReadBitmap(outPadding: Rect? = null, opts: BitmapFactory.Options? = null): Bitmap =
    readBitmap(outPadding, opts) ?: throw IOException("Can't read this as Bitmap!")

@Throws(IOException::class)
fun BufferedSink.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int = 100, flush: Boolean = true): Boolean =
    bitmap.compress(format, quality, outputStream()).also { if (isOpen && flush) flush() }