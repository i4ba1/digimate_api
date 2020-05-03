package id.knt.digimate.repository

import id.knt.digimate.models.Media
import org.springframework.data.jpa.repository.JpaRepository
import id.knt.digimate.models.RunningText
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query

@Repository("runningTextRepository") interface IRunningRepository : JpaRepository<RunningText, String> {
	@Query("select rt from RunningText rt where rt.user.id = ?1")
	fun findRunningTextByUserId(userId: String): List<RunningText>

	@Query(value = "select m from Media m join fetch m.user where m.language = ?1 and m.isPublished = true")
	fun findMediaByUser(lang: String): List<RunningText>?
}