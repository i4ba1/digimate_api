package id.knt.digimate.controller

import id.knt.digimate.dto.UserDto
import id.knt.digimate.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/userManagement")
class UserController(private val userService: UserService) {

	@PostMapping("/createNewUser")
	fun createNewUser(@RequestBody userDto: UserDto): ResponseEntity<Void>{
		val error = userService.addUser(userDto)
		return saveOrUpdateResponse(error)
	}

	@PostMapping("/updateUser")
	fun updateUser(@RequestBody currentUserDto: UserDto): ResponseEntity<Void>{
		val error = userService.update(currentUserDto)
		return saveOrUpdateResponse(error)
	}

	private fun saveOrUpdateResponse(error: Int): ResponseEntity<Void>{
		if (error < 0){
			return ResponseEntity(HttpStatus.NOT_ACCEPTABLE)
		}
		return ResponseEntity(HttpStatus.OK)
	}

	@GetMapping("/getUserById/{id}")
	fun getUserById(@PathVariable("id") id: String): ResponseEntity<UserDto>{
		val user = userService.findUserById(id)
		if (user != null) {
			return ResponseEntity(HttpStatus.OK)
		}
		return ResponseEntity(HttpStatus.FOUND)
	}

	@GetMapping("/getAllUser")
	fun getAllUser(): ResponseEntity<UserDto>?{
		val allUser:List<UserDto>? = userService.findAllUser()
		if (allUser != null) {
			return ResponseEntity(HttpStatus.OK)
		}
		return ResponseEntity(HttpStatus.NOT_FOUND)
	}

	@PostMapping("/deleteUserById")
	fun deleteUserById(@RequestBody userDto: UserDto): ResponseEntity<Void>{
		val error =userService.delete(userDto)
		if (error < 0) {
			return ResponseEntity(HttpStatus.NOT_ACCEPTABLE)
		}
		return ResponseEntity(HttpStatus.OK)
	}
}