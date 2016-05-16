package com.appdecay.kotlin.libenigma.utils

fun <T> Collection<T>.splitInPairs(): List<Pair<T, T>> {
    val list = mapIndexed { i, t -> i to t }.partition { it.first % 2 == 0 }
    return list.first.zip(list.second) { a, b -> a.second to b.second }
}

fun String.splitInPairs(): List<Pair<Char, Char>> = this.toList().splitInPairs<Char>()