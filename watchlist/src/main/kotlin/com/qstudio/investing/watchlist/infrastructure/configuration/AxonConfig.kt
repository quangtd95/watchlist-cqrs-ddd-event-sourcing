package com.qstudio.investing.watchlist.infrastructure.configuration

import com.thoughtworks.xstream.XStream
import org.axonframework.common.jdbc.ConnectionProvider
import org.axonframework.spring.jdbc.SpringDataSourceConnectionProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class AxonConfig {
    @Bean
    fun xStream() = XStream().apply {
        allowTypesByWildcard(arrayOf("com.qstudio.**"))
    }

    //TODO: use the same data source for commands and queries is not the best practice
    @Qualifier("axonDataSource")
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dbcp2")
    fun axonDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Qualifier("axonConnectionProvider")
    @Bean
    fun axonConnectionProvider(@Qualifier("axonDataSource") dataSource: DataSource): ConnectionProvider {
        return SpringDataSourceConnectionProvider(dataSource)
    }
}