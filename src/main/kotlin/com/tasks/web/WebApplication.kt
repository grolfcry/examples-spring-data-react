package com.tasks.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry

@EnableRetry
@SpringBootApplication
class WebApplication

fun main(args: Array<String>) {
	runApplication<WebApplication>(*args)
}
