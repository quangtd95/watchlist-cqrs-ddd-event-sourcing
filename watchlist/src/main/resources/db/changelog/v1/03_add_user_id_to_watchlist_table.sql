--liquibase formatted sql

--changeset quang_thai:add_version_to_watchlist_table
ALTER TABLE public.watchlist
    ADD COLUMN user_id varchar(255) NOT NULL default ('') ;
