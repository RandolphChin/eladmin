/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50733
Source Host           : 127.0.0.1:3306
Source Database       : eladmin_init

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-07-17 11:08:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_job
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job`;
CREATE TABLE `qrtz_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(50) DEFAULT NULL,
  `group_name` varchar(25) DEFAULT NULL,
  `job_class` varchar(125) DEFAULT NULL,
  `cron_expression` varchar(25) DEFAULT NULL,
  `param` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_pause` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrtz_job
-- ----------------------------
INSERT INTO `qrtz_job` VALUES ('5', 'quartzTest', 'default', 'me.zhengjie.modules.quartz.task.QuartzTest', '0/20 * * * * ? *', '{\"username\":\"roy\"}', '2021-07-17 11:00:35', null, '\0', 'wwe');
