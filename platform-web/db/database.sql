-- 创建数据库
CREATE DATABASE javaliu_platform CHARACTER SET utf8 COLLATE utf8_general_ci;
-- 创建用户
CREATE USER 'sontek'@'%' IDENTIFIED BY 'sontek';
-- 授权
GRANT ALL ON javaliu_platform.* TO 'sontek'@'%';