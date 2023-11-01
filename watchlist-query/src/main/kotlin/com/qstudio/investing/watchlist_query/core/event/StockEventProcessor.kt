package com.qstudio.investing.watchlist_query.core.event

import com.qstudio.investing.watchlist_query.core.model.StockView
import com.qstudio.investing.watchlist_query.core.repository.StockViewRepository
import com.qstudio.investing.watchlist_shared_kernel.event.type.StockCreatedEvent
import kotlinx.coroutines.runBlocking
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@ProcessingGroup("stockEvents")
@Component
class StockEventProcessor(
    private val stockViewRepository: StockViewRepository
) {
    private final val logger = LoggerFactory.getLogger(this::class.java)

    @EventHandler
    fun on(event: StockCreatedEvent) {
        runBlocking {
            with(event) {
                logger.info("on @EventHandler at query side: StockCreatedEvent")
                stockViewRepository.save(StockView(this.symbol, this.name))
            }

        }
    }

}