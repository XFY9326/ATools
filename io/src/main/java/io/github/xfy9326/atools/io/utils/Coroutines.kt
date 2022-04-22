@file:Suppress("unused")

package io.github.xfy9326.atools.io.utils

import kotlinx.coroutines.*

suspend inline fun <T> withIOContext(parentJob: Job? = null, noinline block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO + SupervisorJob(parentJob), block)

suspend fun <T> runIOJob(parentJob: Job? = null, printStackTrace: Boolean = true, block: suspend CoroutineScope.() -> T): Result<T> =
    withIOContext(parentJob) {
        runCatching { block() }.also {
            if (printStackTrace) it.exceptionOrNull()?.printStackTrace()
        }
    }