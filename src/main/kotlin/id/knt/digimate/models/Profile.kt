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
data class Profile (
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	val id: UUID? = null,

	@Column(name = "province_name", columnDefinition = "varchar(100)", nullable = false)
	val provinceName: String = "",
	
	@Column(name = "address", columnDefinition = "varchar(150)", nullable = false)
	val address: String = "",
	
	@Lob
	val logo: ByteArray? = null
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Profile

		if (logo != null) {
			if (other.logo == null) return false
			if (!logo.contentEquals(other.logo)) return false
		} else if (other.logo != null) return false

		return true
	}

	override fun hashCode(): Int {
		return logo?.contentHashCode() ?: 0
	}
}