package com.qstudio.investing.watchlist_command.core.domain.watchlist

import com.qstudio.investing.watchlist_command.core.type.AddStockToWatchlistCommand
import com.qstudio.investing.watchlist_command.core.type.RemoveStockFromWatchlistCommand
import com.qstudio.investing.watchlist_command.infrastructure.type.Entity
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistRenamedEvent
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistStockAddedEvent
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistStockRemovedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId

class Watchlist : Entity {

    @EntityId
    lateinit var watchlistId: String
    lateinit var name: String
    lateinit var stocks: MutableSet<WatchlistStock>


    @CommandHandler
    fun handle(command: AddStockToWatchlistCommand) {
        if (isNotIncludeSymbol(command.symbol)) {
            AggregateLifecycle.apply(WatchlistStockAddedEvent().also {
                it.userId = command.userId
                it.watchlistId = command.watchlistId
                it.symbol = command.symbol
            })
        }

    }

    @CommandHandler
    fun handle(command: RemoveStockFromWatchlistCommand) {
        if (isIncludeSymbol(command.symbol)) {
            AggregateLifecycle.apply(WatchlistStockRemovedEvent().also {
                it.userId = command.userId
                it.watchlistId = command.watchlistId
                it.symbol = command.symbol
            })
        }
    }

    @EventSourcingHandler
    fun on(event: WatchlistRenamedEvent) {
        this.name = event.name
    }

    @EventSourcingHandler
    fun on(event: WatchlistStockAddedEvent) {
        stocks.add(WatchlistStock(event.symbol))
    }

    @EventSourcingHandler
    fun on(event: WatchlistStockRemovedEvent) {
        stocks.removeIf { it.symbol == event.symbol }
    }

    private fun isIncludeSymbol(symbol: String): Boolean =
        stocks.map { it.symbol }.contains(symbol)

    private fun isNotIncludeSymbol(symbol: String) = !isIncludeSymbol(symbol)
}