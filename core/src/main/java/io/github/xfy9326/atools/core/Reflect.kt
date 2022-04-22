@file:Suppress("unused", "UNCHECKED_CAST", "NOTHING_TO_INLINE")

package io.github.xfy9326.atools.core

inline fun <T> Any.cast() = this as T

inline fun <T> Any.tryCast() = this as? T

inline fun <T> Any?.castNullable() = this as T?

inline fun <T> Any?.castNonNull() = this as T