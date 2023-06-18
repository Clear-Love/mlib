INSERT INTO  `role` (role) VALUES ('ADMIN'), ('USER');
# 添加两名用户，密码为123456
INSERT INTO `db_account` (`username`, `password`, `email`, `role_id`)
VALUES ('admin', '$2a$10$PLlnCAnXxT/11B.6kmi1CO9qGyFra0qpiygws08NrsEIMhOIk516W', NULL, '1'),
       ('user', '$2a$10$PLlnCAnXxT/11B.6kmi1CO9qGyFra0qpiygws08NrsEIMhOIk516W', NULL, '2');