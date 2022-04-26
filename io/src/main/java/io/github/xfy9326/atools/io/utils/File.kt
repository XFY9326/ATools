@file:Suppress("unused")

package io.github.xfy9326.atools.io.utils

import java.io.File

fun filePath(vararg str: String): String? =
    str.joinToFilePath()

fun Array<out String>.joinToFilePath(): String? =
    if (isEmpty()) null else joinToString(File.separator)

fun File.asParentOf(vararg path: String): File =
    path.joinToFilePath()?.let { File(this, it) } ?: this

fun Iterable<File>.deleteRecursively(): Boolean =
    map { it.deleteRecursively() }.all { it }

fun Array<File>.deleteRecursively(): Boolean =
    map { it.deleteRecursively() }.all { it }

fun Sequence<File>.deleteRecursively(): Boolean =
    map { it.deleteRecursively() }.all { it }

fun File.takeIfExists(): File? =
    takeIf { it.exists() }

fun File.takeIfIsFile(): File? =
    takeIf { it.isFile }

fun File.takeIfIsDirectory(): File? =
    takeIf { it.isDirectory }

fun File.prepareParentFolder(): Boolean =
    parentFile.let { it == null || it.exists() || it.mkdirs() }

@Throws(FileSystemException::class)
fun File.preparedParentFolder(): File =
    if (prepareParentFolder()) {
        this
    } else {
        throw FileSystemException(file = this, reason = "Failed to create parent directory.")
    }

inline fun File.withPreparedParentFolder(crossinline block: (File) -> Unit) {
    if (prepareParentFolder()) block(this)
}

internal fun File.checkOverwrite(overwrite: Boolean) {
    if (exists()) {
        if (!overwrite)
            throw FileAlreadyExistsException(file = this, reason = "The destination file already exists.")
        else if (!delete())
            throw FileAlreadyExistsException(file = this, reason = "Tried to overwrite the destination, but failed to delete it.")
    }
}