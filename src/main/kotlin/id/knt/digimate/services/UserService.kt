package id.knt.digimate.services

import id.knt.digimate.const.DIGIMATE_PROFILE
import id.knt.digimate.dto.ActivityLogDto
import id.knt.digimate.dto.UserDto
import id.knt.digimate.interfaces.IUserService
import id.knt.digimate.models.Profile
import id.knt.digimate.models.User
import id.knt.digimate.repository.IUserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*


@Slf4j
@Service(value = "userService")
class UserService private constructor(
				private val userRepository: IUserRepository,
				private val activityLogService: ActivityLogService) : IUserService {
	private var userHome = System.getProperty("user.home")

	override fun save(newUser: UserDto): Int{
		var user: User? = null
		try {
			user = User()
			user.firstName = newUser.firstName
			user.lastName = newUser.lastName
			user.fullName = user.firstName + " " + user.lastName
			user.homePhone = newUser.homePhone
			user.mobilePhone = newUser.mobilePhone
			user.address = newUser.address
			user.identityNumber = newUser.identityNumber
			user.email = newUser.email
			user.username = newUser.username
			user.password = newUser.password
			user.userType = newUser.userType

			user = userRepository.save(user)


			activityLogService.save(ActivityLogDto(user, null, null, null,
							"success create new user " + user.id, null))
		} catch (e: IOException) {
			activityLogService.save(ActivityLogDto(user, null, null, null,
							"failed create new user ", null))
		}

		if (user == null){
			return 1
		}
		return 0
	}

	private fun saveProfileImage(user: User, profileImage: MultipartFile){
		val fileName = profileImage.originalFilename?.let { StringUtils.cleanPath(it) }
		val path = Paths.get(userHome + File.pathSeparator+ DIGIMATE_PROFILE + File.pathSeparator+fileName)
		var newProfile: Profile? = null

		try {
			Files.copy(profileImage.inputStream, path, StandardCopyOption.REPLACE_EXISTING)
			val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/files/profile/")
							.path(fileName!!)
							.toUriString()
			user.profilePicture = profileImage.bytes
			user.pictureFileContentType = profileImage.contentType!!
			user.pictureFileName = profileImage.originalFilename.toString()
			user.pictureFileSize = profileImage.size

			activityLogService.save(ActivityLogDto(user, null, null, null,
							"success insert new profile " + profile.id, null))
		}catch (e: Exception){
			activityLogService.save(ActivityLogDto(user, null, null, null,
							"failed insert new profile ", e))
		}
	}

	override fun findUserById(id: String): User{
		val optional: Optional<User?> = userRepository.findById(id)
		return optional.get()
	}

	override fun findAllUser(): List<User>{
		return userRepository.findAll(Sort.by("fullName").ascending())
	}

	override fun update(currentUser: UserDto): Int{

		return 0
	}

	override fun delete(id: String): Int {
		TODO("Not yet implemented")
	}
}