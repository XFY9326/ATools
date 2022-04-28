@file:Suppress("unused")

package io.github.xfy9326.atools.io.serialization.json

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File

suspend inline fun <reified T> File.readJSONAsync(json: Json = Json.Default): Result<T> =
    withContext(Dispatchers.IO) {
        runCatching {
            readJSON(json)
        }
    }

suspend inline fun <reified T> File.writeJSONAsync(data: T, json: Json = Json.Default): Result<Unit> =
    withContext(Dispatchers.IO) {
        runCatching {
            writeJSON(data, json)
        }
    }

suspend inline fun <reified T> Uri.readJSONAsync(json: Json = Json.Default): Result<T> =
    withContext(Dispatchers.IO) {
        runCatching {
            readJSON(json)
        }
    }

suspend inline fun <reified T> Uri.writeJSONAsync(data: T, json: Json = Json.Default): Result<Unit> =
    withContext(Dispatchers.IO) {
        runCatching {
            writeJSON(data, json)
        }
    }