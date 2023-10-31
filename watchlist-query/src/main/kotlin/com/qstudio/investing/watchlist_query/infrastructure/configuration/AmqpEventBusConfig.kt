package com.qstudio.investing.watchlist_query.infrastructure.configuration

import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.core.Message
import com.rabbitmq.client.Channel

@Configuration
class AmqpEventBusConfig {

    companion object {
        const val QUEUE_NAME = "q.watchlist.events"
    }

    @Bean
    fun amqpMessageSource(messageConverter: AMQPMessageConverter): SpringAMQPMessageSource {
        return object : SpringAMQPMessageSource(messageConverter) {
            @RabbitListener(queues = [QUEUE_NAME])
            override fun onMessage(message: Message?, channel: Channel?) {
                println("amqp event $message received")
                super.onMessage(message, channel)
            }
        }
    }
}