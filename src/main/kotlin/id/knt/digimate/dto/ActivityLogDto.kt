package id.knt.digimate.dto

import id.knt.digimate.models.*
import java.lang.Exception

data class ActivityLogDto(
	var user: User?,
	var media: Media?,
	var article: Article?,
	var runningText: RunningText?,
	var activity: String?,
	var errorLog: Exception?
)