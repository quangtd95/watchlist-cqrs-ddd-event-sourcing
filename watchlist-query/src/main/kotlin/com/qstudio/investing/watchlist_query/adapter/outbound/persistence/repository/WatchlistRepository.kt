package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.repository

import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Watchlist
import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.WatchlistStock
import com.qstudio.investing.watchlist_query.core.model.WatchlistView
import com.qstudio.investing.watchlist_query.core.repository.WatchlistViewRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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


    override suspend fun getById(id: String): WatchlistView? = coroutineScope {
        val watchlist = async {
            watchlistTable.findById(id)
        }
        val stocks = async {
            watchlistStockTable.findAllByWatchlistId(id)
        }
        watchlist.await()
            ?.let {
                WatchlistView(
                    it.id,
                    it.userId,
                    it.name,
                    stocks.await().map { ws -> ws.symbol }.toSet(),
                    it.createdDate,
                    it.lastModifiedDate
                )
            }
    }

    override suspend fun getAll(): List<WatchlistView> = coroutineScope {
        val watchlistStocks = async {
            watchlistStockTable.findAll()
                .toList()
                .groupBy { it.watchlistId }
                .mapValues { pair -> pair.value.map { it.symbol }.toSet() }
        }
        val watchlists = async {
            watchlistTable.findAll()
        }
        val ws = watchlistStocks.await()
        watchlists.await()
            .map {
                WatchlistView(
                    it.id,
                    it.userId,
                    it.name,
                    ws[it.id],
                    it.createdDate,
                    it.lastModifiedDate
                )
            }
            .toList()
    }

    override suspend fun getByUserId(userId: String): List<WatchlistView> = coroutineScope {
        val watchlistsByUserId = async {
            watchlistTable
                .findAllByUserId(userId)
        }
        val watchlistStocks = async {
            watchlistStockTable
                .findAllByWatchlistIdIsIn(watchlistsByUserId.await().map { it.id }.toList())
                .toList()
                .groupBy { it.watchlistId }
                .mapValues { pair -> pair.value.map { it.symbol }.toSet() }
        }
        watchlistsByUserId
            .await()
            .map {
                WatchlistView(
                    it.id,
                    it.userId,
                    it.name,
                    watchlistStocks.await()[it.id],
                    it.createdDate,
                    it.lastModifiedDate
                )
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