create table IF NOT EXISTS ism
(
    id         uuid not null default gen_random_uuid()
        primary key,
    created_at   timestamp(6),
    updated_at   timestamp(6),
    coming_lang  text,
    definition   text,
    gender       boolean,
    like_count   text,
    name_cry     text,
    name_lat     text,
    named_people text
);