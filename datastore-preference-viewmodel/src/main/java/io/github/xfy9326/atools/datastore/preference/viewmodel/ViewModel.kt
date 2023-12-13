@file:Suppress("unused")

package io.github.xfy9326.atools.datastore.preference.viewmodel

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.xfy9326.atools.datastore.preference.ExtendedDataStore
import io.github.xfy9326.atools.datastore.preference.key.ReadWriteKey
import io.github.xfy9326.atools.datastore.preference.key.stateHasKey
import io.github.xfy9326.atools.datastore.preference.key.stateKey
import kotlinx.coroutines.flow.StateFlow

fun <D : ExtendedDataStore, T : Any> ViewModel.stateDataStoreKey(dataStore: D, key: Preferences.Key<T>): StateFlow<T?> =
    dataStore.stateKey(key, viewModelScope)

fun <D : ExtendedDataStore, T : Any> ViewModel.stateDataStoreKey(dataStore: D, key: Preferences.Key<T>, defaultValue: T): StateFlow<T> =
    dataStore.stateKey(key, defaultValue, viewModelScope)

fun <D : ExtendedDataStore, T> ViewModel.stateDataStore(dataStore: D, block: suspend (Preferences) -> T, initialValue: T): StateFlow<T> =
    dataStore.statePreferences(block, initialValue, viewModelScope)

fun <D : ExtendedDataStore> ViewModel.stateHasKey(dataStore: D, key: Preferences.Key<*>): StateFlow<Boolean> =
    dataStore.stateHasKey(key, viewModelScope)

fun <D : ExtendedDataStore, T : Any> ViewModel.stateDataStoreKey(dataStore: D, key: ReadWriteKey<T>): StateFlow<T?> =
    dataStore.stateKey(key, viewModelScope)

fun <D : ExtendedDataStore> ViewModel.stateHasKey(dataStore: D, key: ReadWriteKey<*>): StateFlow<Boolean> =
    dataStore.stateHasKey(key, viewModelScope)
