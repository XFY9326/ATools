@file:Suppress("unused")

package io.github.xfy9326.atools.core

import java.security.MessageDigest

fun String.md5(): String {
    return MessageDigest.getInstance("MD5").digest(this.toByteArray()).toHex()
}

fun ByteArray.toHex(): String {
    val builder = StringBuilder()
    for (byte in this) {
        val hex = Integer.toHexString(byte.toInt() and 0xFF)
        if (hex.length == 1) builder.append('0')
        builder.append(hex)
    }
    return builder.toString()
}

fun String.hexToByteArray(): ByteArray = ByteArray(length / 2) {
    (substring(2 * it, 2 * it + 2).toInt(16) and 0xFF).toByte()
}
