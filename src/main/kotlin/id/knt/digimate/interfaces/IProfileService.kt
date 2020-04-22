package id.knt.digimate.interfaces

import id.knt.digimate.models.Profile

interface IProfileService {
	fun save(newProfile: Profile):Int
	fun findProfileById(id: String):Profile
	fun findProfileByUserId(userId:String):List<Profile>
	fun findAllProfile():List<Profile>;
	fun update(currentProfile: Profile):Int
	fun delete(id:String):Int
}