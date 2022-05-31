object NoteService {
    var notes = mutableMapOf<Note, MutableList<Comment?>>()
    private var id = 1

fun add( note: Note) : Int {
    note.noteId = id
    id++
    notes[note] = mutableListOf()
    return  note.noteId

}
fun createComment(comment: Comment): Int {
    for (note in notes) {
        if (comment.noteId == note.key.noteId) {
            note.value.add(comment)
            return comment.commentId
        }
    }
    return 0
}

fun delete(noteId: Int): Boolean {
    for (note in notes) {
        if (noteId == note.key.noteId) {
            notes.remove(note.key)
            return true
        }
    }
    return false
}

fun deleteComment(commentID: Int, ownerId: Int): Boolean {
    for (value in notes.values) {
        for (comment in value) {
            if (comment != null) {
                if (comment.commentId == commentID) {
                    if (!comment.isDeleted) {
                        comment.isDeleted = true
                        return true
                    } else {
                        throw CommentNotFoundException()
                    }
                }
            }
        }
    }
    return throw CommentNotFoundException()
}

fun edit(noteId: Int, title: String, text: String, privacyView: String, privacyComment: String): Boolean {
    for (note in notes) {
        if (noteId == note.key.noteId) {
            note.key.title = title
            note.key.text = text
            note.key.privacyView = privacyView
            note.key.privacyComment = privacyComment
            return true
        }
    }
    return false
}

fun editComment(commentID: Int, ownerId: Int, message: String): Boolean {
    for (value in notes.values) {
        for (comment in value) {
            if (comment != null && comment.commentId == commentID) {
                if (!comment.isDeleted) {
                    comment.message = message
                    return true
                } else {
                    return false
                    throw CommentNotFoundException()
                }
            }
        }
    }
    return false
}

fun get(noteIds: ArrayList<Int>, userId: Int, offset: Int, count: Int, sort: Int): MutableList<Note>? {
    val listOfUserComments: MutableList<Note> = mutableListOf()
    for (note in notes.keys) {
        if (note != null && note.ownerId == userId) {
            listOfUserComments.add(note)
        }
    }
    return listOfUserComments
}


fun getById(noteId: Int, ownerId: Int, needWiki: Boolean = false): Note? {
    for (note in notes) {
        if (noteId == note.key.noteId) {
            return note.key
        }
    }
    return null
}

fun getComments(noteId: Int, ownerId: Int, sort: Boolean = false, offset: Int, count: Int): MutableList<Comment?>? {
    for (note in notes) {
        if (noteId == note.key.noteId) {
            return note.value
        }
    }
    return null
}

fun restoreComment(commentID: Int, noteId: Int): Boolean {
    for (note in notes) {
        if (noteId == note.key.noteId) {
            for (comment in note.value) {
                if (comment != null && comment.commentId == commentID) {
                    if (comment.isDeleted == true) {
                    }
                    comment.isDeleted = false
                    return true
                } else {
                    throw CommentNotFoundException()
                }
            }
        }
    }
    return false
}
    fun clear(){
        notes.clear()
    }
}

