package com.qstudio.investing.watchlist_shared_kernel.event.type


class WatchlistCreatedEvent {
    lateinit var id: String
    lateinit var userId: String
    lateinit var name: String

}

class WatchlistRenamedEvent {
    lateinit var id: String
    lateinit var newName: String
}