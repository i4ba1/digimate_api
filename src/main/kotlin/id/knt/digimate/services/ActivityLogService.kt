package id.knt.digimate.services

import id.knt.digimate.dto.ActivityLogDto
import id.knt.digimate.interfaces.ILogActivityService
import id.knt.digimate.models.ActivityLog
import id.knt.digimate.repository.IActivityLogRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ActivityLogService(private val activityLogRepository: IActivityLogRepository) : ILogActivityService {

	override fun save(activityLog: ActivityLogDto) {
		val log = ActivityLog()
		log.media = activityLog.media
		log.article = activityLog.article
		log.runningText = activityLog.runningText

		log.user = activityLog.user
		log.activity = activityLog.activity
		log.errorLog = activityLog.errorLog
		log.activityDate = Date()
		activityLogRepository.save(log)
	}
}