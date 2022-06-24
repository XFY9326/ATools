@file:Suppress("unused")

package io.github.xfy9326.atools.core

import android.content.Context
import android.widget.Toast

fun Context.showToast(text: String, lengthLong: Boolean = false): Unit =
    Toast.makeText(this, text, if (lengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
