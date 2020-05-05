package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity(name = "Media")
@Table(name = "media")
data class Media(

		@Id
		@GeneratedValue(generator = "UUID")
		@GenericGenerator(
				name = "UUID",
				strategy = "org.hibernate.id.UUIDGenerator"
		)
		var id: UUID? = null,

		@Column(name = "title", columnDefinition = "varchar(100)", nullable = false)
		var title: String = "",

		@Column(name = "description", columnDefinition = "text", nullable = false)
		var description: String = "",

		@Column(name = "file_size", columnDefinition = "int", nullable = false)
		var fileSize: Long = Long.MIN_VALUE,

		@Column(name = "mime_type", columnDefinition = "varchar(100)", nullable = false)
		var mimeType: String = "",

		@Column(name = "file_name", columnDefinition = "varchar(100)", nullable = false)
		var fileName: String = "",

		@Lob
		var fileContent: ByteArray? = null,

		@Column(name = "file_url", columnDefinition = "text", nullable = false)
		var fileUrl: String = "",

		@Column(name = "type", columnDefinition = "char(25)", nullable = false)
		var type: String = "",

		@Column(name = "youtube_url", columnDefinition = "char(25)", nullable = false)
		var youtubeUrl: String = "",

		@Temporal(TemporalType.TIMESTAMP)
		var createdAt: Date? = null,

		@Temporal(TemporalType.TIMESTAMP)
		var updatedAt: Date? = null,

		@Column(name = "is_published", columnDefinition = "boolean", nullable = false)
		var isPublished: Boolean = false,

		@Column(name = "language", columnDefinition = "char(3)", nullable = false)
		var language: String = "",

		@ManyToOne(fetch = FetchType.LAZY)
		var user: User? = null
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Media

		if (fileContent != null) {
			if (other.fileContent == null) return false
			if (!fileContent!!.contentEquals(other.fileContent!!)) return false
		} else if (other.fileContent != null) return false

		return true
	}

	override fun hashCode(): Int {
		return fileContent?.contentHashCode() ?: 0
	}
}
