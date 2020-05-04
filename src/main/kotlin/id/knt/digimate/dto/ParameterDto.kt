package id.knt.digimate.dto

data class ParameterDto(
		var id: String = "",
		var description: String = "",
		var type: String = "",
		var listOfTimeRange: List<TimeRangeDto>? = null
)