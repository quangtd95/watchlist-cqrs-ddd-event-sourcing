package com.qstudio.investing.watchlist_command.adapter.rest.v1

import com.qstudio.investing.watchlist_command.adapter.rest.v1.request.CreateWatchListRequest
import com.qstudio.investing.watchlist_command.adapter.rest.v1.request.RenameWatchListRequest
import com.qstudio.investing.watchlist_command.adapter.rest.v1.response.BaseResponse
import com.qstudio.investing.watchlist_command.adapter.rest.v1.response.CreateWatchListResponse
import com.qstudio.investing.watchlist_command.adapter.rest.v1.response.WatchlistResponse
import com.qstudio.investing.watchlist_command.core.command.type.CreateWatchlistCommand
import com.qstudio.investing.watchlist_command.core.command.type.RenameWatchlistCommand
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
@RequestMapping("/api/watchlist")
class WatchlistController(
    private val commandGateway: CommandGateway
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    suspend fun createWatchlist(@Valid @RequestBody request: CreateWatchListRequest): BaseResponse<CreateWatchListResponse> {
        log.info("creating watchlist: $request; requestId = ${request.requestId}")


        val newId = commandGateway
            .send<String>(
                CreateWatchlistCommand(
                watchlistId = UUID.randomUUID().toString(),
                userId = request.userId,
                name = request.name
                )
            ).toMono()
            .awaitSingle()
        return BaseResponse.success(CreateWatchListResponse(WatchlistResponse(newId)))
    }

    @PatchMapping("/{watchlistId}/rename")
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