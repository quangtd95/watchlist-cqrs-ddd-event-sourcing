package com.qstudio.investing.watchlist_command.core.type

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateUserWatchlistCommand(
    @TargetAggregateIdentifier
    val userId: String,
)

data class CreateWatchlistCommand(
    @TargetAggregateIdentifier
    val userId: String,
    val name: String
)

data class RenameWatchlistCommand(
    @TargetAggregateIdentifier
    val watchlistId: String,
    val newName: String
)


