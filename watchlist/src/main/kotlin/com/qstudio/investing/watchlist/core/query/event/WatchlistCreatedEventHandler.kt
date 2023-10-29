package com.qstudio.investing.watchlist.core.query.event

import com.qstudio.investing.watchlist.core.query.model.WatchlistView
import com.qstudio.investing.watchlist.core.query.repository.WatchlistViewRepository
import com.qstudio.investing.watchlist.infrastructure.event.type.WatchlistCreatedEvent
import kotlinx.coroutines.runBlocking
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WatchlistCreatedEventHandler(
    private val watchlistViewRepository: WatchlistViewRepository
) {
    private final val logger = LoggerFactory.getLogger(WatchlistCreatedEventHandler::class.java)

    @EventHandler
    fun on(watchlistCreatedEvent: WatchlistCreatedEvent) {
        runBlocking {
            with(watchlistCreatedEvent) {
                logger.info("on @EventHandler at query side")
                watchlistViewRepository.add(WatchlistView(this.id, this.userId, this.name))
            }

        }
    }
}