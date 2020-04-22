package id.knt.digimate.dto

import org.springframework.web.multipart.MultipartFile

data class UserDto(
	val id: String,
	val firstName: String,
	val lastName: String,
	val fullName: String,
	val email: String,
	val homePhone: String,
	val mobilePhone: String,
	val address: String,
	val identityNumber: String,
	val username: String,
	val password: String,
	val userType: String,
	val profilePicture: MultipartFile
)