create table if not exists product (
    id uuid primary key,
    sku varchar(64) not null unique,
    name varchar(255) not null,
    description text,
    price_cents bigint not null,
    stock_qty integer not null,
    created_at timestamp without time zone not null default now(),
    updated_at timestamp without time zone not null default now()
);
