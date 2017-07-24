-- 删除mldn数据库
DROP DATABASE IF EXISTS mldn ;
-- 创建mldn数据库
CREATE DATABASE mldn CHARACTER SET UTF8 ;
-- 使用数据库
USE mldn ;
-- 删除数据表 
DROP TABLE IF EXISTS member_details ;
DROP TABLE IF EXISTS member_login ;
-- 创建数据表
CREATE TABLE member_login(
	mid			VARCHAR(50) ,
	password	VARCHAR(50) ,
	CONSTRAINT pk_mid1 PRIMARY KEY(mid)
) ;
CREATE TABLE member_details(
	mid			VARCHAR(50) ,
	name		VARCHAR(50) ,
	age			INT ,
	CONSTRAINT pk_mid2 PRIMARY KEY(mid) ,
	CONSTRAINT fk_mid2 FOREIGN KEY(mid) REFERENCES member_login(mid) ON DELETE CASCADE
) ;
