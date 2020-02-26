/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : oasys

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 24/02/2020 19:22:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `allow_comment` tinyint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `sign_in_date` date NOT NULL,
  `sign_in_time` time(0) NOT NULL,
  `sign_out_time` time(0) NOT NULL,
  `sign_out` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES (1, 1, '2019-12-04', '04:40:05', '04:51:49', 1);
INSERT INTO `attendance` VALUES (2, 1, '2019-12-06', '04:40:05', '04:40:05', 1);
INSERT INTO `attendance` VALUES (3, 1, '2019-12-08', '04:40:05', '04:40:05', 1);
INSERT INTO `attendance` VALUES (4, 1, '2019-12-20', '04:40:05', '04:40:05', 0);
INSERT INTO `attendance` VALUES (8, 1, '2019-12-22', '04:40:05', '04:40:05', 1);
INSERT INTO `attendance` VALUES (9, 1, '2019-12-24', '04:40:05', '04:40:05', 1);
INSERT INTO `attendance` VALUES (10, 1, '2019-12-25', '04:40:05', '04:40:05', 0);
INSERT INTO `attendance` VALUES (11, 1, '2019-12-30', '04:40:05', '04:40:05', 1);
INSERT INTO `attendance` VALUES (12, 1, '2019-12-31', '09:12:20', '09:12:49', 1);
INSERT INTO `attendance` VALUES (13, 1, '2020-01-01', '11:09:16', '11:09:20', 1);
INSERT INTO `attendance` VALUES (14, 1, '2020-01-02', '09:34:18', '09:34:18', 0);

