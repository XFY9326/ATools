package io.github.xfy9326.atools.core

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import io.github.xfy9326.atools.core.startup.AToolsInitializer

val AppContext: Context
    get() = AToolsInitializer.applicationContext

fun Context.isPermissionGranted(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
