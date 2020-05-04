package id.knt.digimate.dto

data class WeatherDto(
		var issue: IssueDto? = null,
		var area: AreaDto? = null,
		var param: List<ParameterDto>? = null
)