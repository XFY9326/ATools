@file:Suppress("unused")

package io.github.xfy9326.aio.utils

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

inline fun File.withPreparedParentFolder(crossinline block: () -> Unit) {
    if (prepareParentFolder()) block()
}