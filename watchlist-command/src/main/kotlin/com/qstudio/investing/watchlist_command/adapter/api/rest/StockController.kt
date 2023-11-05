package com.qstudio.investing.watchlist_command.adapter.api.rest

import com.qstudio.investing.watchlist_command.adapter.api.rest.request.CreateStockRequest
import com.qstudio.investing.watchlist_command.adapter.api.rest.request.CreateStocksInBatchRequest
import com.qstudio.investing.watchlist_command.adapter.api.rest.response.BaseResponse
import com.qstudio.investing.watchlist_command.core.dispatcher.StockCommandDispatcher
import jakarta.validation.Valid
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/stocks")
class StockController(
    private val stockCommandDispatcher: StockCommandDispatcher
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    suspend fun createStock(@RequestBody request: CreateStockRequest): BaseResponse<String> {
        log.info("creating stock")
        val id = stockCommandDispatcher.createStock(request.symbol, request.companyName)
        return BaseResponse.success(id)
    }

    @PostMapping("/batch")
    suspend fun createStocksInBatch(@Valid @RequestBody request: CreateStocksInBatchRequest): BaseResponse<String> {
        log.info("creating stocks in batch")
        runBlocking {
            request.stocks.forEach {
                stockCommandDispatcher.createStock(it.symbol, it.companyName)
            }
        }
        return BaseResponse.success("ok")
    }
}