package com.qstudio.investing.watchlist_command.core.service

import com.qstudio.investing.watchlist_command.core.dispatcher.WatchlistCommandDispatcher
import com.qstudio.investing.watchlist_command.core.usecase.WatchlistCommandUseCase
import org.springframework.stereotype.Service

@Service
class WatchlistCommandService(
    private val watchlistCommandDispatcher: WatchlistCommandDispatcher,
) : WatchlistCommandUseCase {

    override suspend fun createUserWatchlist(userId: String): String {
        return watchlistCommandDispatcher.createUserWatchlist(userId)
    }

    override suspend fun createWatchlist(userId: String, name: String): String {
        return watchlistCommandDispatcher.createWatchlist(userId, name)
    }

    override suspend fun renameWatchlist(userId: String, watchlistId: String, name: String) {
        return watchlistCommandDispatcher.renameWatchlist(userId, watchlistId, name)
    }

    override suspend fun addStockToWatchlist(userId: String, watchlistId: String, symbol: String) {
        return watchlistCommandDispatcher.addStockToWatchlist(userId, watchlistId, symbol)
    }

    override suspend fun removeStockFromWatchlist(userId: String, watchlistId: String, symbol: String) {
        return watchlistCommandDispatcher.removeStockFromWatchlist(userId, watchlistId, symbol)
    }
}