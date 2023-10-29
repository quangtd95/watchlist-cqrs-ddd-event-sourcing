package com.qstudio.investing.watchlist.infrastructure.event.type

data class WatchlistCreatedEvent(val id: String, val userId: String, val name: String)