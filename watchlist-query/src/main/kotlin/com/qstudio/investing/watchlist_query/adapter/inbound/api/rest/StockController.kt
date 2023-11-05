package com.qstudio.investing.watchlist_query.adapter.inbound.api.rest

import com.qstudio.investing.watchlist_query.adapter.inbound.api.rest.response.BaseResponse
import com.qstudio.investing.watchlist_query.adapter.inbound.api.rest.response.GetStockResponse
import com.qstudio.investing.watchlist_query.adapter.inbound.api.rest.response.GetStocksResponse
import com.qstudio.investing.watchlist_query.core.dispatcher.WatchlistQueryDispatcher
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stocks")
class StockController(
    private val queryDispatcher: WatchlistQueryDispatcher
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    suspend fun getStocks(): BaseResponse<GetStocksResponse> {
        log.info("querying stocks")
        val stockViewList = queryDispatcher.getStocks()
        return BaseResponse.success(GetStocksResponse(stockViewList))
    }

    @GetMapping("/{symbol}")
    suspend fun getStock(@Valid @PathVariable symbol: String): BaseResponse<GetStockResponse> {
        log.info("querying stock by symbol: symbol: $symbol")
        val stockView = queryDispatcher.getStockBySymbol(symbol)
        return BaseResponse.success(GetStockResponse(stockView))
    }
}