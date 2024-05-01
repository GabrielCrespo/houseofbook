CREATE TABLE tb_role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE tb_user_roles (
    id SERIAL PRIMARY KEY,
    user_id SERIAL NOT NULL,
    role_id SERIAL NOT NULL
);