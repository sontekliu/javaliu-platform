-- 用户表
CREATE TABLE `sys_user` (
  `id` bigint(32) NOT NULL COMMENT '主键ID',
  `email` varchar(128) NOT NULL COMMENT 'Email地址',
  `code` varchar(64) DEFAULT NULL COMMENT '登录用户名',
  `name` varchar(64) DEFAULT NULL COMMENT '用户昵称',
  `password` char(64) DEFAULT NULL COMMENT '密码',
  `sex` int DEFAULT '0' COMMENT '性别',
  `year` int DEFAULT NULL COMMENT '年份',
  `month` int DEFAULT NULL COMMENT '月份',
  `day` int DEFAULT NULL COMMENT '日期',
  `header_pic` VARCHAR(64) DEFAULT NULL COMMENT '头像地址',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `createBy` bigint(32) DEFAULT NULL COMMENT '创建人',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateBy` bigint(32) DEFAULT NULL COMMENT '修改人',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
