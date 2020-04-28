package id.knt.digimate.dto

import org.springframework.web.multipart.MultipartFile

data class ProfileDto (
	var id: String,
	var provinceName: String,
	var address: String,
	var imageLog: MultipartFile,
	var userId: String
)