package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.repository

import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Watchlist
import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.WatchlistStock
import com.qstudio.investing.watchlist_query.core.model.WatchlistView
import com.qstudio.investing.watchlist_query.core.repository.WatchlistViewRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class ReactiveWatchlistRepositoryAdapter(
    private val watchlistTable: WatchlistRepository,
    private val watchlistStockTable: WatchlistStockRepository
) : WatchlistViewRepository {
    override suspend fun save(watchlistView: WatchlistView) {
        if (watchlistTable.existsById(watchlistView.id)) {
            val watchlist = watchlistTable.findById(watchlistView.id)!!
            watchlist.also {
                it.userId = watchlistView.userId
                it.name = watchlistView.name
            }
            watchlistTable.save(watchlist)
        } else {
            watchlistTable.save(Watchlist().also {
                it.id = watchlistView.id
                it.userId = watchlistView.userId
                it.name = watchlistView.name
            })
        }
    }


    override suspend fun getById(id: String): WatchlistView? {
        val stocks = watchlistStockTable.findAllByWatchlistId(id)
            .map { it.symbol }
            .toSet()
        return watchlistTable.findById(id)
            ?.let { WatchlistView(it.id, it.userId, it.name, stocks, it.createdDate, it.lastModifiedDate) }
    }

    override suspend fun getAll(): List<WatchlistView> {
        val watchlistStocks = watchlistStockTable.findAll()
            .toList()
            .groupBy { it.watchlistId }
            .mapValues { pair -> pair.value.map { it.symbol }.toSet() }
        return watchlistTable
            .findAll()
            .map {
                WatchlistView(
                    it.id,
                    it.userId,
                    it.name,
                    watchlistStocks[it.id],
                    it.createdDate,
                    it.lastModifiedDate
                )
            }
            .toList()
    }

    override suspend fun getByUserId(userId: String): List<WatchlistView> {
        return watchlistTable
            .findAllByUserId(userId)
            .map {
                WatchlistView(it.id, it.userId, it.name,
                    watchlistStockTable.findAllByWatchlistId(it.id)
                        .map { ws -> ws.symbol }
                        .toSet(),
                    it.createdDate, it.lastModifiedDate)
            }
            .toList()
    }

    override suspend fun addSymbolToWatchlist(watchlistId: String, symbol: String) {
        if (!watchlistStockTable.existsByWatchlistIdAndSymbol(watchlistId, symbol).awaitSingle()) {
            watchlistStockTable.save(WatchlistStock().also {
                it.id = UUID.randomUUID().toString()
                it.watchlistId = watchlistId
                it.symbol = symbol
            })
        }
    }

}