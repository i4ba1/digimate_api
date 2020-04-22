package id.knt.digimate.repository

import org.springframework.data.jpa.repository.JpaRepository
import id.knt.digimate.models.RunningText
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query

@Repository("runningTextRepository") interface IRunningRepository : JpaRepository<RunningText, String> {
	@Query("select rt from RunningText rt where rt.user.id = ?1")
	fun findRunningTextByUserId(userId: String): List<RunningText>
}