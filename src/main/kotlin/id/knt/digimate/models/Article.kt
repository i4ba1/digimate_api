package id.knt.digimate.models

import javax.persistence.Id
import javax.persistence.GeneratedValue
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Temporal
import javax.persistence.TemporalType
import java.time.LocalDateTime
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn
import id.knt.digimate.models.User
import java.sql.Timestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

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

	@ManyToOne()
	@JoinColumn(name="user_id", nullable = false)
	var user: User? = null
)