package com.appdecay.kotlin.libenigma

import com.appdecay.kotlin.libenigma.utils.*

class Enigma(
        val family: ENIGMA_FAMILY = ENIGMA_FAMILY.MIL,
        val rotors: Collection<Rotor> = family.defaultRotors(),
        val reflector: Reflector = family.defaultReflector()) {

    var stecker: Map<Char, Char> = mapOf()

    companion object {
        fun fromString(familyId: Char = 'M', rotorsId: String = "123", reflectorId: Char = 'B'): Enigma =
                with(ENIGMA_FAMILY.fromId(familyId)) {
                    try {
                        Enigma (this, rotors(rotorsId.reversed()), reflector(reflectorId))
                    } catch(e: Exception) {
                        throw IllegalAccessException("Wrong custom configuration")
                    }
                }

        fun fromToken(token: String): Enigma = with(token.split(":")) {
            try {
                Enigma.fromString(get(0).first(), get(1), get(2).first())
                        .apply {
                            if (count() > 3) this@apply.ringstellung(get(3))
                            if (count() > 4) this@apply.stecker(get(4))
                            if (count() > 5) this@apply.key(get(5))
                        }
            } catch (e: Exception) {
                throw java.lang.IllegalArgumentException("Wrong Token Configuration")
            }
        }

    }

    /* Encode one character inside the machine through the rotors */
    fun encodeRotors(char: Char, debug: Boolean = false)
            = rotors.toMutableList()
            .apply { add(reflector) }
            /* This will pass the first character to the first rotor,
            the output to the next and so on. Then apply the reflector
            and transverse the rotors in the opposite direction. */
            .chainEncode(char, debug)

    /* Encode one character the user presses on the keyboard */
    fun encodeMachine(char: Char, debug: Boolean = false): Char {
        val input = char
                /* First we apply the etw. In military models will be the same character*/
                .etwEncode()
                /* If the character is in the stecker, we must flip it before passing it
                 * to the rotors */
                .steckerSwitch()
        var outputRotors = encodeRotors(input, debug)
        val output = outputRotors
                /* This is the output of the rotors
                *  Again if the character is part of the stecker we need to flip it
                */
                .steckerSwitch()
                /* Finally we apply the etw again */
                .etwDecode()
        return output
    }

    /* Encodes all the caracters in the string in sequence.
     * And returns a String
     */
    fun processString(str: String, debug: Boolean = false) = genString {
        str.filterUpper().forEach {
            append(encodeMachine(it, debug))
        }
    }

    /* Decoding and encoding will be the same */
    fun Char.steckerSwitch() = stecker.forward(this) ?: this

    fun Char.etwEncode(): Char = family.inputOrder()?.backward(this) ?: this
    fun Char.etwDecode(): Char = family.inputOrder()?.forward(this) ?: this

    /*** MACHINE SETTINGS ***/
    /* Sets the steckers from a string */
    fun stecker(steckerPairs: String) {
        stecker = steckerPairs.filterUpper().splitInPairs().toAlphaMap()
    }

    /* Sets the key (Rotors position) */
    fun key(str: String) = with (str.filterAndPad()) {
        rotors.forEachIndexed { i, r -> r.moveTo(get(i)) }
    }

    /* Sets the ringstellung (Initial configuration of rotors) */
    fun ringstellung(str: String) {
        if (family.equals(ENIGMA_FAMILY.MIL))
            rotors.ringstellung(str.filterAndPad())
        //str.filterApply() { ringstellung(it) }
        else with(str.filterAndPad(1)) {
            rotors.ringstellung(dropLast(1))
            reflector.ringstellung(takeLast(1).first())
        }
    }

    /******** Info and Debug ********/
    override fun toString(): String = genString {
        append("Family: ${family.desc}")
        append("\n Rotors: ${rotors.reversed().pretty()} Reflector: $reflector")
        if (stecker.isNotEmpty()) {
            append("\n Steckerbrett: ")
            stecker.simplify().forEach { append("${it.first},${it.second} ") }
        }
        append("\n")
    }

    fun rotorsWiring(): String = rotors.toMutableList().plus(reflector)
            .map { it.wiring.pretty() }.joinToString(separator = "\n")

    /********** UTILS ***********/
    inline fun String.applyIfRotorsLength(rs: Collection<Rotor> = rotors, f: Collection<Rotor>.(String) -> Unit)
            = with (filterUpper()) { if (length >= rs.count()) rs.f(take(rs.count())) }


    /* Will get only alphanumeric characters, convert them to uppercase
    and if necessary will add 'A' if the length is shorter than the number of
    rotors or strip the superfluous characters */
    fun String.filterAndPad(amount: Int = 0) = filterUpper().alphaPad(rotors.count() + amount).reversed()
}

