@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.Px

@Px
fun Float.dpToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

@Px
fun Int.dpToPx() = dpToPxFloat().toInt()

@Px
fun Int.dpToPxFloat() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)

@Px
fun Float.spToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)

@Px
fun Int.spToPx() = spToPxFloat().toInt()

@Px
fun Int.spToPxFloat() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics)