package com.qstudio.investing.watchlist.core.query.repository

import com.qstudio.investing.watchlist.core.query.model.WatchlistView
import kotlinx.coroutines.flow.Flow

interface WatchlistViewRepository {
    suspend fun add(watchlistView: WatchlistView)

    suspend fun getById(id: String): WatchlistView?

    fun getAll(): Flow<WatchlistView>
}
