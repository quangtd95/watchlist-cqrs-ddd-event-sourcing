package com.qstudio.investing.watchlist.adapter.rest.v1.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateWatchListRequest(
    @field:NotNull(message = "userId should not null")
    @field:NotBlank(message = "userId should not be blank")
    val userId: String,

    @field:NotNull(message = "name should not be null")
    @field:NotBlank(message = "name should not be blank")
    val name: String
) : BaseRequest()