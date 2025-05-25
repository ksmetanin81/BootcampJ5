create table goals
(
    id          bigserial
        constraint goals_pk
            primary key,
    name        varchar not null,
    motivation  varchar,
    resources   varchar,
    deadline    date,
    description varchar
);

alter table goals
    owner to admin;

create table tasks
(
    id          bigserial
        constraint tasks_pk
            primary key,
    date        date    not null,
    name        varchar not null,
    description varchar,
    goal_id     bigint  not null
        constraint tasks_goal_fk
            references goals
            on delete cascade
);

alter table tasks
    owner to admin;

create index tasks_goal_id_index
    on tasks (goal_id);