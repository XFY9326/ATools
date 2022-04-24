@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import android.graphics.drawable.*
import android.os.Build
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

fun Drawable.tryStartAnimation() {
    when {
        this is AnimatedVectorDrawable -> start()
        this is AnimatedVectorDrawableCompat -> start()
        this is Animatable -> start()
        this is Animatable2 -> start()
        this is Animatable2Compat -> start()
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && this is AnimatedImageDrawable -> start()
    }
}

fun Drawable.tryStopAnimation() {
    when {
        this is AnimatedVectorDrawable -> stop()
        this is AnimatedVectorDrawableCompat -> stop()
        this is Animatable -> stop()
        this is Animatable2 -> stop()
        this is Animatable2Compat -> stop()
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && this is AnimatedImageDrawable -> stop()
    }
}