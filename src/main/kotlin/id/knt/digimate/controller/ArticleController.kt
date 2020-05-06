package id.knt.digimate.controller

import id.knt.digimate.dto.ArticleDto
import id.knt.digimate.models.Article
import id.knt.digimate.services.ArticleService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/articleManagement")
class ArticleController(private val articleService: ArticleService) {

	@PostMapping(path = ["/createNewArticle"])
	fun createNewArticle(@RequestBody newArticle: ArticleDto): ResponseEntity<Void> {
		return responseNewAndUpdateArticle(articleService.save(newArticle))
	}

	@PostMapping(path = ["/updateArticle"])
	fun updateArticle(@RequestBody existingArticle: ArticleDto): ResponseEntity<Void> {
		return responseNewAndUpdateArticle(articleService.update(existingArticle))
	}

	private fun responseNewAndUpdateArticle(article: Article?): ResponseEntity<Void> {
		return if (article != null) {
			ResponseEntity(HttpStatus.OK)
		} else ResponseEntity(HttpStatus.NOT_MODIFIED)
	}

	@GetMapping(path = ["/getArticleById/{id}"])
	private fun getArticleById(@PathVariable(name = "id") id: String): ResponseEntity<Article?>? {
		val currentArticle = articleService.findArticleById(id)
		return if (currentArticle != null) {
			ResponseEntity(currentArticle, HttpStatus.OK)
		} else ResponseEntity(HttpStatus.NOT_FOUND)
	}

	@GetMapping(path = ["/getAllArticle/{pageNo}"])
	private fun getAllArticle(@PathVariable pageNo: Int): ResponseEntity<Page<Article>>? {
		val articlePage = articleService.findAllArticle(pageNo)
		if (articlePage != null) {
			return ResponseEntity(articlePage, HttpStatus.OK)
		}
		return ResponseEntity(HttpStatus.NOT_FOUND)
	}

	@GetMapping(path = ["/getArticleByUserId/{id}"])
	private fun getArticleByUserId(@PathVariable(name = "id") userId: String): ResponseEntity<List<Article>>? {
		return responseForListAllArticleAndUserArticle(articleService.findArticleByUserId(userId))
	}

	private fun responseForListAllArticleAndUserArticle(articles: List<Article>?): ResponseEntity<List<Article>>? {
		return if (articles == null || articles.isEmpty()) {
			ResponseEntity(HttpStatus.OK)
		} else ResponseEntity(HttpStatus.NOT_FOUND)
	}
}