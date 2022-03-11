@file:Suppress("unused")

package io.github.xfy9326.aio.okio

import android.net.Uri
import io.github.xfy9326.aio.utils.prepareParentFolder
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
    if (target.exists()) {
        if (!overwrite)
            throw FileAlreadyExistsException(file = target, reason = "The destination file already exists.")
        else if (!target.delete())
            throw FileAlreadyExistsException(file = target, reason = "Tried to overwrite the destination, but failed to delete it.")
    }

    if (target.prepareParentFolder()) {
        return source().copyTo(target.sink(append))
    } else {
        throw FileSystemException(file = target, reason = "Failed to create target directory.")
    }
}

@Throws(IOException::class)
fun Uri.copyTo(uri: Uri, append: Boolean = false): Long {
    if (this == uri) return 0
    return source().copyTo(uri.sink(if (append) "wa" else "w"))
}