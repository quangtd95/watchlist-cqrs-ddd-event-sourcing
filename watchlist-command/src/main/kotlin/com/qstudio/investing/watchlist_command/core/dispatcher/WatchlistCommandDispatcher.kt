package com.qstudio.investing.watchlist_command.core.dispatcher

import com.qstudio.investing.watchlist_command.core.type.*
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Component
import reactor.kotlin.core.publisher.toMono

@Component
class WatchlistCommandDispatcher(
    private val commandGateway: CommandGateway,
) {

    suspend fun createUserWatchlist(userId: String): String {
        val command = CreateUserWatchlistCommand(userId)
        return commandGateway.send<String>(command).toMono().awaitSingle()
    }

    suspend fun createWatchlist(userId: String, name: String): String {
        val command = CreateWatchlistCommand(userId, name)
        return commandGateway.send<String>(command).toMono().awaitSingle()
    }

    suspend fun renameWatchlist(userId: String, watchlistId: String, name: String) {
        val command = RenameWatchlistCommand(userId, watchlistId, name)
        commandGateway
            .send<Any?>(command)
            .toMono()
            .awaitSingleOrNull()
    }

    suspend fun addStockToWatchlist(userId: String, watchlistId: String, symbol: String) {
        val command = AddStockToWatchlistCommand(userId, watchlistId, symbol)
        commandGateway
            .send<Any?>(command)
            .toMono()
            .awaitSingleOrNull()
    }

    suspend fun removeStockFromWatchlist(userId: String, watchlistId: String, symbol: String) {
        val command = RemoveStockFromWatchlistCommand(userId, watchlistId, symbol)
        commandGateway
            .send<Any?>(command)
            .toMono()
            .awaitSingleOrNull()
    }
}