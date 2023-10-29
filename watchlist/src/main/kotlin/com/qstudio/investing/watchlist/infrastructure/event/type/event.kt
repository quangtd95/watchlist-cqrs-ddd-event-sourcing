package com.qstudio.investing.watchlist.infrastructure.event.type

data class WatchlistCreatedEvent(val id: String, val userId: String, val name: String)

data class WatchlistRenamedEvent(val id: String, val newName: String)