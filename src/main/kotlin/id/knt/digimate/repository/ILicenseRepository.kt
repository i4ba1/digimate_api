package id.knt.digimate.repository

import id.knt.digimate.models.License
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ILicenseRepository : MongoRepository<License, String> {

	@Query("select l from License l where l.serialNumber =?1")
	fun findLicenseBySerialNumberAndMacAddr(serialNumber:String): License?
}