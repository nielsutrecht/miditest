package com.nibado.miditest

import kotlin.IllegalArgumentException

enum class Note(val index: Int) {
    A(1),
    As(2),
    Bb(2),
    B(3),
    C(4),
    Cs(4),
    Db(5),
    D(6),
    Ds(7),
    Eb(7),
    E(8),
    F(9),
    Fs(10),
    Gb(10),
    G(11),
    Gs(12),
    Ab(12);

    companion object {
        fun build() = NoteBuilder()

        fun parse(note: String) : Note {
            val builder = StringBuilder(2)
            if(note[0] !in 'A' .. 'G') {
                throw IllegalArgumentException("Note should be in A .. G")
            }
            builder.append(note[0])

            if(note.length > 1) {
                val p2 = when(note[1]) {
                    '#' -> 's'
                    '♯' -> 's'
                    's' -> 's'
                    'b' -> 'b'
                    '♭' -> 'b'
                    else -> throw IllegalArgumentException("Illegal note $note")
                }

                builder.append(p2)
            }

            return Note.valueOf(builder.toString())
        }
    }


}