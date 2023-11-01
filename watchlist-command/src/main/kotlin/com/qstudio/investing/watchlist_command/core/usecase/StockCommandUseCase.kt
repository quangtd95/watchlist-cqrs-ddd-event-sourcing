package com.qstudio.investing.watchlist_command.core.usecase

interface StockCommandUseCase {
    suspend fun createStock(symbol: String, name: String): String
}
