create table advertisement
(
    id            uuid         not null,
    title         varchar(255) not null,
    type          varchar(255) not null,
    description   varchar(255) not null,
    cost          integer      not null,
    room_count    integer      not null,
    floor         integer      not null,
    is_bargain    bool         not null default false,
    creation_date timestamp    not null default now(),
    update_date   timestamp,
    client_name   varchar(255) not null,
    client_email  varchar(255) not null,
    client_phone  varchar(255),
    client_city   varchar(255) not null
);









