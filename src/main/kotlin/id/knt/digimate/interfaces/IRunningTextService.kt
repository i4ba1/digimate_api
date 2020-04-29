package id.knt.digimate.interfaces

import id.knt.digimate.dto.RunningTextDto
import id.knt.digimate.models.RunningText

interface IRunningText {
	fun save(newRunningText:RunningTextDto): RunningText?;
	fun findRunningTextById(id:String): RunningText?;
	fun findRunningTextByUserId(userId:String): List<RunningText>?;
	fun findAllRunningText(): List<RunningText>?;
	fun update(runningText:RunningTextDto): RunningText?;
	fun publishOrUnPublish(id:String, isPublished: Boolean): Boolean;
}