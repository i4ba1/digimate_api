package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import id.knt.digimate.models.User
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity(name = "Article")
@Table(name = "article")
data class Article (

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
   )
   @Column(name = "id", updatable = false, nullable = false)
	var id: UUID? = null,

   @Column(name = "title", columnDefinition = "varchar(100)")
	var title: String = "",

	@Column(name = "content", columnDefinition = "text")
	var content: String = "",

	@Temporal(TemporalType.TIMESTAMP)
	var createdAt: Date? = null,

	@Temporal(TemporalType.TIMESTAMP)
	var updatedAt: Date? = null,

	@Column(name = "location_name", columnDefinition = "varchar(150)")
	var locationName:String = "",

	@Column(name = "location_map", columnDefinition = "text")
	var locationMap: String = "",

	@Column(name = "is_published", columnDefinition = "boolean", nullable = false)
	var isPublished: Boolean = false,

	@Column(name = "language", columnDefinition = "char(3)", nullable = false)
	var language: String = "",

	@ManyToOne(fetch = FetchType.LAZY)
	var user: User? = null
)