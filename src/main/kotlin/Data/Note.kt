package Data

data class Note(

    var title: String,
    var text: String,
    var privacy: Int? =null,
    var privacyView: String? = null ,
    var privacyComment: String? = null,
    var NoteIsDeleted :Boolean = false,
    var noteId: Int = 0
)
