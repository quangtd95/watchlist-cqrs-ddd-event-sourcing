package com.qstudio.investing.watchlist_query.adapter.query.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "watchlist")
class Watchlist {
    @Id
    @Column("id")
    lateinit var id: String

    @Column("user_id")
    lateinit var userId: String

    @Column("name")
    lateinit var name: String

    @Version
    private var version: Long? = null
}