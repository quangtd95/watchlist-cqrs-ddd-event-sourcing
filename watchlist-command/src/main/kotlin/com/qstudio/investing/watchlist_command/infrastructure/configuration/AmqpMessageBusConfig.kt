package com.qstudio.investing.watchlist_command.infrastructure.configuration

import org.axonframework.extensions.amqp.AMQPProperties
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpMessageBusConfig(private val amqpProperties: AMQPProperties) {

    @Value("#{axon.ampq.queue}")
    private lateinit var queue: String

    @Bean
    fun watchlistEventsExchange(): TopicExchange {
        return TopicExchange(amqpProperties.exchange)
    }

    @Bean
    fun eventsQueue(): Queue {
        return Queue(queue, amqpProperties.isDurableMessages)
    }

    @Bean
    fun eventsBinding(
        eventsQueue: Queue,
        watchlistEventsExchange: TopicExchange
    ): Binding {
        return BindingBuilder
            .bind(eventsQueue)
            .to(watchlistEventsExchange)
            .with("#")
    }
}
