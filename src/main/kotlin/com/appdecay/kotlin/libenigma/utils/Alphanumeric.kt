package com.appdecay.kotlin.libenigma.utils

import java.util.*

fun Collection<Pair<Char, Char>>.clean(): Collection<Pair<Char, Char>> =
        this.map { p -> if ((p.second).toInt() < (p.first).toInt()) Pair(p.second, p.first) else p }.sortedBy { it.first }.groupBy { it.first }.map { it.value.last() }

fun Collection<Pair<Char, Char>>.toAlphaMap() = generateAlphaMap(this)

fun generateAlphaMap(col: Collection<Pair<Char, Char>>): Map<Char, Char>
        = mutableMapOf<Char, Char>()
        .apply {
            col.cleanAlpha().forEach {
                it.toList().forEach { deleteBoth(it) }
                //putAll(arrayOf(it, it.reverse()))
                put(it.first, it.second)
                put(it.second, it.first)
            }
        }

fun String.alphaArray(): CharArray =
        mutableSetOf<Char>().apply {
            filterUpper().forEach { add(it) }
        }.toCharArray()

fun <K> MutableMap<K, K>.deleteBoth(element: K) {
    remove(element)
    filter { it.value == element }.keys.forEach { remove(it) }
}

fun <K : Comparable<K>> Map<K, K>.simplify(): List<Pair<K, K>> = mutableListOf<Pair<K, K>>().apply {
    for ((k, v) in this@simplify.toSortedMap()) {
        add(k to v)
    }
}.filter { it.first < it.second }

fun <K, V> Map<K, V>.pretty(): String = StringBuilder().apply {
    for ((k, v) in this@pretty) {
        append("$k->$v ")
    }
}.toString()

fun <T> Pair<T, T>.reverse() = second to first
fun <T> Map<T, T>.valueOrSame(key: T) = get(key) ?: key

fun Collection<Pair<Char, Char>>.cleanAlpha() = filter { it.toList().all { it.isAlpha() } }.map { it.first.toUpperCase() to it.second.toUpperCase() }
fun String.cleanAlpha() = filter { it.isAlpha() }.toUpperCase()

fun scrambled(): String = CharRange('A', 'Z').toMutableList().apply { Collections.shuffle(this) }.mergeString()
fun String.generateAlphaMap(): Map<Char, Char>
        = upperAlphaRange.zip(cleanAlpha().toSet().plus(upperAlphaRange)).toMap()

fun <K, V> Map<K, V>.forward(input: K): V? = get(input)
fun <K, V> Map<K, V>.backward(output: V): K? = filter { it.value?.equals(output) ?: false }.map { it.key }.firstOrNull()

fun <T> Collection<Map<T, T>>.forwardChain(input: T): T = fold(input) { acc, map -> map.forward(acc) ?: acc }
fun <T> Collection<Map<T, T>>.backwardChain(input: T): T = reversed().fold(input) { acc, map -> map.backward(acc) ?: acc }

fun Char.shiftAlpha(amount: Int = 1): Char = ((fromAlpha() + amount) % 26).toAlpha()
fun Char.unshiftAlpha(amount: Int = 1): Char = ((fromAlpha() + 26 - amount) % 26).toAlpha()

fun Char.fromAlpha(): Int = (this - 65).toInt()
fun Int.toAlpha(): Char = (this + 65).toChar()

inline fun <reified T> T.toLog(tag: String = "") = apply { println("$tag [${T::class}]: $this") }

val upperAlphaRange = CharRange('A', 'Z')
val lowerAlphaRange = CharRange('a', 'z')

fun <T> Iterable<T>.mergeString(): String = joinToString(separator = "")

fun Char.deviation(): Int? = if (toUpperCase() in upperAlphaRange) this - 'A' else null

fun Char.isAlpha() = this in upperAlphaRange || this in lowerAlphaRange

fun genString(block: StringBuilder.() -> Unit) = StringBuilder().apply(block).toString()
fun String.filterUpper() = filter { it.isAlpha() }.map { it.toUpperCase() }.mergeString()


fun String.alphaPad(count: Int = 3) =
        if (length < count) plus((1..(count - length)).map { 'A' }.joinToString(separator = ""))
        else take(count)