package id.knt.digimate.dto

import id.knt.digimate.models.Article
import id.knt.digimate.models.Media
import id.knt.digimate.models.RunningText
import id.knt.digimate.models.User

data class ActivityLogDto(
	val user: User,
	val media: Media,
	val article: Article,
	val runningText: RunningText,
	val activity: String,
	val log: String
)