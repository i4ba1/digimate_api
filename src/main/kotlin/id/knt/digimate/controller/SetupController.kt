package id.knt.digimate.controller

import id.knt.digimate.dto.UserDto
import id.knt.digimate.models.License
import id.knt.digimate.services.SetupService
import id.knt.digimate.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/setup")
class SetupController(private val setupService: SetupService, private val userService: UserService) {

	@GetMapping("/registerSerialNumber/{serialNumber}")
	fun registerSerialNumber(@PathVariable("serialNumber") serialNumber:String): ResponseEntity<License?>{
		val findLicenseBySerialNumber = setupService.findLicenseBySerialNumber(serialNumber)

		/**
		 * If the result is available, it mean the serial number was used or use different computer
		 */
		if (findLicenseBySerialNumber != null && findLicenseBySerialNumber.limit >= 2) {
			return ResponseEntity(HttpStatus.NOT_ACCEPTABLE)
		}

		//Save to DB
		val license:License? = setupService.activateNewSerialNumber(serialNumber)
		return ResponseEntity(license, HttpStatus.NOT_FOUND)
	}

	@GetMapping("/isSerialNumberExist")
	fun isSerialNumberExist(): ResponseEntity<Boolean>{
		val findAll = setupService.findAll()
		/**
		 * If the serial number is not null then the app was register
		 */
		if (findAll != null) {
			return ResponseEntity(true, HttpStatus.OK)
		}
		return ResponseEntity(false, HttpStatus.NOT_FOUND)
	}

	@PostMapping("/setupSuperAdmin")
	fun setupSuperAdmin(user: UserDto): ResponseEntity<Void>{
		val addUser = userService.addUser(user)
		if (addUser >= 0) {
			return ResponseEntity(HttpStatus.OK)
		}
		return ResponseEntity(HttpStatus.NOT_ACCEPTABLE)
	}

}