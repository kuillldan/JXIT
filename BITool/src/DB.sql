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

DROP TABLE IF EXISTS statusManagement;
CREATE TABLE statusManagement
(
	sid 		INT AUTO_INCREMENT,
	status		INT ,
	mode		INT	,
	startTime	VARCHAR(5),
	endTime		VARCHAR(5),
	CONSTRAINT pk_sid PRIMARY KEY(sid)
);