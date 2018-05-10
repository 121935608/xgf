-- ----------------------------
-- 1、创建用户表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userId` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `userName` varchar(20) DEFAULT '' COMMENT '用户名',
  `accountName` varchar(20) DEFAULT '' COMMENT '账户名',
  `email`  varchar(100) DEFAULT '' COMMENT '用户邮箱',
  `mobilePhone`  VARCHAR(20) DEFAULT '' COMMENT '手机号码',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `salt` varchar(100) DEFAULT '' COMMENT '盐',
  `description` varchar(100) DEFAULT '' COMMENT '描述',
  `locked` varchar(3) DEFAULT '0' COMMENT '账户是否锁定 0正常 1锁定',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Init新增用户表初始化数据
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '阳宗专', 'yzz_ivy@163.com', '1517911213', '172eee54aa664e9dd0536b063796e54e', '', '我是网站超级管理员', '0', '2016-07-21 22:50:00');
INSERT INTO `sys_user` VALUES ('2', 'huawei', '华为科技公司', 'yb_ivy@163.com', '15220051213', '2a24e799b094fb48be3b3a3f49710a12', '', '我是华为公司普通用户', '0', '2016-07-21 22:50:00');



-- ----------------------------
-- 2、创建角色表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleId` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `status` varchar(3) DEFAULT '' COMMENT '状态0可用 1不可用',
  `roleName` varchar(50) DEFAULT '' COMMENT '角色名',
  `roleKey` varchar(50) DEFAULT '' COMMENT '角色标识',
  `description` varchar(50) DEFAULT '' COMMENT '描述',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Init新增角色表初始化数据
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '0', '管理员','sys:admin','管理员', '2016-07-21 22:50:00');
INSERT INTO `sys_role` VALUES ('2', '0', '普通角色','sys:user','普通角色', '2016-07-25 22:50:00');



-- ----------------------------
-- 3、创建菜单权限表
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `permsId` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `permsName` varchar(50) DEFAULT '' COMMENT '菜单名称',
  `parentId` int(11) DEFAULT '0' COMMENT '父菜单ID',
  `permsKey` varchar(50) DEFAULT '' COMMENT '权限字符串',
  `permsType` varchar(40) DEFAULT '' COMMENT '菜单类型 0一级菜单 1二级菜单 3三级菜单',
  `permsUrl` varchar(200) DEFAULT '' COMMENT '菜单链接',
  `permsLevel` int(4) DEFAULT NULL COMMENT '显示顺序',
  `permsIcon` varchar(100) DEFAULT '' COMMENT '菜单图标',
  `available` int(3) DEFAULT '0' COMMENT '菜单是否显示 0显示 1隐藏',
  `description` varchar(200) DEFAULT '' COMMENT '菜单描述',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`permsId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Init新增菜单表初始化数据
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('100', '系统管理', '0', 'system', '0', '#', '1', 'Hui-iconfont Hui-iconfont-system', '0', '系统管理', '2016-07-21 22:50:00');
INSERT INTO `sys_permission` VALUES ('101', '用户管理', '100', 'system:user', '1', '/system/userView.action', '1', '#', '0', '用户管理', '2016-07-21 22:50:00');
INSERT INTO `sys_permission` VALUES ('102', '角色管理', '100', 'system:role', '1', '/system/roleView.action', '2', '#', '0', '角色管理', '2016-07-21 22:50:00');
INSERT INTO `sys_permission` VALUES ('103', '菜单管理', '100', 'system:menu', '1', '/system/menuView.action', '3', '#', '0', '菜单管理', '2016-07-21 22:50:00');
INSERT INTO `sys_permission` VALUES ('104', '操作日志', '100', 'system:operlog', '1', '/system/operlogView.action', '4', '#', '0', '操作日志', '2016-07-21 22:50:00');
INSERT INTO `sys_permission` VALUES ('105', '登录日志', '100', 'system:userlog', '1', '/system/userlogView.action', '5', '#', '0', '登录日志', '2016-07-21 22:50:00');

INSERT INTO `sys_permission` VALUES ('200', '系统监控', '0', 'monitor', '0', '#', '2', 'Hui-iconfont Hui-iconfont-manage2', '0', '系统监控', '2016-07-21 22:50:00');
INSERT INTO `sys_permission` VALUES ('201', '数据监控', '200', 'monitor:druid', '1', '/monitor/druid/index.html', '1', '#', '0', '数据监控', '2016-07-21 22:50:00');
INSERT INTO `sys_permission` VALUES ('202', 'xxxxxx', '200', 'monitor:accesslogger', '1', '/monitor/accessLoggerList.action', '3', '#', '0', 'xxxxxx', '2016-07-21 22:50:00');


-- ----------------------------
-- 4、创建用户和角色关联表  用户N-1角色
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Init新增用户和角色关联表初始化数据
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');



-- ----------------------------
-- 5、创建角色和菜单关联表  角色1-N菜单
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `permsId` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`roleId`,`permsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Init新增角色和菜单关联表初始化数据
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '100');
INSERT INTO `sys_role_permission` VALUES ('1', '101');
INSERT INTO `sys_role_permission` VALUES ('1', '102');
INSERT INTO `sys_role_permission` VALUES ('1', '103');
INSERT INTO `sys_role_permission` VALUES ('1', '104');
INSERT INTO `sys_role_permission` VALUES ('1', '105');

