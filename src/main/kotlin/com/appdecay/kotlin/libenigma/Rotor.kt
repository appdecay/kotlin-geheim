package com.appdecay.kotlin.libenigma


import com.appdecay.kotlin.libenigma.utils.*

open class Rotor(var wiring: Map<Char, Char>, val notch: CharArray = charArrayOf(), val desc: String = "") {

    var status: Char = 'A'

    val position: Int
        get() = ((status.toInt() - 65) + ringstellunfShift) % 26

    var log: String = ""

    var ringstellunfShift = 0

    var ringstellungChar: Char = 'A'

    companion object {
        fun fromString(init: String, notch: String = "", desc: String = "")
                = Rotor(init.generateAlphaMap(), notch.alphaArray(), desc)
    }

    fun transverse(char: Char): Char
            = wiring.forward(char.shifted())?.unshifted() ?: char

    fun reverse(char: Char): Char
            = wiring.backward(char.shifted())?.unshifted() ?: char.shifted()

    fun shiftRotor(amount: Int = 1) {
        status = status.shiftAlpha(amount)
    }

    fun isAtNotch() = if (notch.isNotEmpty()) notch.contains(status) else false

    fun Char.shifted(): Char = shiftAlpha(position).apply { log = "${this@shifted}$this" }
    fun Char.unshifted(): Char = unshiftAlpha(position).apply { log += "${this@unshifted}$this" }

    fun moveTo(char: Char) {
        if (char.isAlpha()) status = char.toUpperCase()
    }

    fun ringstellung(char: Char) {
        val amount = (char.toInt() - 65)
        ringstellungChar = amount.toAlpha()
        ringstellunfShift = 26 - amount
    }

    fun pretty(): String = wiring.pretty()

    override fun toString(): String = genString {
        append("$desc ($ringstellungChar)")
        if (notch.isNotEmpty())
            append(notch.joinToString(separator = ",", prefix = " <", postfix = ">"))
    }
}