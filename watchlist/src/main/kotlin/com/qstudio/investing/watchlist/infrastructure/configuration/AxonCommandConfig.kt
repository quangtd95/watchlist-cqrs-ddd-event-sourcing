package com.qstudio.investing.watchlist.infrastructure.configuration

import org.axonframework.common.jdbc.ConnectionProvider
import org.axonframework.common.transaction.TransactionManager
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine
import org.axonframework.eventsourcing.eventstore.jdbc.PostgresEventTableFactory
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AxonCommandConfig {


    @Bean
    fun eventStore(storageEngine: EventStorageEngine): EventStore {
        return EmbeddedEventStore.builder()
            .storageEngine(storageEngine)
            .build()
    }

    @Bean
    fun storageEngine(
        @Qualifier("axonConnectionProvider") connectionProvider: ConnectionProvider?,
        transactionManager: TransactionManager?,
    ): EventStorageEngine {
        val storageEngine = JdbcEventStorageEngine.builder()
            .connectionProvider(connectionProvider!!)
            .eventSerializer(JacksonSerializer.defaultSerializer())
            .snapshotSerializer(JacksonSerializer.defaultSerializer())
            .transactionManager(transactionManager)
            .build()

        storageEngine.createSchema(PostgresEventTableFactory.INSTANCE)
        return storageEngine
    }

}