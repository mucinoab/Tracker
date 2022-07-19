-- Escribir un script para crear una tabla en SQL que contenga los siguientes
-- campos tasks (ID, CreationDate, UpdateDate, Title, Content), y otra tabla
-- que tenga los siguientes campos (ID, TaskID (con referencia a la tabla
-- anterior), CreationDate, UpdateDate, Comment, Author.

DROP TABLE IF EXISTS activities;
DROP TABLE IF EXISTS comments;

CREATE TABLE activities (
  id             INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  creation_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  title          TEXT NOT NULL,
  content        TEXT NOT NULL
);

CREATE TABLE comments (
  id             INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  activitiy_id   INTEGER NOT NULL,
  creation_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  author         TEXT NOT NULL,
  content        TEXT NOT NULL,

  FOREIGN KEY(activitiy_id) REFERENCES activities(id)
);

INSERT INTO activities (title, content) VALUES ("Actividad 1", "Lorem ipsum");
INSERT INTO comments (activitiy_id, author, content) VALUES (1, "Bruno", "Test comment");
