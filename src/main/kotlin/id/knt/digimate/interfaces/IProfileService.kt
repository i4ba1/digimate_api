package id.knt.digimate.interfaces

import id.knt.digimate.dto.FindProfileByIdDto
import id.knt.digimate.dto.ProfileDto
import id.knt.digimate.models.Profile

interface IProfileService {
	fun save(profile:ProfileDto): Profile?
	fun findProfileById(id: String): FindProfileByIdDto?
	fun update(profileDto: ProfileDto): Profile?
	fun delete(id:String)
}