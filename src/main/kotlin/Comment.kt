data class Comment(
var commentId: Int,
val noteId: Int,
var ownerId: Int,
val replyTo: Int,
var message: String,
val guid: String,
var isDeleted: Boolean = false
)
