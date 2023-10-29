package com.qstudio.investing.watchlist.core.command.domain

import com.qstudio.investing.watchlist.core.command.type.CreateWatchlistCommand
import com.qstudio.investing.watchlist.infrastructure.event.type.WatchlistCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.util.*

@Aggregate
internal class Watchlist {

    private val logger = LoggerFactory.getLogger(Watchlist::class.java)

    @AggregateIdentifier
    final val id: String = UUID.randomUUID().toString()
    var userId: String = ""
    var name: String = ""

    constructor()

    @CommandHandler
    constructor(command: CreateWatchlistCommand) {
        AggregateLifecycle.apply(WatchlistCreatedEvent(id, command.userId, command.name))
    }


    @EventSourcingHandler
    fun onStateChanged(watchlistCreatedEvent: WatchlistCreatedEvent) {
        watchlistCreatedEvent.let {
            this.userId = it.userId
            this.name = it.name
        }

        logger.info("created  watchlist $name for userId = $userId")
    }
}