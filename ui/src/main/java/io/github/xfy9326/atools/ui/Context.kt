@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import kotlin.system.exitProcess

val Context.packageUri: Uri
    get() = "package:${packageName}".toUri()

inline fun <reified A : Activity> Context.startActivity(intentBlock: Intent.() -> Unit = {}) {
    startActivity(Intent(this, A::class.java).apply(intentBlock))
}

fun Context.relaunchApp(statusCode: Int = 0, intentBlock: Intent.() -> Unit = {}) {
    startActivity(packageManager.getLaunchIntentForPackage(packageName)!!.apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intentBlock.invoke(this)
    })
    exitProcess(statusCode)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Context.getColorCompat(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)

@Suppress("NOTHING_TO_INLINE")
inline fun Context.getDrawableCompat(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(this, id)

@Suppress("NOTHING_TO_INLINE")
inline fun Context.getStringArray(@ArrayRes id: Int): Array<String> = resources.getStringArray(id)

@Suppress("NOTHING_TO_INLINE")
inline fun Context.getIntArray(@ArrayRes id: Int): IntArray = resources.getIntArray(id)

@SuppressLint("QueryPermissionsNeeded")
fun Context.tryStartActivity(intent: Intent, options: Bundle? = null): Boolean {
    // 由于兼容性问题，部分系统无法查询到Intent是否可以被处理
    val queryActivity = intent.resolveActivity(packageManager) != null
    return if (queryActivity) {
        ContextCompat.startActivity(this, intent, options)
        true
    } else {
        runCatching {
            ContextCompat.startActivity(this, intent, options)
        }.isSuccess
    }
}

fun Context.hideKeyboard(windowToken: IBinder) {
    getSystemService<InputMethodManager>()?.apply {
        if (isActive) hideSoftInputFromWindow(windowToken, 0)
    }
}
