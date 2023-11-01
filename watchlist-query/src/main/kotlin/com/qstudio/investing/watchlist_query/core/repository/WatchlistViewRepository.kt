package com.qstudio.investing.watchlist_query.core.repository

import com.qstudio.investing.watchlist_query.core.model.WatchlistView
import kotlinx.coroutines.flow.Flow

interface WatchlistViewRepository {
    suspend fun save(watchlistView: WatchlistView)

    suspend fun getById(id: String): WatchlistView?

    suspend fun getAll(): List<WatchlistView>

    suspend fun getByUserId(userId: String): List<WatchlistView>
}
