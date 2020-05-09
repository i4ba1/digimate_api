package id.knt.digimate.interfaces

import id.knt.digimate.models.License

interface ISetupService {
	fun findLicenseBySerialNumber(serialNumber: String): License?
	fun activateNewSerialNumber(serialNumber: String): License?
	fun findAll(): List<License>
}