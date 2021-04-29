/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : testspring

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 29/04/2021 20:28:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (3);

-- ----------------------------
-- Table structure for res_admin
-- ----------------------------
DROP TABLE IF EXISTS `res_admin`;
CREATE TABLE `res_admin`  (
  `raid` int(0) NOT NULL,
  `raname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rapwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`raid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of res_admin
-- ----------------------------
INSERT INTO `res_admin` VALUES (1, '蔡徐坤', 'aaaa');
INSERT INTO `res_admin` VALUES (2, '蔡徐坤', 'aaaa');

-- ----------------------------
-- Table structure for res_food
-- ----------------------------
DROP TABLE IF EXISTS `res_food`;
CREATE TABLE `res_food`  (
  `fid` int(0) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `normprice` double NOT NULL,
  `realprice` double NOT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of res_food
-- ----------------------------

-- ----------------------------
-- Table structure for res_order
-- ----------------------------
DROP TABLE IF EXISTS `res_order`;
CREATE TABLE `res_order`  (
  `roid` int(0) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliverytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ordertime` datetime(6) NULL DEFAULT NULL,
  `payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ps` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`roid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of res_order
-- ----------------------------

-- ----------------------------
-- Table structure for res_order_item
-- ----------------------------
DROP TABLE IF EXISTS `res_order_item`;
CREATE TABLE `res_order_item`  (
  `roiid` int(0) NOT NULL,
  `dealprice` double NULL DEFAULT NULL,
  `fid` int(0) NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  `roid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`roiid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of res_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for res_order_item_temp
-- ----------------------------
DROP TABLE IF EXISTS `res_order_item_temp`;
CREATE TABLE `res_order_item_temp`  (
  `roitid` int(0) NOT NULL,
  `fid` int(0) NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  `quittime` datetime(6) NULL DEFAULT NULL,
  `userid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`roitid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of res_order_item_temp
-- ----------------------------

-- ----------------------------
-- Table structure for res_user
-- ----------------------------
DROP TABLE IF EXISTS `res_user`;
CREATE TABLE `res_user`  (
  `userid` int(0) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of res_user
-- ----------------------------

-- ----------------------------
-- Table structure for resadmin
-- ----------------------------
DROP TABLE IF EXISTS `resadmin`;
CREATE TABLE `resadmin`  (
  `raid` int(0) NOT NULL AUTO_INCREMENT,
  `raname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rapwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`raid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resadmin
-- ----------------------------
INSERT INTO `resadmin` VALUES (1, 'a', '0cc175b9c0f1b6a831c399e269772661');

-- ----------------------------
-- Table structure for resfood
-- ----------------------------
DROP TABLE IF EXISTS `resfood`;
CREATE TABLE `resfood`  (
  `fid` int(0) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `normprice` decimal(8, 2) NULL DEFAULT NULL,
  `realprice` decimal(8, 2) NULL DEFAULT NULL,
  `detail` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fphoto` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resfood
-- ----------------------------
INSERT INTO `resfood` VALUES (1, '素炒莴笋丝', 22.00, 20.00, '营养丰富', '500008.jpg');
INSERT INTO `resfood` VALUES (2, '蛋炒饭', 22.00, 20.00, '营养丰富', '500022.jpg');
INSERT INTO `resfood` VALUES (3, '酸辣鱼', 42.00, 40.00, '营养丰富', '500023.jpg');
INSERT INTO `resfood` VALUES (4, '鲁粉', 12.00, 10.00, '营养丰富', '500024.jpg');
INSERT INTO `resfood` VALUES (5, '西红柿蛋汤', 12.00, 10.00, '营养丰富', '500025.jpg');
INSERT INTO `resfood` VALUES (6, '炖鸡', 102.00, 100.00, '营养丰富', '500026.jpg');
INSERT INTO `resfood` VALUES (7, '炒鸡', 12.00, 10.00, '营养丰富', '500033.jpg');
INSERT INTO `resfood` VALUES (8, '炒饭', 12.00, 10.00, '营养丰富', '500034.jpg');
INSERT INTO `resfood` VALUES (9, '手撕前女友', 12.00, 10.00, '营养丰富', '500035.jpg');
INSERT INTO `resfood` VALUES (10, '面条', 12.00, 10.00, '营养丰富', '500036.jpg');
INSERT INTO `resfood` VALUES (11, '端菜', 12.00, 10.00, '营养丰富', '500038.jpg');
INSERT INTO `resfood` VALUES (12, '酸豆角', 12.00, 10.00, '营养丰富', '500041.jpg');

-- ----------------------------
-- Table structure for resorder
-- ----------------------------
DROP TABLE IF EXISTS `resorder`;
CREATE TABLE `resorder`  (
  `roid` int(0) NOT NULL AUTO_INCREMENT,
  `userid` int(0) NULL DEFAULT NULL,
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ordertime` date NULL DEFAULT NULL,
  `deliverytime` date NULL DEFAULT NULL,
  `ps` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`roid`) USING BTREE,
  INDEX `fk_resorder`(`userid`) USING BTREE,
  CONSTRAINT `fk_resorder` FOREIGN KEY (`userid`) REFERENCES `resuser` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resorder
-- ----------------------------
INSERT INTO `resorder` VALUES (1, 1, '湖南省衡阳市', '13878789999', '2021-04-28', '2021-04-28', '送餐上门', 0);

-- ----------------------------
-- Table structure for resorderitem
-- ----------------------------
DROP TABLE IF EXISTS `resorderitem`;
CREATE TABLE `resorderitem`  (
  `roiid` int(0) NOT NULL AUTO_INCREMENT,
  `roid` int(0) NULL DEFAULT NULL,
  `fid` int(0) NULL DEFAULT NULL,
  `dealprice` decimal(8, 2) NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`roiid`) USING BTREE,
  INDEX `fk_resorderitem_roid`(`roid`) USING BTREE,
  INDEX `fk_tbl_res_fid`(`fid`) USING BTREE,
  CONSTRAINT `fk_resorderitem_roid` FOREIGN KEY (`roid`) REFERENCES `resorder` (`roid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_tbl_res_fid` FOREIGN KEY (`fid`) REFERENCES `resfood` (`fid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resorderitem
-- ----------------------------
INSERT INTO `resorderitem` VALUES (1, 1, 1, 20.00, 1);
INSERT INTO `resorderitem` VALUES (2, 1, 2, 20.00, 1);

-- ----------------------------
-- Table structure for resorderitemtemp
-- ----------------------------
DROP TABLE IF EXISTS `resorderitemtemp`;
CREATE TABLE `resorderitemtemp`  (
  `roitid` int(0) NOT NULL AUTO_INCREMENT,
  `fid` int(0) NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  `quittime` date NULL DEFAULT NULL,
  `userid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`roitid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resorderitemtemp
-- ----------------------------

-- ----------------------------
-- Table structure for resuser
-- ----------------------------
DROP TABLE IF EXISTS `resuser`;
CREATE TABLE `resuser`  (
  `userid` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resuser
-- ----------------------------
INSERT INTO `resuser` VALUES (1, 'a', '0cc175b9c0f1b6a831c399e269772661', 'a@163.com');
INSERT INTO `resuser` VALUES (2, 'b', '0cc175b9c0f1b6a831c399e269772661', 'b@163.com');

SET FOREIGN_KEY_CHECKS = 1;
