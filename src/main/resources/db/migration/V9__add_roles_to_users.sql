CREATE TABLE roles
(
    id        BIGSERIAL,
    role_name VARCHAR(255),
    primary key (id)
);

INSERT INTO roles (role_name)
VALUES ('ADMIN'), ('MANAGER');

CREATE TABLE users_have_roles
(
    id      BIGSERIAL,
    user_id uuid,
    role_id BIGINT,
    primary key (id)
);

ALTER TABLE users_have_roles
    ADD CONSTRAINT role_added_to_user_FK FOREIGN KEY (user_id) REFERENCES person (id) ON DELETE CASCADE;

ALTER TABLE users_have_roles
    ADD CONSTRAINT role_value_added_to_user_FK FOREIGN KEY (role_id) REFERENCES roles (id);

INSERT INTO users_have_roles (user_id, role_id)
VALUES ((select id from person where user_name = 'vlad_'), (select id from roles where role_name = 'ADMIN'));


