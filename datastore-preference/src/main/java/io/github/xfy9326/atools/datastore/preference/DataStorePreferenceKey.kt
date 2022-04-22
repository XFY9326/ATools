@file:Suppress("unused")

package io.github.xfy9326.atools.datastore.preference

import androidx.datastore.preferences.core.*
import kotlin.properties.ReadOnlyProperty

fun booleanPrefKey() =
    ReadOnlyProperty<Any, Preferences.Key<Boolean>> { _, property -> booleanPreferencesKey(property.name) }

fun stringPrefKey() =
    ReadOnlyProperty<Any, Preferences.Key<String>> { _, property -> stringPreferencesKey(property.name) }

fun intPrefKey() =
    ReadOnlyProperty<Any, Preferences.Key<Int>> { _, property -> intPreferencesKey(property.name) }

fun longPrefKey() =
    ReadOnlyProperty<Any, Preferences.Key<Long>> { _, property -> longPreferencesKey(property.name) }

fun floatPrefKey() =
    ReadOnlyProperty<Any, Preferences.Key<Float>> { _, property -> floatPreferencesKey(property.name) }

fun doublePrefKey() =
    ReadOnlyProperty<Any, Preferences.Key<Double>> { _, property -> doublePreferencesKey(property.name) }

fun stringSetPrefKey() =
    ReadOnlyProperty<Any, Preferences.Key<Set<String>>> { _, property -> stringSetPreferencesKey(property.name) }