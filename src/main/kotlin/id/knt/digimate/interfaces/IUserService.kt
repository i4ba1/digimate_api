package id.knt.digimate.interfaces

import id.knt.digimate.dto.UserDto
import id.knt.digimate.models.User

interface IUserService {
	fun save(newUser: UserDto): Int
	fun findUserById(id: String): User
	fun findAllUser(): List<User>
	fun update(currentUser: UserDto): Int
	fun delete(id: String): Int
}