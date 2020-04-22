package id.knt.digimate.interfaces

import id.knt.digimate.dto.RunningTextDto
import id.knt.digimate.models.RunningText

interface IRunningText {
	fun save(newRunningText:RunningTextDto):Int;
	fun findRunningTextById(id:String):RunningText;
	fun findRunningTextByUserId(userId:String):List<RunningText>;
	fun findAllRunningText():List<RunningText>;
	fun update(runningText:RunningTextDto):Int;
	fun delete(id:String):Int;
}