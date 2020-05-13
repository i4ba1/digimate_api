package id.knt.digimate.repository

import id.knt.digimate.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface IUserRepository : JpaRepository<User, String>{
	fun findByUsername(username: String):Optional<User>
	fun existByUsername(username: String): Boolean
	fun existByEmail(email: String): Boolean
}