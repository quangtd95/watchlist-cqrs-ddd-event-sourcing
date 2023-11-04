package com.qstudio.investing.watchlist_query.core.event

import com.qstudio.investing.watchlist_query.core.model.WatchlistView
import com.qstudio.investing.watchlist_query.core.repository.WatchlistViewRepository
import com.qstudio.investing.watchlist_shared_kernel.event.type.UserWatchlistCreatedEvent
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistCreatedEvent
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistRenamedEvent
import kotlinx.coroutines.runBlocking
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@ProcessingGroup("watchlistEvents")
@Component
class WatchlistEventProcessor(
    private val watchlistViewRepository: WatchlistViewRepository
) {
    private final val logger = LoggerFactory.getLogger(WatchlistEventProcessor::class.java)

    @EventHandler
    fun on(event: UserWatchlistCreatedEvent) {
        logger.info("new user joined: ${event.userId}")
    }

    @EventHandler
    fun on(watchlistCreatedEvent: WatchlistCreatedEvent) {
        runBlocking {
            with(watchlistCreatedEvent) {
                logger.info("on @EventHandler at query side: WatchlistCreatedEvent")
                watchlistViewRepository.save(WatchlistView(this.watchlistId, this.userId, this.name))
            }

        }
    }

    @EventHandler
    fun on(watchlistRenamedEvent: WatchlistRenamedEvent) {
        runBlocking {
            with(watchlistRenamedEvent) {
                logger.info("on @EventHandler at query side: WatchlistRenamedEvent")
                //TODO: validate, push notification if error
                watchlistViewRepository.getById(this.watchlistId)?.let {
                    it.name = this.name
                    watchlistViewRepository.save(it)
                }
            }
        }
    }
}