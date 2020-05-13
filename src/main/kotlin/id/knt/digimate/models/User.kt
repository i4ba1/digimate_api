package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*


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
		var id: UUID? = null,

		@Column(name = "first_name", nullable = false)
		var firstName: String = "",

		@Column(name = "last_name", nullable = false)
		var lastName: String = "",

		@Column(name = "full_name", nullable = false)
		var fullName: String = "",

		@Column(name = "email", columnDefinition = "varchar(100)", nullable = false, unique = true)
		var email: String = "",

		@Column(name = "home_phone", columnDefinition = "char(13)", nullable = false, unique = true)
		var homePhone: String = "",

		@Column(name = "mobile_phone", columnDefinition = "char(13)", nullable = false, unique = true)
		var mobilePhone: String = "",

		@Column(name = "address", columnDefinition = "varchar(150)", nullable = false)
		var address: String = "",

		@Column(name = "ktp", columnDefinition = "char(25)", nullable = false, unique = true)
		var identityNumber: String = "",

		@Column(name = "username", columnDefinition = "char(125)", nullable = false, unique = true)
		var username: String = "",

		@Column(name = "password", columnDefinition = "char(50)", nullable = false)
		var password: String = "",

		@Lob
		@Column(name = "profile_picture")
		var profilePicture: ByteArray? = null,

		@Column(name = "picture_file_name", columnDefinition = "char(100)", nullable = false)
		var pictureFileName: String = "",

		@Column(name = "picture_file_size", columnDefinition = "bigint", nullable = false)
		var pictureFileSize: Long = 0,

		@Column(name = "picture_file_content_type", columnDefinition = "char(100)", nullable = false)
		var pictureFileContentType: String = "",

		@Column(name = "profile_picture_url", columnDefinition = "text", nullable = false)
		var profilePictureUrl: String = "",

		@Column(name = "user_type", columnDefinition = "char(25)", nullable = false)
		var userType: String = "",

		@Column(name = "is_active", columnDefinition = "boolean", nullable = false)
		var isActive: Boolean = true,

		@OneToMany(mappedBy = "user",
			cascade = [CascadeType.ALL],
			orphanRemoval = true, fetch = FetchType.LAZY)
		var articles: Set<Article?> = mutableSetOf(null),

		@OneToMany(mappedBy = "user",
				cascade = [CascadeType.ALL],
				orphanRemoval = true, fetch = FetchType.LAZY)
		var media: Set<Media?> = mutableSetOf(null),

		@OneToMany(mappedBy = "user",
				cascade = [CascadeType.ALL],
				orphanRemoval = true, fetch = FetchType.LAZY)
		var runningText: Set<RunningText?> = mutableSetOf(null),

		@OneToMany(mappedBy = "user",
				cascade = [CascadeType.ALL],
				orphanRemoval = true, fetch = FetchType.LAZY)
		var roles: Set<Role?> = mutableSetOf(null)
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as User

		if (profilePicture != null) {
			if (other.profilePicture == null) return false
			if (!profilePicture!!.contentEquals(other.profilePicture!!)) return false
		} else if (other.profilePicture != null) return false

		return true
	}

	override fun hashCode(): Int {
		return profilePicture?.contentHashCode() ?: 0
	}
}