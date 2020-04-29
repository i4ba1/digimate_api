package id.knt.digimate.controller

import id.knt.digimate.dto.RunningTextDto
import id.knt.digimate.models.RunningText
import id.knt.digimate.services.RunningTextService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/runningTextManagement")
class RunningTextController(private val runningTextService: RunningTextService) {

	private var response = 0

	@PostMapping(path = ["/createNewRunningText"])
	fun createNewRunningText(@RequestBody newRunningText: RunningTextDto): ResponseEntity<Void> {
		return responseForCreateNewAndUpdate(runningTextService.save(newRunningText))
	}

	@PostMapping(path = ["/updateRunningText"])
	fun updateRunningText(@RequestBody runningText: RunningTextDto): ResponseEntity<Void>{
		return responseForCreateNewAndUpdate(runningTextService.update(runningText))
	}

	@PostMapping(path = ["/publishUnPublishRunningText"])
	fun publishOrUnPublishRunningText(@RequestBody runningText: RunningText): ResponseEntity<Boolean> {
		val isPublish: Boolean = runningTextService.publishOrUnPublish(runningText.id.toString(), runningText.isPublished)
		return ResponseEntity(isPublish, HttpStatus.OK)
	}

	@GetMapping(path = ["/getAllRunningText"])
	fun getAllRunningText(): ResponseEntity<List<RunningText>>? {
		val runningTexts = runningTextService.findAllRunningText()
		return if (runningTexts != null) {
			ResponseEntity(runningTexts, HttpStatus.OK)
		} else ResponseEntity(HttpStatus.NOT_FOUND)
	}

	@GetMapping(path = ["/getRunningTextByUserId/{userId}"])
	fun getRunningTextByUserId(@PathVariable("userId") userId: String?): ResponseEntity<List<RunningText>>? {
		val runningTexts = runningTextService.findRunningTextByUserId(userId!!)
		return if (runningTexts != null) {
			ResponseEntity(runningTexts, HttpStatus.OK)
		} else ResponseEntity(HttpStatus.NOT_FOUND)
	}

	@GetMapping(path = ["/getRunningTextById/{id}"])
	fun getRunningTextById(@PathVariable("id") id: String?): ResponseEntity<RunningText?>? {
		val runningText = runningTextService.findRunningTextById(id!!)
		return if (runningText != null) {
			ResponseEntity(runningText, HttpStatus.OK)
		} else ResponseEntity(HttpStatus.NOT_FOUND)
	}

	private fun responseForCreateNewAndUpdate(runningTextDto: RunningText?): ResponseEntity<Void> {
		return if (runningTextDto != null) {
			ResponseEntity(HttpStatus.OK)
		} else ResponseEntity(HttpStatus.NOT_MODIFIED)
	}

}