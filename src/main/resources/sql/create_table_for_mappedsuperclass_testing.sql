create table activity_watching_tv
(
    id              uuid     default gen_random_uuid(),
    duration        double precision not null,
    name            varchar(100)     not null,
    appraisal_value smallint default 0,
    constraint id_activity_watching_tv primary key (id)
);


create table activity_development
(
    id          uuid default gen_random_uuid(),
    duration    double precision not null,
    language    varchar(100)     not null,
    used_device varchar(100)     not null,
    constraint id_activity_development primary key (id)
);


create table activity_meet_friend
(
    id          uuid default gen_random_uuid(),
    duration    double precision not null,
    person_name varchar(100)     not null,
    address     varchar(100)
);
