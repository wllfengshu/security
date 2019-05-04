/*
Navicat MySQL Data Transfer

Source Server         : localhost5.5(无密码)
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2019-05-04 18:10:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(64) NOT NULL COMMENT '权限名',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'insertPermission', '插入权限');
INSERT INTO `t_permission` VALUES ('2', 'deletePermission', '删除权限');
INSERT INTO `t_permission` VALUES ('3', 'updatePermission', '修改权限');
INSERT INTO `t_permission` VALUES ('4', 'selectPermission', '查询权限');
INSERT INTO `t_permission` VALUES ('5', 'selectAllPermission', '查询权限列表');
INSERT INTO `t_permission` VALUES ('6', 'insetRole', '插入角色');
INSERT INTO `t_permission` VALUES ('7', 'deleteRole', '删除角色');
INSERT INTO `t_permission` VALUES ('8', 'updateRole', '修改角色');
INSERT INTO `t_permission` VALUES ('9', 'selectRole', '查询角色');
INSERT INTO `t_permission` VALUES ('10', 'selectAllRole', '查询角色列表');
INSERT INTO `t_permission` VALUES ('11', 'insetUser', '插入用户');
INSERT INTO `t_permission` VALUES ('12', 'deleteUser', '删除用户');
INSERT INTO `t_permission` VALUES ('13', 'updateUser', '修改用户');
INSERT INTO `t_permission` VALUES ('14', 'selectUser', '查询用户');
INSERT INTO `t_permission` VALUES ('15', 'selectAllUser', '查询用户列表');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '管理员');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_id` (`role_id`),
  KEY `fk_permission_id` (`permission_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1', '1');
INSERT INTO `t_role_permission` VALUES ('2', '1', '2');
INSERT INTO `t_role_permission` VALUES ('3', '1', '3');
INSERT INTO `t_role_permission` VALUES ('4', '1', '4');
INSERT INTO `t_role_permission` VALUES ('5', '1', '5');
INSERT INTO `t_role_permission` VALUES ('6', '1', '6');
INSERT INTO `t_role_permission` VALUES ('7', '1', '7');
INSERT INTO `t_role_permission` VALUES ('8', '1', '8');
INSERT INTO `t_role_permission` VALUES ('9', '1', '9');
INSERT INTO `t_role_permission` VALUES ('10', '1', '10');
INSERT INTO `t_role_permission` VALUES ('11', '1', '11');
INSERT INTO `t_role_permission` VALUES ('12', '1', '12');
INSERT INTO `t_role_permission` VALUES ('13', '1', '13');
INSERT INTO `t_role_permission` VALUES ('14', '1', '14');
INSERT INTO `t_role_permission` VALUES ('15', '1', '15');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_u_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_id1` (`role_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_role_id1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');
