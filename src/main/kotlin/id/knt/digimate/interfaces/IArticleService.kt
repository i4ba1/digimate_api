package id.knt.digimate.interfaces

import id.knt.digimate.dto.ArticleDto
import id.knt.digimate.models.Article

interface IArticleService {
	fun save(article: ArticleDto): Article?
	fun findArticleById(id: String): Article?
	fun findArticleByUserId(userId: String): List<Article>?
	fun findAllArticle(): List<Article>?
	fun update(article: ArticleDto): Article?
	fun publishUnPublish(articleDto: ArticleDto)
}