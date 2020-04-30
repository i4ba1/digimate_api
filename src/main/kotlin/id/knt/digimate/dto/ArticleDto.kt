package id.knt.digimate.dto

data class ArticleDto (
	var id: String,
	var title: String,
	var content: String,
	var locationName: String,
	var locationMap: String,
	var isPublished: Boolean,
	var language: String,
	var userId: String
)