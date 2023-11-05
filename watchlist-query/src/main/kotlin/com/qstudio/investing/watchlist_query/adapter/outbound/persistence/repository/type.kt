package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.repository

import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Stock
import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Watchlist
import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.WatchlistStock
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository : CoroutineCrudRepository<Stock, String>

@Repository
interface WatchlistRepository : CoroutineCrudRepository<Watchlist, String> {
    suspend fun findAllByUserId(userId: String): List<Watchlist>
}

@Repository
interface WatchlistStockRepository : CoroutineCrudRepository<WatchlistStock, String> {
    suspend fun existsByWatchlistIdAndSymbol(watchlistId: String, symbol: String): Boolean
    suspend fun findAllByWatchlistId(watchlistId: String): List<WatchlistStock>
    suspend fun findAllByWatchlistIdIsIn(watchlistIdList: List<String>): List<WatchlistStock>
    suspend fun deleteAllByWatchlistIdAndSymbol(watchlist: String, symbol: String)
}
