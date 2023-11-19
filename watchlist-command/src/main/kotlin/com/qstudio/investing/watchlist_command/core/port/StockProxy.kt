package com.qstudio.investing.watchlist_command.core.port

interface StockProxy {
    suspend fun checkSymbolExists(symbol: String): Boolean
}