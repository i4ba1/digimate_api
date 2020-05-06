package id.knt.digimate.interfaces

import id.knt.digimate.dto.ArticleDto
import id.knt.digimate.models.Article
import org.springframework.data.domain.Page

interface IArticleService {
	fun save(article: ArticleDto): Article?
	fun findArticleById(id: String): Article?
	fun findArticleByUserId(userId: String): List<Article>?
	fun findArticleByUser(lang: String): MutableMap<String, List<ArticleDto>>?
	fun findAllArticle(pageNo: Int): Page<Article>?
	fun update(article: ArticleDto): Article?
	fun publishUnPublish(articleDto: ArticleDto)
}