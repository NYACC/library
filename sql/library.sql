SET
NAMES
utf8mb4;
SET
FOREIGN_KEY_CHECKS
=
0;

-- ----------------------------
-- Table structure for book_category
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category`
(
  `id`   int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_category
-- ----------------------------
INSERT INTO `book_category`
VALUES (1, 'category1');
INSERT INTO `book_category`
VALUES (2, 'category2');

-- ----------------------------
-- Table structure for book_detail
-- ----------------------------
DROP TABLE IF EXISTS `book_detail`;
CREATE TABLE `book_detail`
(
  `id`             int(11) NOT NULL AUTO_INCREMENT,
  `isbn`           varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category_id`    int(11) NULL DEFAULT NULL,
  `author`         varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rent`           int(1) NULL DEFAULT NULL,
  `rent_time`      datetime(0) NULL DEFAULT NULL,
  `rent_back_time` datetime(0) NULL DEFAULT NULL,
  `rent_user_id`   int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_detail
-- ----------------------------
INSERT INTO `book_detail`
VALUES (1, '123456', 'book1', 1, 'author1', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for library
-- ----------------------------
DROP TABLE IF EXISTS `library`;
CREATE TABLE `library`
(
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `location`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `curator_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of library
-- ----------------------------
INSERT INTO `library`
VALUES (1, 'Library1', 'China', 2);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`
(
  `id`         varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `level`      varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_detail`;
CREATE TABLE `user_detail`
(
  `id`       int(11) NOT NULL AUTO_INCREMENT,
  `user_id`  int(11) NOT NULL,
  `sex`      int(1) NULL DEFAULT NULL,
  `name`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_detail
-- ----------------------------
INSERT INTO `user_detail`
VALUES (1, 1, 1, 'Administator', 'China');
INSERT INTO `user_detail`
VALUES (2, 2, 1, 'Curator', 'China');

-- ----------------------------
-- Table structure for user_security
-- ----------------------------
DROP TABLE IF EXISTS `user_security`;
CREATE TABLE `user_security`
(
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `user_no`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mail`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `permission` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_no`(`user_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_security
-- ----------------------------
INSERT INTO `user_security`
VALUES (1, '1', '13912345678', 'admin@admin.com', '1', 1);
INSERT INTO `user_security`
VALUES (2, '2', '1391234678', 'admin@admin.com', '1', 2);

SET
FOREIGN_KEY_CHECKS
=
1;
