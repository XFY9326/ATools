package io.github.xfy9326.aio.startup

import android.content.Context
import androidx.startup.Initializer

@Suppress("unused")
internal class AIOInitializer : Initializer<Unit> {
    companion object {
        private var startUpApplicationContext: Context? = null
        val applicationContext: Context
            get() = startUpApplicationContext ?: error("Application context doesn't initialize yet!")
    }

    override fun create(context: Context) {
        startUpApplicationContext = context.applicationContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}