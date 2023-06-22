create table IF NOT EXISTS confirmation_token
(
    id           uuid         not null default gen_random_uuid()
        primary key,
    confirmed_at timestamp(6),
    created_at   timestamp(6),
    expires_at   timestamp(6),
    token        varchar(255) not null,
    user_id      uuid         not null
        constraint user_id_fk
            references users
);