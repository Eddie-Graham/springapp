CREATE TABLE Users
(
username varchar(255) NOT NULL UNIQUE,
email varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL
);

CREATE TABLE Posts
(
text varchar(255) NOT NULL,
likes int NOT NULL,
dislikes int NOT NULL,
timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
username varchar(255) NOT NULL UNIQUE
);