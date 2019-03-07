package com.nibado.miditest

import org.junit.Test
import javax.sound.midi.*

class Experiments {
    @Test
    fun listDevices() {
        val devices = MidiSystem.getMidiDeviceInfo().map { MidiSystem.getMidiDevice(it) }
        devices.forEach {
            val isSynth = it is Synthesizer
            val isSeq = it is Sequencer
            val isTransmitter = it is Transmitter
            val isReciever = it is Receiver

            val info = it.deviceInfo

            println("${info.name} ${info.vendor} ${info.version} ${info.description} synth: $isSynth, sequencer: $isSeq, transmitter: $isTransmitter, reciever: $isReciever")
        }
    }
}