-- ----------------------------
-- Table structure for attendance_time
-- ----------------------------
DROP TABLE IF EXISTS `attendance_time`;
CREATE TABLE `attendance_time`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `begin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `end` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance_time
-- ----------------------------
INSERT INTO `attendance_time` VALUES (1, '09:00', '17:00');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int(0) NOT NULL,
  `article_id` int(0) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int(0) NOT NULL,
  `parent_id` int(0) NOT NULL,
  `personal` tinyint(1) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (1, '根目录', '/', '文件夹', '-', 1, 0, 1, '2020-01-02 20:08:09');
INSERT INTO `file` VALUES (2, '文件夹11', '/', '文件夹', '-', 1, 1, 1, '2020-01-02 20:08:51');
INSERT INTO `file` VALUES (3, '文件1', '/1/文件1.jpg', 'jpg', '128k', 1, 2, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (4, '文件1', '/1/文件1.jpg', 'jpg', '128k', 1, 2, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (5, '啊啊', '/1/文件1.jpg', 'jpg', '128k', 1, 1, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (6, '文件3', '/1/文件1.jpg', 'jpg', '128k', 1, 1, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (7, '文件4', '/1/文件1.jpg', 'jpg', '128k', 1, 2, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (8, '文件5', '/1/文件1.jpg', 'jpg', '128k', 1, 1, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (9, '文件6', '/1/文件1.jpg', 'jpg', '128k', 1, 1, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (10, '文件7', '/1/文件1.jpg', 'jpg', '128k', 1, 2, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (11, '文件8', '/1/文件1.jpg', 'jpg', '128k', 1, 1, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (13, '文件10', '/1/文件1.jpg', '0', '128k', 1, 2, 1, '2020-01-02 20:10:13');
INSERT INTO `file` VALUES (15, '稳健', '/', '文件夹', '-', 1, 1, 0, '2020-01-03 17:07:09');
INSERT INTO `file` VALUES (16, '是多少', '/', '文件夹', '-', 1, 1, 1, '2020-01-03 17:57:21');
INSERT INTO `file` VALUES (19, '和护发素', '/', '文件夹', '-', 2, 1, 1, '2020-01-03 20:26:07');
INSERT INTO `file` VALUES (20, '返回', '/', '文件夹', '-', 2, 1, 0, '2020-01-03 20:57:57');
INSERT INTO `file` VALUES (24, '汉化', '/', '文件夹', '25', 1, 1, 0, '2020-01-04 12:15:58');
INSERT INTO `file` VALUES (25, '法国海军', '/', '文件夹', '-', 1, 2, 1, '2020-01-04 12:16:18');
INSERT INTO `file` VALUES (28, '的活动', '/', '文件夹', '-', 1, 2, 1, '2020-01-04 12:21:56');
INSERT INTO `file` VALUES (30, '8', '/file/8.jpg', 'jpg', '1001KB', 1, 25, 1, '2020-01-04 16:43:09');
INSERT INTO `file` VALUES (31, 'oasys', '/file/oasys.zip', 'zip', '55KB', 1, 2, 1, '2020-01-04 16:44:30');
INSERT INTO `file` VALUES (42, '定位的', '/', '文件夹', '-', 1, 1, 1, '2020-01-09 10:33:51');
INSERT INTO `file` VALUES (43, '俄国人', '/', '文件夹', '-', 1, 1, 1, '2020-01-09 10:34:19');
INSERT INTO `file` VALUES (44, '咕噜咕噜', '/', '文件夹', '-', 1, 2, 1, '2020-01-09 10:34:38');
INSERT INTO `file` VALUES (45, '的生活费', '/', '文件夹', '-', 1, 1, 1, '2020-01-09 10:37:54');
INSERT INTO `file` VALUES (46, '的时候都是', '/', '文件夹', '-', 1, 1, 1, '2020-01-09 10:40:05');
INSERT INTO `file` VALUES (47, '甘肃省', '/', '文件夹', '-', 1, 25, 1, '2020-01-09 10:41:45');
INSERT INTO `file` VALUES (48, '查询', '/', '文件夹', '-', 1, 1, 1, '2020-01-09 10:47:59');
INSERT INTO `file` VALUES (49, '寻找幸存者', '/', '文件夹', '-', 1, 47, 1, '2020-01-09 11:21:28');
INSERT INTO `file` VALUES (50, 'devcpp', '/file/devcpp.exe', 'exe', '2MB', 1, 47, 1, '2020-01-09 11:21:37');

-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leave
-- ----------------------------
INSERT INTO `leave` VALUES (1, 1, '2020-02-05', '2020-03-10', '哈佛案件发放马拉松免费空间你上课方式发马上发是发发发', '看法兰克福马拉科夫能看见三分喀什开发商看法兰克福马分喀什开发商', '婚假', 1);
INSERT INTO `leave` VALUES (2, 1, '2020-02-19', '2020-03-19', '结构设计刚开始美国纳斯达克刚开始的功能发了好人卡爱了就会卡斯能否尽快离开', '交给老师的开关打开柜门燃烧的玫瑰打开了婚纱法布雷加斯咖啡离开', '婚假', 2);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `has_read` tinyint(0) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `receiver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '阿基咯撒可怜见拉萨机ask', 1, '2020-02-24 15:21:42', 'citru');
INSERT INTO `notice` VALUES (2, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:38:05', 'citru');
INSERT INTO `notice` VALUES (3, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (4, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (5, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (6, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (7, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (8, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (9, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (10, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (11, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (12, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (13, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (14, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (15, '啊打算过段时间打算看到看见撒旦', 0, '2020-02-24 15:53:47', 'citru');
INSERT INTO `notice` VALUES (16, '你的请假已通过！', 0, '2020-02-24 18:16:33', 'citru');
INSERT INTO `notice` VALUES (17, '你的请假已通过！', 0, '2020-02-24 18:16:43', 'citru');
INSERT INTO `notice` VALUES (18, '你的请假已通过！', 1, '2020-02-24 18:17:09', 'citru');
INSERT INTO `notice` VALUES (19, '你的请假已通过！', 1, '2020-02-24 18:17:13', 'citru');
INSERT INTO `notice` VALUES (20, '员工二', 0, '2020-02-24 19:06:52', '你的请假通过了审核！');
INSERT INTO `notice` VALUES (21, '你的请假通过了审核！', 0, '2020-02-24 19:10:07', '主管一');
INSERT INTO `notice` VALUES (22, '你的请假通过了审核！', 0, '2020-02-24 19:16:38', 'citru');
INSERT INTO `notice` VALUES (23, '你的请假没有通过审核！', 0, '2020-02-24 19:16:56', 'citru');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '员工');
INSERT INTO `role` VALUES (2, '主管');
INSERT INTO `role` VALUES (3, '经理');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `synopsis` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `picture` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'citru', '$2a$10$en2JxasguDAPugsYRPdHGOTazazkihMqq3gC9yi8a8yBeQhpwFgCu', '一个普通员工', '/img/picture/1.jpg', 3);
INSERT INTO `user` VALUES (2, '主管一', '$2a$10$en2JxasguDAPugsYRPdHGOTazazkihMqq3gC9yi8a8yBeQhpwFgCu', '一个主管', '/img/picture/2.jpg', 2);
INSERT INTO `user` VALUES (3, '经理一', '$2a$10$en2JxasguDAPugsYRPdHGOTazazkihMqq3gC9yi8a8yBeQhpwFgCu', '一个经理', '/img/picture/3.jpg', 3);
INSERT INTO `user` VALUES (4, '员工二', '$2a$10$7nBcD/d6Z3P7Lec0UinZDuncdJJpS8VVwYzhsz8NlCl09HaWIffyS', '这是员工一', '/img/picture/4.jpg', 1);

SET FOREIGN_KEY_CHECKS = 1;
