package id.knt.digimate.controller

import id.knt.digimate.dto.ProfileDto
import id.knt.digimate.services.ProfileService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/profileManagement"])
class ProfileController(private val profileService: ProfileService) {

	@PostMapping(path = ["/createNewProfile"])
	fun createNewProfile(@RequestBody profileDto: ProfileDto): ResponseEntity<Void>{
		val profile = profileService.save(profileDto)
		if (profile != null){
			return ResponseEntity(HttpStatus.OK)
		}
		return ResponseEntity(HttpStatus.NOT_MODIFIED)
	}

	@PostMapping(path = ["/updateProfile"])
	fun updateProfile(@RequestBody profileDto:ProfileDto): ResponseEntity<Void>{
		val profile = profileService.update(profileDto)
		if(profile != null){
			return ResponseEntity(HttpStatus.OK)
		}
		return ResponseEntity(HttpStatus.NOT_MODIFIED)
	}
}