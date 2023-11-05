package com.qstudio.investing.watchlist_command.core.domain.watchlist

import com.qstudio.investing.watchlist_command.infrastructure.type.ValueObject

data class WatchlistStock(val symbol: String) : ValueObject