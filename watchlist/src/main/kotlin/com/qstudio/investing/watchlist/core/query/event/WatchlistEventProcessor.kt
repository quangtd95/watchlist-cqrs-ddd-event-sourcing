package com.qstudio.investing.watchlist.core.query.event

import com.qstudio.investing.watchlist.core.query.model.WatchlistView
import com.qstudio.investing.watchlist.core.query.repository.WatchlistViewRepository
import com.qstudio.investing.watchlist.infrastructure.event.type.WatchlistCreatedEvent
import com.qstudio.investing.watchlist.infrastructure.event.type.WatchlistRenamedEvent
import kotlinx.coroutines.runBlocking
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WatchlistEventProcessor(
    private val watchlistViewRepository: WatchlistViewRepository
) {
    private final val logger = LoggerFactory.getLogger(WatchlistEventProcessor::class.java)

    @EventHandler
    fun on(watchlistCreatedEvent: WatchlistCreatedEvent) {
        runBlocking {
            with(watchlistCreatedEvent) {
                logger.info("on @EventHandler at query side: WatchlistCreatedEvent")
                watchlistViewRepository.save(WatchlistView(this.id, this.userId, this.name))
            }

        }
    }

    @EventHandler
    fun on(watchlistRenamedEvent: WatchlistRenamedEvent) {
        runBlocking {
            with(watchlistRenamedEvent) {
                logger.info("on @EventHandler at query side: WatchlistRenamedEvent")
                //TODO: validate, push notification if error
                watchlistViewRepository.getById(this.id)?.let {
                    it.name = this.newName
                    watchlistViewRepository.save(it)
                }
            }
        }
    }
}