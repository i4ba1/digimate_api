package id.knt.digimate.services

import id.knt.digimate.const.DIGIMATE_MEDIA
import id.knt.digimate.dto.ActivityLogDto
import id.knt.digimate.dto.GetMediaDto
import id.knt.digimate.dto.NewMediaDto
import id.knt.digimate.interfaces.IMediaService
import id.knt.digimate.models.Media
import id.knt.digimate.repository.IMediaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*


@Service
class MediaService(
		private val mediaRepository: IMediaRepository,
		private val userService: UserService,
		private val activityLogService: ActivityLogService
) : IMediaService {

	private val userHome = System.getProperty("user.home")
	private var error: Int = 0

	override fun save(newMedia: NewMediaDto): Int {
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
			media.language = newMedia.language
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

	private fun mediaUrl(newMedia: NewMediaDto): String {
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

	override fun findMediaById(id: String): NewMediaDto? {
		val media = mediaRepository.findByIdOrNull(id)
		if (media != null) {
			return NewMediaDto(media.id.toString()
					, media.description, media.description, null, media.youtubeUrl,
					media.fileUrl, media.isPublished, "", media.type, media.language)
		}
		return null
	}

	override fun findMediaByUserId(userId: String): List<NewMediaDto>? {
		val userMediaList: MutableList<NewMediaDto> = mutableListOf()
		mediaRepository.findMediaByUserId(userId).forEach {
			val mediaDto = NewMediaDto(it.id.toString(), it.description, it.description, null, it.youtubeUrl,
					it.fileUrl, it.isPublished, "", it.type, it.language)
			userMediaList.add(mediaDto)
		}
		return userMediaList
	}

	override fun findMediaImageByUser(lang: String): MutableMap<String, List<GetMediaDto>>? {
		val media = mediaRepository.getImageByUser(lang)
		val mediaList: MutableList<GetMediaDto> = mutableListOf()
		val mediaMap: MutableMap<String, List<GetMediaDto>> = mutableMapOf()

		media?.forEach {
			val mediaDto = GetMediaDto(it.id, it.title, it.description, "",
					it.fileUrl, it.isPublished, it.language, it.userId, it.type)
			mediaList.add(mediaDto)
		}

		mediaMap["media"] = mediaList
		return  mediaMap
	}

	override fun findMediaVideoByUser(lang: String): MutableMap<String, List<GetMediaDto>>? {
		val media = mediaRepository.getVideoByUser(lang)
		val mediaList: MutableList<GetMediaDto> = mutableListOf()
		val mediaMap: MutableMap<String, List<GetMediaDto>> = mutableMapOf()

		media?.forEach {
			val mediaDto = GetMediaDto(it.id, it.title, it.description, it.youtubeUrl,
					it.fileUrl, it.isPublished, it.language, it.userId, it.type)
			mediaList.add(mediaDto)
		}

		mediaMap["media"] = mediaList
		return  mediaMap
	}

	override fun findAllMedia(pageNo:Int): Page<NewMediaDto>? {
		val noOfRecords:Int = 20
		val pageable: Pageable = PageRequest.of(pageNo, noOfRecords)
		val allMedia: MutableList<NewMediaDto> = mutableListOf()
		mediaRepository.getAllMedia(pageable).forEach {
			val mediaDto = NewMediaDto(it.id.toString(), it.description, it.description, null, it.youtubeUrl,
					it.fileUrl, it.isPublished, "", it.type, it.language)
			allMedia.add(mediaDto)
		}

		return PageImpl(allMedia, pageable, allMedia.size.toLong())
	}

	override fun update(currentMedia: NewMediaDto): Int {
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

	override fun publish(mediaDto: NewMediaDto): Int {
		val currentMedia = mediaRepository.findByIdOrNull(mediaDto.id)
		if (currentMedia != null) {
			currentMedia.isPublished = false
			mediaRepository.saveAndFlush(currentMedia)
			return error
		}
		return error--
	}
}