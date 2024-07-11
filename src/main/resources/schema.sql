-- unquoted indentifiers are treated as case insensitive and creates CONTENT table, postgres does the opposite
CREATE TABLE IF NOT EXISTS content (
    id INTEGER AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    desc text,
    status VARCHAR(20) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO content (title, desc, status, content_type, date_created)
VALUES ('my post', 'my post', 'IDEA', 'ARTICLE', current_timestamp);