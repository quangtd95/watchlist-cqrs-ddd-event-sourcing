package com.qstudio.investing.watchlist.core.domain

import WatchlistCreatedEvent
import com.qstudio.investing.watchlist.core.CreateWatchlistCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Aggregate
class Watchlist @CommandHandler constructor(command: CreateWatchlistCommand) {
    @AggregateIdentifier
    final val id: String = UUID.randomUUID().toString()

    final val name: String

    init {
        name = command.name
        AggregateLifecycle.apply(WatchlistCreatedEvent(id, name))
    }
}