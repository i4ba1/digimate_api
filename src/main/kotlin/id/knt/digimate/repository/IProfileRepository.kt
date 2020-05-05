package id.knt.digimate.repository


import id.knt.digimate.dto.GetProfileDto
import id.knt.digimate.dto.ProfileDto
import id.knt.digimate.models.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface IProfileRepository : JpaRepository<Profile, String> {

	@Query("select p.id, p.title, p.description, p.provinceName, p.address, p.logoUrl from Profile p")
	fun getProfile(): GetProfileDto
}