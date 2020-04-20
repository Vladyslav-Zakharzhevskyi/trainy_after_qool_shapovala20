ALTER TABLE addresses DROP CONSTRAINT address_has_person_fk;

ALTER TABLE addresses
    ADD CONSTRAINT address_has_person_fk
        FOREIGN KEY (person_id) REFERENCES persons
            ON DELETE SET NULL;

ALTER TABLE persons_has_jobs DROP CONSTRAINT persons_has_jobs_person_id_fk;

ALTER TABLE persons_has_jobs
    ADD CONSTRAINT persons_has_jobs_person_id_fk
        FOREIGN KEY (person_id) REFERENCES persons
            ON DELETE cascade;



ALTER TABLE persons ADD CONSTRAINT unique_username UNIQUE (user_name);
