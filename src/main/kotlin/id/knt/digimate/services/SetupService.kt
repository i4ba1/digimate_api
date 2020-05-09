package id.knt.digimate.services

import id.knt.digimate.interfaces.ISetupService
import id.knt.digimate.models.License
import id.knt.digimate.repository.ILicenseRepository
import org.springframework.stereotype.Service
import oshi.SystemInfo
import oshi.hardware.HardwareAbstractionLayer
import java.util.*

@Service
class SetupService(private val licenseQuery: ILicenseRepository) : ISetupService {

	override fun findLicenseBySerialNumber(serialNumber: String): License? {
		return licenseQuery.findLicenseBySerialNumberAndMacAddr(serialNumber)
	}

	override fun activateNewSerialNumber(serialNumber: String): License? {
		val findLicenseBySerialNumberAndMacAddr = licenseQuery.findLicenseBySerialNumberAndMacAddr(serialNumber)
		val license:License?
		if (findLicenseBySerialNumberAndMacAddr == null) {
			license = License()
			license.serialNumber  = serialNumber
			license.macAddr = computerSN()
			license.isUsed = true
			license.limit = license.limit++
			license.createdAt = Date()
			license.isUsed = true
		}else{
			license = findLicenseBySerialNumberAndMacAddr
			license.limit = license.limit++
		}

		return licenseQuery.save(license)
	}

	override fun findAll(): List<License> {
		return licenseQuery.findAll()
	}

	private fun computerSN(): String{
		val si = SystemInfo()
		val hal: HardwareAbstractionLayer = si.hardware
		return hal.computerSystem.serialNumber
	}
}