package com.qstudio.investing.watchlist_query.adapter.inbound.api.rest.response

import com.qstudio.investing.watchlist_query.core.model.WatchlistView

data class GetAllWatchlistResponse(val watchlists: List<WatchlistView>)