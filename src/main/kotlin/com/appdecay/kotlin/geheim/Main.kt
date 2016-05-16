package com.appdecay.kotlin.geheim

import com.appdecay.kotlin.libenigma.ENIGMA_DEFINITION
import com.appdecay.kotlin.libenigma.Enigma
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import kotlin.system.exitProcess

val formatter: HelpFormatter = HelpFormatter()
val options = generateGeheimOptions()
val parser = DefaultParser()

fun main(args: Array<String>) {
    try {
        with (parser.parse(options, args)) {
            if (hasOption("h")) {
                printGeheimHelp()
                exitProcess(0)
            }
            if (hasOption("l")) {
                ENIGMA_DEFINITION.list_machines()
                exitProcess(0)
            }
            val enigma =
                    if (hasOption("m")) ENIGMA_DEFINITION.machine(getOptionValue("m").toInt())
                    else if (hasOption("t")) Enigma.fromToken(getOptionValue("t"))
                    else Enigma.fromString(
                            getOptionValue("f")?.first() ?: 'M',
                            getOptionValue("r") ?: "123",
                            getOptionValue("R")?.first() ?: 'B')

            enigma.apply {
                if (hasOption("g")) ringstellung(getOptionValue("g"))
                if (hasOption("k")) key(getOptionValue("k"))
                if (hasOption("s")) stecker(getOptionValue("s"))
            }
            if (hasOption("i") || hasOption("t") || hasOption("v")) println(enigma)
            println(enigma.processString(getOptionValue("e") ?: "ABCDE", debug = hasOption("v")))
        }
    } catch (e: Exception) {
        System.err.println("Parsing failed.  Reason: ${e.message}")
        printGeheimHelp()
    }
}

fun printGeheimHelp() = formatter.printHelp("geheim", options)

fun generateGeheimOptions(): Options = Options().apply {
    addOption(Option.builder("r").longOpt("rotors").hasArg().argName("ROTORS")
            .desc("Specify which Rotors to use and their order (Walzenlage).Rotors IDs available: 1-9.")
            .build())
    addOption(Option.builder("R").longOpt("reflector").hasArg().argName("REFLECTOR")
            .desc("Choose the Reflector to use [A,B,C] or [b,c] for thin rotors.")
            .build())
    addOption(Option.builder("g").longOpt("ringstellung").argName("g").hasArg().argName("RINGSTELLUNG")
            .desc("Specify the Ring Settings (Ringstellung) for the chosen rotors.")
            .build())
    addOption(Option.builder("k").longOpt("key").hasArg().argName("KEY")
            .desc("Sets the Message Key or position of the rotors (Grundstellung).")
            .build())
    addOption(Option.builder("s").longOpt("stecker").hasArg().argName("STECKER_PAIRS")
            .desc("Add one or more Plugboard matching pairs (Steckerbrett).")
            .build())
    addOption(Option.builder("f").longOpt("family").hasArg().argName("FAMILY")
            .desc("Enigma Family")
            .build())
    addOption(Option.builder("e").longOpt("encode").hasArg().argName("MESSAGE")
            .desc("Set a Message to encode/decode.")
            .build())
    addOption(Option.builder("t").longOpt("token").hasArg().argName("TOKEN")
            .desc("Use configuration machine:rotors:Reflector:ringstellung:stecker:key")
            .build())
    addOption(Option.builder("m").longOpt("machine").hasArg().argName("MACHINE")
            .type(Int::class.java)
            .desc("Select a prebuilt machine")
            .build())
    addOption(Option.builder("l").longOpt("list_machines").hasArg(false)
            .desc("List predefined machines")
            .build())
    addOption(Option.builder("i").longOpt("machine_info").hasArg(false)
            .desc("Show machine info")
            .build())
    addOption(Option.builder("v").longOpt("verbose").hasArg(false)
            .desc("Verbosity")
            .build())
    addOption(Option.builder("h").longOpt("help").argName("h")
            .desc("Display this help message.")
            .build())
}