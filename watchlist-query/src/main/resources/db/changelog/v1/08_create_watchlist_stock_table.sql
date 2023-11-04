--liquibase formatted sql

--changeset quang_thai:create_table_watchlist_stock
CREATE TABLE public.watchlist_stock
(
    id                 varchar(255) NOT NULL,
    watchlist_id       varchar(255) NOT NULL,
    symbol             varchar(255) NOT NULL,
    created_date       TIMESTAMP default CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP default CURRENT_TIMESTAMP,
    version            bigint,
    PRIMARY KEY (id)
);
