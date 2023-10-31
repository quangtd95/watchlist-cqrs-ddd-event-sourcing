--liquibase formatted sql

--changeset quang_thai:add_version_to_watchlist_table
ALTER TABLE public.watchlist ADD COLUMN version bigint;
