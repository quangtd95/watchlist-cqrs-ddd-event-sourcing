package com.qstudio.investing.watchlist.infrastructure.configuration

import org.axonframework.common.jdbc.ConnectionProvider
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.JdbcTokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.PostgresTokenTableFactory
import org.axonframework.eventhandling.tokenstore.jdbc.TokenSchema
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonQueryConfig {
    @Bean
    fun tokenStore(
        @Qualifier("axonConnectionProvider") connectionProvider: ConnectionProvider
    ): TokenStore {
        val jdbcTokenStore = JdbcTokenStore.builder()
            .connectionProvider(connectionProvider)
            .serializer(JacksonSerializer.defaultSerializer())
            .schema(TokenSchema())
            .build()
        jdbcTokenStore.createSchema(PostgresTokenTableFactory.INSTANCE)

        return jdbcTokenStore
    }

}