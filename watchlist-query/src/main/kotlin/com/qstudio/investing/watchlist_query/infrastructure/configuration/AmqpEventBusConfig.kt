package com.qstudio.investing.watchlist_query.infrastructure.configuration

import com.rabbitmq.client.Channel
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpEventBusConfig {

    @Bean
    fun amqpMessageSource(messageConverter: AMQPMessageConverter): SpringAMQPMessageSource {
        return object : SpringAMQPMessageSource(messageConverter) {
            @RabbitListener(queues = ["#{axon.amqp.queue}"])
            override fun onMessage(message: Message?, channel: Channel?) {
                println("amqp event $message received")
                super.onMessage(message, channel)
            }
        }
    }
}
