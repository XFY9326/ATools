@file:Suppress("unused")

package io.github.xfy9326.aio.utils

import kotlinx.coroutines.*

suspend fun <T> withIOContext(parentJob: Job? = null, block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO + SupervisorJob(parentJob), block)

suspend fun <T> runIOJob(printStackTrace: Boolean = true, block: suspend CoroutineScope.() -> T): Result<T> =
    withIOContext {
        runCatching { block() }.also {
            if(printStackTrace) it.exceptionOrNull()?.printStackTrace()
        }
    }
