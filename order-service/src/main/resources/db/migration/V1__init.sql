create table if not exists orders (
    id uuid primary key,
    user_id varchar(64) not null,
    status varchar(16) not null,
    total_cents bigint not null,
    created_at timestamp without time zone not null default now(),
    updated_at timestamp without time zone not null default now()
);

create table if not exists order_item (
    id uuid primary key,
    order_id uuid not null references orders(id) on delete cascade,
    product_id uuid not null,
    qty integer not null,
    price_cents bigint not null
);

create table if not exists outbox_event (
    id uuid primary key,
    aggregate_type varchar(64) not null,
    aggregate_id uuid not null,
    event_type varchar(64) not null,
    payload jsonb not null,
    created_at timestamp without time zone not null default now(),
    published boolean not null default false
);
