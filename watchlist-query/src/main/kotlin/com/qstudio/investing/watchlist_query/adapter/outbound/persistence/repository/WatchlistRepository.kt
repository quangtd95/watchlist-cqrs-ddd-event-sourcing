package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.repository

import com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity.Watchlist
import com.qstudio.investing.watchlist_query.core.model.WatchlistView
import com.qstudio.investing.watchlist_query.core.repository.WatchlistViewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WatchlistRepository : CoroutineCrudRepository<Watchlist, String> {
    fun findAllByUserId(userId: String): Flow<Watchlist>
}

@Repository
class ReactiveWatchlistRepositoryAdapter(private val repo: WatchlistRepository) : WatchlistViewRepository {
    override suspend fun save(watchlistView: WatchlistView) {
        if (repo.existsById(watchlistView.id)) {
            val watchlist = repo.findById(watchlistView.id)!!
            watchlist.also {
                it.userId = watchlistView.userId
                it.name = watchlistView.name
            }
            repo.save(watchlist)
        } else {
            repo.save(Watchlist().also {
                it.id = watchlistView.id
                it.userId = watchlistView.userId
                it.name = watchlistView.name
            })
        }
    }


    override suspend fun getById(id: String): WatchlistView? {
        return repo.findById(id)?.let { WatchlistView(it.id, it.userId, it.name, it.createdDate, it.lastModifiedDate) }
    }

    override suspend fun getAll(): List<WatchlistView> {
        return repo
            .findAll()
            .map { WatchlistView(it.id, it.userId, it.name, it.createdDate, it.lastModifiedDate) }
            .toList()
    }

    override suspend fun getByUserId(userId: String): List<WatchlistView> {
        return repo
            .findAllByUserId(userId)
            .map { WatchlistView(it.id, it.userId, it.name, it.createdDate, it.lastModifiedDate) }
            .toList()
    }

}