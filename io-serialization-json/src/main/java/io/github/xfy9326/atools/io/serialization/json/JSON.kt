@file:Suppress("unused")

package io.github.xfy9326.atools.io.serialization.json

import android.net.Uri
import io.github.xfy9326.atools.io.okio.sink
import io.github.xfy9326.atools.io.okio.source
import io.github.xfy9326.atools.io.okio.useBuffer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okio.*
import java.io.File
import java.io.FileNotFoundException

@Throws(SerializationException::class)
inline fun <reified T> BufferedSource.readJSON(json: Json = Json.Default): T =
    json.decodeFromString(readUtf8())

@Throws(SerializationException::class)
inline fun <reified T> BufferedSink.writeJSON(json: Json = Json.Default, data: T) {
    writeUtf8(json.encodeToString(data))
}

@Throws(FileNotFoundException::class, SerializationException::class)
inline fun <reified T> File.readJSON(json: Json = Json.Default): T =
    source().readJSON(json)

@Throws(FileNotFoundException::class, SerializationException::class)
inline fun <reified T> File.writeJSON(data: T, json: Json = Json.Default): Unit =
    sink().writeJSON(data, json)

@Throws(IOException::class, SerializationException::class)
inline fun <reified T> Uri.readJSON(json: Json = Json.Default): T =
    source().readJSON(json)

@Throws(IOException::class, SerializationException::class)
inline fun <reified T> Uri.writeJSON(data: T, json: Json = Json.Default): Unit =
    sink().writeJSON(data, json)

@Throws(IOException::class, SerializationException::class)
inline fun <reified T> Source.readJSON(json: Json = Json.Default): T =
    useBuffer { readJSON(json) }

@Throws(IOException::class, SerializationException::class)
inline fun <reified T> Sink.writeJSON(data: T, json: Json = Json.Default): Unit =
    useBuffer { writeJSON(json, data) }