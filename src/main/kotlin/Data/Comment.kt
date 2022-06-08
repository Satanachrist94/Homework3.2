package Data

data class Comment(
var commentId: Int,
val noteId: Int,
val replyTo: Int? = null,
var message: String,
val guid: String? = null,
var isDeleted: Boolean = false
)
