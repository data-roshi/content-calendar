-- unquoted indentifiers sqlite are treated as case insensitive and creates CONTENT table, postgres does the opposite
CREATE TABLE IF NOT EXISTS content (
    id SERIAL PRIMARY KEY ,
    title varchar(255) NOT NULL,
    description text,
    status VARCHAR(20) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url VARCHAR(255)
);

INSERT INTO content (title, description, status, content_type, date_created)
VALUES ('my post', 'my post', 'IDEA', 'ARTICLE', current_timestamp);