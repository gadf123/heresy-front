﻿CREATE TABLE User
(
    `userIdx`       INT             NOT NULL    AUTO_INCREMENT,
    `userId`        VARCHAR(50)     NOT NULL,
    `userNickName`  VARCHAR(20)     NOT NULL,
    `password`      VARCHAR(255)    NOT NULL,
    `experience`    INT             NOT NULL,
    `tendency`      INT             NOT NULL,
    `introduction`  TEXT            NOT NULL,
    `authSnsId`     VARCHAR(50)     NOT NULL,
    `createDate`    DATETIME        NOT NULL,
    `updateDate`    DATETIME        NOT NULL,
    PRIMARY KEY (userIdx)
);



CREATE TABLE AgendaAndDebateBoardArticle
(
    `articleIdx`    INT             NOT NULL    AUTO_INCREMENT,
    `subBoardIdx`   INT             NOT NULL,
    `title`         VARCHAR(100)    NOT NULL,
    `userIdx`       INT             NOT NULL,
    `userNickName`  VARCHAR(20)     NOT NULL,
    `content`       TEXT            NOT NULL,
    `good`          INT             NULL,
    `bad`           INT             NULL,
    `createDate`    DATETIME        NOT NULL,
    `updateDate`    DATETIME        NOT NULL,
    PRIMARY KEY (articleIdx)
);

