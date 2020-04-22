package id.knt.digimate.dto

data class RunningTextDto(
   val id: String,
	val title: String,
	val content: String,
	val isPublished: Boolean,
	val userId: String
)