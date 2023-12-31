CREATE TABLE posts (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   uuid VARCHAR(255),
   name VARCHAR(255) NOT NULL,
   description VARCHAR(255) NOT NULL,
   picture OID,
   location VARCHAR(255) NOT NULL,
   user_id BIGINT,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   specie VARCHAR(255),
   race VARCHAR(255),
   sex VARCHAR(255),
   age VARCHAR(255),
   weight VARCHAR(255),
   castrated BOOLEAN,
   vaccinated BOOLEAN,
   ungerminated BOOLEAN,
   pedigree BOOLEAN,
   special_needs BOOLEAN,
   port SMALLINT,
   CONSTRAINT pk_posts PRIMARY KEY (id)
);

ALTER TABLE posts ADD CONSTRAINT FK_POSTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);