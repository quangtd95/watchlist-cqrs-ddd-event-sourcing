package com.qstudio.investing.watchlist_command.adapter.api.rest

import com.qstudio.investing.watchlist_command.adapter.api.rest.request.CreateWatchListRequest
import com.qstudio.investing.watchlist_command.adapter.api.rest.request.RenameWatchListRequest
import com.qstudio.investing.watchlist_command.adapter.api.rest.response.BaseResponse
import com.qstudio.investing.watchlist_command.adapter.api.rest.response.CreateWatchListResponse
import com.qstudio.investing.watchlist_command.adapter.api.rest.response.WatchlistResponse
import com.qstudio.investing.watchlist_command.core.type.CreateUserWatchlistCommand
import com.qstudio.investing.watchlist_command.core.type.CreateWatchlistCommand
import com.qstudio.investing.watchlist_command.core.type.RenameWatchlistCommand
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import reactor.kotlin.core.publisher.toMono
import java.util.*

@RestController
@RequestMapping("/api/users/{userId}")
class WatchlistCommandDispatcher(
    private val commandGateway: CommandGateway
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    suspend fun createUserWatchlist(
        @Valid @PathVariable userId: String
    ): BaseResponse<String> {
        log.info("creating user watchlist for user $userId")
        val id = commandGateway.send<String>(CreateUserWatchlistCommand(userId)).toMono().awaitSingle()
        return BaseResponse.success(id)
    }

    @PostMapping("/watchlists")
    suspend fun createWatchlist(
        @Valid @PathVariable userId: String, @Valid @RequestBody request: CreateWatchListRequest
    ): BaseResponse<CreateWatchListResponse> {
        log.info("creating watchlist: $request; requestId = ${request.requestId}")

        val command = CreateWatchlistCommand(userId, request.name)
        val newId = commandGateway
            .send<String>(command).toMono()
            .awaitSingle()
        return BaseResponse.success(CreateWatchListResponse(WatchlistResponse(newId)))
    }

    @PatchMapping("/watchlists/{watchlistId}/rename")
    suspend fun renameWatchlist(
        @Valid @PathVariable @NotNull @NotBlank watchlistId: String,
        @Valid @RequestBody request: RenameWatchListRequest
    ): BaseResponse<Map<String, String>> {
        log.info("renaming watchlist: $request; requestId = ${request.requestId}")

        commandGateway
            .send<Any?>(RenameWatchlistCommand(watchlistId, request.newName))
            .toMono()
            .awaitSingleOrNull()

        return BaseResponse.success(mapOf("result" to "rename request accepted"))
    }
}