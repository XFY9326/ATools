@file:Suppress("unused")

package io.github.xfy9326.atools.core

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import androidx.core.os.BundleCompat
import io.github.xfy9326.atools.base.castNullable
import java.io.Serializable

inline fun <reified T : Serializable> Bundle.getSerializableCompat(name: String?): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(name, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getSerializable(name).castNullable<T>()
    }
}

inline fun <reified T : Parcelable> Bundle.getParcelableCompat(name: String?): T? {
    return BundleCompat.getParcelable(this, name, T::class.java)
}

inline fun <reified T : Parcelable> Bundle.getParcelableArrayCompat(name: String?): Array<out T>? {
    return BundleCompat.getParcelableArray(this, name, T::class.java).castNullable()
}

inline fun <reified T : Parcelable> Bundle.getSparseParcelableArrayCompat(name: String?): SparseArray<T>? {
    return BundleCompat.getSparseParcelableArray(this, name, T::class.java)
}

inline fun <reified T : Parcelable> Bundle.getParcelableArrayListCompat(name: String?): ArrayList<T>? {
    return BundleCompat.getParcelableArrayList(this, name, T::class.java)
}
