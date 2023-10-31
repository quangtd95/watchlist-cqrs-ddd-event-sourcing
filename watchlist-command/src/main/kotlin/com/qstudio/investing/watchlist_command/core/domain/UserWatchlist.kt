package com.qstudio.investing.watchlist_command.core.domain

import com.qstudio.investing.watchlist_command.core.type.CreateUserWatchlistCommand
import com.qstudio.investing.watchlist_command.core.type.CreateWatchlistCommand
import com.qstudio.investing.watchlist_shared_kernel.event.type.UserWatchlistCreatedEvent
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.util.*

@Aggregate
class UserWatchlist {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @AggregateIdentifier
    final lateinit var userId: String

    @AggregateMember
    lateinit var watchlists: MutableList<Watchlist>

    constructor()

    @CommandHandler
    constructor(command: CreateUserWatchlistCommand) {
        val event = UserWatchlistCreatedEvent().apply {
            this.userId = command.userId
        }
        AggregateLifecycle.apply(event)
    }

    @EventSourcingHandler
    fun on(event: UserWatchlistCreatedEvent) {
        this.userId = event.userId
        watchlists = mutableListOf()
    }

    @CommandHandler
    fun handle(command: CreateWatchlistCommand): String {
        if (watchlists.map { it.name }.toSet().contains(command.name)) {
            throw RuntimeException("Watchlist is already exists")
        }

        val newWatchlistId = UUID.randomUUID().toString()
        AggregateLifecycle.apply(WatchlistCreatedEvent().apply {
            this.userId = this@UserWatchlist.userId
            this.watchlistId = newWatchlistId
            this.name = command.name
        })
        return newWatchlistId
    }

    @EventSourcingHandler
    fun on(event: WatchlistCreatedEvent) {
        watchlists.add(Watchlist().apply {
            this.watchlistId = event.watchlistId
            this.name = event.name
        })
    }
}