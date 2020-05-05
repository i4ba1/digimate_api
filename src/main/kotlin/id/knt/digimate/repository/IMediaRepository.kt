package id.knt.digimate.repository

import id.knt.digimate.dto.GetMediaDto
import id.knt.digimate.models.Media
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface IMediaRepository : JpaRepository<Media, String> {
	@Query("select m from Media m where m.user.id = ?1")
	fun findMediaByUserId(userId: String): List<Media>

	@Query(value = "select m.id, m.title, m.description, m.type, m.isPublished, m.language, m.fileUrl, m.user.id from " +
			"Media m where m.language= ?1 and m.isPublished = true and m.type='image'")
	fun getImageByUser(lang: String): List<GetMediaDto>?

	@Query(value = "select m.id, m.title, m.description, m.type, m.youtubeUrl, m.isPublished, m.language, m.fileUrl, m.user.id " +
			"from Media m where m.language= ?1 and m.isPublished = true and m.type='video'")
	fun getVideoByUser(lang: String): List<GetMediaDto>?
}