create table role_table
(
    id   int not null AUTO_INCREMENT
        constraint role_table_pk
        primary key,
    name varchar(20) not null
);

create table user_table
(
    id bigint not null AUTO_INCREMENT
        constraint user_table_pk
        primary key,
    login varchar (50),
    email    varchar(50) NULL,
    password varchar(500),
    data_create timestamp default current_timestamp,
    data_update timestamp default current_timestamp,
    data_delete timestamp NULL,
);

create table users_roles
(

    user_id bigint references user_table(id),
    role_id int references role_table(id)
);

insert into role_table(name) values ('ROLE_ADMIN');
insert into role_table(name) values ('ROLE_USER');

insert into user_table(login,password,email) values ('admin','$2a$10$46ffDBTwbIKDJ5pYVZQYhOK93PlHgaQH6Lyt.EDXjCmPoOS8pXiqW','admin@admin.ru'),
                                                    ('user','$2a$10$/R.KSi.10o1rhHSP5PTtPevlYCBf9KWw1FW99hD2pmc/8Cj0Sxzzu','user@user.ru');

insert into users_roles(user_id,role_id) values (1,1),
                                                (1,2),
                                                (2,2);
