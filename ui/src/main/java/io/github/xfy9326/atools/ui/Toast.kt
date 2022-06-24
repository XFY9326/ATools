@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import android.content.Context
import androidx.annotation.StringRes
import io.github.xfy9326.atools.core.AppContext
import io.github.xfy9326.atools.core.showToast

fun Context.showToast(@StringRes resId: Int, vararg params: Any, lengthLong: Boolean = false): Unit =
    showToast(getString(resId, *params), lengthLong)

fun showGlobalToast(text: String, lengthLong: Boolean = false): Unit =
    AppContext.showToast(text, lengthLong)

fun showGlobalToast(@StringRes resId: Int, vararg params: Any, lengthLong: Boolean = false): Unit =
    AppContext.showToast(resId, params, lengthLong)
