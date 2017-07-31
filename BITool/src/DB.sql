DROP DATABASE IF EXISTS bitool;
CREATE DATABASE bitool CHARACTER SET UTF8;

USE bitool;

DROP TABLE IF EXISTS accountManagement;
CREATE TABLE accountManagement
(
  aid    INT    AUTO_INCREMENT,
  userID  VARCHAR(20),
  transformedUserID VARCHAR(20),
  userType  VARCHAR(20),
  ipAddress  VARCHAR(15),
  CONSTRAINT pk_aid  PRIMARY KEY(aid)
);

INSERT INTO accountManagement(userID,transformedUserID,userType,ipAddress) values('yuankui','sheldon','ADMIN','127.0.0.1');
--15.211.153.79
INSERT INTO accountManagement(userID,transformedUserID,userType,ipAddress) values('wenliang','jonathan','ADMIN','15.211.153.79');


DROP TABLE IF EXISTS openOffManagement;
CREATE TABLE openOffManagement
(
  oid    INT   AUTO_INCREMENT, 
  mode  VARCHAR(20) ,
  startTime  VARCHAR(5),
  endTime    VARCHAR(5),
  modtime	timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_oid  PRIMARY KEY(oid)
);

INSERT INTO openOffManagement(mode,startTime,endTime,modtime) VALUES('MAINTAINANCE','09:00','21:30',NULL);