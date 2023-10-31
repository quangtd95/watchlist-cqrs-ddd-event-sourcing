package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.repository

import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Watchlist
import com.qstudio.investing.watchlist_query.core.model.WatchlistView
import com.qstudio.investing.watchlist_query.core.repository.WatchlistViewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WatchlistRepository : CoroutineCrudRepository<Watchlist, String>

@Repository
class ReactiveWatchlistRepositoryAdapter(private val repo: WatchlistRepository) : WatchlistViewRepository {
    override suspend fun save(watchlistView: WatchlistView) {
        if (repo.existsById(watchlistView.id)) {
            val watchlist = repo.findById(watchlistView.id)!!
            watchlist.apply {
                this.userId = watchlistView.userId
                this.name = watchlistView.name
            }
            repo.save(watchlist)
        } else {
            repo.save(Watchlist().apply {
                id = watchlistView.id
                userId = watchlistView.userId
                name = watchlistView.name
            })
        }
    }


    override suspend fun getById(id: String): WatchlistView? {
        return repo.findById(id)?.let { WatchlistView(it.id, it.userId, it.name) }
    }

    override fun getAll(): Flow<WatchlistView> {
        return repo.findAll().map { WatchlistView(it.id, it.userId, it.name) }
    }

}