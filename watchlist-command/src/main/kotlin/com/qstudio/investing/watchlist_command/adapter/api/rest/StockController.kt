package com.qstudio.investing.watchlist_command.adapter.api.rest

import com.qstudio.investing.watchlist_command.adapter.api.rest.request.CreateStockRequest
import com.qstudio.investing.watchlist_command.adapter.api.rest.response.BaseResponse
import com.qstudio.investing.watchlist_command.core.dispatcher.StockCommandDispatcher
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
        val id = stockCommandDispatcher.createStock(request.symbol, request.name)
        return BaseResponse.success(id)
    }


}