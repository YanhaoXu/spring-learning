create table if not exists tb_user
(
    USER_ID     BIGINT not null primary key auto_increment,
    USER_NAME   varchar(100),
    CREATE_TIME TIMESTAMP,
    UPDATE_TIME TIMESTAMP
);
