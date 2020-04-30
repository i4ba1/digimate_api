package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Lob

@Entity(name = "Profile")
@Table(name = "profile")
data class Profile(

				@Id
				@GeneratedValue(generator = "UUID")
				@GenericGenerator(
								name = "UUID",
								strategy = "org.hibernate.id.UUIDGenerator"
				)
				var id: UUID? = null,

				@Column(name = "province_name", columnDefinition = "varchar(100)", nullable = false)
				var provinceName: String = "",

				@Column(name = "address", columnDefinition = "varchar(150)", nullable = false)
				var address: String = "",

				@Lob
				var logo: ByteArray? = null,

				@Column(name = "language", columnDefinition = "char(3)", nullable = false)
				var language: String = "",

				@Column(name = "logo_url", columnDefinition = "text", nullable = false)
				var logoUrl: String = ""
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Profile

		if (logo != null) {
			if (other.logo == null) return false
			if (!logo!!.contentEquals(other.logo!!)) return false
		} else if (other.logo != null) return false

		return true
	}

	override fun hashCode(): Int {
		return logo?.contentHashCode() ?: 0
	}
}