--DROP TABLE IF EXISTS comment;
--DROP TABLE IF EXISTS post;

CREATE TABLE IF NOT EXISTS post (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS comment (
    id BIGSERIAL PRIMARY KEY,
    post_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE
);

CREATE INDEX idx_post_deleted ON post (deleted);
CREATE INDEX idx_comment_deleted ON comment (deleted);
CREATE INDEX idx_comment_post_id ON comment (post_id);

