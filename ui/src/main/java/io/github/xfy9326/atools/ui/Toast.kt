@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import io.github.xfy9326.atools.core.AppContext

@Suppress("NOTHING_TO_INLINE")
inline fun Context.showToast(@StringRes resId: Int, vararg params: Any, showLong: Boolean = false): Unit =
    showToast(getString(resId, *params), showLong)

@Suppress("NOTHING_TO_INLINE")
inline fun Context.showToast(text: String, showLong: Boolean = false): Unit =
    Toast.makeText(this, text, if (showLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()

@Suppress("NOTHING_TO_INLINE")
inline fun showGlobalToast(@StringRes resId: Int, vararg params: Any, showLong: Boolean = false): Unit =
    AppContext.showToast(resId, params, showLong)

@Suppress("NOTHING_TO_INLINE")
inline fun showGlobalToast(text: String, showLong: Boolean = false): Unit =
    AppContext.showToast(text, showLong)