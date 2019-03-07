package com.nibado.miditest

import org.junit.Test
import javax.sound.midi.*
import javax.sound.midi.MidiSystem
import javax.sound.midi.ShortMessage



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

    @Test
    fun sendMessage() {
        val myMsg = ShortMessage()
        // Start playing the note Middle C (60),
        // moderately loud (velocity = 93).
        myMsg.setMessage(ShortMessage.NOTE_ON, 0, 60, 93)
        val rcvr = MidiSystem.getReceiver()
        rcvr.send(myMsg, 1)
    }

    @Test
    fun sendMessageBuilder() {
        val msg = Note.build().note("C").on()
        val rcvr = MidiSystem.getReceiver()
        rcvr.send(msg, 1)
    }
}