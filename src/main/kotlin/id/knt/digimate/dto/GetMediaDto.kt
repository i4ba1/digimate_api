package id.knt.digimate.dto


data class GetMediaDto(
	var id: String,
	var title: String,
	var description: String,
	var youtubeUrl: String,
	var fileUrl: String,
	var isPublished: Boolean = false,
	var language:String,
	var userId: String,
	var type: String
)