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
    lateinit var id: String
    lateinit var newName: String
}