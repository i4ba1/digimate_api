package id.knt.digimate.controller

import id.knt.digimate.dto.WeatherDto
import id.knt.digimate.services.ProfileService
import id.knt.digimate.services.WeatherForecastService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/weatherForecast")
class WeatherController(private val profileService: ProfileService,
				private val weatherForecastService: WeatherForecastService){

	@GetMapping("/getWeather")
	fun getWeather(): ResponseEntity<WeatherDto?>{
		val profile = profileService.findProfile()
		if (profile != null) {
			val weatherDto = weatherForecastService.getCurrentWeather(profile.provinceName)
			return ResponseEntity(weatherDto, HttpStatus.OK)
		}
		return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
	}
}