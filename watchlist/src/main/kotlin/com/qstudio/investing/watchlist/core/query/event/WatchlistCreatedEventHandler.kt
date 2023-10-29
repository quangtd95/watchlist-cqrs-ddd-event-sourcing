package com.qstudio.investing.watchlist.core.query.event

import com.qstudio.investing.watchlist.core.query.model.WatchlistView
import com.qstudio.investing.watchlist.core.query.repository.WatchlistViewRepository
import com.qstudio.investing.watchlist.infrastructure.event.type.WatchlistCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WatchlistCreatedEventHandler {
    private final val logger = LoggerFactory.getLogger(WatchlistCreatedEventHandler::class.java)

    @EventHandler
    fun on(watchlistCreatedEvent: WatchlistCreatedEvent) {
        logger.info("on @EventHandler at query side")
        WatchlistViewRepository.add(WatchlistView(watchlistCreatedEvent.id, watchlistCreatedEvent.name))
    }

}