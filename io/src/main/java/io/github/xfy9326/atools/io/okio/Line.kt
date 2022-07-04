@file:Suppress("unused")

package io.github.xfy9326.atools.io.okio

import okio.BufferedSource
import java.io.IOException

private class LineIterator(private val source: BufferedSource) : Iterator<String> {
    private var nextValue: String? = null
    private var done = false

    override fun hasNext(): Boolean {
        if (nextValue == null && !done) {
            nextValue = source.readUtf8Line()
            if (nextValue == null) done = true
        }
        return nextValue != null
    }

    override fun next(): String {
        if (!hasNext()) throw NoSuchElementException()
        val answer = nextValue
        nextValue = null
        return answer!!
    }
}

@Throws(IOException::class)
fun BufferedSource.lineIterator(): Iterator<String> = LineIterator(this)

@Throws(IOException::class)
fun BufferedSource.forEachLine(block: (String) -> Unit): Unit =
    lineIterator().forEach(block)


