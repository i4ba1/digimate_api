package id.knt.digimate.services

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
		val currentUser: User = userService.findUserById(newRunningText.userId)
		val createdDate = Date()
		val runningText = RunningText(null, newRunningText.title, newRunningText.content, createdDate, null,
						newRunningText.isPublished, currentUser)
		return runningTextRepository.save(runningText)
	}

	override fun findRunningTextById(id: String): RunningText {
		val optional: Optional<RunningText> = runningTextRepository.findById(id)
		if (optional.isPresent) {
			return optional.get()
		}
		return optional.get()
	}

	override fun findRunningTextByUserId(userId: String): List<RunningText> {
		return runningTextRepository.findRunningTextByUserId(userId)
	}

	override fun findAllRunningText(): List<RunningText> {
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

	override fun delete(id: String): Int {
		if (runningTextRepository.findById(id).isPresent) {
			runningTextRepository.deleteById(id)
			return 0
		}
		return 1
	}

}