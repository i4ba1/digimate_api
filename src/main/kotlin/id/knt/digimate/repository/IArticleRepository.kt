package id.knt.digimate.repository

import org.springframework.data.jpa.repository.JpaRepository
import id.knt.digimate.models.Article
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query


@Repository(value = "articleRepository") interface IArticleRepository : JpaRepository<Article, String> {
	
	@Query("select a from Article a where a.user.id = ?1")
	fun findArticleByUserId(userId: String): List<Article>;
}