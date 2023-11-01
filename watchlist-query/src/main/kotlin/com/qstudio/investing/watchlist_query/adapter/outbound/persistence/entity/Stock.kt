package com.qstudio.investing.watchlist_query.adapter.outbound.persistence.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "stock")
class Stock {
    @Id
    @Column("symbol")
    lateinit var symbol: String

    @Column("name")
    lateinit var name: String

    @CreatedDate
    @Column("created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column("last_modified_date")
    lateinit var lastModifiedDate: LocalDateTime

    @Version
    private var version: Long? = null
}