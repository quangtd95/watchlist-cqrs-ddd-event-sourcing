package com.qstudio.investing.watchlist_query.core.model

import java.time.LocalDateTime

data class WatchlistView(
    val id: String,
    var userId: String,
    var name: String,
    var createdDate: LocalDateTime? = null,
    var lastModifiedDate: LocalDateTime? = null,
)