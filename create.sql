CREATE TABLE `db_account` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`username`) USING BTREE COMMENT '用户名唯一' /*!80000 INVISIBLE */,
  UNIQUE KEY `unique_email` (`email`) USING BTREE COMMENT '邮箱唯一' /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci