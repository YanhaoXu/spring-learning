create table if not exists Ingredient
(
    id   varchar(4)  not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Taco
(
    id        identity,
    name      varchar(50) not null,
    createdAt timestamp   not null
);

create table if not exists Taco_Ingredients
(
    taco       bigint     not null,
    ingredient varchar(4) not null
);

alter table Taco_Ingredients
    add foreign key (taco) references Taco (id);
alter table Taco_Ingredients
    add foreign key (ingredient) references Ingredient (id);

create table if not exists Taco_Order
(
    id             identity,
    deliveryName   varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity   varchar(50) not null,
    deliveryState  varchar(2)  not null,
    deliveryZip    varchar(10) not null,
    ccNumber       varchar(16) not null,
    ccExpiration   varchar(5)  not null,
    ccCVV          varchar(3)  not null,
    placedAt       timestamp   not null,
    user           bigint      not null
);

create table if not exists Taco_Order_Tacos
(
    tacoOrder bigint not null,
    taco      bigint not null
);

drop table if exists Users;
-- drop table if exists authorities;
-- drop index if exists ix_auth_username;

-- create table if not exists users
-- (
--     username varchar2(50) not null primary key,
--     password varchar2(50) not null,
--     enabled  char(1) default '1'
-- );

create table if not exists User
(
    id          identity,
    username    varchar2(50) not null unique,
    password    varchar2     not null,
    fullname    varchar(50),
    street      varchar(50),
    city        varchar(50),
    state       varchar(2),
    zip         varchar(10),
    phoneNumber varchar(16),
    enabled     char(1) default '1'
);

alter table Taco_Order
    add foreign key (user) references User (id);

-- create table if not exists authorities
-- (
--     username  varchar2(50) not null,
--     authority varchar2(50) not null,
--     constraint fk_authorities_users
--         foreign key (username) references user (username)
-- );
--
-- create unique index ix_auth_username
--     on authorities (username, authority);