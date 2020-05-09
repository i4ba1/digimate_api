package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "license")
data class License (
		@Id
		@GeneratedValue(generator = "UUID")
		@GenericGenerator(
				name = "license_uuid",
				strategy = "org.hibernate.id.UUIDGenerator"
		)
		@Column(name = "id", updatable = false, nullable = false)
		var id: UUID? = null,

		@Column(name = "serial_number", columnDefinition = "char(20)", unique = true)
		var serialNumber: String = "",

		@Column(name = "physical_address", columnDefinition = "char(20)", unique = true)
		var macAddr: String = "",

		@Column(name = "is_used", columnDefinition = "boolean")
		var limit: Int = 0,

		@Column(name = "is_used", columnDefinition = "boolean")
		var isUsed: Boolean = false,

		@Temporal(TemporalType.TIMESTAMP)
		var createdAt: Date? = null
)