@file:Suppress("unused")

package io.github.xfy9326.atools.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.EmptyCoroutineContext

val AppScope: CoroutineScope by lazy {
    CoroutineScope(EmptyCoroutineContext + SupervisorJob())
}