package com.qstudio.investing.watchlist_query.core.handler

import com.qstudio.investing.watchlist_query.core.model.WatchlistView
import com.qstudio.investing.watchlist_query.core.repository.WatchlistViewRepository
import com.qstudio.investing.watchlist_query.core.type.WatchlistGetAllQuery
import com.qstudio.investing.watchlist_query.core.type.WatchlistGetByIdQuery
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class WatchlistQueryHandler(
    private val watchlistViewRepository: WatchlistViewRepository
) {

    @QueryHandler
    fun handle(watchlistGetByIdQuery: WatchlistGetByIdQuery): WatchlistView? {
        var watchlistView: WatchlistView?
        runBlocking {
            watchlistView = watchlistViewRepository.getById(watchlistGetByIdQuery.watchlistId)
        }
        return watchlistView
    }

    @QueryHandler
    fun handle(watchlistGetAllQuery: WatchlistGetAllQuery): List<WatchlistView> {
        var list: List<WatchlistView>
        runBlocking {
            list = watchlistViewRepository.getAll().toList()
        }
        return list
    }
}