package com.qstudio.investing.watchlist.adapter.rest.v1

import com.qstudio.investing.watchlist.adapter.rest.v1.response.BaseResponse
import com.qstudio.investing.watchlist.adapter.rest.v1.request.CreateWatchListRequest
import com.qstudio.investing.watchlist.adapter.rest.v1.request.RenameWatchListRequest
import com.qstudio.investing.watchlist.adapter.rest.v1.response.CreateWatchListResponse
import com.qstudio.investing.watchlist.core.command.type.CreateWatchlistCommand
import com.qstudio.investing.watchlist.core.command.type.RenameWatchlistCommand
import com.qstudio.investing.watchlist.core.query.model.WatchlistView
import com.qstudio.investing.watchlist.core.query.type.WatchlistGetAllQuery
import com.qstudio.investing.watchlist.core.query.type.WatchlistGetByIdQuery
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/watchlist")
class WatchlistController(
    private val commandGateway: CommandGateway, private val queryGateway: QueryGateway
) {
    private val log = LoggerFactory.getLogger(WatchlistController::class.java)

    @GetMapping("/{watchlistId}")
    fun getWatchlist(@Valid @PathVariable watchlistId: String): BaseResponse<WatchlistView> {
        log.info("querying watchlist: $watchlistId")
        val watchlist = queryGateway.query(
            WatchlistGetByIdQuery(watchlistId),
            WatchlistView::class.java
        ).get()
        return BaseResponse.success(watchlist)
    }

    @GetMapping
    fun getAllWatchlist(): BaseResponse<List<WatchlistView>> {
        log.info("querying all watchlist")
        val watchlist = queryGateway.query(
            WatchlistGetAllQuery(), ResponseTypes.multipleInstancesOf(WatchlistView::class.java)
        ).get()
        return BaseResponse.success(watchlist)
    }

    @PostMapping
    suspend fun createWatchlist(@Valid @RequestBody request: CreateWatchListRequest): BaseResponse<Map<String, CreateWatchListResponse>> {
        log.info("creating watchlist: $request; requestId = ${request.requestId}")

        val watchlistId = commandGateway.sendAndWait<String>(
            CreateWatchlistCommand(
                watchlistId = UUID.randomUUID().toString(),
                userId = request.userId,
                name = request.name
            )
        )
        return BaseResponse.success(mapOf("watchlist" to CreateWatchListResponse(watchlistId)))
    }

    @PatchMapping("/{watchlistId}/rename")
    fun renameWatchlist(
        @Valid @PathVariable @NotNull @NotBlank watchlistId: String,
        @Valid @RequestBody request: RenameWatchListRequest
    ): BaseResponse<Map<String, String>> {
        log.info("renaming watchlist: $request; requestId = ${request.requestId}")

        commandGateway.send<Any>(RenameWatchlistCommand(watchlistId, request.newName))

        return BaseResponse.success(mapOf("result" to "rename request accepted"))
    }
}