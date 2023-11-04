package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.repository

import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Stock
import com.qstudio.investing.watchlist_query.core.model.StockView
import com.qstudio.investing.watchlist_query.core.repository.StockViewRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
class ReactiveStockRepositoryAdapter(private val repo: StockRepository) : StockViewRepository {
    override suspend fun save(stockView: StockView) {
        if (repo.existsById(stockView.symbol)) {
            val stock = repo.findById(stockView.symbol)!!
            stock.also {
                it.symbol = stockView.symbol
                it.name = stockView.name
            }
            repo.save(stock)
        } else {
            repo.save(Stock().also {
                it.symbol = stockView.symbol
                it.name = stockView.name
            })
        }
    }
}