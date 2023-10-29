package com.qstudio.investing.watchlist.core.query.handler

import com.qstudio.investing.watchlist.core.query.model.WatchlistView
import com.qstudio.investing.watchlist.core.query.repository.WatchlistViewRepository
import com.qstudio.investing.watchlist.core.query.type.WatchlistQuery
import kotlinx.coroutines.runBlocking
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class WatchlistProjection(
    private val watchlistViewRepository: WatchlistViewRepository
) {

    @QueryHandler
    fun handle(watchlistQuery: WatchlistQuery): WatchlistView? {
        var watchlistView: WatchlistView?
        runBlocking {
            watchlistView = watchlistViewRepository.get(watchlistQuery.watchlistId)
        }
        return watchlistView
    }
}