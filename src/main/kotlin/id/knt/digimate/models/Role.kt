package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role (
		@Id
		@GeneratedValue(generator = "UUID")
		@GenericGenerator(
				name = "role_id",
				strategy = "org.hibernate.id.UUIDGenerator"
		)
		@Column(name = "id", updatable = false, nullable = false)
		var id: UUID? = null,

		@Enumerated(EnumType.STRING)
		@Column(length = 20, name = "role_name")
		val roleName:ERole? = null
)