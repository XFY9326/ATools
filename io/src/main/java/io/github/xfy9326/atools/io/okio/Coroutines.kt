@file:Suppress("unused")

package io.github.xfy9326.atools.io.okio

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

suspend fun Uri.writeTextAsync(text: String, append: Boolean = false): Result<Unit> =
    withContext(Dispatchers.IO) {
        runCatching {
            writeText(text, append)
        }
    }

suspend fun Uri.readTextAsync(): Result<String> =
    withContext(Dispatchers.IO) {
        runCatching {
            readText()
        }
    }

suspend fun File.writeTextAsync(text: String, append: Boolean = false): Result<Unit> =
    withContext(Dispatchers.IO) {
        runCatching {
            writeText(text, append)
        }
    }

suspend fun File.readTextAsync(): Result<String> =
    withContext(Dispatchers.IO) {
        runCatching {
            readText()
        }
    }
