INSERT INTO tb_user (id, name, email, password, birth_date, created_at)
VALUES (nextval('tb_user_id_seq'), 'Admin', 'admin@email.com', '$2a$10$aYz0Qg56Cx2spumz6Vq88efpCG9G6EwH8V/NvIoOuDQ8uiAbZj2X2', '1971-01-01', NOW())
ON CONFLICT ON CONSTRAINT tb_user_email_key DO NOTHING;

INSERT INTO tb_user_roles (id, user_id, role_id) VALUES (nextval('tb_user_roles_id_seq'), 1, 1) ON CONFLICT ON CONSTRAINT tb_user_roles_pkey DO NOTHING;

INSERT INTO tb_role (id, name, created_at) VALUES (nextval('tb_role_id_seq'), 'ADMIN', NOW()) ON CONFLICT ON CONSTRAINT tb_role_name_key DO NOTHING;
INSERT INTO tb_role (id, name, created_at) VALUES (nextval('tb_role_id_seq'), 'CLIENT', NOW()) ON CONFLICT ON CONSTRAINT tb_role_name_key DO NOTHING;