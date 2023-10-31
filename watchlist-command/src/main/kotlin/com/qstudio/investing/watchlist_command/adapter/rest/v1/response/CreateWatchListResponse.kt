package com.qstudio.investing.watchlist_command.adapter.rest.v1.response

data class CreateWatchListResponse(val watchlist: WatchlistResponse)

data class WatchlistResponse(val id: String)
