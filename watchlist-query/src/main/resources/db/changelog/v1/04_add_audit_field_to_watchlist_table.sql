--liquibase formatted sql

--changeset quang_thai:add_auditfield_to_watchlist_table
ALTER TABLE watchlist
    ADD COLUMN createdDate TIMESTAMP default CURRENT_TIMESTAMP,
    ADD COLUMN lastModifiedDate TIMESTAMP default CURRENT_TIMESTAMP;

