package id.knt.digimate.repository

import id.knt.digimate.models.ERole
import id.knt.digimate.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository:JpaRepository<Role, String> {
	fun findByRoleName(roleName:ERole): Optional<Role>
}