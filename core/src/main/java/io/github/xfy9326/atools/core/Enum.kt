@file:Suppress("unused")

package io.github.xfy9326.atools.core

inline fun <reified E : Enum<E>> tryEnumValueOf(name: String): E? {
    return try {
        enumValueOf<E>(name)
    } catch (e: Exception) {
        null
    }
}