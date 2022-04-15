@file:Suppress("unused")

package io.github.xfy9326.aio.utils

import java.io.*

fun <T : Serializable> T.deepClone(): Result<T> =
    runCatching {
        PipedOutputStream().use { output ->
            PipedInputStream(output).use { input ->
                ObjectOutputStream(output).use { objectOutput ->
                    ObjectInputStream(input).use { objectInput ->
                        objectOutput.writeObject(this@deepClone)
                        objectOutput.flush()
                        objectInput.readObject().cast()
                    }
                }
            }
        }
    }
