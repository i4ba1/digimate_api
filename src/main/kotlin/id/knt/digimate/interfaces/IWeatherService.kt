package id.knt.digimate.interfaces

import id.knt.digimate.dto.WeatherDto

interface IWeatherService {
	fun getCurrentWeather(): WeatherDto?
}