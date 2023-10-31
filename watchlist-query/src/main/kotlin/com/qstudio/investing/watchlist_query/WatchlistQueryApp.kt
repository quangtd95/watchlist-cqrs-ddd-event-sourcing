package com.qstudio.investing.watchlist_query

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
class WatchlistQueryApp : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(WatchlistQueryApp::class.java, *args)
}
