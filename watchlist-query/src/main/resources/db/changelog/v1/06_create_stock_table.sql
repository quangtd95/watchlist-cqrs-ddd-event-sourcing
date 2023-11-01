--liquibase formatted sql

--changeset quang_thai:create_stock_table
CREATE TABLE public.stock
(
    symbol             varchar(255) NOT NULL,
    name               varchar(255) NOT NULL,
    created_date       TIMESTAMP default CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP default CURRENT_TIMESTAMP,
PRIMARY KEY (symbol)
);
