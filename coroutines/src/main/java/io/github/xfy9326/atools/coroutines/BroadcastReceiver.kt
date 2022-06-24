@file:Suppress("unused")

package io.github.xfy9326.atools.coroutines

import android.content.BroadcastReceiver
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun BroadcastReceiver.goAsync(
    coroutineScope: CoroutineScope = GlobalScope,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    block: suspend () -> Unit,
) {
    val result = goAsync()
    coroutineScope.launch(dispatcher) {
        try {
            block()
        } finally {
            result.finish()
        }
    }
}