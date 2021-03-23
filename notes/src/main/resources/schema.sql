
DROP TABLE if EXISTS note;
CREATE TABLE note (
  id bigint auto_increment PRIMARY KEY,
  title VARCHAR (255) ,
  content VARCHAR (255),
  date_creation TIMESTAMP ,
  date_edit TIMESTAMP ,
  owner VARCHAR (55),
  checked BOOLEAN DEFAULT TRUE
);
