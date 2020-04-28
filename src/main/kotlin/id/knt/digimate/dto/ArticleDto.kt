package id.knt.digimate.dto

data class ArticleDto (
	val id: String,
	val title: String,
	val content: String,
	val locationName: String,
	val locationMap: String,
	val isPublished: Boolean,
	val userId: String
)