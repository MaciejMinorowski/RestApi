CREATE TABLE POST (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(400) NOT NULL,
    content VARCHAR(2000) NULL,
    created timestamp
);

CREATE TABLE COMMENT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    content VARCHAR(2000) NULL,
    created timestamp
);

ALTER TABLE COMMENT
    ADD CONSTRAINT comment_post_id
    FOREIGN KEY (post_id) REFERENCES post(id);


INSERT INTO POST (id, title, created, content) VALUES (1, 'First Post', CURRENT_TIMESTAMP, 'This is the content of the first post.');
INSERT INTO POST (id, title, created, content) VALUES (2, 'Second Post', CURRENT_TIMESTAMP, 'This is the content of the second post.');

INSERT INTO COMMENT (id, post_id, content, created) VALUES (1, 1, 'This is a comment on the first post.', CURRENT_TIMESTAMP);
INSERT INTO COMMENT (id, post_id, content, created) VALUES (2, 1, 'This is a second comment on the first post.', CURRENT_TIMESTAMP);
INSERT INTO COMMENT (id, post_id, content, created) VALUES (3, 2, 'This is a comment on the second post.', CURRENT_TIMESTAMP);
INSERT INTO COMMENT (id, post_id, content, created) VALUES (4, 2, 'This is a second comment on the first post.', CURRENT_TIMESTAMP);
