package id.knt.digimate.services

import id.knt.digimate.dto.ArticleDto
import id.knt.digimate.interfaces.IArticleService
import id.knt.digimate.models.Article
import id.knt.digimate.repository.IArticleRepository
import org.springframework.stereotype.Service
import java.util.*


@Service("articleService")
class ArticleService(private val userService: UserService, private val articleRepository: IArticleRepository) : IArticleService  {

	override fun save(article: ArticleDto): Int {
		val user = userService.findUserById(article.userId)
		val newArticle = Article()
		newArticle.title = article.title
		newArticle.content = article.content
		newArticle.createdAt = Date()
		newArticle.locationName = article.locationName
		newArticle.locationMap = article.locationMap
		newArticle.user = user

		newArticle = arti
	}

	override fun findArticleById(id: String): Article {
		TODO("Not yet implemented")
	}

	override fun findArticleByUserId(userId: String): List<Article> {
		TODO("Not yet implemented")
	}

	override fun findAllArticle(): List<Article> {
		TODO("Not yet implemented")
	}

	override fun update(currentArticle: Article) {
		TODO("Not yet implemented")
	}

	override fun delete(articleDto: ArticleDto) {
		TODO("Not yet implemented")
	}

}