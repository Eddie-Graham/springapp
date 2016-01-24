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
  id                  SERIAL PRIMARY KEY,
  username            VARCHAR(255) NOT NULL UNIQUE,
  email               VARCHAR(255) NOT NULL UNIQUE,
  password            VARCHAR(255) NOT NULL,
  authority           VARCHAR(255) NOT NULL DEFAULT 'ROLE_USER',
  enabled             BOOLEAN      NOT NULL DEFAULT TRUE,
  hasProfilePic       BOOLEAN      NOT NULL DEFAULT FALSE,
  latitude            DECIMAL,
  longitude           DECIMAL,
  registeredTimestamp TIMESTAMP    NOT NULL DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'GMT'),
  profileViews        INT          NOT NULL DEFAULT 0
);

-- sign in, Password123
INSERT INTO Users (username, email, authority, password)
VALUES ('test', 'jamgrah2@aol.com', 'ROLE_ADMIN', '$2a$10$fD/hHGPW/gR0KA4bp.YiUOAiitM/5V3WSfPLWwFVMYpBZdHq/C2ne');

CREATE TABLE Posts
(
  id        SERIAL PRIMARY KEY,
  text      VARCHAR(255) NOT NULL,
  likes     INT          NOT NULL DEFAULT 0,
  dislikes  INT          NOT NULL DEFAULT 0,
  total     INT          NOT NULL DEFAULT 0,
  timestamp TIMESTAMP    NOT NULL DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'GMT'),
  user_id   INT          NOT NULL,
  FOREIGN KEY (user_id) REFERENCES Users (id)
);

CREATE TABLE Post_Comments
(
  id            SERIAL PRIMARY KEY,
  text          VARCHAR(255) NOT NULL,
  likes         INT          NOT NULL DEFAULT 0,
  dislikes      INT          NOT NULL DEFAULT 0,
  total         INT          NOT NULL DEFAULT 0,
  timestamp     TIMESTAMP    NOT NULL DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'GMT'),
  user_id       INT          NOT NULL,
  masterPost_id INT          NOT NULL,
  postIndex     INT          NOT NULL,
  FOREIGN KEY (user_id) REFERENCES Users (id),
  FOREIGN KEY (masterPost_id) REFERENCES Posts (id)
);

CREATE TABLE Like_Records
(
  user_id INT NOT NULL,
  post_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES Users (id),
  FOREIGN KEY (post_id) REFERENCES Posts (id)
);

CREATE TABLE Like_Records_Comments
(
  user_id INT NOT NULL,
  post_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES Users (id),
  FOREIGN KEY (post_id) REFERENCES Post_Comments (id)
);

CREATE TABLE Dislike_Records
(
  user_id INT NOT NULL,
  post_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES Users (id),
  FOREIGN KEY (post_id) REFERENCES Posts (id)
);

CREATE TABLE Dislike_Records_Comments
(
  user_id INT NOT NULL,
  post_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES Users (id),
  FOREIGN KEY (post_id) REFERENCES Post_Comments (id)
);

CREATE TABLE Tags
(
  tag     VARCHAR(255) NOT NULL,
  post_id INT          NOT NULL,
  FOREIGN KEY (post_id) REFERENCES Posts (id)
);