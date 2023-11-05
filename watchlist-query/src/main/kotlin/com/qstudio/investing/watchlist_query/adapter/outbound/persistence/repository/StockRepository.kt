package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.repository

import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Stock
import com.qstudio.investing.watchlist_query.core.model.StockView
import com.qstudio.investing.watchlist_query.core.repository.StockViewRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
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

    override suspend fun getBySymbol(symbol: String): StockView? {
        return repo.findById(symbol)?.let {
            StockView(it.symbol, it.name, it.createdDate, it.lastModifiedDate)
        }
    }

    override suspend fun getAll(): List<StockView> {
        return repo.findAll()
            .map { StockView(it.symbol, it.name, it.createdDate, it.lastModifiedDate) }
            .toList()
    }
}