package com.appdecay.kotlin.libenigma

import com.appdecay.kotlin.libenigma.utils.mergeString

fun Collection<Rotor>.transverse(char: Char): Char =
        fold(char) { acc, map -> map.transverse(acc) }

fun Collection<Rotor>.reverse(char: Char) =
        reversed().fold(char) { acc, map -> map.reverse(acc) }

fun Collection<Rotor>.shiftRotor(which: Int) = forEachIndexed { i, r -> if (i.equals(which)) r.shiftRotor() }

fun Collection<Rotor>.shiftAll() {
    val firstAtNotch = first().isAtNotch()
    mapIndexed { i, r -> i to r }.dropLast(1).reversed().forEach {
        with(it.second) {
            if (isAtNotch()) {
                /* if the rotor is at notch, we shift
                both this rotor and the next one */
                this@shiftAll.shiftRotor(it.first + 1);
                shiftRotor()
            }
        }
    }
    /* if is at notch we already shifted it */
    if (!firstAtNotch) first().shiftRotor()
}


fun Collection<Rotor>.chainEncode(char: Char, debug: Boolean = false): Char {
    /* Last is the reflector */
    with (toList().dropLast(1)) {
        /* Before the encoding, we sshift the rotors which are notched */
        shiftAll()
        /* First pass */
        val input = transverse(char)
        val inputLog = log()
        /*  Reflector */
        val med = this@chainEncode.last().transverse(input)
        /* Second pass */
        val output = reverse(med)
        val outputLog = reversed().log()
        /* Debug */
        if (debug) {
            print ("$char -> $inputLog")
            print(" [ ${this@chainEncode.last().log} ] ")
            print (" $outputLog -> $output")
            println(" [${status()}]")
        }
        return output
    }
}

/*********** Configuration *********/

/*
fun Collection<Rotor>.move(str: String) {
    if (str.length.equals(size))
        forEachIndexed { i, r -> r.moveTo(str[i]) }
}
*/

fun Collection<Rotor>.ringstellung(str: String) {
    if (str.length.equals(size))
        forEachIndexed { i, r -> r.ringstellung(str[i]) }
}

/***** Info and Debug *********/
fun Collection<Rotor>.pretty() = this.joinToString(separator = " | ", postfix = " ]", prefix = "[ ")

fun Collection<Rotor>.status(): String = map { it.status }.mergeString()

fun Collection<Rotor>.log(): String = map { it.log }.joinToString(separator = " | ")

