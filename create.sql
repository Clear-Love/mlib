CREATE TABLE `book` (
                        `isbn` varchar(20) DEFAULT NULL COMMENT 'ISBN编号',
                        `language` varchar(50) DEFAULT NULL COMMENT '语言',
                        `collect_count` int NOT NULL DEFAULT '0' COMMENT '收藏次数',
                        `title` varchar(255) NOT NULL COMMENT '书名',
                        `author` varchar(512) NOT NULL COMMENT '作者',
                        `description` varchar(500) DEFAULT NULL COMMENT '简介',
                        `publish_date` varchar(100) DEFAULT NULL COMMENT '出版时间',
                        `book_id` int NOT NULL AUTO_INCREMENT COMMENT '图书id',
                        `publisher` varchar(255) DEFAULT NULL COMMENT '出版社',
                        `rating_num` varchar(20) DEFAULT NULL COMMENT '评分',
                        `price` varchar(255) DEFAULT NULL COMMENT '价格',
                        `cover_image` varchar(255) DEFAULT NULL COMMENT '封面',
                        PRIMARY KEY (`book_id`),
                        UNIQUE KEY `ISBN_unique` (`isbn`) COMMENT 'isbn'
) ENGINE=InnoDB AUTO_INCREMENT=46713 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图书'

