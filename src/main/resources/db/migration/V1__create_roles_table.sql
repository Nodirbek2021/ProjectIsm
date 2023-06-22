CREATE TABLE IF NOT EXISTS "roles"
(
    id         uuid not null default gen_random_uuid(),
    created_at timestamp(6),
    updated_at timestamp(6),
    name       varchar(255),
    role_type  varchar(255),

    CONSTRAINT "role_pkey" PRIMARY KEY ("id")
);


