--liquibase formatted sql

--changeset quang_thai:rename_audit_fields
BEGIN TRANSACTION;

ALTER TABLE watchlist
    RENAME COLUMN createdDate TO created_date;

ALTER TABLE watchlist
    RENAME COLUMN lastModifiedDate TO last_modified_date;

COMMIT;


