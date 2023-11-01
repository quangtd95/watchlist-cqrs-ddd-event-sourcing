--liquibase formatted sql

--changeset quang_thai:add_version_to_stock_table
ALTER TABLE public.stock ADD COLUMN version bigint;
