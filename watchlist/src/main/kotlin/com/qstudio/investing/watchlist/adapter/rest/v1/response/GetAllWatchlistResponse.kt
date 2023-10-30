package com.qstudio.investing.watchlist.adapter.rest.v1.response

import com.qstudio.investing.watchlist.core.query.model.WatchlistView

data class GetAllWatchlistResponse(val watchlists: List<WatchlistView>)