DROP TABLE Like_Records;

DROP TABLE Dislike_Records;

DROP TABLE Posts;

DROP TABLE Users;

----------------------------------

CREATE TABLE Users
(
id SERIAL PRIMARY KEY,
username varchar(255) NOT NULL UNIQUE,
email varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL,
authority varchar(255) NOT NULL DEFAULT 'ROLE_USER',
enabled boolean NOT NULL DEFAULT TRUE,
hasProfilePic boolean NOT NULL DEFAULT FALSE
);

-- sign in
INSERT INTO Users (username, email, password) VALUES ('test', 'jamgrah2@aol.com', 'password');

CREATE TABLE Posts
(
id SERIAL PRIMARY KEY,
text varchar(255) NOT NULL,
likes int NOT NULL DEFAULT 0,
dislikes int NOT NULL DEFAULT 0,
total int NOT NULL DEFAULT 0,
timestamp TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'BST'), 
user_id int NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id)
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