package id.knt.digimate.interfaces

import id.knt.digimate.dto.UserDto
import id.knt.digimate.models.User

interface IUserService {
	fun save(newUser: UserDto): Int
	fun findUserById(id: String): UserDto?
	fun findAllUser(): List<UserDto>?
	fun update(currentUser: UserDto): Int
	fun delete(userDto: UserDto): Int
}