ALTER TABLE AgendaAndDebateBoardArticle ADD CONSTRAINT FK_AgendaAndDebateBoardArticle_userIdx_User_userIdx FOREIGN KEY (userIdx)
 REFERENCES User (userIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE Role
(
    `roleIdx`     INT            NOT NULL    AUTO_INCREMENT,
    `roleName`    VARCHAR(20)    NULL,
    `createDate`  DATETIME       NULL,
    `updateDate`  DATETIME       NULL,
    PRIMARY KEY (roleIdx)
);



CREATE TABLE BasicBoardArticle
(
    `articleIdx`    INT             NOT NULL    AUTO_INCREMENT,
    `subBoardIdx`   INT             NOT NULL,
    `title`         VARCHAR(100)    NOT NULL,
    `userIdx`       INT             NOT NULL,
    `userNickName`  VARCHAR(20)     NOT NULL,
    `content`       TEXT            NOT NULL,
    `good`          INT             NULL,
    `bad`           INT             NULL,
    `createDate`    DATETIME        NOT NULL,
    `updateDate`    DATETIME        NOT NULL,
    PRIMARY KEY (articleIdx)
);

ALTER TABLE BasicBoardArticle ADD CONSTRAINT FK_BasicBoardArticle_userIdx_User_userIdx FOREIGN KEY (userIdx)
 REFERENCES User (userIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE UserJob
(
    `userJobIdx`  INT         NOT NULL    AUTO_INCREMENT,
    `userIdx`     INT         NOT NULL,
    `jobIdx`      INT         NOT NULL,
    `createDate`  DATETIME    NOT NULL,
    `updateDate`  DATETIME    NOT NULL,
    PRIMARY KEY (userJobIdx)
);

ALTER TABLE UserJob ADD CONSTRAINT FK_UserJob_userIdx_User_userIdx FOREIGN KEY (userIdx)
 REFERENCES User (userIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE Title
(
    `titleIdx`    INT            NOT NULL    AUTO_INCREMENT,
    `title`       VARCHAR(45)    NULL,
    `createDate`  DATETIME       NULL,
    `updateDate`  DATETIME       NULL,
    PRIMARY KEY (titleIdx)
);



CREATE TABLE Hometown
(
    `hometownIdx`   INT            NOT NULL    AUTO_INCREMENT,
    `homeTownName`  VARCHAR(45)    NOT NULL,
    `createDate`    DATETIME       NOT NULL,
    `updateDate`    DATETIME       NOT NULL,
    PRIMARY KEY (hometownIdx)
);



CREATE TABLE Job
(
    `jobIdx`      INT            NOT NULL    AUTO_INCREMENT,
    `jobName`     VARCHAR(20)    NOT NULL,
    `createDate`  DATETIME       NOT NULL,
    `updateDate`  DATETIME       NOT NULL,
    PRIMARY KEY (jobIdx)
);

ALTER TABLE Job ADD CONSTRAINT FK_Job_jobIdx_UserJob_jobIdx FOREIGN KEY (jobIdx)
 REFERENCES UserJob (jobIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE UserTitle
(
    `userTitleIdx`  INT         NOT NULL    AUTO_INCREMENT,
    `userIdx`       INT         NULL,
    `titleIdx`      INT         NULL,
    `createDate`    DATETIME    NULL,
    `updateDate`    DATETIME    NULL,
    PRIMARY KEY (userTitleIdx)
);

ALTER TABLE UserTitle ADD CONSTRAINT FK_UserTitle_userIdx_User_userIdx FOREIGN KEY (userIdx)
 REFERENCES User (userIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE UserTitle ADD CONSTRAINT FK_UserTitle_titleIdx_Title_titleIdx FOREIGN KEY (titleIdx)
 REFERENCES Title (titleIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;


CREATE TABLE UserRoles
(
    `rolesIdx`    INT         NOT NULL    AUTO_INCREMENT,
    `userIdx`     INT         NULL,
    `roleIdx`     INT         NULL,
    `createDate`  DATETIME    NULL,
    `updateDate`  DATETIME    NULL,
    PRIMARY KEY (rolesIdx)
);

ALTER TABLE UserRoles ADD CONSTRAINT FK_UserRoles_roleIdx_Role_roleIdx FOREIGN KEY (roleIdx)
 REFERENCES Role (roleIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE UserRoles ADD CONSTRAINT FK_UserRoles_userIdx_User_userIdx FOREIGN KEY (userIdx)
 REFERENCES User (userIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE Privileges
(
    `privileageIdx`  INT         NOT NULL    AUTO_INCREMENT,
    `roleIdx`        INT         NULL,
    `createDate`     DATETIME    NULL,
    `updateDate`     DATETIME    NULL,
    PRIMARY KEY (privileageIdx)
);

ALTER TABLE Privileges ADD CONSTRAINT FK_Privileges_roleIdx_Role_roleIdx FOREIGN KEY (roleIdx)
 REFERENCES Role (roleIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE UserHometown
(
    `userHometownIdx`  INT         NOT NULL    AUTO_INCREMENT,
    `userIdx`          INT         NOT NULL,
    `hometownIdx`      INT         NOT NULL,
    `createDate`       DATETIME    NOT NULL,
    `updateDate`       DATETIME    NOT NULL,
    PRIMARY KEY (userHometownIdx)
);

ALTER TABLE UserHometown ADD CONSTRAINT FK_UserHometown_hometownIdx_Hometown_hometownIdx FOREIGN KEY (hometownIdx)
 REFERENCES Hometown (hometownIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE UserHometown ADD CONSTRAINT FK_UserHometown_userIdx_User_userIdx FOREIGN KEY (userIdx)
 REFERENCES User (userIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE AgendaAndDebateBoardCommet
(
    `commentIdx`  INT             NOT NULL    AUTO_INCREMENT,
    `articleIdx`  INT             NOT NULL,
    `userIdx`     INT             NOT NULL,
    `comment`     VARCHAR(400)    NOT NULL,
    `good`        INT             NULL,
    `bad`         INT             NULL,
    `createDate`  DATETIME        NOT NULL,
    `updateDate`  DATETIME        NOT NULL,
    PRIMARY KEY (commentIdx)
);

ALTER TABLE AgendaAndDebateBoardCommet ADD CONSTRAINT FK_AgendaAndDebateBoardCommet_articleIdx_AgendaAndDebateBoardArticle_articleIdx FOREIGN KEY (articleIdx)
 REFERENCES AgendaAndDebateBoardArticle (articleIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE Tendency
(
    `articleIdx`  INT         NULL,
    `superRight`  INT         NULL,
    `superLeft`   INT         NULL,
    `right`       INT         NULL,
    `middle`      INT         NULL,
    `createDate`  DATETIME    NOT NULL,
    `updateDate`  DATETIME    NOT NULL,
    PRIMARY KEY (articleIdx)
);

ALTER TABLE Tendency ADD CONSTRAINT FK_Tendency_articleIdx_AgendaAndDebateBoardArticle_articleIdx FOREIGN KEY (articleIdx)
 REFERENCES AgendaAndDebateBoardArticle (articleIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE BasicBoardComment
(
    `commentIdx`  INT             NOT NULL    AUTO_INCREMENT,
    `articleIdx`  INT             NOT NULL,
    `userIdx`     INT             NOT NULL,
    `comment`     VARCHAR(400)    NOT NULL,
    `good`        INT             NULL,
    `bad`         INT             NULL,
    `createDate`  DATETIME        NOT NULL,
    `updateDate`  DATETIME        NOT NULL,
    PRIMARY KEY (commentIdx)
);

ALTER TABLE BasicBoardComment ADD CONSTRAINT FK_BasicBoardComment_articleIdx_BasicBoardArticle_articleIdx FOREIGN KEY (articleIdx)
 REFERENCES BasicBoardArticle (articleIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE BasicBoardUpload
(
    `fileIdx`     INT             NOT NULL    AUTO_INCREMENT,
    `fileName`    VARCHAR(255)    NOT NULL,
    `articleIdx`  INT             NOT NULL,
    `userIdx`     INT             NOT NULL,
    `createDate`  DATETIME        NOT NULL,
    `updateDate`  DATETIME        NOT NULL,
    PRIMARY KEY (fileIdx)
);

ALTER TABLE BasicBoardUpload ADD CONSTRAINT FK_BasicBoardUpload_articleIdx_BasicBoardArticle_articleIdx FOREIGN KEY (articleIdx)
 REFERENCES BasicBoardArticle (articleIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE AgendaAndDebateBoardUpload
(
    `fileIdx`     INT             NOT NULL    AUTO_INCREMENT,
    `fileName`    VARCHAR(255)    NULL,
    `articleIdx`  INT             NULL,
    `userIdx`     INT             NULL,
    `createDate`  DATETIME        NULL,
    `updateDate`  DATETIME        NULL,
    PRIMARY KEY (fileIdx)
);

ALTER TABLE AgendaAndDebateBoardUpload ADD CONSTRAINT FK_AgendaAndDebateBoardUpload_articleIdx_AgendaAndDebateBoardArticle_articleIdx FOREIGN KEY (articleIdx)
 REFERENCES AgendaAndDebateBoardArticle (articleIdx)  ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE Level
(
    `levelIdx`    INT    NOT NULL    AUTO_INCREMENT,
    `degree`      INT    NOT NULL,
    `level`       INT    NOT NULL,
    `experience`  INT    NOT NULL,
    PRIMARY KEY (levelIdx)
);

-- DROP TABLE AGENDAANDDEBATEBOARDUPLOAD;
-- DROP TABLE BASICBOARDARTICLE;
-- DROP TABLE AGENDAANDDEBATEBOARDARTICLE;
-- DROP TABLE AGENDAANDDEBATEBOARDCOMMET;
-- DROP TABLE BASICBOARDCOMMENT;
-- DROP TABLE BASICBOARDUPLOAD;
-- DROP TABLE HOMETOWN;
-- DROP TABLE JOB;
-- DROP TABLE LEVEL;
-- DROP TABLE PRIVILEGES;
-- DROP TABLE ROLE;
-- DROP TABLE TENDENCY;
-- DROP TABLE TITLE;
-- DROP TABLE UUSER;
-- DROP TABLE USERHOMETOWN;
-- DROP TABLE USERJOB;
-- DROP TABLE USERROLES;
-- DROP TABLE USERTITLE;





