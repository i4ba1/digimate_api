package id.knt.digimate.interfaces

import id.knt.digimate.dto.MediaDto
import id.knt.digimate.models.Media

interface IMediaService {
	fun save(newMedia:MediaDto):Int
	fun findMediaById(id:String):MediaDto?
	fun findMediaByUserId(userId:String):List<MediaDto>?
	fun findAllMedia():List<MediaDto>?
	fun update(currentMedia:MediaDto):Int;
	fun publish(mediaDto:MediaDto):Int;
}