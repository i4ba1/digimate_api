package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import id.knt.digimate.models.Article


@Entity
@Table(name = "user")
data class User(

				@Id
				@GeneratedValue(generator = "UUID")
				@GenericGenerator(
								name = "UUID",
								strategy = "org.hibernate.id.UUIDGenerator"
				)
				@Column(name = "id", updatable = false, nullable = false)
				val id: String = "",

				@Column(name = "first_name", nullable = false)
				val firstName: String = "",

				@Column(name = "last_name", nullable = false)
				val lastName: String = "",

				@Column(name = "full_name", nullable = false)
				val fullName: String = "",

				@Column(name = "email", columnDefinition = "varchar(100)", nullable = false, unique = true)
				val email: String = "",

				@Column(name = "home_phone", columnDefinition = "char(13)", nullable = false, unique = true)
				val homePhone: String = "",

				@Column(name = "mobile_phone", columnDefinition = "char(13)", nullable = false, unique = true)
				val mobilePhone: String = "",

				@Column(name = "address", columnDefinition = "varchar(150)", nullable = false)
				val address: String = "",

				@Column(name = "ktp", columnDefinition = "char(25)", nullable = false, unique = true)
				val identityNumber: String = "",

				@Column(name = "username", columnDefinition = "char(125)", nullable = false, unique = true)
				val username: String = "",

				@Column(name = "password", columnDefinition = "char(50)", nullable = false)
				val password: String = "",

				@Lob
				@Column(name = "profile_picture")
				val profilePicture: ByteArray? = null,

				@Column(name = "picture_file_name", columnDefinition = "char(100)", nullable = false)
				val pictureFileName: String = "",

				@Column(name = "picture_file_size", columnDefinition = "bigint", nullable = false)
				val pictureFileSize: Long = 0,

				@Column(name = "picture_file_content_type", columnDefinition = "char(100)", nullable = false)
				val pictureFileContentType: String = "",

				@Column(name = "user_type", columnDefinition = "char(25)", nullable = false)
				val userType: String = "",

				@Column(name = "is_active", columnDefinition = "boolean", nullable = false)
				val isActive: Boolean = true,

				@OneToMany(mappedBy = "user")
				val articles: Set<Article?> = setOf(null),

				@OneToMany(mappedBy = "user")
				val media: Set<Media?> = setOf(null),

				@OneToMany(mappedBy = "user")
				val runningText: Set<RunningText?> = setOf(null)
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as User

		if (profilePicture != null) {
			if (other.profilePicture == null) return false
			if (!profilePicture.contentEquals(other.profilePicture)) return false
		} else if (other.profilePicture != null) return false

		return true
	}

	override fun hashCode(): Int {
		return profilePicture?.contentHashCode() ?: 0
	}
}