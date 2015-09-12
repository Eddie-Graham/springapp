CREATE TABLE Users
(
username varchar(255) NOT NULL UNIQUE,
email varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL
);

CREATE TABLE Posts
(
text varchar(255) NOT NULL,
likes int NOT NULL DEFAULT 0,
dislikes int NOT NULL DEFAULT 0,
timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
username varchar(255) NOT NULL,
FOREIGN KEY (username) REFERENCES Users(username)
);