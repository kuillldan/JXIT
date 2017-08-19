DROP DATABASE IF EXISTS HRDB;
CREATE DATABASE HRDB CHARSET=UTF8;
USE HRDB;

﻿DROP TABLE IF EXISTS EMP ;
DROP TABLE IF EXISTS DEPT;
CREATE TABLE DEPT
       (DEPTNO INT PRIMARY KEY,
 DNAME VARCHAR(14) ,
 LOC VARCHAR(13) ) ;

CREATE TABLE EMP
       (EMPNO INT(4)  PRIMARY KEY,
 ENAME VARCHAR(10),
 JOB VARCHAR(9),
 MGR INT(4),
 HIREDATE DATE,
 SAL INT(7),
 COMM INT(7),
 DEPTNO INT(2),
 CONSTRAINT FK_DEPTNO FOREIGN KEY(DEPTNO) REFERENCES DEPT(DEPTNO));






INSERT INTO DEPT VALUES
 (10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');
INSERT INTO DEPT VALUES
 (30,'SALES','CHICAGO');
INSERT INTO DEPT VALUES
 (40,'OPERATIONS','BOSTON');
INSERT INTO EMP VALUES
(7369,'SMITH','CLERK',7902,date_format('1980-12-17','%Y-%m-%d'),800,NULL,20);
INSERT INTO EMP VALUES
(7499,'ALLEN','SALESMAN',7698,date_format('1981-2-20','%Y-%m-%d'),1600,300,30);
INSERT INTO EMP VALUES
(7521,'WARD','SALESMAN',7698,date_format('1981-2-22','%Y-%m-%d'),1250,500,30);
INSERT INTO EMP VALUES
(7566,'JONES','MANAGER',7839,date_format('1981-2-4','%Y-%m-%d'),2975,NULL,20);
INSERT INTO EMP VALUES
(7654,'MARTIN','SALESMAN',7698,date_format('1981-9-28','%Y-%m-%d'),1250,1400,30);
INSERT INTO EMP VALUES
(7698,'BLAKE','MANAGER',7839,date_format('1981-1-5','%Y-%m-%d'),2850,NULL,30);
INSERT INTO EMP VALUES
(7782,'CLARK','MANAGER',7839,date_format('1981-9-6','%Y-%m-%d'),2450,NULL,10);
INSERT INTO EMP VALUES
(7788,'SCOTT','ANALYST',7566,date_format('1987-11-22','%Y-%m-%d'),3000,NULL,20);
INSERT INTO EMP VALUES
(7839,'KING','PRESIDENT',NULL,date_format('1981-11-17','%Y-%m-%d'),5000,NULL,10);
INSERT INTO EMP VALUES
(7844,'TURNER','SALESMAN',7698,date_format('1981-9-8','%Y-%m-%d'),1500,0,30);
INSERT INTO EMP VALUES
(7876,'ADAMS','CLERK',7788,date_format('1987-10-18','%Y-%m-%d'),1100,NULL,20);
INSERT INTO EMP VALUES
(7900,'JAMES','CLERK',7698,date_format('1981-12-3','%Y-%m-%d'),950,NULL,30);
INSERT INTO EMP VALUES
(7902,'FORD','ANALYST',7566,date_format('1981-3-12','%Y-%m-%d'),3000,NULL,20);
INSERT INTO EMP VALUES
(7934,'MILLER','CLERK',7782,date_format('1982-1-23','%Y-%m-%d'),1300,NULL,10);
DROP TABLE IF EXISTS BONUS;
CREATE TABLE BONUS
 (
 ENAME VARCHAR(10),
 JOB VARCHAR(9),
 SAL INT,
 COMM INT
 );
DROP TABLE IF EXISTS SALGRADE;
CREATE TABLE SALGRADE
      ( GRADE INT,
 LOSAL INT,
 HISAL INT );
 
 
 
INSERT INTO SALGRADE VALUES (1,700,1200);
INSERT INTO SALGRADE VALUES (2,1201,1400);
INSERT INTO SALGRADE VALUES (3,1401,2000);
INSERT INTO SALGRADE VALUES (4,2001,3000);
INSERT INTO SALGRADE VALUES (5,3001,9999);
COMMIT;
 
 
DROP TABLE IF EXISTS admin;
CREATE TABLE admin(
 aid   VARCHAR(50),
 password  VARCHAR(32),
 CONSTRAINT PK_AID PRIMARY KEY(aid)
);

INSERT INTO admin(aid,password) VALUES ('admin','5D41402ABC4B2A76B9719D911017C592') ;
-- 用户名：mldn，密码：java
INSERT INTO admin(aid,password) VALUES ('mldn','93F725A07423FE1C889F448B33D21F46') ;
COMMIT ;  
 
ALTER TABLE EMP ADD (photo VARCHAR(50) DEFAULT 'nophoto.jpg');
ALTER TABLE EMP ADD (note VARCHAR(1024));