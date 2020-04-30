package id.knt.digimate.dto

import org.springframework.web.multipart.MultipartFile

data class MediaDto(
		var id: String,
		var title: String,
		var description: String,
		var mediaFile: MultipartFile?,
		var youtubeUrl: String,
		var fileUrl: String,
		var isPublished: Boolean = false,
		var language:String,
		var userId: String,
		var type: String
)