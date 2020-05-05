package id.knt.digimate.controller

import id.knt.digimate.dto.*
import id.knt.digimate.services.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path=["/player"])
class PlayerController(
    private val articleService: ArticleService,
    private val mediaService: MediaService,
    private val runningTextService: RunningTextService,
    private val profileService: ProfileService) {

	@GetMapping(path = ["/getDigimateData/{lang}"])
	fun getDigimateData(@PathVariable("lang") lang: String): ResponseEntity<PlayerDto>{
		val articleMap: MutableMap<String, List<ArticleDto>> = articleService.findArticleByUser(lang)
				?: return ResponseEntity(HttpStatus.NOT_FOUND)
		val mediaImageMap: MutableMap<String, List<GetMediaDto>> = mediaService.findMediaImageByUser(lang)
				?: return ResponseEntity(HttpStatus.NOT_FOUND)
		val mediaVideoMap: MutableMap<String, List<GetMediaDto>>? = mediaService.findMediaVideoByUser(lang)
				?: return ResponseEntity(HttpStatus.NOT_FOUND)
		val runningTextMap: MutableMap<String, List<RunningTextDto>>? = runningTextService.findRunningTextByUser(lang)
				?: return ResponseEntity(HttpStatus.NOT_FOUND)

		val profile = profileService.findProfile()
				?: return ResponseEntity(HttpStatus.NOT_FOUND)
		val profileMap: MutableMap<String, GetProfileDto> = mutableMapOf()
		profileMap["profile"] = profile

		val playerDto = PlayerDto(articleMap, mediaImageMap, mediaVideoMap, runningTextMap, profileMap)
		return ResponseEntity(playerDto, HttpStatus.OK)
	}

	fun getWeatherForecast(){

	}
}