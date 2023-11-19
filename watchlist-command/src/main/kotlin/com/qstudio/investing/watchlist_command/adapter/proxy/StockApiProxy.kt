package com.qstudio.investing.watchlist_command.adapter.proxy

import com.qstudio.investing.watchlist_command.core.port.StockProxy
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class StockApiProxy(webClientBuilder: WebClient.Builder) : StockProxy {

    @Value("\${app.stock.api.endpoint}")
    private val stockEndpoint = "http://localhost:3031"

    private val webClient: WebClient = webClientBuilder
        .baseUrl(stockEndpoint)
        .build()

    override suspend fun checkSymbolExists(symbol: String): Boolean {
        val response = webClient.get()
            .uri("/api/stocks/$symbol")
            .retrieve()
            .bodyToMono(StockResponse::class.java)
            .awaitSingle()

        return response.data.stock != null
    }

}

data class StockResponse(
    val status: Int,
    val message: String,
    val data: StockListResponse
)

data class StockListResponse(
    val stock: StockDataResponse?
)

data class StockDataResponse(
    val symbol: String,
    val name: String,
)