package Service

import Data.Comment
import Data.Note
import Exception.CommentNotFoundException
import Exception.NotesNotFoundException
import Service.NoteService.notes

object CommentService : CrudService<Comment> {

    override fun add(comment: Comment): Int {
        for (note in notes) {
            if (comment.noteId == note.key.noteId) {
                note.value.add(comment)
                return comment.commentId
            } else throw NotesNotFoundException("Заметка не найдена")

        }
        return 0
    }

    override fun delete(commentID: Int): Boolean {
        for (value in notes.values) {
            if(value.isNotEmpty()) {
            for (comm in value)
                    if (comm.commentId == commentID && !comm.isDeleted) {
                            comm.isDeleted = true
                            return true
                        } else throw CommentNotFoundException()

                    }else throw CommentNotFoundException()
                }
        return false
            }




    override fun getById(id: Int): Comment? {

        TODO("Not yet implemented")
    }

    override fun restore(id: Int): Boolean {
        for (note in notes) {
            if (id == note.key.noteId) {
                for (comment in note.value) {
                    if (comment.commentId == id) {
                        if (comment.isDeleted == true) {
                            comment.isDeleted = false
                            return true
                        }

                    } else throw CommentNotFoundException()
                    }
                } else throw CommentNotFoundException()
            }
       return false
        }




    override fun read(id: Int): MutableList<Comment>? {
        for (note in notes) {
            if (id == note.key.noteId) {
                return note.value
            }
        }

        return null
    }

    override fun edit(comment: Comment): Boolean {

        for (value in notes) {
            if (value.value.contains(comment)) {
                comment.message = "редактирование комментария"
                return true
            } else {
                throw CommentNotFoundException()
            }

        }
        return false
    }

}




