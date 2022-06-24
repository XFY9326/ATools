@file:Suppress("unused")

package io.github.xfy9326.atools.core

import android.content.Context
import android.content.Intent
import android.net.Uri

private const val MIME_APK = "application/vnd.android.package-archive"

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