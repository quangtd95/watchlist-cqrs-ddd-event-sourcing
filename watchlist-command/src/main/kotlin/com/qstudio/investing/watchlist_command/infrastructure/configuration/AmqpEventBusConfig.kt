package com.qstudio.investing.watchlist_command.infrastructure.configuration

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpEventBusConfig {

    companion object {
        const val TOPIC_EXCHANGE_NAME = "ex.watchlist.events"

        const val QUEUE_NAME = "q.watchlist.events"
    }

    @Qualifier("watchlistEventsExchange")
    @Bean
    fun watchlistEventsExchange(): TopicExchange {
        return TopicExchange(TOPIC_EXCHANGE_NAME)
    }

    @Qualifier("eventsQueue")
    @Bean
    fun eventsQueue(): Queue {
        return Queue(QUEUE_NAME, true)
    }

    @Bean
    fun eventsBinding(
        @Qualifier("eventsQueue") queue: Queue,
        @Qualifier("watchlistEventsExchange") exchange: TopicExchange
    ): Binding {
        return BindingBuilder
            .bind(queue)
            .to(exchange)
            .with("#")
    }
}