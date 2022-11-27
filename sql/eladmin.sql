/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : eladmin

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 27/11/2022 23:04:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_entrust_company
-- ----------------------------
DROP TABLE IF EXISTS `base_entrust_company`;
CREATE TABLE `base_entrust_company`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父单位ID',
  `sub_count` int(11) NULL DEFAULT NULL COMMENT '子单位个数',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `company_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del_status` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '委托单位base_entrust_company' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_entrust_company
-- ----------------------------
INSERT INTO `base_entrust_company` VALUES (1564900225052049410, NULL, 2, '中试部', '许昌市许继大道', '2022-08-31 16:57:43', '2022-08-31 17:07:31', 0);
INSERT INTO `base_entrust_company` VALUES (1564902438788923393, 1564900225052049400, 0, '基础测试中心', '许继大道66号', '2022-08-31 17:06:31', '2022-08-31 17:07:47', 0);
INSERT INTO `base_entrust_company` VALUES (1564902691776757762, 1564900225052049410, 0, '二次设备试验中心', '许继大道10086号', '2022-08-31 17:07:31', '2022-08-31 17:07:31', 0);
INSERT INTO `base_entrust_company` VALUES (1564903033721585666, NULL, 0, '开普检测', '许昌市示范区01号', '2022-08-31 17:08:52', '2022-08-31 17:08:52', 0);
INSERT INTO `base_entrust_company` VALUES (1564915716592353282, NULL, 0, 'dfdfdf', '大大大', '2022-08-31 17:59:16', '2022-08-31 17:59:25', 1);

-- ----------------------------
-- Table structure for code_column_config
-- ----------------------------
DROP TABLE IF EXISTS `code_column_config`;
CREATE TABLE `code_column_config`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `column_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `extra` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `form_show` bit(1) NULL DEFAULT NULL,
  `form_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `list_show` bit(1) NULL DEFAULT NULL,
  `not_null` bit(1) NULL DEFAULT NULL,
  `query_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date_annotation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX `idx_table_name`(`table_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成字段信息存储' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of code_column_config
