package com.qstudio.investing.watchlist.adapter.query.persistence.repository

import com.qstudio.investing.watchlist.adapter.query.persistence.entity.Watchlist
import com.qstudio.investing.watchlist.core.query.model.WatchlistView
import com.qstudio.investing.watchlist.core.query.repository.WatchlistViewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WatchlistRepository : CoroutineCrudRepository<Watchlist, String>

@Repository
class ReactiveWatchlistRepositoryAdapter(private val repo: WatchlistRepository) : WatchlistViewRepository {
    override suspend fun add(watchlistView: WatchlistView) {
        repo.save(Watchlist().apply {
            id = watchlistView.id
            userId = watchlistView.userId
            name = watchlistView.name
        })
    }

    override suspend fun getById(id: String): WatchlistView? {
        return repo.findById(id)?.let { WatchlistView(it.id, it.userId, it.name) }
    }

    override fun getAll(): Flow<WatchlistView> {
        return repo.findAll().map { WatchlistView(it.id, it.userId, it.name) }
    }

}