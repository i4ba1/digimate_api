package id.knt.digimate.services

import id.knt.digimate.const.DIGIMATE_PROFILE
import id.knt.digimate.dto.ActivityLogDto
import id.knt.digimate.dto.UserDto
import id.knt.digimate.interfaces.IUserService
import id.knt.digimate.models.User
import id.knt.digimate.repository.IUserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
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
@Service
class UserService private constructor(
				private val userRepository: IUserRepository,
				private val activityLogService: ActivityLogService) : IUserService {
	private var userHome = System.getProperty("user.home")

	override fun save(newUser: UserDto): Int {
			var user: User? = null
			try {
				user = User()
				saveOrUpdateSpecifiedField(user, newUser)
				user.fullName = user.firstName + " " + user.lastName
				user.identityNumber = newUser.identityNumber
				user.username = newUser.username
				user.password = newUser.password
				user.userType = newUser.userType
				newUser.profilePicture?.let { setUserProfilePicture(user!!, it) }
				user = userRepository.save(user)

				activityLogService.save(ActivityLogDto(user, null, null, null,
								"success create new user " + user.id, null))
			} catch (e: IOException) {
				activityLogService.save(ActivityLogDto(user, null, null, null,
								"failed create new user ", null))
			}

			if (user == null) {
				return -1
			}
			return 0
	}

	private fun setUserProfilePicture(user: User, profileImage: MultipartFile) {
		val fileName = profileImage.originalFilename?.let { StringUtils.cleanPath(it) }
		val path = Paths.get(userHome + File.pathSeparator + DIGIMATE_PROFILE + File.pathSeparator + fileName)

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
			user.profilePictureUrl = fileDownloadUri

		} catch (e: Exception) {
			activityLogService.save(ActivityLogDto(user, null, null, null,
							"failed insert new profile ", e))
		}
	}

	override fun findUserById(id: String): UserDto? {
		val optional: Optional<User> = userRepository.findById(id)
		if (optional.isPresent){
			val user = optional.get()
			return UserDto(user.id.toString(), user.firstName, user.lastName, user.fullName, user.email, user
							.homePhone, user.mobilePhone, user.address, user.identityNumber, user.username, user.password, user
							.userType, null, user.profilePictureUrl)
		}
		return null
	}

	override fun getUser(id: String): User? {
		return userRepository.findByIdOrNull(id)
	}

	override fun findAllUser(): List<UserDto>? {
		val allUsers: List<User> = userRepository.findAll(Sort.by("fullName").ascending())
		val users = mutableListOf<UserDto>()
		for (user in allUsers){
			val userDto = UserDto(user.id.toString(), user.firstName, user.lastName, user.fullName, user.email, user
							.homePhone, user.mobilePhone, user.address, user.identityNumber, user.username, user.password, user
							.userType, null, user.profilePictureUrl)
			users.add(userDto)
		}
		return users
	}

	override fun update(currentUser: UserDto): Int {
		var user = userRepository.findByIdOrNull(currentUser.id)
		if (user != null) {
			user = saveOrUpdateSpecifiedField(user, currentUser)
			userRepository.saveAndFlush(user)
			return 0
		}
		return -1
	}

	private fun saveOrUpdateSpecifiedField(user: User, currentUser: UserDto): User{
		user.firstName = currentUser.firstName
		user.lastName = currentUser.lastName
		user.address = currentUser.address
		user.homePhone = currentUser.homePhone
		user.mobilePhone = currentUser.mobilePhone
		user.email = currentUser.email
		return user
	}

	override fun delete(userDto: UserDto): Int {
		val user = userRepository.findByIdOrNull(userDto.id)
		if (user != null){
			user.isActive = false
			userRepository.saveAndFlush(user)
			return 0
		}
		return -1
	}
}