package com.qstudio.investing.watchlist_query.infrastructure.event.type


class WatchlistCreatedEvent {
    lateinit var id: String
    lateinit var userId: String
    lateinit var name: String

    constructor()

    constructor(id: String, userId: String, name: String) {
        this.id = id
        this.userId = userId
        this.name = name
    }
}

class WatchlistRenamedEvent {
    lateinit var id: String
    lateinit var newName: String

    constructor()

    constructor(id: String, newName: String) {
        this.id = id
        this.newName = newName
    }
}