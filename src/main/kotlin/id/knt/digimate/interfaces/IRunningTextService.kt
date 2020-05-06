package id.knt.digimate.interfaces

import id.knt.digimate.dto.RunningTextDto
import id.knt.digimate.models.RunningText
import org.springframework.data.domain.Page

interface IRunningText {
	fun save(newRunningText:RunningTextDto): RunningText?
	fun findRunningTextById(id:String): RunningText?
	fun findRunningTextByUserId(userId:String): List<RunningText>?
	fun findRunningTextByUser(lang: String): MutableMap<String, List<RunningTextDto>>?
	fun findAllRunningText(pageNo: Int): Page<RunningText>?
	fun update(runningText:RunningTextDto): RunningText?
	fun publishOrUnPublish(id:String, isPublished: Boolean): Boolean
}