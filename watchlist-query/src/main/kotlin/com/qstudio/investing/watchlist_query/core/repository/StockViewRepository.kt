package com.qstudio.investing.watchlist_query.core.repository

import com.qstudio.investing.watchlist_query.core.model.StockView

interface StockViewRepository {
    suspend fun save(stockView: StockView)

    suspend fun getBySymbol(symbol: String): StockView?

    suspend fun getAll(): List<StockView>
}
