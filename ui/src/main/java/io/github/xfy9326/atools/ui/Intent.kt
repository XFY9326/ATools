@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri

private const val MIME_APK = "application/vnd.android.package-archive"

fun ClipData.getItemUris(): List<Uri> {
    if (itemCount == 0) return emptyList()
    return buildList(itemCount) {
        for (i in 0 until itemCount) {
            getItemAt(i)?.uri?.let(::add)
        }
    }
}

fun Context.getLaunchAppIntent(): Intent? =
    packageManager.getLaunchIntentForPackage(packageName)?.apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

fun Context.installPackage(uri: Uri): Boolean =
    tryStartActivity(Intent(Intent.ACTION_VIEW).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
        setDataAndType(uri, MIME_APK)
    })

fun Context.openUrlInBrowser(uri: Uri): Boolean =
    tryStartActivity(Intent(Intent.ACTION_VIEW).apply {
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    })