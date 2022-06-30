@file:Suppress("unused")

package io.github.xfy9326.atools.base

fun <K, V> Collection<V>.classification(keyFactory: (V) -> K): Map<K, List<V>> {
    if (size == 0) return emptyMap()
    val map = HashMap<K, ArrayList<V>>()
    forEach {
        val key = keyFactory(it)
        map.getOrPut(key) {
            ArrayList()
        }.add(it)
    }
    return map
}

fun <K, V> Collection<V>.countByKey(keyFactory: (V) -> K): Map<K, Int> {
    if (size == 0) return emptyMap()
    val map = HashMap<K, Int>()
    forEach {
        val key = keyFactory(it)
        map[key] = (map[key] ?: 0) + 1
    }
    return map
}
