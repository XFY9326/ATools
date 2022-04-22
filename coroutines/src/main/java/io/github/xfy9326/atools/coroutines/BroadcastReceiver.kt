@file:Suppress("unused")

package io.github.xfy9326.atools.coroutines

import android.content.BroadcastReceiver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun BroadcastReceiver.goAsync(
    coroutineScope: CoroutineScope = AppScope,
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