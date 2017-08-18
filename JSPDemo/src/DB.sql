DROP DATABASE IF EXISTS hrdb;
CREATE DATABASE hrdb CHARSET=UTF8;
USE hrdb;

CREATE TABLE dept
(
	deptno INT AUTO_INCREMENT,
	dname VARCHAR(50) NOT NULL,
	loc VARCHAR(50) NOT NULL,
	CONSTRAINT pk_deptno PRIMARY KEY(deptno)
);

CREATE TABLE emp
(
	empno INT AUTO_INCREMENT,
	ename VARCHAR(50) NOT NULL,
	job VARCHAR(50) NOT NULL,
	hiredate TIMESTAMP,
	sal FLOAT,
	comm FLOAT,
	photo VARCHAR(100),
	note VARCHAR(100),
	CONSTRAINT pk_empno PRIMARY KEY(empno)
);

INSERT INTO emp (ename,job,hiredate,sal,comm) VALUES('sheldon','dev','2011-07-06',3800.00,100.00);
INSERT INTO emp (ename,job,hiredate,sal,comm) VALUES('yuankui','test','2011-07-06',3800.00,100.00);
INSERT INTO emp (ename,job,hiredate,sal,comm) VALUES('wenliang','sales','2011-07-06',3800.00,100.00);
INSERT INTO emp (ename,job,hiredate,sal,comm) VALUES('lanchen','intern','2011-07-06',3800.00,100.00);
INSERT INTO emp (ename,job,hiredate,sal,comm) VALUES('yanling','support','2011-07-06',3800.00,100.00);