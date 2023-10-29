package com.qstudio.investing.watchlist.core.query.handler

import com.qstudio.investing.watchlist.core.query.model.WatchlistView
import com.qstudio.investing.watchlist.core.query.repository.WatchlistViewRepository
import com.qstudio.investing.watchlist.core.query.type.WatchlistQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class WatchlistProjection {

    @QueryHandler
    fun handle(watchlistQuery: WatchlistQuery): WatchlistView? {
        return WatchlistViewRepository.watchlistArray.firstOrNull { it.id == watchlistQuery.watchlistId }
    }
}