CREATE TABLE `book_format` (
                               `format_id` int NOT NULL AUTO_INCREMENT COMMENT '格式id',
                               `format_name` varchar(50) NOT NULL COMMENT '格式名称',
                               PRIMARY KEY (`format_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图书格式'

CREATE TABLE `book_format_relation` (
                                        `format_id` int NOT NULL COMMENT '格式名称',
                                        `location_url` varchar(255) NOT NULL COMMENT '图书路径',
                                        `uuid` int NOT NULL,
                                        `book_id` int NOT NULL COMMENT '图书id',
                                        PRIMARY KEY (`book_id`,`format_id`),
                                        KEY `book_format_relation_book_format_format_id_fk` (`format_id`),
                                        CONSTRAINT `book_format_relation_book_book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
                                        CONSTRAINT `book_format_relation_book_format_format_id_fk` FOREIGN KEY (`format_id`) REFERENCES `book_format` (`format_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图书格式关系'

CREATE TABLE `book_type` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `type_name` varchar(50) NOT NULL COMMENT '图书类型名称',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `type_name` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图书类型'

CREATE TABLE `book_type_relation` (
                                      `book_id` int NOT NULL COMMENT '图书id',
                                      `type_id` int NOT NULL COMMENT '图书类型id',
                                      PRIMARY KEY (`book_id`,`type_id`),
                                      KEY `book_type_relation_fk_2` (`type_id`),
                                      CONSTRAINT `book_type_relation_fk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
                                      CONSTRAINT `book_type_relation_fk_2` FOREIGN KEY (`type_id`) REFERENCES `book_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图书类型标签关系'

CREATE TABLE `bookList` (
                            `list_id` int NOT NULL AUTO_INCREMENT COMMENT '书单ID',
                            `list_name` varchar(100) NOT NULL COMMENT '书单名称',
                            `description` varchar(500) DEFAULT NULL COMMENT '书单描述',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `create_user` int NOT NULL COMMENT '创建者',
                            `collect_count` int DEFAULT '0' COMMENT '收藏数量',
                            `book_count` int NOT NULL DEFAULT '0' COMMENT '包含图书数',
                            PRIMARY KEY (`list_id`),
                            KEY `bookList_db_account_id_fk` (`create_user`),
                            CONSTRAINT `bookList_db_account_id_fk` FOREIGN KEY (`create_user`) REFERENCES `db_account` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='书单'

CREATE TABLE `bookList_book_relation` (
                                          `book_id` int NOT NULL COMMENT '图书ID',
                                          `list_id` int NOT NULL COMMENT '书单ID',
                                          PRIMARY KEY (`book_id`,`list_id`),
                                          KEY `book_id` (`book_id`),
                                          KEY `list_id` (`list_id`),
                                          CONSTRAINT `bookList_book_relation_fk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
                                          CONSTRAINT `bookList_book_relation_fk_2` FOREIGN KEY (`list_id`) REFERENCES `bookList` (`list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='书单和书的关系'

CREATE TABLE `db_account` (
                              `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
                              `username` varchar(255) NOT NULL COMMENT '用户名',
                              `password` varchar(255) NOT NULL COMMENT '密码',
                              `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                              `phone_number` varchar(20) DEFAULT NULL COMMENT '电话',
                              `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
                              `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
                              `date_of_birth` datetime DEFAULT NULL COMMENT '生日',
                              `level` int NOT NULL DEFAULT '0' COMMENT '等级',
                              `exp` int NOT NULL DEFAULT '0' COMMENT '经验',
                              `avatar` varchar(100) DEFAULT NULL COMMENT '头像url',
                              `gender` enum('男','女','私密') NOT NULL DEFAULT '私密' COMMENT '性别',
                              `role_id` int NOT NULL COMMENT '角色id',
                              PRIMARY KEY (`user_id`),
                              UNIQUE KEY `unique_name` (`username`) USING BTREE COMMENT '用户名唯一' /*!80000 INVISIBLE */,
                              UNIQUE KEY `unique_email` (`email`) USING BTREE COMMENT '邮箱唯一' /*!80000 INVISIBLE */,
                              KEY `db_account_role_role_id_fk` (`role_id`),
                              CONSTRAINT `db_account_role_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
                              CONSTRAINT `user_level_check` CHECK ((`level` between 0 and 6))
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号'

CREATE TABLE `role` (
                        `role_id` int NOT NULL AUTO_INCREMENT,
                        `role` varchar(255) DEFAULT NULL,
                        `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`role_id`),
                        UNIQUE KEY `role_role_uindex` (`role`) COMMENT '角色名唯一'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user_collection_bookLists` (
                                             `user_id` int NOT NULL COMMENT '用户id',
                                             `bookList_id` int NOT NULL COMMENT '书单id',
                                             PRIMARY KEY (`user_id`,`bookList_id`),
                                             KEY `user_collection_bookLists_fk_2` (`bookList_id`),
                                             CONSTRAINT `user_collection_bookLists_fk_1` FOREIGN KEY (`user_id`) REFERENCES `db_account` (`user_id`),
                                             CONSTRAINT `user_collection_bookLists_fk_2` FOREIGN KEY (`bookList_id`) REFERENCES `bookList` (`list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user_collection_books` (
                                         `user_id` int NOT NULL COMMENT '用户id',
                                         `book_id` int NOT NULL COMMENT '图书id',
                                         PRIMARY KEY (`user_id`,`book_id`),
                                         KEY `user_collection_books_fk_2` (`book_id`),
                                         CONSTRAINT `user_collection_books_fk_1` FOREIGN KEY (`user_id`) REFERENCES `db_account` (`user_id`),
                                         CONSTRAINT `user_collection_books_fk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user_comment_book` (
                                     `book_id` int NOT NULL,
                                     `user_id` int NOT NULL,
                                     `content` varchar(500) DEFAULT NULL,
                                     `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                     `is_long` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为长评',
                                     PRIMARY KEY (`user_id`,`book_id`),
                                     KEY `book_id` (`book_id`),
                                     CONSTRAINT `user_comment_book_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
                                     CONSTRAINT `user_comment_book_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `db_account` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论图书'

CREATE TABLE `user_comment_bookList` (
                                         `bookList_id` int NOT NULL,
                                         `user_id` int NOT NULL,
                                         `content` varchar(500) DEFAULT NULL,
                                         `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                         PRIMARY KEY (`user_id`,`bookList_id`),
                                         KEY `comment_fk_1` (`bookList_id`),
                                         KEY `comment_fk_2` (`user_id`),
                                         CONSTRAINT `user_comment_book_fk_1` FOREIGN KEY (`bookList_id`) REFERENCES `bookList` (`list_id`),
                                         CONSTRAINT `user_comment_book_fk_2` FOREIGN KEY (`user_id`) REFERENCES `db_account` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论书单'

