package Service

import Data.Comment
import Data.Note
import Exception.NotesNotFoundException



object NoteService : CrudService<Note> {
    var notes = mutableMapOf<Note, MutableList<Comment>>()
    var id :Int = 0


    override fun add(note: Note): Int {
        id++
        val c = note.copy(noteId = id)
        note.noteId = id
        notes[c] = mutableListOf()
        return c.noteId

    }


    override fun delete(id: Int): Boolean {
        for (key in notes.keys) {
            if (key.noteId == id && !key.NoteIsDeleted) {
                key.NoteIsDeleted = true
                return true
            }
        }
        /*  for (note in notes) {
              if (id == note.key.noteId) {
                  notes.remove(note.key)
                  return true
              }

          }
          return false*/ return false
    }

    override fun getById(id: Int): Note? {
        for (note in notes) {
            if (id == note.key.noteId) {
                return note.key
            }
        }
        return null
    }

    override fun restore(id: Int): Boolean {
        for (note in notes) {
            if (id == note.key.noteId)
                 if (note.key.NoteIsDeleted ==true) {
                 note.key.NoteIsDeleted = false
                return true
                }


        }
        return false
    }

    override fun read(id: Int): MutableList<Note>? {

        TODO("Not yet implemented")
    }

    fun clear() {
        notes.clear()
    }


    override fun edit(note: Note): Boolean {
        for (not in notes) {
            if (not.key.noteId == note.noteId) {
                with(note) {
                    title = "редактирование заголовка"
                    text = "Редактирование текста"
                    return true
                }

            }

        }
        return false
    }

}



