package com.qstudio.investing.watchlist.adapter.rest.v1.response

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateWatchListRequest(
    @field:NotBlank(message = "watchlist should not be empty")
    val name: String
)
