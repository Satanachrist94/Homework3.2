import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class NoteServiceTests {
    @Before
    fun setUp() {

    }
    @After
        fun tearDown() {
            NoteService.clear()
        }


    @Test
    fun add() {

        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val result = NoteService.add(note)
        assertEquals(1, result)
    }

    @Test
    fun addCrash() {
        val note = Note(1, 5, "1", "1", 1, "1", "1")
        val result = NoteService.add(note)
        assertNotEquals(5, result)
    }


    @Test
    fun createComment() {

        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val comment = Comment(1, 1, 1, 1, "1+1", "1")
        NoteService.add(note)
        val result = NoteService.createComment(comment)
        assertEquals(1, result)
    }

    @Test
    fun noCreateComment() {

        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val comment = Comment(1, 5, 1, 1, "1+1", "1")
        NoteService.add(note)
        val result = NoteService.createComment(comment)
        assertEquals(0, result)
    }

    @Test
    fun delete() {

        val note = Note(1, 1, "1", "1", 1, "1", "1")
        NoteService.add(note)
        val result = NoteService.delete(1)
        assertTrue(result)

    }

    @Test
    fun cantDelete() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        NoteService.add(note)
        val result = NoteService.delete(5)
        assertFalse(result)

    }

    @Test
    fun deleteComment() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val comment = Comment(1, 1, 1, 1, "1+1", "1")
        NoteService.add(note)
        NoteService.createComment(comment)
        val result = NoteService.deleteComment(1, 1)
        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun cantDeleteComment() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val comment = Comment(1, 1, 1, 1, "1+1", "1")
        NoteService.add(note)
        NoteService.createComment(comment)
        val result = NoteService.deleteComment(3, 1)

    }


    @Test
    fun edit() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        NoteService.add(note)
        val result = NoteService.edit(1, "121", "1454", "232", "464")
        assertTrue(result)
    }


    @Test
    fun editFalse() {

        val note = Note(1, 1, "1", "1", 1, "1", "1")
        NoteService.add(note)
        val result = NoteService.edit(2, "121", "1454", "232", "464")
        assertFalse(result)
    }

    @Test
    fun editComment() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val comment = Comment(1, 1, 1, 1, "1+1", "1")
        NoteService.add(note)
        NoteService.createComment(comment)
        val result = NoteService.editComment(1, 1, "12121")
        assertTrue(result)
    }

    @Test
    fun cantEditComment() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val comment = Comment(5, 1, 1, 1, "1+1", "1")
        NoteService.add(note)
        NoteService.createComment(comment)
        val result = NoteService.editComment(1, 1, "12121")
        assertFalse(result)
    }

    @Test
    fun get() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val note2 = Note(1, 2, "1", "1", 1, "1", "1")
        NoteService.add(note)
        NoteService.add(note2)
        val result = NoteService.get(arrayListOf(1, 2), 1, 1, 1, 1)
        assertEquals(arrayListOf(note, note2), result)
    }

    @Test
    fun getNo() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val note2 = Note(2, 2, "1", "1", 1, "1", "1")
        NoteService.add(note)
        NoteService.add(note2)
        val result = NoteService.get(arrayListOf(1, 2), 1, 1, 1, 1)
        assertNotEquals(arrayListOf(note, note2), result)
    }

    @Test
    fun getById() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val note2 = Note(1, 2, "1", "1", 1, "1", "1")
        NoteService.add(note)
        NoteService.add(note2)
        val result = NoteService.getById(2, 1)
        assertEquals(note2, result)
    }


    @Test
    fun getNoById() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val note2 = Note(1, 2, "1", "1", 1, "1", "1")
        NoteService.add(note)
        NoteService.add(note2)
        val result = NoteService.getById(5, 1)
        assertNotEquals(note2, result)
    }

    @Test
    fun getComments() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val note2 = Note(1, 2, "1", "1", 1, "1", "1")
        NoteService.add(note)
        NoteService.add(note2)
        val comment = Comment(1, 1, 1, 1, "1+1", "1")
        val comment2 = Comment(2, 1, 1, 1, "1+1", "1")
        NoteService.createComment(comment)
        NoteService.createComment(comment2)
        val result = NoteService.getComments(1, 1, false, 1, 1)
        assertEquals(mutableListOf(comment, comment2), result)
    }

    @Test
    fun getNoComments() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val note2 = Note(1, 2, "1", "1", 1, "1", "1")
        NoteService.add(note)
        NoteService.add(note2)
        val comment = Comment(1, 1, 1, 1, "1+1", "1")
        val comment2 = Comment(2, 2, 1, 1, "1+1", "1")
        NoteService.createComment(comment)
        NoteService.createComment(comment2)
        val result = NoteService.getComments(1, 1, false, 1, 1)
        assertNotEquals(mutableListOf(comment, comment2), result)
    }

    @Test
    fun restoreComment() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val note2 = Note(1, 2, "1", "1", 1, "1", "1")
        NoteService.add(note)
        NoteService.add(note2)
        val comment = Comment(1, 1, 1, 1, "1+1", "1", true)
        val comment2 = Comment(2, 1, 1, 1, "1+1", "1")
        NoteService.createComment(comment)
        NoteService.createComment(comment2)
        val result = NoteService.restoreComment(1, 1)
        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun noRestoreComment() {
        val note = Note(1, 1, "1", "1", 1, "1", "1")
        val note2 = Note(1, 2, "1", "1", 1, "1", "1")
        NoteService.add(note)
        NoteService.add(note2)
        val comment = Comment(1, 1, 1, 1, "1+1", "1", false)
        val comment2 = Comment(2, 1, 1, 1, "1+1", "1")
        NoteService.createComment(comment)
        NoteService.createComment(comment2)
        NoteService.restoreComment(5, 1)
    }
}
