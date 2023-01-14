create table if not exists tb_user
(
    USER_ID     BIGINT not null primary key auto_increment,
    USER_NAME   VARCHAR(100) DEFAULT NULL,
    EMAIL       VARCHAR(100) DEFAULT NULL,
    CARD_NO     VARCHAR(17)  DEFAULT NULL,
    NICK_NAME   VARCHAR(10)  DEFAULT NULL,
    SEX         INT          DEFAULT 0,
    AGE         INT          DEFAULT 0,
    CREATE_TIME TIMESTAMP,
    UPDATE_TIME TIMESTAMP
);
