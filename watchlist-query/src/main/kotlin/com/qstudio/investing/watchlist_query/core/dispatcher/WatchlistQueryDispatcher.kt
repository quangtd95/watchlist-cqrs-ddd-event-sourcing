package com.qstudio.investing.watchlist_query.core.dispatcher

import com.qstudio.investing.watchlist_query.core.model.WatchlistView
import com.qstudio.investing.watchlist_query.core.type.WatchlistGetByIdQuery
import com.qstudio.investing.watchlist_query.core.type.WatchlistSearchQuery
import com.qstudio.investing.watchlist_query.core.usecase.WatchlistQueryUseCase
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.axonframework.extensions.kotlin.query
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import reactor.kotlin.core.publisher.toMono

@Service
class WatchlistQueryDispatcher(
    private val queryGateway: QueryGateway
) : WatchlistQueryUseCase {
    override suspend fun getWatchlistById(watchlistId: String): WatchlistView? {
        return queryGateway
            .query<WatchlistView?, WatchlistGetByIdQuery>(WatchlistGetByIdQuery(watchlistId))
            .toMono()
            .awaitSingleOrNull()
    }


    override suspend fun getAllWatchlistBySpecification(userId: String?): List<WatchlistView> {
        return queryGateway
            .queryMany<WatchlistView, WatchlistSearchQuery>(WatchlistSearchQuery(userId))
            .toMono()
            .awaitSingle()
    }
}