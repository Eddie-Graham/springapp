CREATE TABLE Users
(
id SERIAL PRIMARY KEY,
username varchar(255) NOT NULL UNIQUE,
email varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL
);

CREATE TABLE Posts
(
id SERIAL PRIMARY KEY,
text varchar(255) NOT NULL,
likes int NOT NULL DEFAULT 0,
dislikes int NOT NULL DEFAULT 0,
timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
username varchar(255) NOT NULL,
FOREIGN KEY (username) REFERENCES Users(username)
);

CREATE TABLE Like_Records
(
user_id int NOT NULL,
post_id int NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (post_id) REFERENCES Posts(id)
);

CREATE TABLE Dislike_Records
(
user_id int NOT NULL,
post_id int NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (post_id) REFERENCES Posts(id)
);