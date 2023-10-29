package com.qstudio.investing.watchlist.core.query.repository

import com.qstudio.investing.watchlist.core.query.model.WatchlistView

object WatchlistViewRepository {
    val watchlistArray = mutableListOf<WatchlistView>()

    fun add(watchlistView: WatchlistView) {
        watchlistArray.add(watchlistView)
    }
}
