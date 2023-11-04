package com.qstudio.investing.watchlist_command.core.domain

import com.qstudio.investing.watchlist_command.core.type.CreateUserWatchlistCommand
import com.qstudio.investing.watchlist_command.core.type.CreateWatchlistCommand
import com.qstudio.investing.watchlist_command.core.type.RenameWatchlistCommand
import com.qstudio.investing.watchlist_shared_kernel.event.type.UserWatchlistCreatedEvent
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistCreatedEvent
import com.qstudio.investing.watchlist_shared_kernel.event.type.WatchlistRenamedEvent
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

    @CommandHandler
    fun handle(command: CreateWatchlistCommand): String {
        command.validate()

        val newWatchlistId = UUID.randomUUID().toString()
        AggregateLifecycle.apply(WatchlistCreatedEvent().also {
            it.userId = this@UserWatchlist.userId
            it.watchlistId = newWatchlistId
            it.name = command.name
        })
        return newWatchlistId
    }

    @CommandHandler
    fun handle(command: RenameWatchlistCommand) {
        command.validate()

        AggregateLifecycle.apply(WatchlistRenamedEvent().also {
            it.userId = command.userId
            it.watchlistId = command.watchlistId
            it.name = command.name
        })
    }

    @EventSourcingHandler
    fun on(event: UserWatchlistCreatedEvent) {
        this.userId = event.userId
        watchlists = mutableListOf()
    }

    @EventSourcingHandler
    fun on(event: WatchlistCreatedEvent) {
        watchlists.add(Watchlist().also {
            it.watchlistId = event.watchlistId
            it.name = event.name
            it.stocks = mutableSetOf()
        })
    }

    private fun CreateWatchlistCommand.validate() {
        if (watchlists.map { it.name }.toSet().contains(name)) {
            throw RuntimeException("Watchlist $name is already exists")
        }
    }

    private fun RenameWatchlistCommand.validate() {
        if (watchlists
                .filter { it.watchlistId !== watchlistId }
                .map { it.name }
                .toSet().contains(name)
        ) {
            throw RuntimeException("Watchlist $name is already exists")
        }
    }
}