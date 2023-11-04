package com.qstudio.investing.watchlist_command.core.usecase

interface WatchlistCommandUseCase {
    suspend fun createUserWatchlist(userId: String): String

    suspend fun createWatchlist(userId: String, name: String): String

    suspend fun renameWatchlist(userId: String, watchlistId: String, name: String)

    suspend fun addStockToWatchlist(userId: String, watchlistId: String, symbol: String)
}
