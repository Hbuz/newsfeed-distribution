CREATE TABLE IF NOT EXISTS news( id serial PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  description VARCHAR (2000) NOT NULL,
  pubblicationData VARCHAR (50) NOT NULL,
  image VARCHAR (50) NOT NULL);
