package id.knt.digimate.repository

import id.knt.digimate.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface IUserRepository : JpaRepository<User, String>{
	
}