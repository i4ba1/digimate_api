package id.knt.digimate.dto

import org.springframework.web.multipart.MultipartFile

data class UserDto(
	var id: String,
	var firstName: String,
	var lastName: String,
	var fullName: String,
	var email: String,
	var homePhone: String,
	var mobilePhone: String,
	var address: String,
	var identityNumber: String,
	var username: String,
	var password: String,
	var userType: String,
	var profilePicture: MultipartFile?,
	var profilePictureUrl: String
)