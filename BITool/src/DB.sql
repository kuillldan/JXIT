DROP DATABASE IF EXISTS bitool;
CREATE DATABASE bitool CHARACTER SET UTF8;

USE bitool;

DROP TABLE IF EXISTS accountManagement;
CREATE TABLE accountManagement
(
	aid		INT		AUTO_INCREMENT,
	userID	VARCHAR(20),
	transformedUserID VARCHAR(20),
	userType	VARCHAR(20),
	ipAddress	VARCHAR(15),
	CONSTRAINT pk_aid	PRIMARY KEY(aid)
);

INSERT INTO accountManagement(userID,transformedUserID,userType,ipAddress) values('yuankui','sheldon','NORMAL','127.0.0.1');


DROP TABLE IF EXISTS openOffManagement;
CREATE TABLE openOffManagement
(
	oid		INT 	AUTO_INCREMENT,
	status	VARCHAR(10) ,
	mode	VARCHAR(10) ,
	startTime	VARCHAR(5),
	endTime		VARCHAR(5),
	CONSTRAINT pk_oid	PRIMARY KEY(oid)
);

INSERT INTO openOffManagement(status,mode,startTime,endTime) VALUES('OPEN','AUTO','09:00','21:30');