-- ----------------------------
INSERT INTO `code_column_config` VALUES (13, 'qrtz_simple_triggers', 'SCHED_NAME', 'varchar', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (14, 'qrtz_simple_triggers', 'TRIGGER_NAME', 'varchar', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (15, 'qrtz_simple_triggers', 'TRIGGER_GROUP', 'varchar', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (16, 'qrtz_simple_triggers', 'REPEAT_COUNT', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (17, 'qrtz_simple_triggers', 'REPEAT_INTERVAL', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (18, 'qrtz_simple_triggers', 'TIMES_TRIGGERED', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (19, 'base_person_certific', 'id', 'bigint', NULL, '', b'0', NULL, 'PRI', b'1', b'1', NULL, 'id', NULL);
INSERT INTO `code_column_config` VALUES (22, 'base_person_certific', 'certific_name', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', 'Like', '证书名称', NULL);
INSERT INTO `code_column_config` VALUES (23, 'base_person_certific', 'certific_no', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', 'Like', '证书编号', NULL);
INSERT INTO `code_column_config` VALUES (24, 'base_person_certific', 'certific_level', 'char', 'person_certificate_level', '', b'1', 'Select', '', b'1', b'0', '=', '证书级别', NULL);
INSERT INTO `code_column_config` VALUES (25, 'base_person_certific', 'certific_project', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', 'Like', '取证项目', NULL);
INSERT INTO `code_column_config` VALUES (26, 'base_person_certific', 'certific_time', 'date', NULL, '', b'1', 'Date', '', b'1', b'0', 'BetWeen', '取证时间', NULL);
INSERT INTO `code_column_config` VALUES (27, 'base_person_certific', 'certific_dept', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', 'Like', '发证单位', NULL);
INSERT INTO `code_column_config` VALUES (28, 'base_person_certific', 'validity_time', 'date', NULL, '', b'1', 'Date', '', b'1', b'0', 'Like', '有效期至', NULL);
INSERT INTO `code_column_config` VALUES (29, 'base_person_certific', 'attach', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', NULL, '电子附件', NULL);
INSERT INTO `code_column_config` VALUES (30, 'base_person_certific', 'remark', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', NULL, '备注', NULL);
INSERT INTO `code_column_config` VALUES (31, 'base_person_certific', 'create_by', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', 'Like', '创建人', NULL);
INSERT INTO `code_column_config` VALUES (32, 'base_person_certific', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', 'CreationTimestamp');
INSERT INTO `code_column_config` VALUES (33, 'base_person_certific', 'update_by', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '修改人', NULL);
INSERT INTO `code_column_config` VALUES (34, 'base_person_certific', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '修改时间', 'UpdateTimestamp');
INSERT INTO `code_column_config` VALUES (35, 'base_person_certific', 'del_status', 'int', 'del_status', '', b'0', NULL, '', b'0', b'0', NULL, '删除状态', NULL);
INSERT INTO `code_column_config` VALUES (36, 'base_person_certific', 'user_id', 'bigint', NULL, '', b'1', 'Select', '', b'1', b'0', '=', '姓名', NULL);
INSERT INTO `code_column_config` VALUES (37, 'base_person_certific', 'dept_id', 'bigint', NULL, '', b'1', 'Select', '', b'1', b'0', '=', '部门', NULL);
INSERT INTO `code_column_config` VALUES (38, 'sys_user', 'user_id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'1', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (39, 'sys_user', 'dept_id', 'bigint', NULL, '', b'1', NULL, 'MUL', b'1', b'0', NULL, '部门名称', NULL);
INSERT INTO `code_column_config` VALUES (40, 'sys_user', 'username', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'0', NULL, '用户名', NULL);
INSERT INTO `code_column_config` VALUES (41, 'sys_user', 'gender', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '性别', NULL);
INSERT INTO `code_column_config` VALUES (42, 'sys_user', 'phone', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '手机号码', NULL);
INSERT INTO `code_column_config` VALUES (43, 'sys_user', 'email', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'0', NULL, '邮箱', NULL);
INSERT INTO `code_column_config` VALUES (44, 'sys_user', 'password', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '密码', NULL);
INSERT INTO `code_column_config` VALUES (45, 'sys_user', 'is_admin', 'bit', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '是否为admin账号', NULL);
INSERT INTO `code_column_config` VALUES (46, 'sys_user', 'enabled', 'bit', NULL, '', b'1', NULL, 'MUL', b'1', b'0', NULL, '状态：1启用、0禁用', NULL);
INSERT INTO `code_column_config` VALUES (47, 'sys_user', 'create_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建者', NULL);
INSERT INTO `code_column_config` VALUES (48, 'sys_user', 'update_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新者', NULL);
INSERT INTO `code_column_config` VALUES (49, 'sys_user', 'pwd_reset_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '修改密码的时间', NULL);
INSERT INTO `code_column_config` VALUES (50, 'sys_user', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建日期', NULL);
INSERT INTO `code_column_config` VALUES (51, 'sys_user', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新时间', NULL);
INSERT INTO `code_column_config` VALUES (52, 'base_company_certific', 'id', 'bigint', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, 'id', NULL);
INSERT INTO `code_column_config` VALUES (53, 'base_company_certific', 'organ_name', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', '=', '获证机构名称', NULL);
INSERT INTO `code_column_config` VALUES (54, 'base_company_certific', 'organ_address', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', '=', '获证机构地址', NULL);
INSERT INTO `code_column_config` VALUES (55, 'base_company_certific', 'check_range', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '检验检测能力范围', NULL);
INSERT INTO `code_column_config` VALUES (56, 'base_company_certific', 'certific_no', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '证书编码', NULL);
INSERT INTO `code_column_config` VALUES (57, 'base_company_certific', 'certific_time', 'date', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '取证时间', NULL);
INSERT INTO `code_column_config` VALUES (58, 'base_company_certific', 'certific_dept', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '发证单位', NULL);
INSERT INTO `code_column_config` VALUES (59, 'base_company_certific', 'validity_time', 'date', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '有效期至', NULL);
INSERT INTO `code_column_config` VALUES (60, 'base_company_certific', 'attach', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '电子附件', NULL);
INSERT INTO `code_column_config` VALUES (61, 'base_company_certific', 'create_by', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建人', NULL);
INSERT INTO `code_column_config` VALUES (62, 'base_company_certific', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL);
INSERT INTO `code_column_config` VALUES (63, 'base_company_certific', 'update_by', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '修改人', NULL);
INSERT INTO `code_column_config` VALUES (64, 'base_company_certific', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '修改时间', NULL);
INSERT INTO `code_column_config` VALUES (65, 'base_company_certific', 'remark', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备注', NULL);
INSERT INTO `code_column_config` VALUES (66, 'base_company_certific', 'del_status', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '删除状态', NULL);
INSERT INTO `code_column_config` VALUES (67, 'base_equipment_account', 'id', 'bigint', NULL, '', b'0', NULL, 'PRI', b'0', b'0', NULL, 'id', NULL);
INSERT INTO `code_column_config` VALUES (68, 'base_equipment_account', 'equip_no', 'char', NULL, '', b'1', 'Input', '', b'1', b'0', '=', '设备编号', NULL);
INSERT INTO `code_column_config` VALUES (69, 'base_equipment_account', 'equip_type', 'char', 'equip_type_dict', '', b'1', 'Radio', '', b'1', b'1', '=', '设备类型', NULL);
INSERT INTO `code_column_config` VALUES (70, 'base_equipment_account', 'out_factory_no', 'char', NULL, '', b'1', NULL, '', b'1', b'0', 'Like', '出厂编号', NULL);
INSERT INTO `code_column_config` VALUES (71, 'base_equipment_account', 'equip_name', 'char', NULL, '', b'1', NULL, '', b'1', b'1', 'Like', '设备名称', NULL);
INSERT INTO `code_column_config` VALUES (72, 'base_equipment_account', 'equip_model', 'char', NULL, '', b'1', NULL, '', b'1', b'1', 'Like', '设备型号', NULL);
INSERT INTO `code_column_config` VALUES (73, 'base_equipment_account', 'right_level', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '准确度等级', NULL);
INSERT INTO `code_column_config` VALUES (74, 'base_equipment_account', 'meterage_range', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '规格/测量范围', NULL);
INSERT INTO `code_column_config` VALUES (75, 'base_equipment_account', 'original_value', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '原值（元）', NULL);
INSERT INTO `code_column_config` VALUES (76, 'base_equipment_account', 'manufacturer', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '制造厂', NULL);
INSERT INTO `code_column_config` VALUES (77, 'base_equipment_account', 'contry', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '国别', NULL);
INSERT INTO `code_column_config` VALUES (78, 'base_equipment_account', 'in_factory_date', 'date', NULL, '', b'1', 'Date', '', b'1', b'0', NULL, '入厂日期', NULL);
INSERT INTO `code_column_config` VALUES (79, 'base_equipment_account', 'supplier', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '供应商', NULL);
INSERT INTO `code_column_config` VALUES (80, 'base_equipment_account', 'work_status', 'char', 'work_status_dict', '', b'1', NULL, '', b'1', b'0', '=', '工作状态', NULL);
INSERT INTO `code_column_config` VALUES (81, 'base_equipment_account', 'meterage_sort', 'char', 'meterage_sort_dict', '', b'1', NULL, '', b'1', b'0', '=', '计量分类', NULL);
INSERT INTO `code_column_config` VALUES (82, 'base_equipment_account', 'meterage_type', 'char', 'meterage_type_dict', '', b'1', NULL, '', b'1', b'0', '=', '计量类型', NULL);
INSERT INTO `code_column_config` VALUES (83, 'base_equipment_account', 'calibre_month', 'char', NULL, '', b'0', NULL, '', b'1', b'0', '=', '校准月份', NULL);
INSERT INTO `code_column_config` VALUES (84, 'base_equipment_account', 'calibre_year', 'char', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '校准年份', NULL);
INSERT INTO `code_column_config` VALUES (85, 'base_equipment_account', 'validity_time_month', 'char', NULL, '', b'0', NULL, '', b'1', b'0', '=', '有效月份', NULL);
INSERT INTO `code_column_config` VALUES (86, 'base_equipment_account', 'validity_time', 'date', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '有效期至', NULL);
INSERT INTO `code_column_config` VALUES (87, 'base_equipment_account', 'sure_interval', 'char', NULL, '', b'1', NULL, '', b'1', b'0', '=', '确认间隔', NULL);
INSERT INTO `code_column_config` VALUES (88, 'base_equipment_account', 'meterage_form', 'char', 'meterage_form_dict', '', b'1', NULL, '', b'1', b'0', '=', '计量形式', NULL);
INSERT INTO `code_column_config` VALUES (89, 'base_equipment_account', 'certific_company', 'char', NULL, '', b'1', NULL, '', b'1', b'0', 'Like', '证书单位', NULL);
INSERT INTO `code_column_config` VALUES (90, 'base_equipment_account', 'certific_no', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '证书编号', NULL);
INSERT INTO `code_column_config` VALUES (91, 'base_equipment_account', 'on_certific_date', 'date', NULL, '', b'1', 'Date', '', b'1', b'0', NULL, '贴证日期', NULL);
INSERT INTO `code_column_config` VALUES (92, 'base_equipment_account', 'calibre_fee', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '校准费（元）', NULL);
INSERT INTO `code_column_config` VALUES (93, 'base_equipment_account', 'calibre_address', 'char', 'calibre_address_dict', '', b'1', 'Radio', '', b'1', b'0', NULL, '校准地点', NULL);
INSERT INTO `code_column_config` VALUES (94, 'base_equipment_account', 'calibre_date', 'date', NULL, '', b'1', 'Date', '', b'1', b'0', NULL, '校准日期', NULL);
INSERT INTO `code_column_config` VALUES (95, 'base_equipment_account', 'source_way', 'char', 'source_way_dict', '', b'1', NULL, '', b'1', b'0', '=', '溯源方式', NULL);
INSERT INTO `code_column_config` VALUES (96, 'base_equipment_account', 'source_company', 'char', NULL, '', b'1', NULL, '', b'1', b'0', 'Like', '溯源单位', NULL);
INSERT INTO `code_column_config` VALUES (97, 'base_equipment_account', 'entrust_company', 'char', NULL, '', b'1', NULL, '', b'1', b'0', 'Like', '委托单位', NULL);
INSERT INTO `code_column_config` VALUES (98, 'base_equipment_account', 'entrust_son_company', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '委托子单位', NULL);
INSERT INTO `code_column_config` VALUES (99, 'base_equipment_account', 'by_user', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '使用人', NULL);
INSERT INTO `code_column_config` VALUES (100, 'base_equipment_account', 'storage_location', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '存放地点', NULL);
INSERT INTO `code_column_config` VALUES (101, 'base_equipment_account', 'equip_attach', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '主要附件', NULL);
INSERT INTO `code_column_config` VALUES (102, 'base_equipment_account', 'meterage_ask', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '计量要求', NULL);
INSERT INTO `code_column_config` VALUES (103, 'base_equipment_account', 'sure_result', 'char', 'sure_result_dict', '', b'1', NULL, '', b'1', b'0', NULL, '确认结果', NULL);
INSERT INTO `code_column_config` VALUES (104, 'base_equipment_account', 'by_calibrator', 'char', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '校准员', NULL);
INSERT INTO `code_column_config` VALUES (105, 'base_equipment_account', 'by_verifier', 'char', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '核验员', NULL);
INSERT INTO `code_column_config` VALUES (106, 'base_equipment_account', 'by_signer', 'char', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '签发人', NULL);
INSERT INTO `code_column_config` VALUES (107, 'base_equipment_account', 'calibre_status', 'char', NULL, '', b'0', NULL, '', b'1', b'0', '=', '校准状态', NULL);
INSERT INTO `code_column_config` VALUES (108, 'base_equipment_account', 'certific_status', 'char', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '证书状态', NULL);
INSERT INTO `code_column_config` VALUES (109, 'base_equipment_account', 'customer_type', 'char', 'customer_type_dict', '', b'1', NULL, '', b'1', b'0', '=', '客户类别', NULL);
INSERT INTO `code_column_config` VALUES (110, 'base_equipment_account', 'remark', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备注', NULL);
INSERT INTO `code_column_config` VALUES (111, 'base_equipment_account', 'create_by', 'char', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '创建人', NULL);
INSERT INTO `code_column_config` VALUES (112, 'base_equipment_account', 'create_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '创建时间', 'CreationTimestamp');
INSERT INTO `code_column_config` VALUES (113, 'base_equipment_account', 'update_by', 'char', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '修改人', NULL);
INSERT INTO `code_column_config` VALUES (114, 'base_equipment_account', 'update_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '修改时间', 'UpdateTimestamp');
INSERT INTO `code_column_config` VALUES (115, 'base_equipment_account', 'del_status', 'int', 'del_status', '', b'0', NULL, '', b'1', b'0', NULL, '删除状态', NULL);
INSERT INTO `code_column_config` VALUES (116, 'base_country', 'id', 'int', NULL, 'auto_increment', b'0', NULL, 'PRI', b'0', b'0', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (117, 'base_country', 'iso2', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (118, 'base_country', 'iso3', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (119, 'base_country', 'country', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '', NULL);
INSERT INTO `code_column_config` VALUES (120, 'base_country', 'country_cn', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '中国惯用名', NULL);
INSERT INTO `code_column_config` VALUES (121, 'base_entrust_company', 'id', 'bigint', NULL, '', b'0', NULL, 'PRI', b'0', b'1', NULL, 'ID', NULL);
INSERT INTO `code_column_config` VALUES (123, 'base_entrust_company', 'company_name', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'0', 'Like', '单位名称', NULL);
INSERT INTO `code_column_config` VALUES (124, 'base_entrust_company', 'company_address', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'0', NULL, '单位地址', NULL);
INSERT INTO `code_column_config` VALUES (125, 'base_entrust_company', 'create_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '创建时间', 'CreationTimestamp');
INSERT INTO `code_column_config` VALUES (126, 'base_entrust_company', 'update_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '修改时间', 'UpdateTimestamp');
INSERT INTO `code_column_config` VALUES (127, 'base_entrust_company', 'pid', 'bigint', NULL, '', b'1', 'Input', '', b'1', b'0', 'Like', '父单位ID', NULL);
INSERT INTO `code_column_config` VALUES (128, 'base_entrust_company', 'sub_count', 'int', NULL, '', b'0', NULL, '', b'0', b'0', NULL, '子单位个数', NULL);

-- ----------------------------
-- Table structure for code_gen_config
-- ----------------------------
DROP TABLE IF EXISTS `code_gen_config`;
CREATE TABLE `code_gen_config`  (
  `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `cover` bit(1) NULL DEFAULT NULL COMMENT '是否覆盖',
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `pack` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '至于哪个包下',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端代码生成的路径',
  `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端Api文件路径',
  `prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表前缀',
  `api_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  PRIMARY KEY (`config_id`) USING BTREE,
  INDEX `idx_table_name`(`table_name`(100)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成器配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of code_gen_config
-- ----------------------------
INSERT INTO `code_gen_config` VALUES (1, 'base_person_certific', 'randolph', b'1', 'zsb-system', 'xj.zhongshibu.mudules.certificateManage', 'D:\\projects\\zzssbb\\zsb-lims-web\\src\\views\\certificateManage\\person', 'D:\\projects\\zzssbb\\zsb-lims-web\\src\\api\\certificateManage\\person', NULL, '人员资质');
INSERT INTO `code_gen_config` VALUES (2, 'base_company_certific', 'long', b'1', 'zsb-system', 'xj.zhongshibu.modules.certificateManage', 'D:\\projects\\zzssbb\\zsb-lims-web\\src\\views\\certificateManage\\company', 'D:\\projects\\zzssbb\\zsb-lims-web\\src\\api\\certificateManage\\company', NULL, '公司资质');
INSERT INTO `code_gen_config` VALUES (3, 'base_equipment_account', 'chen', b'0', 'zsb-system', 'xj.zhongshibu.modules.base.equipment', 'D:\\java\\zsb\\xjms\\zsb-lims-web\\src\\views\\base\\equipment', 'D:\\java\\zsb\\xjms\\zsb-lims-web\\src\\api', NULL, '设备台账');
INSERT INTO `code_gen_config` VALUES (4, 'base_country', 'chen', b'0', 'zsb-system', 'xj.zhongshibu.modules.base.country', 'D:\\java\\zsb\\xjms\\zsb-lims-web\\src\\views\\base\\country', 'D:\\java\\zsb\\xjms\\zsb-lims-web\\src\\api', 'base', '各国编码');
INSERT INTO `code_gen_config` VALUES (5, 'base_entrust_company', 'Randolph', b'1', 'zsb-system', 'xj.zhongshibu.modules.base.entrustCompany', 'D:\\projects\\zsb-lims-web\\src\\views\\base\\entrustCompany', 'D:\\projects\\zsb-lims-web\\src\\api\\base\\entrustCompany', NULL, '委托单位');

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('SsmScheduler', 'TestingTask', 'default', '0/30 * * * * ? *', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FIRED_TIME` bigint(20) NOT NULL,
  `SCHED_TIME` bigint(20) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job`;
CREATE TABLE `qrtz_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `group_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `job_class` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cron_expression` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_pause` bit(1) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job
-- ----------------------------
INSERT INTO `qrtz_job` VALUES (5, 'quartzTest', 'default', 'me.zhengjie.modules.quartz.task.QuartzTest', '0/20 * * * * ? *', '{\"username\":\"roy\"}', '2021-07-17 11:00:35', NULL, b'0', 'wwe');

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('SsmScheduler', 'TestingTask', 'default', NULL, 'xj.zhongshibu.modules.quartz.task.TestTask', '0', '1', '0', '0', 0x230D0A23467269204175672031392031343A33363A35312043535420323032320D0A);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('SsmScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('SsmScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(20) NOT NULL,
  `CHECKIN_INTERVAL` bigint(20) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('SsmScheduler', 'DESKTOP-9JEFCAH1669561290528', 1669561482892, 2000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(20) NOT NULL,
  `REPEAT_INTERVAL` bigint(20) NOT NULL,
  `TIMES_TRIGGERED` bigint(20) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(20) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(20) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `START_TIME` bigint(20) NOT NULL,
  `END_TIME` bigint(20) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(6) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('SsmScheduler', 'TestingTask', 'default', 'TestingTask', 'default', '{\"game\":\"穿越火线\"}', 1661064810000, 1661064780000, 5, 'PAUSED', 'CRON', 1660891011000, 0, NULL, 2, 0x230D0A23536174204175672032302031303A35383A33382043535420323032320D0A67616D653D5C75374137465C75384438415C75373036425C75374542460D0A);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级部门',
  `sub_count` int(11) NULL DEFAULT 0 COMMENT '子部门数目',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `dept_sort` int(11) NULL DEFAULT 999 COMMENT '排序',
  `enabled` bit(1) NOT NULL COMMENT '状态',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE,
  INDEX `inx_pid`(`pid`) USING BTREE,
  INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (6, 8, 0, '基础测试中心', 1, b'1', 'admin', 'zsb_admin', '2019-03-25 09:52:18', '2022-08-24 14:32:40');
INSERT INTO `sys_dept` VALUES (7, NULL, 0, '技术研发部', 0, b'1', 'admin', 'zsb_admin', '2019-03-25 11:04:50', '2022-08-24 14:39:14');
INSERT INTO `sys_dept` VALUES (8, NULL, 4, '测试中心部', 1, b'1', 'admin', 'zsb_admin', '2019-03-25 11:04:53', '2022-08-24 14:33:03');
INSERT INTO `sys_dept` VALUES (15, 8, 0, '二次设备试验中心', 2, b'1', 'admin', 'zsb_admin', '2020-05-13 22:56:53', '2022-08-24 14:32:10');
INSERT INTO `sys_dept` VALUES (18, 8, 0, '系统集成试验中心', 3, b'1', 'zsb_admin', 'zsb_admin', '2022-08-24 14:32:33', '2022-08-24 14:32:33');
INSERT INTO `sys_dept` VALUES (19, 8, 0, '可靠性测试中心', 4, b'1', 'zsb_admin', 'zsb_admin', '2022-08-24 14:33:03', '2022-08-24 14:33:03');
INSERT INTO `sys_dept` VALUES (20, NULL, 4, '计量校准部', 3, b'1', 'zsb_admin', 'zsb_admin', '2022-08-24 14:35:20', '2022-08-24 14:41:15');
INSERT INTO `sys_dept` VALUES (21, 20, 0, '长度专业实验室', 1, b'1', 'zsb_admin', 'zsb_admin', '2022-08-24 14:39:37', '2022-08-24 14:39:37');
INSERT INTO `sys_dept` VALUES (22, 20, 0, '热学专业实验室', 2, b'1', 'zsb_admin', 'zsb_admin', '2022-08-24 14:40:02', '2022-08-24 14:40:02');
INSERT INTO `sys_dept` VALUES (23, 20, 0, '电学专业实验室', 3, b'1', 'zsb_admin', 'zsb_admin', '2022-08-24 14:40:54', '2022-08-24 14:40:54');
INSERT INTO `sys_dept` VALUES (24, 20, 0, '力学专业实验室', 4, b'1', 'zsb_admin', 'zsb_admin', '2022-08-24 14:41:15', '2022-08-24 14:41:15');
INSERT INTO `sys_dept` VALUES (25, NULL, 0, '安全质量部', 4, b'1', 'zsb_admin', 'zsb_admin', '2022-08-24 14:42:08', '2022-08-24 14:42:08');
INSERT INTO `sys_dept` VALUES (26, NULL, 0, '财务部', 5, b'1', 'zsb_admin', 'zsb_admin', '2022-08-24 14:42:32', '2022-08-24 14:42:32');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'user_status', '用户状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (4, 'dept_status', '部门状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (5, 'job_status', '岗位状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (6, 'person_certificate_level', '人员资质证书级别', 'zsb_admin', 'zsb_admin', '2022-08-23 16:54:06', '2022-08-23 16:54:06');
INSERT INTO `sys_dict` VALUES (7, 'del_status', '删除标识', 'zsb_admin', 'zsb_admin', '2022-08-23 22:04:21', '2022-08-23 22:04:21');
INSERT INTO `sys_dict` VALUES (8, 'equip_type_dict', '设备类型字典', 'zsb_admin', 'zsb_admin', '2022-08-29 17:54:20', '2022-08-30 09:44:52');
INSERT INTO `sys_dict` VALUES (9, 'work_status_dict', '工作状态字典', 'zsb_admin', 'zsb_admin', '2022-08-30 09:21:01', '2022-08-30 10:08:35');
INSERT INTO `sys_dict` VALUES (10, 'meterage_sort_dict', '计量分类字典', 'zsb_admin', 'zsb_admin', '2022-08-30 09:23:55', '2022-08-30 10:08:32');
INSERT INTO `sys_dict` VALUES (11, 'meterage_type_dict', '计量类型字典', 'zsb_admin', 'zsb_admin', '2022-08-30 09:25:28', '2022-08-30 10:08:28');
INSERT INTO `sys_dict` VALUES (12, 'meterage_form_dict', '计量形式字典', 'zsb_admin', 'zsb_admin', '2022-08-30 09:44:22', '2022-08-30 10:08:23');
INSERT INTO `sys_dict` VALUES (13, 'calibre_address_dict', '校准地址字典', 'zsb_admin', 'zsb_admin', '2022-08-30 09:46:46', '2022-08-30 10:08:15');
INSERT INTO `sys_dict` VALUES (14, 'source_way_dict', '溯源方式字典', 'zsb_admin', 'zsb_admin', '2022-08-30 09:48:35', '2022-08-30 10:08:11');
INSERT INTO `sys_dict` VALUES (15, 'sure_result_dict', '确认结果字典', 'zsb_admin', 'zsb_admin', '2022-08-30 09:59:56', '2022-08-30 10:08:07');
INSERT INTO `sys_dict` VALUES (16, 'calibre_status_dict', '校准状态', 'zsb_admin', 'zsb_admin', '2022-08-30 10:03:14', '2022-08-30 10:08:04');
INSERT INTO `sys_dict` VALUES (17, 'certific_status_dict', '证书状态字典', 'zsb_admin', 'zsb_admin', '2022-08-30 10:06:24', '2022-08-30 10:08:00');
INSERT INTO `sys_dict` VALUES (18, 'customer_type_dict', '客户类别', 'zsb_admin', 'zsb_admin', '2022-08-30 10:07:53', '2022-08-30 10:07:53');

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dict_id` bigint(20) NULL DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `dict_sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `FK5tpkputc6d9nboxojdbgnpmyb`(`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典详情' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1, 1, '激活', 'true', 1, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (2, 1, '禁用', 'false', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (3, 4, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (4, 4, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (5, 5, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (6, 5, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (7, 6, '一级注册计量师', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-23 17:05:55', '2022-08-23 17:06:32');
INSERT INTO `sys_dict_detail` VALUES (8, 6, '二级注册计量师', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-23 17:06:25', '2022-08-23 17:06:25');
INSERT INTO `sys_dict_detail` VALUES (9, 7, '未删除', '0', 1, 'zsb_admin', 'zsb_admin', '2022-08-23 22:04:42', '2022-08-23 22:04:42');
INSERT INTO `sys_dict_detail` VALUES (10, 7, '已删除', '1', 2, 'zsb_admin', 'zsb_admin', '2022-08-23 22:04:53', '2022-08-23 22:04:53');
INSERT INTO `sys_dict_detail` VALUES (11, 8, '长度', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-29 17:55:57', '2022-08-29 17:55:57');
INSERT INTO `sys_dict_detail` VALUES (12, 8, '热学', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-29 17:56:46', '2022-08-29 17:56:46');
INSERT INTO `sys_dict_detail` VALUES (13, 8, '电学', '3', 3, 'zsb_admin', 'zsb_admin', '2022-08-29 17:57:07', '2022-08-29 17:57:07');
INSERT INTO `sys_dict_detail` VALUES (14, 8, '力学', '4', 4, 'zsb_admin', 'zsb_admin', '2022-08-29 17:57:18', '2022-08-29 17:57:18');
INSERT INTO `sys_dict_detail` VALUES (15, 9, '合格', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 09:21:25', '2022-08-30 09:21:25');
INSERT INTO `sys_dict_detail` VALUES (16, 9, '停用', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 09:21:39', '2022-08-30 09:21:39');
INSERT INTO `sys_dict_detail` VALUES (17, 9, '限用', '3', 3, 'zsb_admin', 'zsb_admin', '2022-08-30 09:21:51', '2022-08-30 09:21:51');
INSERT INTO `sys_dict_detail` VALUES (18, 9, '报废', '4', 4, 'zsb_admin', 'zsb_admin', '2022-08-30 09:22:02', '2022-08-30 09:22:02');
INSERT INTO `sys_dict_detail` VALUES (19, 9, '丢失', '5', 5, 'zsb_admin', 'zsb_admin', '2022-08-30 09:22:19', '2022-08-30 09:22:19');
INSERT INTO `sys_dict_detail` VALUES (20, 9, '外借', '6', 6, 'zsb_admin', 'zsb_admin', '2022-08-30 09:22:41', '2022-08-30 09:22:41');
INSERT INTO `sys_dict_detail` VALUES (21, 9, '封存', '7', 7, 'zsb_admin', 'zsb_admin', '2022-08-30 09:22:56', '2022-08-30 09:22:56');
INSERT INTO `sys_dict_detail` VALUES (22, 10, 'A', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 09:24:09', '2022-08-30 09:24:09');
INSERT INTO `sys_dict_detail` VALUES (23, 10, 'B', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 09:24:18', '2022-08-30 09:24:18');
INSERT INTO `sys_dict_detail` VALUES (24, 10, 'C', '3', 3, 'zsb_admin', 'zsb_admin', '2022-08-30 09:24:26', '2022-08-30 09:24:26');
INSERT INTO `sys_dict_detail` VALUES (25, 11, '工作', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 09:26:17', '2022-08-30 09:26:17');
INSERT INTO `sys_dict_detail` VALUES (26, 11, '认可', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 09:26:26', '2022-08-30 09:26:26');
INSERT INTO `sys_dict_detail` VALUES (27, 11, '标准', '3', 3, 'zsb_admin', 'zsb_admin', '2022-08-30 09:26:46', '2022-08-30 09:26:46');
INSERT INTO `sys_dict_detail` VALUES (28, 11, '次标', '4', 4, 'zsb_admin', 'zsb_admin', '2022-08-30 09:26:58', '2022-08-30 09:26:58');
INSERT INTO `sys_dict_detail` VALUES (29, 11, '外送', '5', 5, 'zsb_admin', 'zsb_admin', '2022-08-30 09:27:23', '2022-08-30 09:27:23');
INSERT INTO `sys_dict_detail` VALUES (30, 11, 'CNAS', '6', 6, 'zsb_admin', 'zsb_admin', '2022-08-30 09:27:35', '2022-08-30 09:27:35');
INSERT INTO `sys_dict_detail` VALUES (31, 11, '非标', '7', 7, 'zsb_admin', 'zsb_admin', '2022-08-30 09:27:47', '2022-08-30 09:27:47');
INSERT INTO `sys_dict_detail` VALUES (32, 12, '校准', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 09:45:20', '2022-08-30 09:45:20');
INSERT INTO `sys_dict_detail` VALUES (33, 12, '检定', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 09:45:36', '2022-08-30 09:45:36');
INSERT INTO `sys_dict_detail` VALUES (34, 12, '检测', '3', 3, 'zsb_admin', 'zsb_admin', '2022-08-30 09:45:45', '2022-08-30 09:45:45');
INSERT INTO `sys_dict_detail` VALUES (35, 13, '现场', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 09:46:57', '2022-08-30 09:46:57');
INSERT INTO `sys_dict_detail` VALUES (36, 13, '实验室', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 09:47:10', '2022-08-30 09:47:10');
INSERT INTO `sys_dict_detail` VALUES (37, 13, '送校', '3', 3, 'zsb_admin', 'zsb_admin', '2022-08-30 09:47:48', '2022-08-30 09:47:48');
INSERT INTO `sys_dict_detail` VALUES (38, 14, '自检', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 09:48:52', '2022-08-30 09:48:52');
INSERT INTO `sys_dict_detail` VALUES (39, 14, '外送', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 09:48:58', '2022-08-30 09:48:58');
INSERT INTO `sys_dict_detail` VALUES (40, 15, 'P', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 10:02:12', '2022-08-30 10:02:12');
INSERT INTO `sys_dict_detail` VALUES (41, 15, 'F', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 10:02:25', '2022-08-30 10:02:25');
INSERT INTO `sys_dict_detail` VALUES (42, 16, '待接收', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 10:03:37', '2022-08-30 10:03:37');
INSERT INTO `sys_dict_detail` VALUES (43, 16, '待校准', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 10:03:46', '2022-08-30 10:03:46');
INSERT INTO `sys_dict_detail` VALUES (44, 16, '校准中', '3', 3, 'zsb_admin', 'zsb_admin', '2022-08-30 10:04:07', '2022-08-30 10:04:07');
INSERT INTO `sys_dict_detail` VALUES (45, 16, '已校准', '4', 4, 'zsb_admin', 'zsb_admin', '2022-08-30 10:04:19', '2022-08-30 10:04:28');
INSERT INTO `sys_dict_detail` VALUES (46, 16, '异常', '5', 5, 'zsb_admin', 'zsb_admin', '2022-08-30 10:04:59', '2022-08-30 10:04:59');
INSERT INTO `sys_dict_detail` VALUES (47, 16, '退检', '6', 6, 'zsb_admin', 'zsb_admin', '2022-08-30 10:05:14', '2022-08-30 10:05:14');
INSERT INTO `sys_dict_detail` VALUES (48, 16, '已取回', '7', 7, 'zsb_admin', 'zsb_admin', '2022-08-30 10:05:31', '2022-08-30 10:05:31');
INSERT INTO `sys_dict_detail` VALUES (49, 17, '待核验', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 10:06:48', '2022-08-30 10:06:48');
INSERT INTO `sys_dict_detail` VALUES (50, 17, '待批准', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 10:06:58', '2022-08-30 10:06:58');
INSERT INTO `sys_dict_detail` VALUES (51, 17, '已批准', '3', 3, 'zsb_admin', 'zsb_admin', '2022-08-30 10:07:08', '2022-08-30 10:07:08');
INSERT INTO `sys_dict_detail` VALUES (52, 18, '内部', '1', 1, 'zsb_admin', 'zsb_admin', '2022-08-30 10:09:06', '2022-08-30 10:09:06');
INSERT INTO `sys_dict_detail` VALUES (53, 18, '外部', '2', 2, 'zsb_admin', 'zsb_admin', '2022-08-30 10:09:15', '2022-08-30 10:09:15');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `job_sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
  INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (8, '人事专员', b'1', 3, NULL, NULL, '2019-03-29 14:52:28', NULL);
INSERT INTO `sys_job` VALUES (10, '产品经理', b'1', 4, NULL, NULL, '2019-03-29 14:55:51', NULL);
INSERT INTO `sys_job` VALUES (11, '全栈开发', b'1', 2, NULL, 'admin', '2019-03-31 13:39:30', '2020-05-05 11:33:43');
INSERT INTO `sys_job` VALUES (12, '软件测试', b'1', 5, NULL, 'admin', '2019-03-31 13:39:43', '2020-05-10 19:56:26');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `log_create_time_index`(`create_time`) USING BTREE,
  INDEX `inx_log_type`(`log_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4034 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级菜单ID',
  `sub_count` int(11) NULL DEFAULT 0 COMMENT '子菜单数目',
  `type` int(11) NULL DEFAULT NULL COMMENT '菜单类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `menu_sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外链',
  `cache` bit(1) NULL DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) NULL DEFAULT b'0' COMMENT '隐藏',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  UNIQUE INDEX `uniq_title`(`title`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
  INDEX `inx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, NULL, 8, 0, '系统管理', NULL, NULL, 1, 'system', 'system', b'0', b'0', b'0', NULL, NULL, 'admin', '2018-12-18 15:11:29', '2022-08-19 11:29:57');
INSERT INTO `sys_menu` VALUES (2, 1, 3, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', 'user', b'0', b'0', b'0', 'user:list', NULL, NULL, '2018-12-18 15:14:44', NULL);
INSERT INTO `sys_menu` VALUES (3, 1, 3, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', 'role', b'0', b'0', b'0', 'roles:list', NULL, NULL, '2018-12-18 15:16:07', NULL);
INSERT INTO `sys_menu` VALUES (5, 1, 3, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', 'menu', b'0', b'0', b'0', 'menu:list', NULL, NULL, '2018-12-18 15:17:28', NULL);
INSERT INTO `sys_menu` VALUES (6, NULL, 5, 0, '系统监控', NULL, NULL, 10, 'monitor', 'monitor', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:17:48', NULL);
INSERT INTO `sys_menu` VALUES (7, 6, 0, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', 'logs', b'0', b'1', b'0', NULL, NULL, 'admin', '2018-12-18 15:18:26', '2020-06-06 13:11:57');
INSERT INTO `sys_menu` VALUES (9, 6, 0, 1, 'SQL监控', 'Sql', 'monitor/sql/index', 18, 'sqlMonitor', 'druid', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:19:34', NULL);
INSERT INTO `sys_menu` VALUES (10, NULL, 5, 0, '组件管理', NULL, NULL, 50, 'zujian', 'components', b'0', b'0', b'0', NULL, NULL, 'zsb_admin', '2018-12-19 13:38:16', '2022-08-30 10:54:46');
INSERT INTO `sys_menu` VALUES (11, 10, 0, 1, '图标库', 'Icons', 'components/icons/index', 51, 'icon', 'icon', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-19 13:38:49', NULL);
INSERT INTO `sys_menu` VALUES (18, 36, 3, 1, '存储管理', 'Storage', 'tools/storage/index', 34, 'qiniu', 'storage', b'0', b'0', b'0', 'storage:list', NULL, NULL, '2018-12-31 11:12:15', NULL);
INSERT INTO `sys_menu` VALUES (22, 21, 2, 0, '二级菜单1', NULL, '', 999, 'menu', 'menu1', b'0', b'0', b'0', NULL, NULL, 'admin', '2019-01-04 16:23:29', '2020-06-21 17:27:20');
INSERT INTO `sys_menu` VALUES (23, 21, 0, 1, '二级菜单2', NULL, 'nested/menu2/index', 999, 'menu', 'menu2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:23:57', NULL);
INSERT INTO `sys_menu` VALUES (24, 22, 0, 1, '三级菜单1', 'Test', 'nested/menu1/menu1-1', 999, 'menu', 'menu1-1', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:24:48', NULL);
INSERT INTO `sys_menu` VALUES (27, 22, 0, 1, '三级菜单2', NULL, 'nested/menu1/menu1-2', 999, 'menu', 'menu1-2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-07 17:27:32', NULL);
INSERT INTO `sys_menu` VALUES (28, 1, 3, 1, '任务调度', 'Quartz', 'system/timing/index', 999, 'timing', 'quartz', b'0', b'0', b'0', 'timing:list', NULL, 'admin', '2019-01-07 20:34:40', '2022-08-18 22:10:10');
INSERT INTO `sys_menu` VALUES (30, 36, 0, 1, '代码生成', 'GeneratorIndex', 'generator/index', 32, 'dev', 'generator', b'0', b'1', b'0', NULL, NULL, NULL, '2019-01-11 15:45:55', NULL);
INSERT INTO `sys_menu` VALUES (32, 6, 0, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', 'errorLog', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-13 13:49:03', NULL);
INSERT INTO `sys_menu` VALUES (35, 1, 3, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', 'dept', b'0', b'0', b'0', 'dept:list', NULL, NULL, '2019-03-25 09:46:00', NULL);
INSERT INTO `sys_menu` VALUES (36, NULL, 7, 0, '系统工具', NULL, '', 30, 'sys-tools', 'sys-tools', b'0', b'0', b'0', NULL, NULL, 'zsb_admin', '2019-03-29 10:57:35', '2022-08-20 17:50:21');
INSERT INTO `sys_menu` VALUES (37, 1, 3, 1, '岗位管理', 'Job', 'system/job/index', 7, 'Steve-Jobs', 'job', b'0', b'0', b'0', 'job:list', NULL, NULL, '2019-03-29 13:51:18', NULL);
INSERT INTO `sys_menu` VALUES (38, 36, 0, 1, '接口文档', 'Swagger', 'tools/swagger/index', 36, 'swagger', 'swagger2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 19:57:53', NULL);
INSERT INTO `sys_menu` VALUES (39, 1, 3, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', 'dict', b'0', b'0', b'0', 'dict:list', NULL, NULL, '2019-04-10 11:49:04', NULL);
INSERT INTO `sys_menu` VALUES (41, 6, 0, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', 'online', b'0', b'0', b'0', NULL, NULL, NULL, '2019-10-26 22:08:43', NULL);
INSERT INTO `sys_menu` VALUES (44, 2, 0, 2, '用户新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'user:add', NULL, NULL, '2019-10-29 10:59:46', NULL);
INSERT INTO `sys_menu` VALUES (45, 2, 0, 2, '用户编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'user:edit', NULL, NULL, '2019-10-29 11:00:08', NULL);
INSERT INTO `sys_menu` VALUES (46, 2, 0, 2, '用户删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'user:del', NULL, NULL, '2019-10-29 11:00:23', NULL);
INSERT INTO `sys_menu` VALUES (48, 3, 0, 2, '角色创建', NULL, '', 2, '', '', b'0', b'0', b'0', 'roles:add', NULL, NULL, '2019-10-29 12:45:34', NULL);
INSERT INTO `sys_menu` VALUES (49, 3, 0, 2, '角色修改', NULL, '', 3, '', '', b'0', b'0', b'0', 'roles:edit', NULL, NULL, '2019-10-29 12:46:16', NULL);
INSERT INTO `sys_menu` VALUES (50, 3, 0, 2, '角色删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'roles:del', NULL, NULL, '2019-10-29 12:46:51', NULL);
INSERT INTO `sys_menu` VALUES (52, 5, 0, 2, '菜单新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'menu:add', NULL, NULL, '2019-10-29 12:55:07', NULL);
INSERT INTO `sys_menu` VALUES (53, 5, 0, 2, '菜单编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'menu:edit', NULL, NULL, '2019-10-29 12:55:40', NULL);
INSERT INTO `sys_menu` VALUES (54, 5, 0, 2, '菜单删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'menu:del', NULL, NULL, '2019-10-29 12:56:00', NULL);
INSERT INTO `sys_menu` VALUES (56, 35, 0, 2, '部门新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dept:add', NULL, NULL, '2019-10-29 12:57:09', NULL);
INSERT INTO `sys_menu` VALUES (57, 35, 0, 2, '部门编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dept:edit', NULL, NULL, '2019-10-29 12:57:27', NULL);
INSERT INTO `sys_menu` VALUES (58, 35, 0, 2, '部门删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dept:del', NULL, NULL, '2019-10-29 12:57:41', NULL);
INSERT INTO `sys_menu` VALUES (60, 37, 0, 2, '岗位新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'job:add', NULL, NULL, '2019-10-29 12:58:27', NULL);
INSERT INTO `sys_menu` VALUES (61, 37, 0, 2, '岗位编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'job:edit', NULL, NULL, '2019-10-29 12:58:45', NULL);
INSERT INTO `sys_menu` VALUES (62, 37, 0, 2, '岗位删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'job:del', NULL, NULL, '2019-10-29 12:59:04', NULL);
INSERT INTO `sys_menu` VALUES (64, 39, 0, 2, '字典新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dict:add', NULL, NULL, '2019-10-29 13:00:17', NULL);
INSERT INTO `sys_menu` VALUES (65, 39, 0, 2, '字典编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dict:edit', NULL, NULL, '2019-10-29 13:00:42', NULL);
INSERT INTO `sys_menu` VALUES (66, 39, 0, 2, '字典删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dict:del', NULL, NULL, '2019-10-29 13:00:59', NULL);
INSERT INTO `sys_menu` VALUES (73, 28, 0, 2, '任务新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'qrtzJob:add', NULL, 'admin', '2019-10-29 13:07:28', '2021-07-17 08:45:37');
INSERT INTO `sys_menu` VALUES (74, 28, 0, 2, '任务编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'qrtzJob:edit', NULL, 'admin', '2019-10-29 13:07:41', '2021-07-17 08:45:50');
INSERT INTO `sys_menu` VALUES (75, 28, 0, 2, '任务删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'qrtzJob:del', NULL, 'admin', '2019-10-29 13:07:54', '2021-07-17 08:46:02');
INSERT INTO `sys_menu` VALUES (77, 18, 0, 2, '上传文件', NULL, '', 2, '', '', b'0', b'0', b'0', 'storage:add', NULL, NULL, '2019-10-29 13:09:09', NULL);
INSERT INTO `sys_menu` VALUES (78, 18, 0, 2, '文件编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'storage:edit', NULL, NULL, '2019-10-29 13:09:22', NULL);
INSERT INTO `sys_menu` VALUES (79, 18, 0, 2, '文件删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'storage:del', NULL, NULL, '2019-10-29 13:09:34', NULL);
INSERT INTO `sys_menu` VALUES (80, 6, 0, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', b'0', b'0', b'0', 'monitor:list', NULL, 'admin', '2019-11-07 13:06:39', '2020-05-04 18:20:50');
INSERT INTO `sys_menu` VALUES (82, 36, 0, 1, '生成配置', 'GeneratorConfig', 'generator/config', 33, 'dev', 'generator/config/:tableName', b'0', b'1', b'1', '', NULL, NULL, '2019-11-17 20:08:56', NULL);
INSERT INTO `sys_menu` VALUES (92, 90, 3, 1, '服务器', 'ServerDeploy', 'mnt/server/index', 22, 'server', 'mnt/serverDeploy', b'0', b'0', b'0', 'serverDeploy:list', NULL, NULL, '2019-11-10 10:29:25', NULL);
INSERT INTO `sys_menu` VALUES (93, 90, 3, 1, '应用管理', 'App', 'mnt/app/index', 23, 'app', 'mnt/app', b'0', b'0', b'0', 'app:list', NULL, NULL, '2019-11-10 11:05:16', NULL);
INSERT INTO `sys_menu` VALUES (94, 90, 3, 1, '部署管理', 'Deploy', 'mnt/deploy/index', 24, 'deploy', 'mnt/deploy', b'0', b'0', b'0', 'deploy:list', NULL, NULL, '2019-11-10 15:56:55', NULL);
INSERT INTO `sys_menu` VALUES (97, 90, 1, 1, '部署备份', 'DeployHistory', 'mnt/deployHistory/index', 25, 'backup', 'mnt/deployHistory', b'0', b'0', b'0', 'deployHistory:list', NULL, NULL, '2019-11-10 16:49:44', NULL);
INSERT INTO `sys_menu` VALUES (102, 97, 0, 2, '删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deployHistory:del', NULL, NULL, '2019-11-17 09:32:48', NULL);
INSERT INTO `sys_menu` VALUES (103, 92, 0, 2, '服务器新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:add', NULL, NULL, '2019-11-17 11:08:33', NULL);
INSERT INTO `sys_menu` VALUES (104, 92, 0, 2, '服务器编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:edit', NULL, NULL, '2019-11-17 11:08:57', NULL);
INSERT INTO `sys_menu` VALUES (105, 92, 0, 2, '服务器删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:del', NULL, NULL, '2019-11-17 11:09:15', NULL);
INSERT INTO `sys_menu` VALUES (106, 93, 0, 2, '应用新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:add', NULL, NULL, '2019-11-17 11:10:03', NULL);
INSERT INTO `sys_menu` VALUES (107, 93, 0, 2, '应用编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:edit', NULL, NULL, '2019-11-17 11:10:28', NULL);
INSERT INTO `sys_menu` VALUES (108, 93, 0, 2, '应用删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:del', NULL, NULL, '2019-11-17 11:10:55', NULL);
INSERT INTO `sys_menu` VALUES (109, 94, 0, 2, '部署新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:add', NULL, NULL, '2019-11-17 11:11:22', NULL);
INSERT INTO `sys_menu` VALUES (110, 94, 0, 2, '部署编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:edit', NULL, NULL, '2019-11-17 11:11:41', NULL);
INSERT INTO `sys_menu` VALUES (111, 94, 0, 2, '部署删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:del', NULL, NULL, '2019-11-17 11:12:01', NULL);
INSERT INTO `sys_menu` VALUES (112, 98, 0, 2, '数据库新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:add', NULL, NULL, '2019-11-17 11:12:43', NULL);
INSERT INTO `sys_menu` VALUES (113, 98, 0, 2, '数据库编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:edit', NULL, NULL, '2019-11-17 11:12:58', NULL);
INSERT INTO `sys_menu` VALUES (114, 98, 0, 2, '数据库删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:del', NULL, NULL, '2019-11-17 11:13:14', NULL);
INSERT INTO `sys_menu` VALUES (116, 36, 0, 1, '生成预览', 'Preview', 'generator/preview', 999, 'java', 'generator/preview/:tableName', b'0', b'1', b'1', NULL, NULL, NULL, '2019-11-26 14:54:36', NULL);
INSERT INTO `sys_menu` VALUES (123, NULL, 2, 0, '示例', NULL, NULL, 2, 'database', 'base', b'0', b'0', b'0', NULL, 'zsb_admin', 'lims_admin', '2022-08-31 09:32:25', '2022-11-27 22:47:18');
INSERT INTO `sys_menu` VALUES (124, 123, 0, 1, '委托单位', '委托单位', 'base/entrustCompany/index', 1, 'list', 'entrustCompany', b'0', b'0', b'0', 'baseEntrustCompany:list', 'zsb_admin', 'zsb_admin', '2022-08-31 09:37:31', '2022-08-31 09:37:31');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `level` int(11) NULL DEFAULT NULL COMMENT '角色级别',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
  INDEX `role_name_index`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 1, '-', '全部', NULL, 'admin', '2018-11-23 11:04:37', '2020-08-06 16:10:24');
INSERT INTO `sys_role` VALUES (2, '普通用户', 2, '-', '本级', NULL, 'admin', '2018-11-23 13:09:06', '2020-09-05 10:45:12');

-- ----------------------------
-- Table structure for sys_roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_depts`;
CREATE TABLE `sys_roles_depts`  (
  `role_id` bigint(20) NOT NULL,
  `dept_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE,
  INDEX `FK7qg6itn5ajdoa9h9o78v9ksur`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色部门关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_roles_depts
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus`  (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
  INDEX `FKcngg2qadojhi3a651a5adkvbq`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_roles_menus
-- ----------------------------
INSERT INTO `sys_roles_menus` VALUES (1, 1);
INSERT INTO `sys_roles_menus` VALUES (2, 1);
INSERT INTO `sys_roles_menus` VALUES (3, 1);
INSERT INTO `sys_roles_menus` VALUES (5, 1);
INSERT INTO `sys_roles_menus` VALUES (6, 1);
INSERT INTO `sys_roles_menus` VALUES (7, 1);
INSERT INTO `sys_roles_menus` VALUES (9, 1);
INSERT INTO `sys_roles_menus` VALUES (10, 1);
INSERT INTO `sys_roles_menus` VALUES (11, 1);
INSERT INTO `sys_roles_menus` VALUES (18, 1);
INSERT INTO `sys_roles_menus` VALUES (22, 1);
INSERT INTO `sys_roles_menus` VALUES (23, 1);
INSERT INTO `sys_roles_menus` VALUES (24, 1);
INSERT INTO `sys_roles_menus` VALUES (27, 1);
INSERT INTO `sys_roles_menus` VALUES (28, 1);
INSERT INTO `sys_roles_menus` VALUES (30, 1);
INSERT INTO `sys_roles_menus` VALUES (32, 1);
INSERT INTO `sys_roles_menus` VALUES (35, 1);
INSERT INTO `sys_roles_menus` VALUES (36, 1);
INSERT INTO `sys_roles_menus` VALUES (37, 1);
INSERT INTO `sys_roles_menus` VALUES (38, 1);
INSERT INTO `sys_roles_menus` VALUES (39, 1);
INSERT INTO `sys_roles_menus` VALUES (41, 1);
INSERT INTO `sys_roles_menus` VALUES (44, 1);
INSERT INTO `sys_roles_menus` VALUES (45, 1);
INSERT INTO `sys_roles_menus` VALUES (46, 1);
INSERT INTO `sys_roles_menus` VALUES (48, 1);
INSERT INTO `sys_roles_menus` VALUES (49, 1);
INSERT INTO `sys_roles_menus` VALUES (50, 1);
INSERT INTO `sys_roles_menus` VALUES (52, 1);
INSERT INTO `sys_roles_menus` VALUES (53, 1);
INSERT INTO `sys_roles_menus` VALUES (54, 1);
INSERT INTO `sys_roles_menus` VALUES (56, 1);
INSERT INTO `sys_roles_menus` VALUES (57, 1);
INSERT INTO `sys_roles_menus` VALUES (58, 1);
INSERT INTO `sys_roles_menus` VALUES (60, 1);
INSERT INTO `sys_roles_menus` VALUES (61, 1);
INSERT INTO `sys_roles_menus` VALUES (62, 1);
INSERT INTO `sys_roles_menus` VALUES (64, 1);
INSERT INTO `sys_roles_menus` VALUES (65, 1);
INSERT INTO `sys_roles_menus` VALUES (66, 1);
INSERT INTO `sys_roles_menus` VALUES (73, 1);
INSERT INTO `sys_roles_menus` VALUES (74, 1);
INSERT INTO `sys_roles_menus` VALUES (75, 1);
INSERT INTO `sys_roles_menus` VALUES (77, 1);
INSERT INTO `sys_roles_menus` VALUES (78, 1);
INSERT INTO `sys_roles_menus` VALUES (79, 1);
INSERT INTO `sys_roles_menus` VALUES (80, 1);
INSERT INTO `sys_roles_menus` VALUES (82, 1);
INSERT INTO `sys_roles_menus` VALUES (92, 1);
INSERT INTO `sys_roles_menus` VALUES (93, 1);
INSERT INTO `sys_roles_menus` VALUES (94, 1);
INSERT INTO `sys_roles_menus` VALUES (97, 1);
INSERT INTO `sys_roles_menus` VALUES (102, 1);
INSERT INTO `sys_roles_menus` VALUES (103, 1);
INSERT INTO `sys_roles_menus` VALUES (104, 1);
INSERT INTO `sys_roles_menus` VALUES (105, 1);
INSERT INTO `sys_roles_menus` VALUES (106, 1);
INSERT INTO `sys_roles_menus` VALUES (107, 1);
INSERT INTO `sys_roles_menus` VALUES (108, 1);
INSERT INTO `sys_roles_menus` VALUES (109, 1);
INSERT INTO `sys_roles_menus` VALUES (110, 1);
INSERT INTO `sys_roles_menus` VALUES (111, 1);
INSERT INTO `sys_roles_menus` VALUES (112, 1);
INSERT INTO `sys_roles_menus` VALUES (113, 1);
INSERT INTO `sys_roles_menus` VALUES (114, 1);
INSERT INTO `sys_roles_menus` VALUES (116, 1);
INSERT INTO `sys_roles_menus` VALUES (123, 1);
INSERT INTO `sys_roles_menus` VALUES (124, 1);
INSERT INTO `sys_roles_menus` VALUES (65, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门名称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `is_admin` bit(1) NULL DEFAULT b'0' COMMENT '是否为admin账号',
  `enabled` bit(1) NULL DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `pwd_reset_time` datetime(0) NULL DEFAULT NULL COMMENT '修改密码的时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UK_kpubos9gc2cvtkb0thktkbkes`(`email`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `uniq_username`(`username`) USING BTREE,
  UNIQUE INDEX `uniq_email`(`email`) USING BTREE,
  INDEX `FK5rwmryny6jthaaxkogownknqp`(`dept_id`) USING BTREE,
  INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 7, 'lims_admin', '男', '18888888887', '117@qq.com', '$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa', b'1', b'1', NULL, 'zsb_admin', '2020-05-03 16:38:31', '2018-08-23 09:11:56', '2022-08-24 14:38:20');
INSERT INTO `sys_user` VALUES (2, 7, 'test', '男', '19999999999', '231@qq.com', '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', b'0', b'1', 'admin', 'zsb_admin', NULL, '2020-05-05 11:15:49', '2022-08-24 14:39:00');
INSERT INTO `sys_user` VALUES (3, 25, '贝吉塔', '男', '18888888888', '666@163.com', '$2a$10$t2EzWReFmlP6ULN9y3hG5e/wNa.gZ4hYx80rCc1dho0Sa3CEgyBJO', b'0', b'1', 'zsb_admin', 'zsb_admin', NULL, '2022-08-24 16:05:01', '2022-08-24 16:05:01');
INSERT INTO `sys_user` VALUES (4, 21, '卡卡罗特', '男', '13288888888', '121@163.com', '$2a$10$IHZgVwbBmVfAeSbfqlunROhRu3sD81F7Ui.PMNG8ZJhiTvYuO9c..', b'0', b'1', 'zsb_admin', 'zsb_admin', NULL, '2022-08-24 17:43:12', '2022-08-24 17:43:12');

-- ----------------------------
-- Table structure for sys_users_jobs
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_jobs`;
CREATE TABLE `sys_users_jobs`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `job_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_users_jobs
-- ----------------------------
INSERT INTO `sys_users_jobs` VALUES (1, 11);
INSERT INTO `sys_users_jobs` VALUES (2, 12);
INSERT INTO `sys_users_jobs` VALUES (3, 12);
INSERT INTO `sys_users_jobs` VALUES (4, 10);

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES (1, 1);
INSERT INTO `sys_users_roles` VALUES (2, 2);
INSERT INTO `sys_users_roles` VALUES (3, 2);
INSERT INTO `sys_users_roles` VALUES (4, 2);

-- ----------------------------
-- Table structure for tool_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `tool_local_storage`;
CREATE TABLE `tool_local_storage`  (
  `storage_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大小',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`storage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '本地存储' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tool_local_storage
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
