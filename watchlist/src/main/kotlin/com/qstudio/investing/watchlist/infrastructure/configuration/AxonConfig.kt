package com.qstudio.investing.watchlist.infrastructure.configuration

import com.thoughtworks.xstream.XStream
import org.axonframework.common.jdbc.ConnectionProvider
import org.axonframework.common.jdbc.DataSourceConnectionProvider
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.JdbcTokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.PostgresTokenTableFactory
import org.axonframework.eventhandling.tokenstore.jdbc.TokenSchema
import org.axonframework.serialization.json.JacksonSerializer
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

    @Bean
    fun querySideTokenStore(connectionProvider: ConnectionProvider): TokenStore {
        val jdbcTokenStore = JdbcTokenStore.builder()
            .connectionProvider(connectionProvider)
            .serializer(JacksonSerializer.defaultSerializer())
            .schema(TokenSchema())
            .build()
        jdbcTokenStore.createSchema(PostgresTokenTableFactory.INSTANCE)

        return jdbcTokenStore
    }

    @Bean
    fun jdbcQueryConnectionProvider(dataSource: DataSource): ConnectionProvider {
        return DataSourceConnectionProvider(dataSource)
    }

    @Bean
    fun jdbcQueryDataSource(): DataSource {
        return DataSourceBuilder.create().url("jdbc:postgresql://localhost:3032/investing").username("postgres")
            .password("mhsatuck").driverClassName("org.postgresql.Driver").build()
    }
}