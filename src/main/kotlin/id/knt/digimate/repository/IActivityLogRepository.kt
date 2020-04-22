package id.knt.digimate.repository

import org.springframework.data.jpa.repository.JpaRepository
import id.knt.digimate.models.ActivityLog
import org.springframework.stereotype.Repository

@Repository(value = "activityLogRepository") interface IActivityLogRepository : JpaRepository<ActivityLog, String> {
	
}