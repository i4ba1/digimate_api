package id.knt.digimate.interfaces

import id.knt.digimate.dto.GetMediaDto
import id.knt.digimate.dto.NewMediaDto

interface IMediaService {
	fun save(newMedia:NewMediaDto):Int
	fun findMediaById(id:String):NewMediaDto?
	fun findMediaByUserId(userId:String):List<NewMediaDto>?
	fun findMediaImageByUser(lang: String):  MutableMap<String, List<GetMediaDto>>?
	fun findMediaVideoByUser(lang: String):  MutableMap<String, List<GetMediaDto>>?
	fun findAllMedia():List<NewMediaDto>?
	fun update(currentMedia:NewMediaDto):Int;
	fun publish(mediaDto:NewMediaDto):Int;
}