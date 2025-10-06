create table if not exists inventory_reservation (
    id uuid primary key,
    order_id uuid not null,
    product_id uuid not null,
    qty integer not null,
    status varchar(16) not null,
    created_at timestamp without time zone not null default now()
);
