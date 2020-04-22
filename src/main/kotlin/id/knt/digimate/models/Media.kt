package id.knt.digimate.models

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Lob
import javax.persistence.TemporalType
import javax.persistence.Temporal
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn

@Entity(name = "Media")
@Table(name = "media")
data class Media (
		
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	val id: String,

	@Column(name = "title", columnDefinition = "varchar(100)", nullable = false)
	val title:String,
	
	@Column(name = "description", columnDefinition = "text", nullable = false)
	val description: String,
	
	@Column(name = "file_size", columnDefinition = "int", nullable = false)
	val fileSize: Long,
	
	@Column(name = "mime_type", columnDefinition = "varchar(100)", nullable = false)
	val mimeType: String,
	
	@Column(name = "file_name", columnDefinition = "varchar(100)", nullable = false)
	val fileName: String,

	@Lob
	val fileContent: ByteArray,
	
	@Column(name = "type", columnDefinition = "char(25)", nullable = false)
	val type: String,
	
	@Column(name = "type", columnDefinition = "char(25)", nullable = false)
	val videoUrl: String,
	
	@Temporal(TemporalType.TIMESTAMP)
	val createdAt: LocalDateTime,

	@Temporal(TemporalType.TIMESTAMP)
	val updatedAt: LocalDateTime,
	
	@Column(name = "is_published", columnDefinition = "boolean", nullable = false)
	val isPublished: Boolean = false,
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	val user: User
)
