package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
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
	val id: String,

	@Column(name = "province_name", columnDefinition = "varchar(100)", nullable = false)
	val provinceName: String,
	
	@Column(name = "address", columnDefinition = "varchar(150)", nullable = false)
	val address: String,
	
	@Lob
	val logo: ByteArray
)