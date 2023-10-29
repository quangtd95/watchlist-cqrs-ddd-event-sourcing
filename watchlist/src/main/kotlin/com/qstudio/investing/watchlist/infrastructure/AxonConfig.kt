package com.qstudio.investing.watchlist.infrastructure

import com.thoughtworks.xstream.XStream
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig {
    @Bean
    fun xStream() = XStream().apply {
        allowTypesByWildcard(arrayOf("com.qstudio.**"))
    }
}