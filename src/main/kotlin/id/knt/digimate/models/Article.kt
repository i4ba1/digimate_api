package id.knt.digimate.modles

import java.util.UUID
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
import javax.persistence.Entity
import javax.persistence.Table

@Entity(name = "Article")
@Table(name = "article")
data class Article(
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
   )
   @Column(name = "id", updatable = false, nullable = false)
	val id: UUID,
   
   @Column(name = "title", columnDefinition = "varchar(100)")
	val title: String,
	
	@Column(name = "content", columnDefinition = "text")
	val content: String,
	
	@Temporal(TemporalType.TIMESTAMP)
	val createdAt: LocalDateTime,
	
	@Temporal(TemporalType.TIMESTAMP)
	val udpatedAt: LocalDateTime,

	@Column(name = "location_name", columnDefinition = "varchar(150)")
	val locationName:String,

	@Column(name = "location_map", columnDefinition = "text")
	val locationMap: String,
	
	@Column(name = "is_published", columnDefinition = "boolean", nullable = false)
	val isPublished: Boolean = false,
	
	@ManyToOne()
	@JoinColumn(name="user_id", nullable = false)
	val user: User
)