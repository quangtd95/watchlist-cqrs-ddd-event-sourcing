package com.qstudio.investing.watchlist_command.core.dispatcher

import com.qstudio.investing.watchlist_command.core.type.CreateStockCommand
import com.qstudio.investing.watchlist_command.core.usecase.StockCommandUseCase
import kotlinx.coroutines.reactor.awaitSingle
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Service
import reactor.kotlin.core.publisher.toMono

@Service
class StockCommandDispatcher(
    private val commandGateway: CommandGateway,
) : StockCommandUseCase {

    override suspend fun createStock(symbol: String, name: String): String {
        val command = CreateStockCommand(symbol, name)
        return commandGateway.send<String>(command).toMono().awaitSingle()
    }
}