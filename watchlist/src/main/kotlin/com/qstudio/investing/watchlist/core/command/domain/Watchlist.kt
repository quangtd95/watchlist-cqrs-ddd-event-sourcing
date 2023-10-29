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
    var name: String = ""

    constructor()

    @CommandHandler
    constructor(command: CreateWatchlistCommand) {
        AggregateLifecycle.apply(WatchlistCreatedEvent(id, command.name))
    }


    @EventSourcingHandler
    fun onStateChanged(watchlistCreatedEvent: WatchlistCreatedEvent) {
        this.name = watchlistCreatedEvent.name
        logger.info("watchlist created $name")
    }
}