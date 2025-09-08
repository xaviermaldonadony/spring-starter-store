create table profiles
(
    id             bigint       not null
        primary key,
    bio            TEXT ,
    phone_mumber   varchar(15)  ,
    date_of_birth  date         ,
    loyalty_points int UNSIGNED DEFAULT 0,
    constraint Profile_users_id_fk
        foreign key (id) references users (id)
);
