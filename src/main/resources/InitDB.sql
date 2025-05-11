create table tasks
(
    id          bigserial not null
        constraint tasks_pk
            primary key,
    date        date      not null,
    name        varchar   not null,
    description varchar
);