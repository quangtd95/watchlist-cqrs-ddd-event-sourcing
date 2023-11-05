package com.qstudio.investing.watchlist_query.core.handler

import com.qstudio.investing.watchlist_query.core.model.StockView
import com.qstudio.investing.watchlist_query.core.repository.StockViewRepository
import com.qstudio.investing.watchlist_query.core.type.StockGetBySymbolQuery
import com.qstudio.investing.watchlist_query.core.type.StocksGetQuery
import kotlinx.coroutines.runBlocking
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class StockQueryHandler(
    private val stockViewRepository: StockViewRepository
) {

    @QueryHandler
    fun handle(query: StocksGetQuery): List<StockView> {
        return runBlocking {
            stockViewRepository.getAll()
        }
    }

    @QueryHandler
    fun handle(query: StockGetBySymbolQuery): StockView? {
        return runBlocking {
            stockViewRepository.getBySymbol(query.symbol)
        }
    }

}