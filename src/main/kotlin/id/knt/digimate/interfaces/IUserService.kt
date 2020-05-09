package id.knt.digimate.interfaces

import id.knt.digimate.dto.UserDto
import id.knt.digimate.models.User

interface IUserService {
	fun addUser(newUser: UserDto): Int
	fun findUserById(id: String): UserDto?
	fun getUser(id: String): User?
	fun findAllUser(): List<UserDto>?
	fun update(currentUser: UserDto): Int
	fun delete(userDto: UserDto): Int
}