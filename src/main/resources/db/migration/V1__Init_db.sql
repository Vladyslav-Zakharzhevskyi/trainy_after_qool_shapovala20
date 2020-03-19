create table activity_development
(
    id          uuid             not null,
    duration    double precision not null,
    language    varchar(100)     not null,
    used_device varchar(100)     not null,
    primary key (id)
);

create table activity_meet_friend
(
    id          uuid             not null,
    duration    double precision not null,
    address     varchar(100)     not null,
    person_name varchar(100)     not null,
    primary key (id)
);

create table activity_watching_tv
(
    id              uuid             not null,
    duration        double precision not null,
    appraisal_value integer,
    name            varchar(100)     not null,
    primary key (id)
);

create table addresses
(
    id           uuid         not null,
    address_type varchar(15)  not null,
    building_num varchar(10)  not null,
    city         varchar(100) not null,
    street       varchar(100) not null,
    person_id    uuid,
    primary key (id)
);

create table jobs
(
    id         uuid         not null,
    position   varchar(100) not null,
    strategy   varchar(255),
    address_id uuid         not null,
    primary key (id)
);

create table persons
(
    id         uuid        not null,
    user_name  varchar(20) not null,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    password   varchar(60) not null,
    age        integer,
    salary     double precision,
    currency   varchar(3),
    primary key (id)
);




create table persons_has_jobs
(
    person_id uuid not null,
    job_id    uuid not null
);


alter table if exists addresses
    add constraint address_has_person_FK foreign key (person_id) references persons;

alter table if exists jobs
    add constraint job_has_address_FK foreign key (address_id) references addresses;

alter table if exists persons_has_jobs
    add constraint persons_has_jobs_job_id_FK foreign key (job_id) references jobs;

alter table if exists persons_has_jobs
    add constraint persons_has_jobs_person_id_FK foreign key (person_id) references persons;
