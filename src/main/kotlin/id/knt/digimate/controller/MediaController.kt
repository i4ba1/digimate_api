package id.knt.digimate.controller

import id.knt.digimate.dto.NewMediaDto
import id.knt.digimate.services.MediaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(path = ["/mediaManagement"])
class MediaController(private val mediaService: MediaService) {

	@PostMapping(path = ["/createNewMedia"])
	fun createNewMedia(@RequestBody newMedia: NewMediaDto): ResponseEntity<Void>? {
		val response: Int = mediaService.save(newMedia)
		return responseNewAndUpdateMedia(response)
	}

	private fun responseNewAndUpdateMedia(response: Int): ResponseEntity<Void>? {
		return if (response <= 0) {
			ResponseEntity(HttpStatus.OK)
		} else ResponseEntity(HttpStatus.NOT_MODIFIED)
	}

	@PostMapping(path = ["/updateMedia"])
	fun updateMedia(@RequestBody updateMedia: NewMediaDto): ResponseEntity<Void>? {
		val response: Int = mediaService.update(updateMedia)
		return responseNewAndUpdateMedia(response)
	}

	@GetMapping(path = ["/getMediaById/{id}"])
	fun getMediaById(@PathVariable("id") id: String): ResponseEntity<NewMediaDto>? {
		val media: NewMediaDto? = mediaService.findMediaById(id)
		return if (media != null) {
			ResponseEntity<NewMediaDto>(media, HttpStatus.OK)
		} else ResponseEntity<NewMediaDto>(HttpStatus.NOT_FOUND)
	}

	@GetMapping(path = ["/getMediaByUserId/{userId}"])
	fun getMediaByUserId(@PathVariable(value = "userId") userId: String): ResponseEntity<List<NewMediaDto>>? {
		val userMediaList: List<NewMediaDto>? = mediaService.findMediaByUserId(userId)
		return responseForListAllMediaAndUserMedia(userMediaList)
	}

	@GetMapping(path = ["/getAllMedia"])
	fun getAllMedia(): ResponseEntity<List<NewMediaDto>>? {
		val allMedia: List<NewMediaDto>? = mediaService.findAllMedia()
		return responseForListAllMediaAndUserMedia(allMedia)
	}

	private fun responseForListAllMediaAndUserMedia(userMediaList: List<NewMediaDto>?): ResponseEntity<List<NewMediaDto>>? {
		return if (userMediaList == null || userMediaList.isEmpty()) {
			ResponseEntity<List<NewMediaDto>>(userMediaList, HttpStatus.NOT_FOUND)
		} else ResponseEntity<List<NewMediaDto>>(HttpStatus.OK)
	}

}