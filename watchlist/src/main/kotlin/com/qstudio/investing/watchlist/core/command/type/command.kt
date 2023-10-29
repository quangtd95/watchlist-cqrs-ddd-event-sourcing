package com.qstudio.investing.watchlist.core.command.type

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateWatchlistCommand(
    @TargetAggregateIdentifier
    val watchlistId: String,
    val userId: String,
    val name: String
)

data class RenameWatchlistCommand(
    @TargetAggregateIdentifier
    val watchlistId: String,
    val newName: String
)


