@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri

fun ClipData.getItemUris(): List<Uri> {
    if (itemCount == 0) return emptyList()
    return buildList(itemCount) {
        for (i in 0 until itemCount) {
            getItemAt(i)?.uri?.let(::add)
        }
    }
}
