package com.qstudio.investing.watchlist.core.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateWatchlistCommand(
    @TargetAggregateIdentifier val id: String, val name: String
)
