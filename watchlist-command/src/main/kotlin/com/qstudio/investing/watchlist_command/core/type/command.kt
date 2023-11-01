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
    val userId: String,
    val watchlistId: String,
    val name: String
)

data class CreateStockCommand(
    @TargetAggregateIdentifier
    val symbol: String,
    val name: String
)


