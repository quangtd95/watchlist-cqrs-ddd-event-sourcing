package com.qstudio.investing.watchlist_query

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
@EnableR2dbcAuditing //https://medium.com/swlh/data-auditing-with-spring-data-r2dbc-5d428fc94688
class WatchlistQueryApp : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(WatchlistQueryApp::class.java, *args)
}
