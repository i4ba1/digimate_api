package id.knt.digimate.services

import id.knt.digimate.dto.ArticleDto
import id.knt.digimate.dto.RunningTextDto
import id.knt.digimate.interfaces.IRunningText
import id.knt.digimate.models.RunningText
import id.knt.digimate.models.User
import id.knt.digimate.repository.IRunningRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service(value = "runningTextService")
class RunningTextService(private val runningTextRepository: IRunningRepository, private val userService: UserService) :
				IRunningText {

	override fun save(newRunningText: RunningTextDto): RunningText {
		val currentUser: User? = userService.getUser(newRunningText.userId)
		val createdDate = Date()
		val runningText = RunningText(null, newRunningText.title, newRunningText.content, createdDate, null,
						newRunningText.isPublished, currentUser?.id.toString())
		return runningTextRepository.save(runningText)
	}

	override fun findRunningTextById(id: String): RunningText? {
		val optional: Optional<RunningText> = runningTextRepository.findById(id)
		if (optional.isPresent) {
			return optional.get()
		}
		return optional.get()
	}

	override fun findRunningTextByUserId(userId: String): List<RunningText>? {
		return runningTextRepository.findRunningTextByUserId(userId)
	}

	override fun findRunningTextByUser(lang: String): MutableMap<String, List<RunningTextDto>>? {
		val runningTexts = runningTextRepository.findMediaByUser(lang)
		val runningTextList: MutableList<RunningTextDto> = mutableListOf()
		val runningTextMap: MutableMap<String, List<RunningTextDto>> = mutableMapOf()

		runningTexts?.forEach {
			val runningTextDto = RunningTextDto(it.id.toString(), it.title, it.content, it.isPublished, it.user?.id.toString())
			runningTextList.add(runningTextDto)
		}

		runningTextMap["runningText"] = runningTextList
		return  runningTextMap
	}

	override fun findAllRunningText(): List<RunningText>? {
		return runningTextRepository.findAll(Sort.by("createdAt").descending())
	}

	override fun update(runningText: RunningTextDto): RunningText? {
		val optional = runningTextRepository.findById(runningText.id)
		if(optional.isPresent){
			val currentRunningText = optional.get()
			currentRunningText.title = runningText.title
			currentRunningText.content = runningText.content
			currentRunningText.updatedAt = Date()
			return runningTextRepository.save(currentRunningText)
		}
		return null
	}

	override fun publishOrUnPublish(id: String, isPublish: Boolean): Boolean {
		val optional = runningTextRepository.findById(id)
		var runningText: RunningText? = null
		if (optional.isPresent) {
			runningText = optional.get()
			runningText.isPublished = isPublish
			runningTextRepository.saveAndFlush(runningText)
		}

		if (runningText != null) {
			return runningText.isPublished
		}
		return false
	}

}