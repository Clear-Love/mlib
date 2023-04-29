CREATE TABLE `db_account` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
                            `username` varchar(255) DEFAULT NULL COMMENT '用户名',
                            `password` varchar(255) DEFAULT NULL COMMENT '密码',
                            `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                            `role` varchar(5) NOT NULL COMMENT '角色',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `unique_name` (`username`) USING BTREE COMMENT '用户名唯一' /*!80000 INVISIBLE */,
                            UNIQUE KEY `unique_email` (`email`) USING BTREE COMMENT '邮箱唯一' /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号';

CREATE TABLE `user_config` (
                             `level` int NOT NULL COMMENT '等级',
                             `exp` int NOT NULL COMMENT '经验',
                             `username` varchar(255) NOT NULL COMMENT '用户名',
                             PRIMARY KEY (`username`),
                             CONSTRAINT `user_config_db_account_username_fk` FOREIGN KEY (`username`) REFERENCES `db_account` (`username`),
                             CONSTRAINT `level` CHECK (((0 <= `level`) <= 6))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户配置';