CREATE TABLE offer
(
    id                 BIGSERIAL,
    message            TEXT         NOT NULL,
    date_created       TIMESTAMP(0) NOT NULL,
    date_updated       TIMESTAMP(0),
    author             uuid         NOT NULL,
    posted_anonymously BOOLEAN DEFAULT FALSE,
    archived           BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)
);

ALTER TABLE offer ADD CONSTRAINT offer_has_author
    FOREIGN KEY (author) REFERENCES person(id) ON DELETE SET NULL;

