ALTER TABLE roles alter column created_at set default current_timestamp;
ALTER TABLE roles alter column updated_at set default current_timestamp;

ALTER TABLE users alter column created_at set default current_timestamp;
ALTER TABLE users alter column updated_at set default current_timestamp;

ALTER TABLE ism alter column created_at set default current_timestamp;
ALTER TABLE ism alter column updated_at set default current_timestamp;

ALTER TABLE confirmation_token alter column created_at set default current_timestamp;
