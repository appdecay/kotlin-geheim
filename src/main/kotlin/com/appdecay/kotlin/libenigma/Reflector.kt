package com.appdecay.kotlin.libenigma


import com.appdecay.kotlin.libenigma.utils.genString
import com.appdecay.kotlin.libenigma.utils.generateAlphaMap

class Reflector(wiring: Map<Char, Char>, desc: String) : Rotor(wiring, charArrayOf(), desc) {

    companion object {
        fun fromPredefined(definition: REFLECTOR_DEFINITION): Reflector = with (definition) { Reflector(wiring.generateAlphaMap(), desc) }

    }

    override fun toString(): String = genString {
        append("$desc")
        if (!ringstellungChar.equals('A')) append(" ($ringstellungChar)")
    }
}