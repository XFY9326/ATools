@file:Suppress("unused")

package io.github.xfy9326.atools.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import io.github.xfy9326.atools.base.castNullable
import java.io.Serializable

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

inline fun <reified T : Serializable> Intent.getSerializableExtraCompat(name: String?): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(name, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getSerializableExtra(name).castNullable<T>()
    }
}

inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(name: String?): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(name)
    }
}

inline fun <reified T : Parcelable> Intent.getParcelableArrayExtraCompat(name: String?): Array<out T>? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayExtra(name, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableArrayExtra(name).castNullable()
    }
}

inline fun <reified T : Parcelable> Intent.getParcelableArrayListExtraCompat(name: String?): ArrayList<T>? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayListExtra(name, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableArrayListExtra(name)
    }
}
