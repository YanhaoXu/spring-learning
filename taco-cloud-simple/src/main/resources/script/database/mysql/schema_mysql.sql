drop table if exists Taco_Ingredients;
drop table if exists Ingredient;
drop table if exists Taco_Order_Tacos;
drop table if exists Taco;
drop table if exists Taco_Order;
drop table if exists User;

create table if not exists Ingredient
(
    id   varchar(4)  not null,
    name varchar(25) not null,
    type varchar(10) not null,
    primary key (id)
);

create table if not exists Taco
(
    id        bigint auto_increment,
    name      varchar(50) not null,
    createdAt timestamp   not null,
    primary key (id)
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
    id             bigint auto_increment,
    deliveryName   varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity   varchar(50) not null,
    deliveryState  varchar(2)  not null,
    deliveryZip    varchar(10) not null,
    ccNumber       varchar(16) not null,
    ccExpiration   varchar(5)  not null,
    ccCVV          varchar(3)  not null,
    placedAt       timestamp   not null,
    primary key (id)
);

create table if not exists Taco_Order_Tacos
(
    tacoOrder bigint not null,
    taco      bigint not null
);

create table if not exists User
(
    id          bigint auto_increment,
    username    varchar(50)  not null unique,
    password    varchar(255) not null,
    fullname    varchar(50),
    street      varchar(50),
    city        varchar(50),
    state       varchar(2),
    zip         varchar(10),
    phoneNumber varchar(16),
    enabled     char(1) default '1',
    primary key (id)
);