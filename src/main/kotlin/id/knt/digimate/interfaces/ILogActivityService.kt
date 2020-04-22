package id.knt.digimate.interfaces

import id.knt.digimate.dto.ActivityLogDto

interface ILogActivityService {
	fun save(activityLog:ActivityLogDto)
}