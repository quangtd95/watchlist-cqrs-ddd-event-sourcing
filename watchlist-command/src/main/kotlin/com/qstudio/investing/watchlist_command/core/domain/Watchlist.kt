package com.qstudio.investing.watchlist_command.core.domain

import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistRenamedEvent
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.EntityId

class Watchlist {

    @EntityId
    lateinit var watchlistId: String
    lateinit var name: String
    lateinit var stocks: MutableList<Stock>

    @EventSourcingHandler
    fun on(event: WatchlistRenamedEvent) {
        this.name = event.name
    }
}