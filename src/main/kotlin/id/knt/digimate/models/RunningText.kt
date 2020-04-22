package id.knt.digimate.models

import java.io.Serializable
import java.time.LocalDateTime

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
import id.knt.digimate.models.User



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
	val id:String,
	
	@Column(name = "title", columnDefinition = "varchar(100)")
	val title: String,
	
	@Column(name = "content", columnDefinition = "text")
	val content: String,
	
	@Temporal(TemporalType.TIMESTAMP)
	val createdAt: LocalDateTime,
	
	@Temporal(TemporalType.TIMESTAMP)
	val udpatedAt: LocalDateTime,
	
	@Column(name = "is_published", columnDefinition = "boolean", nullable = false)
	val isPublished:Boolean = false,
	
	@ManyToOne()
	@JoinColumn(name="user_id", nullable = false)
	val user: User

)
