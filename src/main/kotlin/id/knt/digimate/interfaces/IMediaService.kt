package id.knt.digimate.interfaces

import id.knt.digimate.dto.MediaDto
import id.knt.digimate.models.Media

interface IMediaService {
	fun save(newMedia:MediaDto):Int
	fun findMediaById(id:String):Media
	fun findMediaByUserId(userId:String):List<Media>
	fun findAllMedia():List<Media>
	fun update(currentMedia:MediaDto):Int;
	fun delete(mediaDto:MediaDto):Int;
}