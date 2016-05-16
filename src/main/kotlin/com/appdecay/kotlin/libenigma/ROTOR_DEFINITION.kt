package com.appdecay.kotlin.libenigma

import com.appdecay.kotlin.libenigma.utils.generateAlphaMap

enum class ROTOR_DEFINITION(val wiring: String, val notch: CharArray, val id: Char, val desc: String, val family: ENIGMA_FAMILY) {
    // Military M3/M4
    MIL_1 ("EKMF3LGDQVZNTOWYHXUSPAIBRCJ", charArrayOf('Q'), '1', "I", ENIGMA_FAMILY.MIL),
    MIL_2 ("AJDKSIRUXBLHWTMCQGZNPYFVOE", charArrayOf('E'), '2', "II", ENIGMA_FAMILY.MIL),
    MIL_3 ("BDFHJLCPRTXVZNYEIWGAKMUSQO", charArrayOf('V'), '3', "III", ENIGMA_FAMILY.MIL),
    MIL_4("ESOVPZJAYQUIRHXLNFTGKDCMWB", charArrayOf('J'), '4', "IV", ENIGMA_FAMILY.MIL),
    MIL_5("VZBRGITYUPSDNHLXAWMJQOFECK", charArrayOf('Z'), '5', "V", ENIGMA_FAMILY.MIL),

    // Kriegsmarine
    MIL_6("JPGVOUMFYQBENHZRDKASXLICTW", charArrayOf('Z', 'M'), '6', "VI", ENIGMA_FAMILY.MIL),
    MIL_7("NZJHGRCXMYSWBOUFAIVLPEKQDT", charArrayOf('Z', 'M'), '7', "VII", ENIGMA_FAMILY.MIL),
    MIL_8("FKQHTLXOCBJSPDZRAMEWNIUYGV", charArrayOf('Z', 'M'), '8', "VIII", ENIGMA_FAMILY.MIL),

    // Kriegsmarine 4th rotors
    MIL_BETA("LEYJVCNIXWPBQMDRTAKZGFUHOS", charArrayOf(), 'B', "Beta", ENIGMA_FAMILY.MIL),
    MIL_GAMMA("FSOKANUERHMBTIYCWLQPZXVGJD", charArrayOf(), 'C', "Gamma", ENIGMA_FAMILY.MIL),

    // Enigma D
    D_1("LPGSZMHAEOQKVXRFYBUTNICJDW", charArrayOf('Y'), '1', "I", ENIGMA_FAMILY.D),
    D_2("SLVGBTFXJQOHEWIRZYAMKPCNDU", charArrayOf('E'), '2', "II", ENIGMA_FAMILY.D),
    D_3("CJGDPSHKTURAWZXFMYNQOBVLIE", charArrayOf('N'), '3', "III", ENIGMA_FAMILY.D),

    // Railway Enigma
    RAIL_1("JGDQOXUSCAMIFRVTPNEWKBLZYH", charArrayOf('N'), '1', "I", ENIGMA_FAMILY.RAIL),
    RAIL_2("NTZPSFBOKMWRCJDIVLAEYUXHGQ", charArrayOf('E'), '2', "II", ENIGMA_FAMILY.RAIL),
    RAIL_3("JVIUBHTCDYAKEQZPOSGXNRMWFL", charArrayOf('Y'), '3', "III", ENIGMA_FAMILY.RAIL),

    // Swiss Air Force Enigma
    SWISS_1("PEZUOHXSCVFMTBGLRINQJWAYDK", charArrayOf('Y'), '1', "I", ENIGMA_FAMILY.SWISS),
    SWISS_2("ZOUESYDKFWPCIQXHMVBLGNJRAT", charArrayOf('E'), '2', "II", ENIGMA_FAMILY.SWISS),
    SWISS_3("EHRVXGAOBQUSIMZFLYNWKTPDJC", charArrayOf('N'), '3', "III", ENIGMA_FAMILY.SWISS)
    ;


    fun rotor() = Rotor(wiring.generateAlphaMap(), notch, desc)
}