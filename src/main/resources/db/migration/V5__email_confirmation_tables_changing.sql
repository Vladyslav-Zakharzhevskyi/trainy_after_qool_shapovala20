ALTER TABLE email_confirmation_token DROP CONSTRAINT email_token_has_person;

ALTER TABLE email_confirmation_token
    ADD CONSTRAINT email_token_has_person
        FOREIGN KEY (person_id) REFERENCES persons
            ON DELETE CASCADE;

DROP TABLE IF EXISTS advertisement;


CREATE TABLE IF NOT EXISTS position
(
    id       SERIAL PRIMARY KEY,
    position VARCHAR(255) NOT NULL,
    type     VARCHAR(255) NOT NULL
);

ALTER TABLE persons
    ADD COLUMN position_id INTEGER DEFAULT NULL;

ALTER TABLE persons
    ADD CONSTRAINT person_has_position_fk
        FOREIGN KEY (position_id) REFERENCES position (id);


INSERT INTO position (position, type)
VALUES ('Java Developer', 'BASE'),
       ('Marketolog', 'BASE'),
       ('Buhgalter', 'BASE');




