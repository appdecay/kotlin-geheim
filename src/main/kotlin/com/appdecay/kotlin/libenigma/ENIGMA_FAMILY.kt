package com.appdecay.kotlin.libenigma


import com.appdecay.kotlin.libenigma.utils.generateAlphaMap

enum class ENIGMA_FAMILY(val desc: String, val id: Char, val defaultRotors: String, val defaultReflector: Char) {
    MIL("Enigma M3/M4", 'M', "123", 'B'),
    D ("Comercial Enigma D", 'D', "123", 'D'),
    RAIL("Railway Enigma", 'R', "123", 'R'),
    SWISS("Swiss Air Force Enigma", 'S', "123", 'S')
    ;

    fun rotor(id: Char): Rotor = if (isRotorValid(id))
        ROTOR_DEFINITION.values().filter { equals(it.family) }.first { id.equals(it.id) }.rotor()
    else defaultRotors().first()
    //ROTOR_DEFINITION.values().filter { equals(it.family) }
    //.firstOrNull { id.equals(it.id) }?.rotor() ?: rotor('1')

    fun rotors(str: String) = with (str.filter { isRotorValid(it) }) {
        val col = plus(defaultRotors.reversed().drop(count()))
        mutableListOf<Rotor>().apply { col.forEach { this@apply.add(rotor(it)) } }.toList()
    }

    fun defaultRotors() = mutableListOf<Rotor>().apply {
        defaultRotors.reversed().forEach { this@apply.add(rotor(it)) }
    }.toList()

    fun reflector(id: Char): Reflector = REFLECTOR_DEFINITION
            .values().filter { equals(it.family) }
            .firstOrNull { id.equals(it.id) }?.reflector() ?: defaultReflector()

    fun defaultReflector(): Reflector = reflector(defaultReflector)

    fun inputOrder(): Map<Char, Char>? = when (this) {
        MIL -> null
        D -> DEFAULT_ETW.generateAlphaMap()
        RAIL -> DEFAULT_ETW.generateAlphaMap()
        SWISS -> DEFAULT_ETW.generateAlphaMap()
    }

    fun isRotorValid(char: Char)
            = ROTOR_DEFINITION.values().filter { equals(it.family) }.filter { char.equals(it.id) }.any()
    //= isDigit()

    companion object {
        val DEFAULT_ETW: String = "QWERTZUIOASDFGHJKPYXCVBNML"
        fun fromId(id: Char = 'M'): ENIGMA_FAMILY = values().firstOrNull() { id.equals(it.id) } ?: MIL
    }
}