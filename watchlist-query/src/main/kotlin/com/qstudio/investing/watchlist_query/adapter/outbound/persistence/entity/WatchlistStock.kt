package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("watchlist_stock")
class WatchlistStock {
    @Id
    @Column("id")
    lateinit var id: String

    @Column("watchlist_id")
    lateinit var watchlistId: String

    @Column("symbol")
    lateinit var symbol: String

    @CreatedDate
    @Column("created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column("last_modified_date")
    lateinit var lastModifiedDate: LocalDateTime

    @Version
    private var version: Long? = null
}