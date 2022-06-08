import Data.Comment
import Data.Note
import Service.CommentService
import Service.NoteService
import Service.NoteService.notes

fun main() {
    val note = Note("ss", " ss")
    NoteService.add(note)
   // NoteService.add(Note("111", "вторая заметка"))
//println(notes)
    val comment = Comment(1,note.noteId,1,"dsss")
CommentService.add(comment)
    CommentService.delete(comment.commentId)
   // println(CommentService.restore(1))
    println(NoteService.delete(1))
    println(NoteService.restore(2))

}
