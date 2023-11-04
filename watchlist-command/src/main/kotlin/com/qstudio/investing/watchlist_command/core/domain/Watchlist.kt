package com.qstudio.investing.watchlist_command.core.domain

import com.qstudio.investing.watchlist_command.core.type.AddStockToWatchlistCommand
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistRenamedEvent
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistStockAddedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId

class Watchlist {

    @EntityId
    lateinit var watchlistId: String
    lateinit var name: String
    lateinit var stocks: MutableSet<String>

    @EventSourcingHandler
    fun on(event: WatchlistRenamedEvent) {
        this.name = event.name
    }

    @CommandHandler
    fun handle(command: AddStockToWatchlistCommand) {
        if (!stocks.contains(command.symbol)) {
            AggregateLifecycle.apply(WatchlistStockAddedEvent().also {
                it.userId = command.userId
                it.watchlistId = command.watchlistId
                it.symbol = command.symbol
            })
        }

    }

    @EventSourcingHandler
    fun on(event: WatchlistStockAddedEvent) {
        stocks.add(event.symbol)
    }
}