//package com.qstudio.investing.watchlist.infrastructure.configuration
//
//import org.axonframework.common.jdbc.ConnectionProvider
//import org.axonframework.common.transaction.TransactionManager
//import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
//import org.axonframework.eventsourcing.eventstore.EventStorageEngine
//import org.axonframework.eventsourcing.eventstore.EventStore
//import org.axonframework.eventsourcing.eventstore.jdbc.EventTableFactory
//import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine
//import org.axonframework.serialization.Serializer
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//
//@Configuration
//class AxonCommandConfig {
//
//
//    @Bean
//    fun eventStore(storageEngine: EventStorageEngine): EventStore {
//        return EmbeddedEventStore.builder()
//            .storageEngine(storageEngine)
//            .build()
//    }
//
//    @Bean
//    fun storageEngine(
//        serializer: Serializer?,
//        connectionProvider: ConnectionProvider?,
//        @Qualifier("eventSerializer") eventSerializer: Serializer?,
//        transactionManager: TransactionManager?,
//        tableFactory: EventTableFactory?
//    ): EventStorageEngine {
//        val storageEngine = JdbcEventStorageEngine.builder()
//            .snapshotSerializer(serializer)
//            .connectionProvider(connectionProvider!!)
//            .eventSerializer(eventSerializer)
//            .transactionManager(transactionManager)
//            .build()
//        storageEngine.createSchema(tableFactory)
//        return storageEngine
//    }
//}