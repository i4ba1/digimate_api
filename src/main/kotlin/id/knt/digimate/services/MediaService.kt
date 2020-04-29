package id.knt.digimate.services

import id.knt.digimate.const.DIGIMATE_MEDIA
import id.knt.digimate.dto.ActivityLogDto
import id.knt.digimate.dto.MediaDto
import id.knt.digimate.interfaces.IMediaService
import id.knt.digimate.models.Media
import id.knt.digimate.repository.IMediaRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*


@Service(value = "mediaService")
class MediaService(
		private val mediaRepository: IMediaRepository,
		private val userService: UserService,
		private val activityLogService: ActivityLogService
) : IMediaService {

	private val userHome = System.getProperty("user.home")
	private var error: Int = 0

	override fun save(newMedia: MediaDto): Int {
		val user = userService.getUser(newMedia.userId)
		var media: Media? = null
		try {
			media = Media()
			media.title = newMedia.title
			media.description = newMedia.description
			media.createdAt = Date()
			media.type = newMedia.type
			media.isPublished = newMedia.isPublished

			if (newMedia.youtubeUrl.isNotEmpty()) {
				media.youtubeUrl = newMedia.youtubeUrl
			} else {
				media.fileName = newMedia.mediaFile?.originalFilename.toString()
				media.fileSize = newMedia.mediaFile!!.size
				media.fileContent = newMedia.mediaFile!!.bytes
				media.fileUrl = mediaUrl(newMedia)
			}
			media.user = user
			mediaRepository.save(media)

			activityLogService.save(ActivityLogDto(user, media, null, null,
					"success create new media " + media.id, null))
			return error
		} catch (e: Exception) {
			activityLogService.save(ActivityLogDto(user, media, null, null,
					"failed create new media ", e))
			return error--
		}
	}

	private fun mediaUrl(newMedia: MediaDto): String {
		val mediaFile = newMedia.mediaFile
		val fileName = mediaFile!!.originalFilename?.let { StringUtils.cleanPath(it) }
		val path = Paths.get(userHome + File.pathSeparator + DIGIMATE_MEDIA + File.pathSeparator + fileName)

		try {
			Files.copy(mediaFile.inputStream, path, StandardCopyOption.REPLACE_EXISTING)
			return ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/media/")
					.path(fileName!!)
					.toUriString()
		} catch (e: Exception) {
			e.printStackTrace()
		}
		return ""
	}

	override fun findMediaById(id: String): MediaDto? {
		val media = mediaRepository.findByIdOrNull(id)
		if (media != null) {
			return MediaDto(media.id.toString()
					, media.description, media.description, null, media.youtubeUrl,
					media.fileUrl, media.isPublished, "", media.type)
		}
		return null
	}

	override fun findMediaByUserId(userId: String): List<MediaDto>? {
		val userMediaList: MutableList<MediaDto> = mutableListOf()
		mediaRepository.findMediaByUser(userId).forEach {
			val mediaDto = MediaDto(it.id.toString(), it.description, it.description, null, it.youtubeUrl,
					it.fileUrl, it.isPublished, "", it.type)
			userMediaList.add(mediaDto)
		}
		return userMediaList
	}

	override fun findAllMedia(): List<MediaDto>? {
		val allMedia: MutableList<MediaDto> = mutableListOf()
		mediaRepository.findAll(Sort.by("createdAt").descending()).forEach {
			val mediaDto = MediaDto(it.id.toString(), it.description, it.description, null, it.youtubeUrl,
					it.fileUrl, it.isPublished, "", it.type)
			allMedia.add(mediaDto)
		}
		return allMedia
	}

	override fun update(currentMedia: MediaDto): Int {
		val media = mediaRepository.findByIdOrNull(currentMedia.id)
		val user = userService.getUser(currentMedia.userId)
		try {
			if (media != null) {
				media.title = currentMedia.title
				media.description = currentMedia.description
				val mediaFile = currentMedia.mediaFile
				if (mediaFile != null) {
					if (media.fileSize != mediaFile.size) {
						media.fileName = mediaFile.originalFilename.toString()
						media.fileSize = mediaFile.size
						media.fileContent = mediaFile.bytes
						media.fileUrl = mediaUrl(currentMedia)
					}
				}
				media.updatedAt = Date()
				mediaRepository.saveAndFlush(media)

				activityLogService.save(ActivityLogDto(user, media, null, null,
						"success create new media " + media.id, null))
				return error
			}
		}catch (e: Exception){
			activityLogService.save(ActivityLogDto(user, media, null, null,
					"failed create new media ", e))
			return error--
		}
		return -2
	}

	override fun publish(mediaDto: MediaDto): Int {
		val currentMedia = mediaRepository.findByIdOrNull(mediaDto.id)
		if (currentMedia != null) {
			currentMedia.isPublished = false
			mediaRepository.saveAndFlush(currentMedia)
			return error
		}
		return error--
	}
}