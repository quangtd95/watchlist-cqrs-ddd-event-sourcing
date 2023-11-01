package com.qstudio.investing.watchlist_query.adapter.inbound.api.rest

import com.qstudio.investing.watchlist_query.adapter.inbound.api.rest.response.BaseResponse
import com.qstudio.investing.watchlist_query.adapter.inbound.api.rest.response.GetAllWatchlistResponse
import com.qstudio.investing.watchlist_query.adapter.inbound.api.rest.response.GetWatchlistResponse
import com.qstudio.investing.watchlist_query.core.dispatcher.WatchlistQueryDispatcher
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/watchlists")
class WatchlistController(
    private val queryDispatcher: WatchlistQueryDispatcher
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/{watchlistId}")
    suspend fun getWatchlist(@Valid @PathVariable watchlistId: String): BaseResponse<GetWatchlistResponse> {
        log.info("querying watchlist: $watchlistId")
        val watchlist = queryDispatcher.getWatchlistById(watchlistId)
        return BaseResponse.success(GetWatchlistResponse(watchlist))
    }

    @GetMapping
    suspend fun getAllWatchlist(@Param("userId") userId: String?): BaseResponse<GetAllWatchlistResponse> {
        log.info("querying all watchlist")
        val watchlists = queryDispatcher.getAllWatchlistBySpecification(userId)
        return BaseResponse.success(GetAllWatchlistResponse(watchlists))

    }
}