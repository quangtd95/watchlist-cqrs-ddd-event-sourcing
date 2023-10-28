package com.qstudio.investing.watchlist.adapter.rest.v1

import com.qstudio.investing.watchlist.adapter.rest.v1.response.BaseResponse
import com.qstudio.investing.watchlist.adapter.rest.v1.response.CreateWatchListRequest
import com.qstudio.investing.watchlist.adapter.rest.v1.response.CreateWatchListResponse
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/watchlist")
class WatchlistController {
    private val log = LoggerFactory.getLogger(WatchlistController::class.java)

    @PostMapping
    suspend fun createWatchlist(@Valid @RequestBody createWatchListRequest: CreateWatchListRequest): BaseResponse<Map<String, CreateWatchListResponse>> {
        log.info("creating watchlist with name: ${createWatchListRequest.name}")

        return BaseResponse.success(
            mapOf("watchlist" to CreateWatchListResponse("dummy id"))
        )
    }
}