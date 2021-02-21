DROP TABLE IF EXISTS users;
create table users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(255) not null,
    enabled boolean not null
);
DROP TABLE IF EXISTS authorities;
create table authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

--DROP TABLE IF EXISTS offers;
--
--CREATE TABLE offers (
--  id INT AUTO_INCREMENT  PRIMARY KEY,
--  name VARCHAR(250) NOT NULL,
--  validFrom TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
--  validTill TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP.
--  location VARCHAR(250) DEFAULT NULL,
--  images VARCHAR(250) NOT NULL
--);