@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package io.github.xfy9326.atools.datastore.preference

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import io.github.xfy9326.atools.core.AppContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

abstract class AbstractDataStore(name: String) {
    private val Context.dataStore by preferencesDataStore(name)
    val dataStore by lazy { AppContext.dataStore }

    fun <T> readDistinctValue(enabled: Boolean = true, block: suspend (Preferences) -> T): Flow<T> =
        dataStore.data.map(block).let { if (enabled) it.distinctUntilChanged() else it }

    fun <T> readNullableValue(key: Preferences.Key<T>, distinct: Boolean = true): Flow<T?> =
        readDistinctValue(distinct) { it[key] }

    fun <T> readNullableValues(vararg keys: Preferences.Key<T>, distinct: Boolean = true): Flow<Map<Preferences.Key<T>, T?>> =
        readDistinctValue(distinct) { keys.associateWith { k -> it[k] } }

    fun <T : Any> readValue(key: Preferences.Key<T>, defaultValue: T, distinct: Boolean = true): Flow<T> =
        readDistinctValue(distinct) { it[key] ?: defaultValue }

    fun <T : Any> readValues(vararg pairs: Pair<Preferences.Key<T>, T>, distinct: Boolean = true): Flow<Map<Preferences.Key<T>, T>> =
        readDistinctValue(distinct) { pairs.associate { (k, v) -> Pair(k, (it[k] ?: v)) } }

    fun <T : Any> readValueLazy(key: Preferences.Key<T>, defaultValue: suspend () -> T, distinct: Boolean = true): Flow<T> =
        readDistinctValue(distinct) { it[key] ?: defaultValue() }

    fun <T : Any> readValuesLazy(vararg pairs: Pair<Preferences.Key<T>, suspend () -> T>, distinct: Boolean = true): Flow<Map<Preferences.Key<T>, T>> =
        readDistinctValue(distinct) { pairs.associate { (k, v) -> Pair(k, (it[k] ?: v())) } }

    fun <T : Any> hasValue(key: Preferences.Key<T>, distinct: Boolean = true): Flow<Boolean> =
        readDistinctValue(distinct) { it.contains(key) }

    suspend fun <T : Any> writeValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { it[key] = value }
    }

    suspend fun <T : Any> writeValues(vararg pairs: Pair<Preferences.Key<T>, T>) {
        dataStore.edit { pairs.forEach { (k, v) -> it[k] = v } }
    }

    suspend fun <T : Any> removeValue(vararg key: Preferences.Key<T>) {
        dataStore.edit { key.forEach { k -> it.remove(k) } }
    }

    suspend fun <T : Enum<T>> writeEnumValue(key: Preferences.Key<String>, value: T) {
        writeValue(key, value.name)
    }

    suspend fun <T : Enum<T>> writeEnumSetValue(key: Preferences.Key<Set<String>>, values: Set<T>) {
        writeValue(key, values.map { v -> v.name }.toSet())
    }

    inline fun <reified T : Enum<T>> readEnumValue(key: Preferences.Key<String>, defaultValue: T, distinct: Boolean = true): Flow<T> =
        readDistinctValue(distinct) {
            it[key]?.let { str -> enumValueOf<T>(str) } ?: defaultValue
        }

    inline fun <reified T : Enum<T>> readEnumSetValue(key: Preferences.Key<Set<String>>, defaultValues: Set<T>, distinct: Boolean = true): Flow<Set<T>> =
        readDistinctValue(distinct) {
            it[key]?.map { str -> enumValueOf<T>(str) }?.toSet() ?: defaultValues
        }
}