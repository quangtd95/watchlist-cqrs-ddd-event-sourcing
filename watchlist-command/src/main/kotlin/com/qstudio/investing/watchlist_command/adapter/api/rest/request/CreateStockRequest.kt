package com.qstudio.investing.watchlist_command.adapter.api.rest.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateStockRequest(
    @field:NotNull(message = "symbol should not be null")
    @field:NotBlank(message = "symbol should not be blank")
    val symbol: String,

    @field:NotNull(message = "name should not be null")
    @field:NotBlank(message = "name should not be blank")
    val name: String
) : BaseRequest()