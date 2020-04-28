package id.knt.digimate.services

import id.knt.digimate.dto.ProfileDto
import id.knt.digimate.const.DIGIMATE_PROFILE
import id.knt.digimate.dto.ActivityLogDto
import id.knt.digimate.dto.FindProfileByIdDto
import id.knt.digimate.interfaces.IProfileService
import id.knt.digimate.models.Profile
import id.knt.digimate.repository.IProfileRepository
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption


@Service(value = "profileService")
class ProfileService(private val profileRepository:IProfileRepository,
				private val activityLogService: ActivityLogService,
				private val userService: UserService) : IProfileService {
	private var userHome = System.getProperty("user.home")

	override fun save(profile:ProfileDto): Profile? {
		val user = userService.findUserById(profile.userId)
		val file = profile.imageLog
		val fileName = file.originalFilename?.let { StringUtils.cleanPath(it) }
		val path = Paths.get(userHome + File.pathSeparator+ DIGIMATE_PROFILE + File.pathSeparator+fileName)
		var newProfile:Profile? = null

		try {
			Files.copy(file.inputStream, path, StandardCopyOption.REPLACE_EXISTING)
			val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/files/profile/")
							.path(fileName!!)
							.toUriString()

			newProfile = Profile(null, profile.provinceName, profile.address,
							profile.imageLog.bytes, fileDownloadUri)
			newProfile = profileRepository.save(newProfile)

			activityLogService.save(ActivityLogDto(user, null, null, null,
							"success insert new profile " + profile.id, null))
		}catch (e: Exception){
			activityLogService.save(ActivityLogDto(user, null, null, null,
							"failed insert new profile ", e))
		}

		return newProfile
	}

	override fun findProfileById(id: String): FindProfileByIdDto? {
		val profile = profileRepository.findById(id).get()
		val findProfile = FindProfileByIdDto()
		findProfile.id = profile.id.toString()
		findProfile.provinceName = profile.provinceName
		findProfile.address = profile.address
		findProfile.logoUrl = profile.logoUrl
		return findProfile
	}

	override fun update(profileDto: ProfileDto): Profile? {
		var profile: Profile? = null
		val user = userService.findUserById(profileDto.userId)

		try {
			profile = profileRepository.findById(profileDto.id.toString()).get()
			activityLogService.save(ActivityLogDto(user, null, null, null,
							"success update profile " + profile.id, null))
		}catch (e: Exception){
			activityLogService.save(ActivityLogDto(user, null, null, null,
							"failed update profile ", e))
		}
		return profile
	}

	override fun delete(id: String) {
		val profile = profileRepository.findById(id).get()
		profileRepository.delete(profile)
	}
}