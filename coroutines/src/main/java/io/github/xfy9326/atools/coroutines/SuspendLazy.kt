package io.github.xfy9326.atools.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.properties.ReadOnlyProperty

fun <T> suspendLazy(coroutineContext: CoroutineContext = Dispatchers.Default, initializer: suspend () -> T) =
    ReadOnlyProperty<Any, SuspendLazy<T>> { _, _ ->
        SuspendLazyImpl(coroutineContext, initializer)
    }

interface SuspendLazy<T> {
    suspend fun value(): T

    suspend fun refresh(): T
}

private class ValueWrapper<T>(val value: T)

private class SuspendLazyImpl<T>(
    private val coroutineContext: CoroutineContext,
    private val initializer: suspend () -> T
) : SuspendLazy<T> {
    private val mutex = Mutex()
    private var cached: ValueWrapper<T>? = null

    override suspend fun value(): T =
        cached?.value ?: mutex.withLock {
            cached?.value ?: withContext(coroutineContext) {
                initializer()
            }.also {
                cached = ValueWrapper(it)
            }
        }

    override suspend fun refresh(): T =
        mutex.withLock {
            withContext(coroutineContext) {
                initializer()
            }.also {
                cached = ValueWrapper(it)
            }
        }
}