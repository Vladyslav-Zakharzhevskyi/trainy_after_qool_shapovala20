create table system_properties
(
    id    serial primary key,
    key   varchar(255) not null unique,
    value varchar(255) not null
);

insert INTO system_properties (key, value)
VALUES ('CONFIRM_EMAILS_ENABLED', TRUE);

alter table persons
    add email varchar(40) not null default '',
    add email_confirmed boolean default true;


create table email_confirmation_token
(
    id           serial primary key,
    token        varchar(255) not null unique,
    person_id    uuid         not null,
    date_expired timestamp(0) not null
);



alter table email_confirmation_token
    add constraint email_token_has_person foreign key (person_id) references persons(id);


