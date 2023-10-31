--liquibase formatted sql

--changeset quang_thai:create_watchlist_table
CREATE TABLE public.watchlist
(
    id   varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);
