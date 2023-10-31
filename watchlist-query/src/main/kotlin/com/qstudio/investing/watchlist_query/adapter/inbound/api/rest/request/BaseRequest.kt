package com.qstudio.investing.watchlist_query.adapter.inbound.api.rest.request

import java.util.UUID

open class BaseRequest(
    val requestId: String = UUID.randomUUID().toString(),
) {
    override fun toString(): String {
        return "requestId=$requestId"
    }
}