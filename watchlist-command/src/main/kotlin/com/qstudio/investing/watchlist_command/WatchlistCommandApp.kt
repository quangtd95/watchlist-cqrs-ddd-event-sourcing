package com.qstudio.investing.watchlist_command

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class WatchlistCommandApp : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(WatchlistCommandApp::class.java, *args)
}
