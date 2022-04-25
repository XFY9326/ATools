@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import androidx.fragment.app.Fragment
import io.github.xfy9326.atools.core.tryCast

fun <T> Fragment.requireOwner() = (parentFragment ?: requireActivity()).tryCast<T>()