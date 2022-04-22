@file:Suppress("unused")

package io.github.xfy9326.atools.ui

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

fun AlertDialog.Builder.show(lifecycleOwner: LifecycleOwner, onDismiss: (DialogInterface) -> Unit = {}) {
    val dialog = create()
    val observer = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            if (dialog.isShowing) dialog.dismiss()
            owner.lifecycle.removeObserver(this)
        }
    }
    setOnDismissListener {
        onDismiss(it)
        lifecycleOwner.lifecycle.removeObserver(observer)
    }
    lifecycleOwner.lifecycle.addObserver(observer)
    dialog.show()
}