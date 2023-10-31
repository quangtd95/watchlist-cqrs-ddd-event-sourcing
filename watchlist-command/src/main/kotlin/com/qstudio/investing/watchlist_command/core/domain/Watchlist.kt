package com.qstudio.investing.watchlist_command.core.domain

import org.axonframework.modelling.command.EntityId

class Watchlist {

    @EntityId
    lateinit var watchlistId: String
    lateinit var name: String
}