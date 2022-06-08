package Service

import Data.Note
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

internal class NoteServiceTest {
    @Before
    fun setup() {

    }

    @After
    fun tearDown() {
        NoteService.clear()
    }

    @Test
    fun add() {

        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val expectedId = NoteService.notes.keys.size
        val result = note.noteId
        assertEquals(expectedId, result)
    }

    @Test
    fun deleteNoteComplete() {
        val note = Note("Заголовок", "21")
        NoteService.add(note)

        val result = NoteService.delete(note.noteId)
        assertTrue(result)
    }

    @Test
    fun deleteNoteFalse() {
        val note = Note("Заголовок", "Заметка")
        NoteService.add(note)
        assertFalse(NoteService.delete(2))

    }

    @Test
    fun getById() {
        val note = Note("Заголовок", "Заметка")

        val result = NoteService.getById(NoteService.add(note))
        assertEquals(note, result)

    }

    @Test
    fun editNoteComplete() {
        val note = Note("Заголовок", "Заметка")
        NoteService.add(note)
        val result = NoteService.edit(note)

        assertTrue(result)
    }

    @Test
    fun editNoteFalse() {
        val note = Note("Заголовок", "Заметка")
        NoteService.add(note)
        val noteTest = Note("Тестовая заметка ", "Тест должен пройти")
        val result = NoteService.edit(noteTest)
        assertFalse(result)

    }
    @Test
    fun restoreNoteComplete() {
        val note = Note("Заголовок", "Заметка")
        NoteService.add(note)
        NoteService.delete(note.noteId)
        val result = NoteService.restore(note.noteId)
        assertTrue(result)

    }

}








