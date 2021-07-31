/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : django

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 31/07/2021 23:13:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `phone` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (3, '李司辰', '123', '1242', 'lsc');
INSERT INTO `users` VALUES (4, '崔器', '123', '543', 'cq');
INSERT INTO `users` VALUES (5, '姚汝能', '123', '4324', 'yrn');
INSERT INTO `users` VALUES (29, 'kriswu', '123', '123', '432@fsf.com');
INSERT INTO `users` VALUES (30, 'kriswu', '123', '123', '432@fsf.com');
INSERT INTO `users` VALUES (31, 'kriswu', '123', '123', '432@fsf.com');
INSERT INTO `users` VALUES (32, '檀棋', '123', ' 1235', 'tq');
INSERT INTO `users` VALUES (33, 'michael', '123', 'MAN', 'zx');
INSERT INTO `users` VALUES (34, 'kriswu', '123', '123', '432@fsf.com');
INSERT INTO `users` VALUES (35, 'kriswu', '123', '123', '432@fsf.com');
INSERT INTO `users` VALUES (36, '1rr3534t34t43t23', '4234', '3454', 'rsjd@dlksj.com');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
