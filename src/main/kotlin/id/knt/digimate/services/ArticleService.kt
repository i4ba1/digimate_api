package id.knt.digimate.services

import id.knt.digimate.dto.ActivityLogDto
import id.knt.digimate.dto.ArticleDto
import id.knt.digimate.interfaces.IArticleService
import id.knt.digimate.models.Article
import id.knt.digimate.repository.IArticleRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.Date


@Service("articleService")
class ArticleService (
				private val userService: UserService,
				private val articleRepository: IArticleRepository,
				private val activityLogService: ActivityLogService) :
				IArticleService {

	override fun save(article: ArticleDto): Article? {
		val user = userService.getUser(article.userId)

		var newArticle: Article? = null
		try {
			newArticle = Article()
			newArticle.title = article.title
			newArticle.content = article.content
			newArticle.createdAt = Date()
			newArticle.locationName = article.locationName
			newArticle.locationMap = article.locationMap
			newArticle.user = user
			newArticle.language = article.language
			newArticle = articleRepository.save(newArticle)
			activityLogService.save(ActivityLogDto(user, null, newArticle, null,
							"success insert new article " + newArticle.id, null))
		} catch (e: Exception) {
			activityLogService.save(ActivityLogDto(user, null, newArticle, null,
							"failed insert new article ", null))
		}

		return newArticle
	}

	override fun findArticleById(id: String): Article? {
		val optional = articleRepository.findById(id)
		return optional.get()
	}

	override fun findArticleByUserId(userId: String): List<Article>? {
		return articleRepository.findArticleByUserId(userId)
	}

	override fun findAllArticle(): List<Article>? {
		return articleRepository.findAll(Sort.by("createdAt").descending())
	}

	override fun update(article: ArticleDto): Article? {
		var currentArticle = findArticleById(article.id)
		val user = userService.getUser(article.userId)

		try {
			if (currentArticle != null) {
				currentArticle.title = article.title
				currentArticle.updatedAt = Date()
				currentArticle.content = article.content
				currentArticle.isPublished = article.isPublished
				currentArticle.locationName = article.locationName
				currentArticle.locationMap = article.locationMap
				currentArticle.language = article.language
				currentArticle = articleRepository.saveAndFlush(currentArticle)
			}
			activityLogService.save(ActivityLogDto(user, null, currentArticle, null,
							"success update on article " + article.id, null))
		} catch (e: Exception) {
			activityLogService.save(ActivityLogDto(user, null, currentArticle, null,
							"failed update on article " + article.id, e))
		}
		return currentArticle
	}

	override fun publishUnPublish(articleDto: ArticleDto) {
		val user = userService.getUser(articleDto.userId)
		var currentArticle: Article? = null

		try {
			if (findArticleById(articleDto.id).also { currentArticle = it } != null) {
				articleRepository.deleteById(articleDto.id)
				activityLogService.save(ActivityLogDto(user, null, currentArticle, null,
								"success delete existing article " + articleDto.id, null))
			}
		} catch (e: Exception) {
			activityLogService.save(ActivityLogDto(user, null, currentArticle, null,
							"failed delete existing article " + articleDto.id, e))
		}
	}

}