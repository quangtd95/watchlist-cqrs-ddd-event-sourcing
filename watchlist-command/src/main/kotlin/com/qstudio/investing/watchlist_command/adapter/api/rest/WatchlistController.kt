package com.qstudio.investing.watchlist_command.adapter.api.rest

import com.qstudio.investing.watchlist_command.adapter.api.rest.request.CreateWatchListRequest
import com.qstudio.investing.watchlist_command.adapter.api.rest.request.RenameWatchListRequest
import com.qstudio.investing.watchlist_command.adapter.api.rest.response.BaseResponse
import com.qstudio.investing.watchlist_command.adapter.api.rest.response.CreateWatchListResponse
import com.qstudio.investing.watchlist_command.adapter.api.rest.response.WatchlistResponse
import com.qstudio.investing.watchlist_command.core.usecase.WatchlistCommandUseCase
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/{userId}")
class WatchlistController(
    private val watchlistCore: WatchlistCommandUseCase
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    suspend fun createUserWatchlist(
        @Valid @PathVariable userId: String
    ): BaseResponse<String> {
        log.info("creating user watchlist for user $userId")
        val id = watchlistCore.createUserWatchlist(userId)
        return BaseResponse.success(id)
    }

    @PostMapping("/watchlists")
    suspend fun createWatchlist(
        @Valid @PathVariable userId: String,
        @Valid @RequestBody request: CreateWatchListRequest
    ): BaseResponse<CreateWatchListResponse> {
        log.info("creating watchlist: $request; requestId = ${request.requestId}")
        val newId = watchlistCore.createWatchlist(userId, request.name)
        return BaseResponse.success(CreateWatchListResponse(WatchlistResponse(newId)))
    }

    @PatchMapping("/watchlists/{watchlistId}")
    suspend fun renameWatchlist(
        @Valid @PathVariable userId: String,
        @Valid @PathVariable @NotNull @NotBlank watchlistId: String,
        @Valid @RequestBody request: RenameWatchListRequest
    ): BaseResponse<Map<String, String>> {
        log.info("renaming watchlist: $request; requestId = ${request.requestId}")
        watchlistCore.renameWatchlist(userId, watchlistId, request.name)
        return BaseResponse.success(mapOf("result" to "rename request accepted"))
    }

    @PostMapping("/watchlists/{watchlistId}/stocks/{symbol}")
    suspend fun addStockToWatchlist(
        @Valid @PathVariable userId: String,
        @Valid @PathVariable @NotNull @NotBlank watchlistId: String,
        @Valid @PathVariable @NotNull @NotBlank symbol: String,
    ): BaseResponse<Map<String, String>> {
        log.info("add stock $symbol to watchlist: $watchlistId")
        watchlistCore.addStockToWatchlist(userId, watchlistId, symbol)
        return BaseResponse.success(mapOf("result" to "add stock accepted"))
    }


    @DeleteMapping("/watchlists/{watchlistId}/stocks/{symbol}")
    suspend fun removeStockFromWatchlist(
        @Valid @PathVariable userId: String,
        @Valid @PathVariable @NotNull @NotBlank watchlistId: String,
        @Valid @PathVariable @NotNull @NotBlank symbol: String,
    ): BaseResponse<Map<String, String>> {
        log.info("remove stock $symbol from watchlist: $watchlistId")
        watchlistCore.removeStockFromWatchlist(userId, watchlistId, symbol)
        return BaseResponse.success(mapOf("result" to "remove stock accepted"))
    }

}