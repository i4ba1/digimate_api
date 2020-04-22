package id.knt.digimate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
open class DigimateApplication {

	fun main(args: Array<String>) {
		runApplication<DigimateApplication>(*args)
	}
}
