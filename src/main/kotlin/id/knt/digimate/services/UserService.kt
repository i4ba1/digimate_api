package id.knt.digimate.services

import id.knt.digimate.dto.UserDto
import id.knt.digimate.interfaces.IUserService
import id.knt.digimate.models.User
import org.springframework.stereotype.Service
import id.knt.digimate.repository.IUserRepository
import lombok.extern.slf4j.Slf4j
import id.knt.digimate.models.Article
import java.util.Optional
import org.springframework.data.domain.Sort
import org.springframework.web.multipart.MultipartFile

@Slf4j
@Service(value = "userService")
class UserService private constructor(private var userRepository: IUserRepository) : IUserService {

	override fun save(newUser: UserDto): Int{

		return 1
	}

	override fun findUserById(id: String): User{
		val optional: Optional<User?> = userRepository.findById(id)
		return optional.get()
	}

	override fun findAllUser(): List<User>{
		return userRepository.findAll(Sort.by("fullName").ascending())
	}

	override fun update(currentUser: UserDto): Int{

		return 0
	}

	override fun delete(id: String): Int {
		TODO("Not yet implemented")
	}
}