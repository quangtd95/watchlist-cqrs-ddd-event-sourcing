package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.repository

import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Stock
import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Watchlist
import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.WatchlistStock
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface StockRepository : CoroutineCrudRepository<Stock, String>

@Repository
interface WatchlistRepository : CoroutineCrudRepository<Watchlist, String> {
    fun findAllByUserId(userId: String): Flow<Watchlist>
}

@Repository
interface WatchlistStockRepository : CoroutineCrudRepository<WatchlistStock, String> {
    fun existsByWatchlistIdAndSymbol(watchlistId: String, symbol: String): Mono<Boolean>
    fun findAllByWatchlistId(watchlistId: String): Flow<WatchlistStock>
    fun findAllByWatchlistIdIsIn(watchlistIdList: List<String>): Flow<WatchlistStock>
}
