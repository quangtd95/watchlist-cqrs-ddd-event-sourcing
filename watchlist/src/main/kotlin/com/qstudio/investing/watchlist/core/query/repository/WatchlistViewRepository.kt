package com.qstudio.investing.watchlist.core.query.repository

import com.qstudio.investing.watchlist.core.query.model.WatchlistView

interface WatchlistViewRepository {
    suspend fun add(watchlistView: WatchlistView)

    suspend fun get(id: String): WatchlistView?
}
