/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : vue-shop

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-10-07 16:56:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户账号，主键',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名，账号',
  `user_password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_phone` int(11) DEFAULT NULL COMMENT '用户手机',
  `user_sex` varchar(255) DEFAULT NULL COMMENT '用户性别',
  `user_grade` int(11) DEFAULT NULL COMMENT '用户级别 0，1，2······',
  `user_state` int(10) unsigned zerofill DEFAULT NULL COMMENT '用户状态，0代表不可用，1代表可用',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin123', '1594586999', '男', '0', '0000000001');
INSERT INTO `user` VALUES ('2', 'zhangsan', 'admin123', '1784836775', '男', '1', '0000000001');
INSERT INTO `user` VALUES ('3', 'lisi', 'admin123', '1553675486', '女', '2', '0000000001');
