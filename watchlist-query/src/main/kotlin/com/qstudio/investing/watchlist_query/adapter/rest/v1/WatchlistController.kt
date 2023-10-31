package com.qstudio.investing.watchlist_query.adapter.rest.v1

import com.qstudio.investing.watchlist_query.adapter.rest.v1.response.BaseResponse
import com.qstudio.investing.watchlist_query.adapter.rest.v1.response.GetAllWatchlistResponse
import com.qstudio.investing.watchlist_query.adapter.rest.v1.response.GetWatchlistResponse
import com.qstudio.investing.watchlist_query.core.query.model.WatchlistView
import com.qstudio.investing.watchlist_query.core.query.type.WatchlistGetAllQuery
import com.qstudio.investing.watchlist_query.core.query.type.WatchlistGetByIdQuery
import jakarta.validation.Valid
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping("/api/watchlist")
class WatchlistController(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/{watchlistId}")
    suspend fun getWatchlist(@Valid @PathVariable watchlistId: String): BaseResponse<GetWatchlistResponse> {
        log.info("querying watchlist: $watchlistId")
        val watchlist = queryGateway
            .query<WatchlistView?, WatchlistGetByIdQuery>(WatchlistGetByIdQuery(watchlistId))
            .toMono()
            .awaitSingleOrNull()

        return BaseResponse.success(GetWatchlistResponse(watchlist))
    }

    @GetMapping
    suspend fun getAllWatchlist(): BaseResponse<GetAllWatchlistResponse> {
        log.info("querying all watchlist")
        val watchlists = queryGateway
            .queryMany<WatchlistView, WatchlistGetAllQuery>(WatchlistGetAllQuery())
            .toMono()
            .awaitSingle()
        return BaseResponse.success(GetAllWatchlistResponse(watchlists))

    }
}