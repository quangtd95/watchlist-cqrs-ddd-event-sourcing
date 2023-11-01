package com.qstudio.investing.watchlist_command.core.domain

import com.qstudio.investing.watchlist_command.core.type.RenameWatchlistCommand
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistRenamedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId

class Watchlist {

    @EntityId
    lateinit var watchlistId: String
    lateinit var name: String

    @CommandHandler
    fun handle(command: RenameWatchlistCommand) {
        AggregateLifecycle.apply(WatchlistRenamedEvent().also {
            it.userId = command.userId
            it.watchlistId = command.watchlistId
            it.name = command.name
        })
    }

    @EventSourcingHandler
    fun on(event: WatchlistRenamedEvent) {
        this.name = event.name
    }
}