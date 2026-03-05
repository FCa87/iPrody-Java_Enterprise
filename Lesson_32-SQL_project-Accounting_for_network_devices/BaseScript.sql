create schema networks;

create table networks.networks(
    id bigserial PRIMARY KEY,
    name varchar(255) not null unique,
    description text,
    created_at timestamp not null default current_timestamp
)

create table networks.devices(
    id bigserial PRIMARY KEY,
    network_id bigint not null references networks.networks(id),
    name varchar(255) not null,
    ip_address varchar(15) not null,
    mac_address varchar(17) unique,
    type varchar(50) not null,
    status varchar(50) not null,
    created_at timestamp not null default current_timestamp
)

create table networks.connections(
    id bigserial PRIMARY KEY,
    device_from_id bigint not null references networks.devices(id),
    device_to_id bigint not null references networks.devices(id),
    connection_type varchar(50) not null,
    status varchar(50) not null,
    created_at timestamp not null default current_timestamp
)