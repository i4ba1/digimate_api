package id.knt.digimate.interfaces

import id.knt.digimate.dto.ArticleDto
import id.knt.digimate.models.Article

interface IArticleService {
	fun save(article: ArticleDto): Int
	fun findArticleById(id: String): Article
	fun findArticleByUserId(userId: String): List<Article>
	fun findAllArticle(): List<Article>
	fun update(currentArticle: Article)
	fun delete(articleDto: ArticleDto)
}