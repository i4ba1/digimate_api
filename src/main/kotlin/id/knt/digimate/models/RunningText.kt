package id.knt.digimate.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import org.hibernate.annotations.GenericGenerator
import java.util.*

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
	
	@ManyToOne()
	@JoinColumn(name="user_id", nullable = false)
	var user: User? =null

)
