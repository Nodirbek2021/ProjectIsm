create table IF NOT EXISTS "users"
(
    id         uuid not null default gen_random_uuid(),
    created_at timestamp(6),
    updated_at timestamp(6),
    email      varchar(255),
    firstname  varchar(255),
    is_enabled boolean not null,
    lastname   varchar(255),
    password   varchar(255),
    role_id    uuid,

    CONSTRAINT "user_pkey" PRIMARY KEY ("id")
);

alter table "users" add constraint "role_id_fkey" foreign key ("role_id") references "roles"("id") on DELETE cascade on update no action;