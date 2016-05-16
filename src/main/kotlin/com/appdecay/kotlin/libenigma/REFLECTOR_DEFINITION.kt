package com.appdecay.kotlin.libenigma


import com.appdecay.kotlin.libenigma.utils.generateAlphaMap

enum class REFLECTOR_DEFINITION(val wiring: String, val id: Char, val desc: String, val family: ENIGMA_FAMILY) {
    MIL_ALPHA ("EJMZALYXVBWFCRQUONTSPIKHGD", 'A', "Alpha", ENIGMA_FAMILY.MIL),
    MIL_BETA ("YRUHQSLDPXNGOKMIEBFZCWVJAT", 'B', "Beta", ENIGMA_FAMILY.MIL),
    MIL_GAMMA ("FVPJIAOYEDRZXWGCTKUQSBNMHL", 'C', "Gamma", ENIGMA_FAMILY.MIL),
    MIL_THIN_BETA ("ENKQAUYWJICOPBLMDXZVFTHRGS", 'b', "Thin Beta", ENIGMA_FAMILY.MIL),
    MIL_THIN_GAMMA ("RDOBJNTKVEHMLFCWZAXGYIPSUQ", 'c', "Thin Gamma", ENIGMA_FAMILY.MIL),

    D_REFLECTOR("IMETCGFRAYSQBZXWLHKDVUPOJN", 'D', "D", ENIGMA_FAMILY.D),
    RAIL_REFLECTOR("QYHOGNECVPUZTFDJAXWMKISRBL", 'R', "Rail", ENIGMA_FAMILY.RAIL),
    SWISS_REFLECTOR("IMETCGFRAYSQBZXWLHKDVUPOJN", 'S', "Swiss", ENIGMA_FAMILY.SWISS)
    ;

    fun reflector(): Reflector = Reflector(wiring.generateAlphaMap(), desc)

}
