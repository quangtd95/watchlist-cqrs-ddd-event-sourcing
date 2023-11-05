package com.qstudio.investing.watchlist_command.core.domain.stock

import com.qstudio.investing.watchlist_command.core.type.CreateStockCommand
import com.qstudio.investing.watchlist_command.infrastructure.type.AggregateRoot
import com.qstudio.investing.watchlist_shared_kernel.event.type.StockCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class Stock : AggregateRoot {
    @AggregateIdentifier
    lateinit var symbol: String
    lateinit var name: String

    constructor()

    @CommandHandler
    constructor(command: CreateStockCommand) {
        AggregateLifecycle.apply(StockCreatedEvent().also {
            it.symbol = command.symbol
            it.name = command.name
        })
    }

    @EventSourcingHandler
    fun on(event: StockCreatedEvent) {
        this.symbol = event.symbol
        this.name = event.name
    }
}