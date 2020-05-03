package id.knt.digimate.dto

import org.springframework.web.multipart.MultipartFile

data class GetProfileDto (
	var id: String,
	var title: String,
	var description: String,
	var provinceName: String,
	var address: String,
	var imageUrl: String
)