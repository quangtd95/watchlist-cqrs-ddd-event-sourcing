package com.qstudio.investing.watchlist_command.infrastructure.configuration

import org.axonframework.extensions.amqp.AMQPProperties
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(prefix = "app.messageBus", name = ["type"], havingValue = "amqp", matchIfMissing = false)
class AmqpMessageBusConfig(private val amqpProperties: AMQPProperties) {

    @Value("\${axon.amqp.queue}")
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
