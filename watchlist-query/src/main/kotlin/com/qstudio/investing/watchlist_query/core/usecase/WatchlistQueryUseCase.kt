package com.qstudio.investing.watchlist_query.core.usecase

import com.qstudio.investing.watchlist_query.core.model.StockView
import com.qstudio.investing.watchlist_query.core.model.WatchlistView

interface WatchlistQueryUseCase {
    suspend fun getWatchlistById(watchlistId: String): WatchlistView?
    suspend fun getAllWatchlistBySpecification(userId: String?): List<WatchlistView>
    suspend fun getStockBySymbol(symbol: String): StockView?
    suspend fun getStocks(): List<StockView>
}