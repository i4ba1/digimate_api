package id.knt.digimate.dto

import org.springframework.web.multipart.MultipartFile

data class ProfileDto (
	var id: String,
	var title: String,
	var description: String,
	var provinceName: String,
	var address: String,
	var imageLogo: MultipartFile,
	var imageUrl: String,
	var userId: String
)