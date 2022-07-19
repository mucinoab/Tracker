package com.bruno.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class TrackerApplication implements CommandLineRunner {
    @Autowired
    JdbcTemplate db;

    public static void main(String[] args) {
        SpringApplication.run(TrackerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        // HACK: All these queries are used to create mock data.

        db.execute("DROP TABLE IF EXISTS activities;");
        db.execute("DROP TABLE IF EXISTS comments;");

        db.execute(" CREATE TABLE activities (id IDENTITY PRIMARY KEY NOT NULL, creation_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, update_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, title          TEXT NOT NULL, content        TEXT NOT NULL);");
        db.execute("CREATE TABLE comments ( id             IDENTITY PRIMARY KEY NOT NULL, activity_id   INTEGER NOT NULL, creation_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, update_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, author         TEXT NOT NULL, content        TEXT NOT NULL, FOREIGN KEY(activity_id) REFERENCES activities(id));");

        db.execute("INSERT INTO activities (title, content) VALUES ('Actividad 1', 'Contenido 1');");
        db.execute("INSERT INTO comments (activity_id, author, content) VALUES (1, 'rodolfo', 'Test comment 1.1');");
        db.execute("INSERT INTO comments (activity_id, author, content) VALUES (1, 'cuau', 'Test comment 1.2');");

        db.execute("INSERT INTO activities (title, content) VALUES ('Actividad 2', 'Contenido 2');");
        db.execute("INSERT INTO comments (activity_id, author, content) VALUES (2, 'paco', 'Test comment 2.1');");

        db.execute("INSERT INTO activities (title, content) VALUES ('Actividad 3', 'Post without comments');");
    }
}
