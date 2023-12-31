package com.qstudio.investing.watchlist_query.core.type

data class WatchlistGetByIdQuery(val watchlistId: String)

data class WatchlistSearchQuery(val userId: String? = null)

data class StockGetBySymbolQuery(val symbol: String)

class StocksGetQuery()