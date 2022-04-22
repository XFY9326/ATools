@file:Suppress("unused")

package io.github.xfy9326.aio.okio

import okio.*
import kotlin.io.use

inline fun <T> Source.useBuffer(crossinline block: BufferedSource.() -> T) = buffer().use(block)

inline fun <T> Sink.useBuffer(flush: Boolean = true, crossinline block: BufferedSink.() -> T) = buffer().use {
    try {
        it.block()
    } finally {
        if (flush && it.isOpen) it.flush()
    }
}