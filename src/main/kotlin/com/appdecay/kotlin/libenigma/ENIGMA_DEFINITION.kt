package com.appdecay.kotlin.libenigma


enum class ENIGMA_DEFINITION(val id: Int, val desc: String, val enigma: Enigma) {
    DEFAULT(0, "Default Machine", Enigma()),
    DOENITZ(1, "Doenitz Machine",
            Enigma.fromString('M', "125", 'B').apply {
                ringstellung("FVN")
                stecker("PO,ML,IU,KJ,NH,YT,GB,VF,RE,DC")
            }),
    BARBAROSSA(2, "1941 Barbarossa Machine",
            Enigma.fromString('M', "245", 'B').apply {
                ringstellung("BUL")
                stecker("AV,BS,CG,DL,FU,HZ,IN,KM,OW,RX")
            }),
    U264(3, "U-264 M4 Message Breaking Project",
            Enigma.fromString('M', "B241", 'b').apply {
                ringstellung("AAAV")
                stecker("AT,BL,GJ,DF,HM,NW,OP,QY,RZ,VX")
            }),
    SCHNAR(4, "Scharnhorst Machine",
            Enigma.fromString('M', "368", 'B').apply {
                ringstellung("AHM")
                stecker("AN,EZ,HK,IJ,LR,MQ,OT,PV,SW,UX")
            }),
    TURING(5, "Turing Treatise Machine",
            Enigma.fromString('R', "312", 'B').apply {
                ringstellung("ZQPM")
            }),
    ;

    companion object {
        fun machine(enigmaId: Int = 0): Enigma = ENIGMA_DEFINITION.values().first { it.id == enigmaId }.enigma
        fun list_machines() = ENIGMA_DEFINITION.values().forEach { println("${it.id}: ${it.desc}\n${it.enigma.toString()}") }
    }

}