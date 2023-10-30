package com.qstudio.investing.watchlist.core.command.domain

import com.qstudio.investing.watchlist.core.command.type.CreateWatchlistCommand
import com.qstudio.investing.watchlist.core.command.type.RenameWatchlistCommand
import com.qstudio.investing.watchlist.infrastructure.event.type.WatchlistCreatedEvent
import com.qstudio.investing.watchlist.infrastructure.event.type.WatchlistRenamedEvent
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
    final var id: String = ""
    var userId: String = ""
    var name: String = ""

    constructor()

    @CommandHandler
    constructor(command: CreateWatchlistCommand) {
        this.id = command.watchlistId
        apply(WatchlistCreatedEvent(this.id, command.userId, command.name))
    }

    @CommandHandler
    fun on(command: RenameWatchlistCommand) {
        apply(WatchlistRenamedEvent(id, command.newName))
    }

    @EventSourcingHandler
    fun onStateChanged(watchlistCreatedEvent: WatchlistCreatedEvent) {
        watchlistCreatedEvent.let {
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