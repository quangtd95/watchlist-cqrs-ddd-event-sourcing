package com.qstudio.investing.watchlist_command.core.port

interface StockPort {
    suspend fun checkSymbolExists(symbol: String): Boolean
}