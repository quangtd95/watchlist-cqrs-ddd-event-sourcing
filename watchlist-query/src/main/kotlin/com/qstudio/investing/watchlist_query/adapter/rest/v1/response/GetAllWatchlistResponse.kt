package com.qstudio.investing.watchlist_query.adapter.rest.v1.response

import com.qstudio.investing.watchlist_query.core.query.model.WatchlistView

data class GetAllWatchlistResponse(val watchlists: List<WatchlistView>)