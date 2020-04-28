package id.knt.digimate.repository


import id.knt.digimate.models.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository(value = "profileRepository") interface IProfileRepository : JpaRepository<Profile, String> {
}