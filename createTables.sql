DROP TABLE Like_Records;

DROP TABLE Like_Records_Comments;

DROP TABLE Dislike_Records;

DROP TABLE Dislike_Records_Comments;

DROP TABLE Tags;

DROP TABLE Post_Comments;

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
hasProfilePic boolean NOT NULL DEFAULT FALSE,
latitude decimal,
longitude decimal
);

-- sign in, Password123
INSERT INTO Users (username, email, password) VALUES ('test', 'jamgrah2@aol.com', '$2a$10$fD/hHGPW/gR0KA4bp.YiUOAiitM/5V3WSfPLWwFVMYpBZdHq/C2ne');

CREATE TABLE Posts
(
id SERIAL PRIMARY KEY,
text varchar(255) NOT NULL,
likes int NOT NULL DEFAULT 0,
dislikes int NOT NULL DEFAULT 0,
total int NOT NULL DEFAULT 0,
timestamp TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'GMT'), 
user_id int NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Post_Comments
(
id SERIAL PRIMARY KEY,
text varchar(255) NOT NULL,
likes int NOT NULL DEFAULT 0,
dislikes int NOT NULL DEFAULT 0,
total int NOT NULL DEFAULT 0,
timestamp TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'GMT'), 
user_id int NOT NULL,
masterPost_id int NOT NULL,
postIndex int NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (masterPost_id) REFERENCES Posts(id)
);

CREATE TABLE Like_Records
(
user_id int NOT NULL,
post_id int NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (post_id) REFERENCES Posts(id)
);

CREATE TABLE Like_Records_Comments
(
user_id int NOT NULL,
post_id int NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (post_id) REFERENCES Post_Comments(id)
);

CREATE TABLE Dislike_Records
(
user_id int NOT NULL,
post_id int NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (post_id) REFERENCES Posts(id)
);

CREATE TABLE Dislike_Records_Comments
(
user_id int NOT NULL,
post_id int NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (post_id) REFERENCES Post_Comments(id)
);

CREATE TABLE Tags
(
tag varchar(255) NOT NULL,
post_id int NOT NULL,
FOREIGN KEY (post_id) REFERENCES Posts(id)
);