package com.qstudio.investing.watchlist_query.core.query.repository

import com.qstudio.investing.watchlist_query.core.query.model.WatchlistView
import kotlinx.coroutines.flow.Flow

interface WatchlistViewRepository {
    suspend fun save(watchlistView: WatchlistView)

    suspend fun getById(id: String): WatchlistView?

    fun getAll(): Flow<WatchlistView>
}
