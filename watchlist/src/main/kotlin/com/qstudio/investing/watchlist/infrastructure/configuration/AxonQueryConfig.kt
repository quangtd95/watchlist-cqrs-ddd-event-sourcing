package com.qstudio.investing.watchlist.infrastructure.configuration

import com.thoughtworks.xstream.XStream
import org.axonframework.common.jdbc.ConnectionProvider
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.JdbcTokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.PostgresTokenTableFactory
import org.axonframework.eventhandling.tokenstore.jdbc.TokenSchema
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.axonframework.spring.jdbc.SpringDataSourceConnectionProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class AxonQueryConfig {
    @Bean
    fun tokenStore(
        @Qualifier("tokenStoreConnectionProvider") connectionProvider: ConnectionProvider
    ): TokenStore {
        val jdbcTokenStore = JdbcTokenStore.builder()
            .connectionProvider(connectionProvider)
            .serializer(JacksonSerializer.defaultSerializer())
            .schema(TokenSchema())
            .build()
        jdbcTokenStore.createSchema(PostgresTokenTableFactory.INSTANCE)

        return jdbcTokenStore
    }

    @Qualifier("tokenStoreConnectionProvider")
    @Bean
    fun connectionProvider(@Qualifier("tokenStoreDataSource") dataSource: DataSource): ConnectionProvider {
        return SpringDataSourceConnectionProvider(dataSource)
    }

    @Qualifier("tokenStoreDataSource")
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dbcp2")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}