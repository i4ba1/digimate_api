package id.knt.digimate.controller

import id.knt.digimate.dto.MediaDto
import id.knt.digimate.services.MediaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(path = ["/mediaManagement"])
class MediaController(private val mediaService: MediaService) {

	@PostMapping(path = ["/createNewMedia"])
	fun createNewMedia(@RequestBody newMedia: MediaDto): ResponseEntity<Void>? {
		val response: Int = mediaService.save(newMedia)
		return responseNewAndUpdateMedia(response)
	}

	private fun responseNewAndUpdateMedia(response: Int): ResponseEntity<Void>? {
		return if (response <= 0) {
			ResponseEntity(HttpStatus.OK)
		} else ResponseEntity(HttpStatus.NOT_MODIFIED)
	}

	@PostMapping(path = ["/updateMedia"])
	fun updateMedia(@RequestBody updateMedia: MediaDto): ResponseEntity<Void>? {
		val response: Int = mediaService.update(updateMedia)
		return responseNewAndUpdateMedia(response)
	}

	@GetMapping(path = ["/getMediaById/{id}"])
	fun getMediaById(@PathVariable("id") id: String): ResponseEntity<MediaDto>? {
		val media: MediaDto? = mediaService.findMediaById(id)
		return if (media != null) {
			ResponseEntity<MediaDto>(media, HttpStatus.OK)
		} else ResponseEntity<MediaDto>(HttpStatus.NOT_FOUND)
	}

	@GetMapping(path = ["/getMediaByUserId/{userId}"])
	fun getMediaByUserId(@PathVariable(value = "userId") userId: String): ResponseEntity<List<MediaDto>>? {
		val userMediaList: List<MediaDto>? = mediaService.findMediaByUserId(userId)
		return responseForListAllMediaAndUserMedia(userMediaList)
	}

	@GetMapping(path = ["/getAllMedia"])
	fun getAllMedia(): ResponseEntity<List<MediaDto>>? {
		val allMedia: List<MediaDto>? = mediaService.findAllMedia()
		return responseForListAllMediaAndUserMedia(allMedia)
	}

	private fun responseForListAllMediaAndUserMedia(userMediaList: List<MediaDto>?): ResponseEntity<List<MediaDto>>? {
		return if (userMediaList == null || userMediaList.isEmpty()) {
			ResponseEntity<List<MediaDto>>(userMediaList, HttpStatus.NOT_FOUND)
		} else ResponseEntity<List<MediaDto>>(HttpStatus.OK)
	}

}