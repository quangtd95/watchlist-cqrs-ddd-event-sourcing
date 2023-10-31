package com.qstudio.investing.watchlist_command.core.domain

import com.qstudio.investing.watchlist_command.core.type.CreateWatchlistCommand
import com.qstudio.investing.watchlist_command.core.type.RenameWatchlistCommand
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistCreatedEvent
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistRenamedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory

@Aggregate
internal class Watchlist {

    private val logger = LoggerFactory.getLogger(Watchlist::class.java)

    @AggregateIdentifier
    final lateinit var id: String
    var userId: String = ""
    var name: String = ""

    constructor()

    @CommandHandler
    constructor(command: CreateWatchlistCommand) {
        apply(WatchlistCreatedEvent().apply {
            this.id = command.watchlistId
            this.userId = command.userId
            this.name = command.name
        })
    }

    @CommandHandler
    fun on(command: RenameWatchlistCommand) {
        apply(WatchlistRenamedEvent().apply {
            this.id = command.watchlistId
            this.newName = command.newName
        })
    }

    @EventSourcingHandler
    fun onStateChanged(watchlistCreatedEvent: WatchlistCreatedEvent) {
        watchlistCreatedEvent.let {
            this.id = it.id
            this.userId = it.userId
            this.name = it.name
            logger.info("created  watchlist $name for userId = $userId")
        }
    }

    @EventSourcingHandler
    fun onStateChanged(watchlistRenamedEvent: WatchlistRenamedEvent) {
        val oldName = name
        watchlistRenamedEvent.let {
            this.name = it.newName
        }
        logger.info("renamed watchlist $oldName to $name")
    }
}