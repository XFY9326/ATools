@file:Suppress("unused")

package io.github.xfy9326.atools.io.okio

import okio.*
import kotlin.io.use

inline fun <T> Source.useBuffer(block: BufferedSource.() -> T) = buffer().use(block)

inline fun <T> Sink.useBuffer(flush: Boolean = true, block: BufferedSink.() -> T) = buffer().use {
    try {
        it.block()
    } finally {
        if (flush && it.isOpen) it.flush()
    }
}