create table persons
(
    id         uuid default gen_random_uuid(),
    first_name varchar(20) not null,
    last_name  varchar(20),
    age        integer     not null,
    salary     real        not null,
    currency   varchar(3),

    constraint persons_id primary key (id)
);

create table addresses
(
    id           uuid default gen_random_uuid(),
    city         varchar(100),
    street       varchar(100) not null,
    building_num varchar(10)  not null,
    address_type varchar(15)  not null,
    person_id    uuid,

    constraint addresses_id primary key (id),
    constraint addresses_has_person foreign key (person_id) references persons (id) on delete set null
);


create table jobs
(
    id         uuid default gen_random_uuid(),
    position   varchar(100) not null,
    strategy   varchar(255),
    address_id uuid         not null,

    constraint jobs_id primary key (id),
    constraint jobs_has_address foreign key (address_id) references addresses (id) on DELETE restrict
);

create table persons_has_jobs
(
    person_id uuid not null,
    job_id    uuid not null,

    constraint persons_has_jobs_person_id foreign key (person_id) references persons (id) on DELETE restrict,
    constraint persons_has_jobs_job_id foreign key (job_id) references jobs (id) on DELETE restrict
);
