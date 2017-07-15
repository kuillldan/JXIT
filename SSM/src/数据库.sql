-- 删除mldn数据库

DROP DATABASE IF EXISTS ibatis ;
-- 创建mldn数据库
CREATE DATABASE ibatis CHARACTER SET UTF8 ;
-- 使用数据库
USE ibatis ;
-- 删除数据表 
DROP TABLE IF EXISTS news ;
-- 创建数据表
CREATE TABLE news(
	nid			INT		AUTO_INCREMENT ,
	title		VARCHAR(50) ,
	pub_date	DATE ,
	content		TEXT ,
	CONSTRAINT pk_nid PRIMARY KEY(nid)
) ;
