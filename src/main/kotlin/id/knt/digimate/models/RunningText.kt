package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * @author nizar
 *
 */
@Entity(name = "RunningText")
@Table(name = "running_text")
data class RunningText (

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
	var content: String? = "",
	
	@Temporal(TemporalType.TIMESTAMP)
	var createdAt: Date? = null,
	
	@Temporal(TemporalType.TIMESTAMP)
	var updatedAt: Date? = null,
	
	@Column(name = "is_published", columnDefinition = "boolean", nullable = false)
	var isPublished:Boolean = false,

	@Column(name = "language", columnDefinition = "char(3)", nullable = false)
	var language: String = "",
	
	@ManyToOne(fetch = FetchType.LAZY)
	var user: User? =null

)
