package com.qstudio.investing.watchlist_query.core.handler

import com.qstudio.investing.watchlist_query.core.model.WatchlistView
import com.qstudio.investing.watchlist_query.core.repository.WatchlistViewRepository
import com.qstudio.investing.watchlist_query.core.type.WatchlistGetByIdQuery
import com.qstudio.investing.watchlist_query.core.type.WatchlistSearchQuery
import kotlinx.coroutines.runBlocking
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class WatchlistQueryHandler(
    private val watchlistViewRepository: WatchlistViewRepository
) {

    @QueryHandler
    fun handle(query: WatchlistGetByIdQuery): WatchlistView? {
        var watchlistView: WatchlistView?
        runBlocking {
            watchlistView = watchlistViewRepository.getById(query.watchlistId)
        }
        return watchlistView
    }


    @QueryHandler
    fun handle(query: WatchlistSearchQuery): List<WatchlistView> {
        var list: List<WatchlistView>
        runBlocking {
            list = if (query.userId != null) {
                watchlistViewRepository.getByUserId(query.userId)
            } else {
                watchlistViewRepository.getAll()
            }
        }
        return list
    }

}