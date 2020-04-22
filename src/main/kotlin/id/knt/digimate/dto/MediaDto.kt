package id.knt.digimate.dto

data class MediaDto(
   val id: String,
	val title: String,
	val description: String,
	val mediaFile: String,
	val videoUrl: String,
	val isPublished: Boolean = false,
	val userId: String,
	val type: String
)