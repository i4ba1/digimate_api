package id.knt.digimate.dto

data class RunningTextDto(
	var id: String,
	var title: String,
	var content: String?,
	var isPublished: Boolean,
	var userId: String
)