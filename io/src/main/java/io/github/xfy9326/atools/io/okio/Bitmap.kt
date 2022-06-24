@file:Suppress("unused")

package io.github.xfy9326.atools.io.okio

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.net.Uri
import io.github.xfy9326.atools.io.file.AssetFile
import io.github.xfy9326.atools.io.file.RawResFile
import okio.BufferedSink
import okio.BufferedSource
import okio.sink
import okio.source
import java.io.File
import java.io.IOException

@Throws(IOException::class)
fun BufferedSource.readBitmap(outPadding: Rect? = null, opts: BitmapFactory.Options? = null): Bitmap? =
    BitmapFactory.decodeStream(inputStream(), outPadding, opts)

@Throws(IOException::class)
fun BufferedSink.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int = 100, flush: Boolean = true): Boolean =
    bitmap.compress(format, quality, outputStream()).also { if (isOpen && flush) flush() }

@Throws(IOException::class)
fun AssetFile.readBitmap(outPadding: Rect? = null, opts: BitmapFactory.Options? = null): Bitmap? =
    source().useBuffer {
        readBitmap(outPadding, opts)
    }

@Throws(IOException::class)
fun RawResFile.readBitmap(outPadding: Rect? = null, opts: BitmapFactory.Options? = null): Bitmap? =
    source().useBuffer {
        readBitmap(outPadding, opts)
    }

@Throws(IOException::class)
fun Uri.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int = 100, flush: Boolean = true): Unit =
    sink().useBuffer {
        writeBitmap(bitmap, format, quality, flush)
    }

@Throws(IOException::class)
fun Uri.readBitmap(outPadding: Rect? = null, opts: BitmapFactory.Options? = null): Bitmap? =
    source().useBuffer {
        readBitmap(outPadding, opts)
    }

@Throws(IOException::class)
fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int = 100, flush: Boolean = true): Unit =
    sink().useBuffer {
        writeBitmap(bitmap, format, quality, flush)
    }

@Throws(IOException::class)
fun File.readBitmap(outPadding: Rect? = null, opts: BitmapFactory.Options? = null): Bitmap? =
    source().useBuffer {
        readBitmap(outPadding, opts)
    }
