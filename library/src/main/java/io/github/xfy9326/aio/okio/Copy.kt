@file:Suppress("unused")

package io.github.xfy9326.aio.okio

import android.graphics.Bitmap
import android.net.Uri
import io.github.xfy9326.aio.utils.checkOverwrite
import io.github.xfy9326.aio.utils.use
import okio.*
import java.io.File
import java.io.IOException

@Throws(IOException::class)
fun Source.copyTo(sink: Sink, closeAll: Boolean = true): Long {
    return if (closeAll) {
        use { input ->
            sink.use { output ->
                input.copyTo(output, false)
            }
        }
    } else {
        sink.buffer().writeAll(buffer())
    }
}

@Throws(IOException::class)
fun File.copyTo(uri: Uri, append: Boolean = false): Long {
    if (!this.exists())
        throw NoSuchFileException(file = this, reason = "The source file doesn't exist.")
    else if (!this.isDirectory)
        throw FileSystemException(file = this, reason = "The source file can't be directory.")

    return source().copyTo(uri.sink(if (append) "wa" else "w"))
}

@Throws(IOException::class)
fun Uri.copyTo(target: File, overwrite: Boolean = false, append: Boolean = false): Long {
    target.checkOverwrite(overwrite)
    return target.source().copyTo(target.sink(append))
}

@Throws(IOException::class)
fun Uri.copyTo(uri: Uri, append: Boolean = false): Long {
    if (this == uri) return 0
    return source().copyTo(uri.sink(if (append) "wa" else "w"))
}

// Copy image with specific compressFormat and quality
// If compressFormat is null, quality is useless
@Throws(IOException::class)
fun Uri.copyBitmapTo(target: File, overwrite: Boolean = false, compressFormat: Bitmap.CompressFormat? = null, quality: Int = 100) {
    if (compressFormat == null) {
        copyTo(target, overwrite)
    } else {
        target.checkOverwrite(overwrite)
        source().useBuffer { forceReadBitmap() }.use { bitmap ->
            target.sink().useBuffer { writeBitmap(bitmap, compressFormat, quality) }
        }
    }
}