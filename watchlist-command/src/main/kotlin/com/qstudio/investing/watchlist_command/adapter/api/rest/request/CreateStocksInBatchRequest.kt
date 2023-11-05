package com.qstudio.investing.watchlist_command.adapter.api.rest.request

import jakarta.validation.Valid

data class CreateStocksInBatchRequest(
    @Valid
    var stocks: List<CreateStockRequest>
) : BaseRequest()