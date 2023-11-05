package com.qstudio.investing.watchlist_command.core.dispatcher

import com.qstudio.investing.watchlist_command.core.type.*
import com.qstudio.investing.watchlist_command.core.usecase.WatchlistCommandUseCase
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Service
import reactor.kotlin.core.publisher.toMono

@Service
class WatchlistCommandDispatcher(
    private val commandGateway: CommandGateway,
) : WatchlistCommandUseCase {

    override suspend fun createUserWatchlist(userId: String): String {
        val command = CreateUserWatchlistCommand(userId)
        return commandGateway.send<String>(command).toMono().awaitSingle()
    }

    override suspend fun createWatchlist(userId: String, name: String): String {
        val command = CreateWatchlistCommand(userId, name)
        return commandGateway.send<String>(command).toMono().awaitSingle()
    }

    override suspend fun renameWatchlist(userId: String, watchlistId: String, name: String) {
        val command = RenameWatchlistCommand(userId, watchlistId, name)
        commandGateway
            .send<Any?>(command)
            .toMono()
            .awaitSingleOrNull()
    }

    override suspend fun addStockToWatchlist(userId: String, watchlistId: String, symbol: String) {
        val command = AddStockToWatchlistCommand(userId, watchlistId, symbol)
        commandGateway
            .send<Any?>(command)
            .toMono()
            .awaitSingleOrNull()
    }

    override suspend fun removeStockFromWatchlist(userId: String, watchlistId: String, symbol: String) {
        val command = RemoveStockFromWatchlistCommand(userId, watchlistId, symbol)
        commandGateway
            .send<Any?>(command)
            .toMono()
            .awaitSingleOrNull()
    }
}