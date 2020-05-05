package id.knt.digimate.repository

import org.springframework.data.jpa.repository.JpaRepository
import id.knt.digimate.models.Article
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query


@Repository
interface IArticleRepository : JpaRepository<Article, String> {
	
	@Query("select a from Article a where a.user.id = ?1")
	fun findArticleByUserId(userId: String): List<Article>?

	@Query(value = "select a from Article a join fetch a.user where a.language = ?1 and a.isPublished = true")
	fun findArticleByUser(lang: String): List<Article>?
}