CREATE TABLE tb_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    birth_date TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);