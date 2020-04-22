package id.knt.digimate.repository

import id.knt.digimate.models.Media
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query


@Repository(value = "mediaRepository") interface IMediaRepository : JpaRepository<Media, String> {
	@Query("select m from Media m where m.user.id = ?1")
	fun findMediaByUser(userId: String): List<Media>;
}