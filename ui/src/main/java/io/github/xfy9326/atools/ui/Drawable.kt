@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import android.graphics.drawable.*
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

fun Drawable.tryStartAnimation() {
    when (this) {
        is AnimatedVectorDrawable -> start()
        is AnimatedVectorDrawableCompat -> start()
        is Animatable -> start()
    }
}

fun Drawable.tryStopAnimation() {
    when (this) {
        is AnimatedVectorDrawable -> stop()
        is AnimatedVectorDrawableCompat -> stop()
        is Animatable -> stop()
    }
}