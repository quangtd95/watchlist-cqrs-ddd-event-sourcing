package com.qstudio.investing.watchlist_shared_kernel.event.type


class UserWatchlistCreatedEvent {
    lateinit var userId: String
}

class WatchlistCreatedEvent {
    lateinit var userId: String
    lateinit var watchlistId: String
    lateinit var name: String
}

class WatchlistRenamedEvent {
    lateinit var userId: String
    lateinit var watchlistId: String
    lateinit var name: String
}

class WatchlistStockAddedEvent {
    lateinit var userId: String
    lateinit var watchlistId: String
    lateinit var symbol: String
}

class WatchlistStockRemovedEvent {
    lateinit var userId: String
    lateinit var watchlistId: String
    lateinit var symbol: String
}

class StockCreatedEvent {
    lateinit var symbol: String
    lateinit var name: String
}