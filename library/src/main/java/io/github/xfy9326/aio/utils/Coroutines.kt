@file:Suppress("unused")

package io.github.xfy9326.aio.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex

suspend inline fun <T> withIOContext(parentJob: Job? = null, noinline block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO + SupervisorJob(parentJob), block)

suspend inline fun <T> runIOJob(printStackTrace: Boolean = true, crossinline block: suspend CoroutineScope.() -> T): Result<T> =
    withIOContext {
        runCatching { block() }.also {
            if (printStackTrace) it.exceptionOrNull()?.printStackTrace()
        }
    }

@Throws(IllegalStateException::class)
inline fun Mutex.withTryLock(owner: Any? = null, action: () -> Unit): Boolean {
    return if (tryLock(owner)) {
        try {
            action()
        } finally {
            unlock(owner)
        }
        true
    } else {
        false
    }
}

@Throws(IllegalStateException::class)
inline fun <T> Mutex.withTryLock(owner: Any? = null, action: () -> T, onHasLocked: () -> T): T {
    return if (tryLock(owner)) {
        try {
            action()
        } finally {
            unlock(owner)
        }
    } else {
        onHasLocked()
    }
}