package Service

import Data.Comment
import Data.Note
import Exception.CommentNotFoundException
import Exception.NotesNotFoundException
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

 internal class CommentServiceTest {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
        NoteService.clear()

    }

    @Test
    fun addCommentComplete() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, note.noteId, 1, "Создание коммента")
       val result =  CommentService.add(comment)
        assertEquals(1,result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun addCommentFalse() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, 2, 1, "Создание коммента")
        CommentService.add(comment)

    }


    @Test
    fun deleteCommentComplete() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, note.noteId, 1, "Создание коммента")
        CommentService.add(comment)
        val result = CommentService.delete(1)
        assertTrue(result)

    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentFalse() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, note.noteId, 1, "Создание коммента")
        CommentService.add(comment)
        CommentService.delete(2)

    }

    @Test
    fun getById() {
    }

    @Test
    fun restoreCommentComplete() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, note.noteId, 1, "Создание коммента")
        CommentService.add(comment)
        CommentService.delete(comment.commentId)
        val result = CommentService.restore(comment.commentId)
        assertTrue(result)

    }

    @Test(expected = CommentNotFoundException::class)
    fun  restoreCommentFalse() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, note.noteId, 1, "Создание коммента")
        CommentService.add(comment)
        CommentService.delete(comment.commentId)
        CommentService.restore(comment.commentId)

    }

    @Test
    fun readCommentComplete() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, note.noteId, 1, "Создание коммента")
        CommentService.add(comment)
       val expected =NoteService.notes.getValue(note)
        val result = CommentService.read(note.noteId)
        assertEquals(expected,result)

    }
    @Test
    fun readCommentFalse() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, note.noteId, 1, "Создание коммента")
        CommentService.add(comment)
        val expected =NoteService.notes.getValue(note)
        val result = CommentService.read(5)
        assertNotEquals(expected,result)

    }

    @Test
    fun editCommentComplete() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, note.noteId, 1, "Создание коммента")
        CommentService.add(comment)
        val result = CommentService.edit(comment)
        assertTrue(result)

    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentFalse() {
        val note = Note("Заголовок", "заметка")
        NoteService.add(note)
        val comment = Comment(1, note.noteId, 1, "Создание коммента")
        val comment2 = Comment(1, note.noteId, 1, "Второй коммент")
        CommentService.add(comment)
        CommentService.edit(comment2)

    }
}