INSERT INTO `sys_role_permission` VALUES ('1', '200');
INSERT INTO `sys_role_permission` VALUES ('1', '201');
INSERT INTO `sys_role_permission` VALUES ('1', '202');


-- ----------------------------
-- 6、创建操作日志管理表
-- ----------------------------
DROP TABLE IF EXISTS `sys_operlog`;
CREATE TABLE `sys_operlog` (
  `logId` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(50) DEFAULT '' COMMENT '模块信息',
  `title` varchar(50) DEFAULT '' COMMENT '模块名',
  `channel` varchar(50) DEFAULT '' COMMENT '渠道',
  `requestParam` varchar(255) DEFAULT '' COMMENT '请求参数',
  `userIp` varchar(30) DEFAULT '' COMMENT '执行地址',
  `url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `userId` varchar(50) DEFAULT '' COMMENT '用户ID',
  `status` varchar(3) DEFAULT '' COMMENT '状态0正常 1异常',
  `userName` varchar(50) DEFAULT '' COMMENT '用户账号',
  `errorMessage` varchar(255) DEFAULT '' COMMENT '错误消息',
  `createTIme` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 7、系统初始化数据
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
create table `sys_code` (
   `codekey`         varchar(50)  NOT NULL COMMENT '标识KEY',
   `codeid`          varchar(50)  NOT NULL COMMENT '标识ID',
   `codevalue`       varchar(50)  NOT NULL COMMENT '数据值',
   `codedesc`        varchar(255) DEFAULT NULL COMMENT '描述',
   `servicekey`      varchar(50)  DEFAULT NULL COMMENT '模块',
   PRIMARY KEY (`codeid`, `codekey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 初始化是否锁定状态
-- ----------------------------
insert into sys_code values ('sys-locked','0','正常','locked_status_0','JEEYZZ');
insert into sys_code values ('sys-locked','1','锁定','locked_status_1','JEEYZZ');

-- ----------------------------
-- 初始化菜单选项列表
-- ----------------------------
insert into sys_code values ('sys-available','0','显示','menu_available_0','JEEYZZ');
insert into sys_code values ('sys-available','1','隐藏','menu_available_1','JEEYZZ');

-- ----------------------------
-- 初始化角色状态列表
-- ----------------------------
insert into sys_code values ('sys-role_status','0','可用','role_status_0','JEEYZZ');
insert into sys_code values ('sys-role_status','1','不可用','role_status_1','JEEYZZ');

-- ----------------------------
-- 初始化登录状态列表
-- ----------------------------
INSERT INTO sys_code VALUES ('sys-login_status','0','成功','login_status_0','JEEYZZ');
INSERT INTO sys_code VALUES ('sys-login_status','1','失败','login_status_1','JEEYZZ');

-- 初始化优惠券审核状态列表
-- ----------------------------
INSERT INTO sys_code VALUES ('crm-audit-status','1','通过','audit-status_1','JEEYZZ');
INSERT INTO sys_code VALUES ('crm-audit-status','2','不通过','audit-status_2','JEEYZZ');

-- ----------------------------
-- 8、系统登录日志信息
-- ----------------------------
DROP TABLE IF EXISTS `sys_userlog`;
CREATE TABLE `sys_userlog` (
  `signId`   INT(11) NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(50)  DEFAULT '' COMMENT '用户账号',
  `status`   VARCHAR(50)  DEFAULT '' COMMENT '登录状态 0成功 1失败',
  `loginIP`  VARCHAR(50)  DEFAULT '' COMMENT '登录IP地址',
  `browser`  VARCHAR(50)  DEFAULT '' COMMENT '浏览器类型',
  `os`       VARCHAR(50)  DEFAULT '' COMMENT '操作系统',
  `msg`      VARCHAR(255) DEFAULT '' COMMENT '提示消息',
  `loginTime` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`signId`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;