package com.qstudio.investing.watchlist.adapter.rest.v1.response

import com.qstudio.investing.watchlist.adapter.rest.v1.request.BaseRequest
import jakarta.validation.constraints.NotBlank

data class CreateWatchListRequest(
    @field:NotBlank(message = "watchlist should not be empty")
    val name: String
) : BaseRequest()