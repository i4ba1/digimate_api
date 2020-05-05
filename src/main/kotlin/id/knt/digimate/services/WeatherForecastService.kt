package id.knt.digimate.services

import id.knt.digimate.dto.*
import id.knt.digimate.interfaces.IWeatherService
import org.springframework.stereotype.Service
import org.w3c.dom.Element
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import java.net.URL
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

@Service("weatherForecastService")
class WeatherForecastService : IWeatherService {
	override fun getCurrentWeather(): WeatherDto? {

		// fake end point that returns XML response
		try {
			val weatherDto = WeatherDto()
			val url = URL("https://data.bmkg.go.id/datamkg/MEWS/DigitalForecast/DigitalForecast-Indonesia.xml")
			val province = "Aceh"
			val factory = DocumentBuilderFactory.newInstance()
			val builder = factory.newDocumentBuilder()
			val stream = url.openStream()
			val document = builder.parse(stream)

			val now = Calendar.getInstance()
			now.time = Date()
			val currentDate: String = (Calendar.YEAR+Calendar.MONDAY+Calendar.DAY_OF_MONTH).toString()
			val hourOfDay = now[Calendar.HOUR_OF_DAY]
			println("Hour of day: $hourOfDay")

			// normalize XML response
			document.documentElement.normalize()
			//System.out.println("Root element :" + document.getDocumentElement().getNodeName());
			val nodeList = document.getElementsByTagName("forecast")
			/*
			 * Loop for tag <forecast></forecast>
			 */for (i in 0 until nodeList.length) {
				//System.out.println(nodeList.item(i).getAttributes().item(0));
				val forecastNode = nodeList.item(i)
				if (forecastNode.nodeType == Node.ELEMENT_NODE) {
					val elem = forecastNode as Element

					//List of tag <issue></issue>
					val issues = elem.getElementsByTagName("issue")
					for(m: Int in 0 until issues.length){
						val mapIssue:NamedNodeMap = issues.item(m).attributes
						val timestamp =  mapIssue.getNamedItem("timestamp")
						val year = mapIssue.getNamedItem("year")
						val month = mapIssue.getNamedItem("month")
						val day = mapIssue.getNamedItem("day")
						val hour = mapIssue.getNamedItem("hour")
						val minute = mapIssue.getNamedItem("minute")
						val second = mapIssue.getNamedItem("second")

						val issueDto = IssueDto(timestamp.nodeValue.toLong(), year.nodeValue.toInt(), month
							  .nodeValue.toInt(), day.nodeValue.toInt(), hour.nodeValue.toInt(),
							  minute.nodeValue.toInt(), second.nodeValue.toInt())
						weatherDto.issue = issueDto
					}

					val areaList = elem.getElementsByTagName("area")
					/*
					  Loop for tag <area></area>
					 */
					for (j in 0 until areaList.length) {

						val map = areaList.item(j).attributes
						val latitude = map.getNamedItem("latitude").textContent
						val longitude = map.getNamedItem("longitude").textContent
						val coordinate = map.getNamedItem("coordinate").textContent
						val domain = map.getNamedItem("domain").textContent
						val type = map.getNamedItem("type").textContent
						val description = map.getNamedItem("description").textContent

						val area = AreaDto(map.getNamedItem("id").textContent, latitude, longitude, coordinate, type,
							  description, domain)
						weatherDto.area = area

						//System.out.println("coordinate "+coordinate+" -domain "+domain);
						if (province.compareTo(domain) == 0) {
							val areaNode = areaList.item(j)
							if (areaNode.nodeType == Node.ELEMENT_NODE) {
								val elementArea = areaNode as Element
								val parameterList = elementArea.getElementsByTagName("parameter")
								val listOfParameter: MutableList<ParameterDto> = mutableListOf()

								/*
								 * Loop for tag <parameter></parameter>
								 */
								for (k in 0 until parameterList.length) {
									val mapParameter = parameterList.item(k).attributes

									val parameterDto = ParameterDto()
									parameterDto.id = mapParameter.getNamedItem("id").textContent
									parameterDto.description = mapParameter.getNamedItem("description").textContent
									parameterDto.type = mapParameter.getNamedItem("type").textContent
									val parameterNode = parameterList.item(k)
									val listTimeRange: MutableList<TimeRangeDto> = mutableListOf()

									if (parameterNode.nodeType == Node.ELEMENT_NODE) {
										val elementParameter = parameterNode as Element
										val timeRangeList = elementParameter.getElementsByTagName("timerange")

										/*
										 * Loop for tag <timerange></timerange>
										 */
										for (l in 0 until timeRangeList.length - 1) {
											val mapTimeRange = timeRangeList.item(l).attributes
											val timeRangeDto = TimeRangeDto()
											timeRangeDto.type = mapTimeRange.getNamedItem("type").textContent
											timeRangeDto.datetime = mapTimeRange.getNamedItem("datetime").textContent

											/**
											 * For hourly
											 */
											if (timeRangeDto.type.compareTo("hourly") == 0) {
												val hourly = mapTimeRange.getNamedItem(
														"h").textContent
												println(hourly)
												if (hourOfDay % 6 == 0) {
													timeRangeDto.h = hourly

													val nodeTimeRange = timeRangeList.item(l)
													val elementTime = nodeTimeRange as Element
													timeRangeDto.value = elementTime.getElementsByTagName("value").item(0).textContent
												}else {
													val x: Int = hourOfDay / 6
													val s: Int = hourOfDay - x
													if (s == hourly.toInt()) {
														val nodeTimeRange = timeRangeList.item(l)
														val elementTime = nodeTimeRange as Element
														timeRangeDto.value = elementTime.getElementsByTagName("value").item(0).textContent
													}
												}
											}

											/**
											 * For daily
											 */
											if (timeRangeDto.type.compareTo("daily") == 0) {
												val daily = mapTimeRange.getNamedItem(
													  "daily").textContent

												if (currentDate == daily) {
													timeRangeDto.daily = daily

													val nodeTimeRange = timeRangeList.item(l)
													val elementTime = nodeTimeRange as Element
													timeRangeDto.value = elementTime.getElementsByTagName("value").item(0).textContent
												}
											}

											listTimeRange.add(timeRangeDto)

										}
									}
									parameterDto.listOfTimeRange = listTimeRange
									listOfParameter.add(parameterDto)
									weatherDto.param = listOfParameter
								}
							}

							break
						}
					}

				}
			}
		} catch (e: Exception) {
			e.printStackTrace()
		}

		return null
	}


}
