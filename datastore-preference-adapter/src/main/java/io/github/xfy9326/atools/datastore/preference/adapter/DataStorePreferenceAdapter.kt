@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.xfy9326.atools.datastore.preference.adapter

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

open class DataStorePreferenceAdapter(private val dataStore: DataStore<Preferences>, scope: CoroutineScope) : PreferenceDataStore(),
    CoroutineScope by CoroutineScope(scope.coroutineContext + SupervisorJob() + Dispatchers.IO) {
    protected val dsData = dataStore.data.shareIn(scope, SharingStarted.Eagerly, 1)

    protected open fun <T> putData(key: Preferences.Key<T>, value: T?) {
        launch {
            dataStore.edit {
                if (value != null) it[key] = value else it.remove(key)
            }
        }
    }

    protected open fun <T> readNullableData(key: Preferences.Key<T>, defValue: T?): T? {
        return runBlocking(coroutineContext) {
            dsData.map { it[key] ?: defValue }.firstOrNull()
        }
    }

    protected open fun <T> readNonNullData(key: Preferences.Key<T>, defValue: T): T {
        return runBlocking(coroutineContext) {
            dsData.map { it[key] ?: defValue }.first()
        }
    }

    override fun putString(key: String, value: String?) = putData(stringPreferencesKey(key), value)

    override fun putStringSet(key: String, values: Set<String>?) = putData(stringSetPreferencesKey(key), values)

    override fun putInt(key: String, value: Int) = putData(intPreferencesKey(key), value)

    override fun putLong(key: String, value: Long) = putData(longPreferencesKey(key), value)

    override fun putFloat(key: String, value: Float) = putData(floatPreferencesKey(key), value)

    override fun putBoolean(key: String, value: Boolean) = putData(booleanPreferencesKey(key), value)


    override fun getString(key: String, defValue: String?): String? = readNullableData(stringPreferencesKey(key), defValue)

    override fun getStringSet(key: String, defValues: Set<String>?): Set<String>? = readNullableData(stringSetPreferencesKey(key), defValues)

    override fun getInt(key: String, defValue: Int): Int = readNonNullData(intPreferencesKey(key), defValue)

    override fun getLong(key: String, defValue: Long): Long = readNonNullData(longPreferencesKey(key), defValue)

    override fun getFloat(key: String, defValue: Float): Float = readNonNullData(floatPreferencesKey(key), defValue)

    override fun getBoolean(key: String, defValue: Boolean): Boolean = readNonNullData(booleanPreferencesKey(key), defValue)
}