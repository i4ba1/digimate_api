package id.knt.digimate.dto

data class PlayerDto(
	var articleDto: MutableMap<String, List<ArticleDto>>?,
	var image: MutableMap<String, List<GetMediaDto>>?,
	var video: MutableMap<String, List<GetMediaDto>>?,
	var runningTextDto: MutableMap<String, List<RunningTextDto>>?,
	var profileDto: MutableMap<String, GetProfileDto>?
)