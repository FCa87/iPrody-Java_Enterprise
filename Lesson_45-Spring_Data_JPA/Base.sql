create schema if not exists springdatajpa

create table if not exists springdatajpa.orders (
    id BIGSERIAL PRIMARY key,
    orderName VARCHAR(255) NOT null,
    amount DECIMAL(10, 2),
    currency VARCHAR(3)
);

insert into springdatajpa.orders (ordername, amount, currency) values ('Perevod1', 566.52, 'RUB');
insert into springdatajpa.orders (ordername, amount, currency) values ('Perevod2', 700.00, 'USD');
insert into springdatajpa.orders (ordername, amount, currency) values ('Perevod3', 200.00, 'EUR');