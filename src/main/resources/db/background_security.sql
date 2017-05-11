/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : background_security

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-11 17:56:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_logs
-- ----------------------------
DROP TABLE IF EXISTS `sys_logs`;
CREATE TABLE `sys_logs` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id_` int(11) DEFAULT NULL COMMENT '用户ID',
  `logs_type_` char(4) NOT NULL COMMENT '日志类型：1001：正常  1002：异常',
  `url_` varchar(255) NOT NULL COMMENT '请求url',
  `ip_` varchar(50) NOT NULL COMMENT '请求IP',
  `class_path_` varchar(255) NOT NULL COMMENT '请求类路径',
  `ask_method_` varchar(50) NOT NULL COMMENT '请求方法',
  `method_path_` varchar(500) NOT NULL COMMENT '请求方法全路径',
  `request_method_` char(4) NOT NULL COMMENT '请求方式',
  `params_` longtext COMMENT '请求参数',
  `description_` varchar(255) DEFAULT NULL COMMENT '描述',
  `error_code_` varchar(500) DEFAULT NULL COMMENT '异常代码',
  `error_msg_` longtext COMMENT '异常信息',
  `method_result_value_` longtext COMMENT '请求方法返回值',
  `create_date_` datetime DEFAULT NULL COMMENT '创建时间',
  `waste_time_` bigint(20) DEFAULT NULL COMMENT '方法消耗时间(秒为单位)',
  `waste_time_msg_` varchar(32) DEFAULT NULL COMMENT '方法执行消耗时间转换值',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志纪录';

-- ----------------------------
-- Records of sys_logs
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_menus`;
CREATE TABLE `sys_resource_menus` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_number_` varchar(32) NOT NULL COMMENT '菜单编号',
  `menu_name_` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_type_` char(4) NOT NULL COMMENT '菜单类型(1001：目录  1002:菜单  1003：按钮)',
  `parent_menu_number_` varchar(50) DEFAULT NULL COMMENT '父级',
  `serial_number_` tinyint(4) DEFAULT NULL COMMENT '序号',
  `menu_icon_` varchar(50) DEFAULT NULL COMMENT '图标',
  `menu_url_` varchar(255) DEFAULT NULL COMMENT 'URL',
  `authorized_signs_` varchar(255) DEFAULT NULL COMMENT '授权标识(多个用逗号分隔，如：user:list,user:create)',
  `status_` char(4) DEFAULT NULL COMMENT '状态 1001：禁用   1002：正常',
  `create_date_` datetime NOT NULL COMMENT '创建人',
  `create_user_id_` int(11) NOT NULL COMMENT '创建时间',
  `update_user_id_` int(11) DEFAULT NULL COMMENT '修改人',
  `update_date_` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源菜单';

-- ----------------------------
-- Records of sys_resource_menus
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name_` varchar(40) NOT NULL COMMENT '角色名称',
  `remarks_` varchar(255) DEFAULT NULL COMMENT '备注',
  `status_` char(4) DEFAULT NULL COMMENT '状态 1001：禁用   1002：正常',
  `create_date_` datetime NOT NULL COMMENT '创建人',
  `create_user_id_` int(11) NOT NULL COMMENT '创建时间',
  `update_user_id_` int(11) DEFAULT NULL COMMENT '修改人',
  `update_date_` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id_` int(11) NOT NULL COMMENT '角色ID',
  `menu_id_` int(11) NOT NULL COMMENT '菜单ID',
  `create_date_` datetime NOT NULL COMMENT '创建人',
  `create_user_id_` int(11) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单对应关系(角色资源菜单)';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_account`;
CREATE TABLE `sys_users_account` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_number_` varchar(32) NOT NULL COMMENT '用户编号',
  `user_id_` int(11) NOT NULL COMMENT '用户ID',
  `user_name_` varchar(50) NOT NULL COMMENT '用户名',
  `user_pwd_` varchar(50) NOT NULL COMMENT '用户密码',
  `account_type_` char(4) DEFAULT '1001' COMMENT '帐号类型  1001：普通用户 ',
  `integral_` bigint(20) NOT NULL DEFAULT '10' COMMENT '积分',
  `status_` char(40) NOT NULL DEFAULT '1002' COMMENT '状态 1001：禁用   1002：正常',
  `salt_` varchar(10) DEFAULT NULL COMMENT '盐值',
  `create_date_` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户';

-- ----------------------------
-- Records of sys_users_account
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id_` int(11) NOT NULL COMMENT '用户ID',
  `role_id_` int(11) NOT NULL COMMENT '角色ID',
  `create_date_` datetime NOT NULL COMMENT '创建人',
  `create_user_id_` int(11) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色对应关系(用户角色)';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
