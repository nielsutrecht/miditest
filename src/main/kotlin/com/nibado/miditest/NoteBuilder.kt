package com.nibado.miditest

import java.lang.IllegalArgumentException
import javax.sound.midi.ShortMessage

class NoteBuilder {
    private var velocity = 93
    private var note = 60 //Middle C
    private var channel = 0

    fun velocity(velocity: Int): NoteBuilder {
        inRange(velocity, 0..127)

        this.velocity = velocity

        return this
    }

    fun note(note: Int): NoteBuilder {
        inRange(note, 0..127)

        this.note = note

        return this
    }

    fun note(octave: Int, note: Note) : NoteBuilder {
        inRange(octave, 0 .. 9)

        return note(octave * 12 + 20 + note.index)
    }

    fun note(octave: Int, note: String) = note(octave, Note.parse(note))

    fun note(note: Note) = note(4, note)

    fun note(note: String) = note(Note.parse(note))

    fun channel(channel: Int): NoteBuilder {
        this.channel = channel

        return this
    }

    fun on() = ShortMessage(ShortMessage.NOTE_ON, channel, note, velocity)

    private fun inRange(value: Int, range: IntRange) {
        if (!range.contains(value)) {
            throw IllegalArgumentException("$value not in range ${range.start}-${range.endInclusive}")
        }
    }
}