package com.qstudio.investing.watchlist_command.adapter.api.rest.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RenameWatchListRequest(
    @field:NotNull(message = "newName should not be null")
    @field:NotBlank(message = "newName should not be blank")
    val name: String
) : BaseRequest()