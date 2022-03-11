@file:Suppress("unused")

package io.github.xfy9326.aio.utils

import kotlinx.coroutines.*

suspend inline fun <T> withIOContext(parentJob: Job? = null, noinline block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO + SupervisorJob(parentJob), block)

suspend inline fun <T> runIOJob(printStackTrace: Boolean = true, crossinline block: suspend CoroutineScope.() -> T): Result<T> =
    withIOContext {
        runCatching { block() }.also {
            if (printStackTrace) it.exceptionOrNull()?.printStackTrace()
        }
    }
