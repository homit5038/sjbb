--云南省房地产业协会估价分会专家库系统   表设计
go
--1 用户表
CREATE TABLE TUser(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	loginName VARCHAR(32), --登录名
	name VARCHAR(100), --用户名
	password VARCHAR(100), --密码
	phoneNum VARCHAR(14), --联系号码
	deptName VARCHAR(100), --部门名称
	email VARCHAR(100), --邮箱
	availability INT,  --有效性	
	createTime DATETIME, --创建时间
	createUserID NUMERIC(19,0), --创建者id
	creatorIp VARCHAR(100), --创建者ip
	lastUpdaterIp VARCHAR(100), --修改者ip
	updateLastTime DATETIME, --最后修改时间
	updateUserID NUMERIC(19, 0), --修改用户id
	primary key (id)  --主键
)

--2 用户角色对照表
CREATE TABLE TUserRole(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	role VARCHAR(100), --角色编码
	roleName VARCHAR(100), --角色名称
	userId NUMERIC(19,0), --角色对应用户id
	primary key (id)  --主键
)


--2 年级对照表
CREATE TABLE TGrade(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	gradename VARCHAR(100), --角色编码
	primary key (id)  --主键
)

--2  班级对照表
CREATE TABLE TClasses(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	cassesname VARCHAR(100), --角色编码
	pradeid  NUMERIC(19,0), --年级对应id
	primary key (id)  --主键
)


--3 专家表
CREATE TABLE TExpert(
	id NUMERIC(19,0) IDENTITY NOT NULL, 
	expertName VARCHAR(100) NOT NULL, --专家名称
	assessmentStructure  VARCHAR(200) NOT NULL, --评估机构
	expertEmail VARCHAR(50), --邮箱
	phoneNum VARCHAR(14), --电话号码
	expertTypeID NUMERIC(19,0), --专家类型ID
	flag INT, --抽取类型 1：必选 0：可选  -1：不可选
	availability INT,--有效性
	noteStatus INT,
	createTime DATETIME, --创建时间
	createUserID NUMERIC(19,0), --创建者id
	creatorIp VARCHAR(100), --创建者ip
	lastUpdaterIp VARCHAR(100), --修改者ip
	updateLastTime DATETIME, --最后修改时间
	updateUserID NUMERIC(19, 0), --修改用户id
	primary key (id)  --主键
)

--4 抽取批次表
CREATE TABLE TExpertBatch(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	batchName VARCHAR(100), --批次名称
	extractPepleNum INT, --抽取人数
	remark VARCHAR(255), --备注
	availability INT,--有效性
	batchStatus INT, --状态  0：待抽取  1：已抽取  2：已确认
	createTime DATETIME, --创建时间
	createUserID NUMERIC(19,0), --创建者id
	creatorIp VARCHAR(100), --创建者ip
	lastUpdaterIp VARCHAR(100), --修改者ip
	updateLastTime DATETIME, --最后修改时间
	updateUserID NUMERIC(19, 0), --修改用户id
    primary key (id)  --主键
)

--5 批次和专家对照表
CREATE TABLE Expert_Batch_dz(
	batchID NUMERIC(19,0) not null,
    expertID NUMERIC(19,0) not null,
    extractStatus INT DEFAULT 0,
	noteStatus INT DEFAULT 0
)

--6 操作日志表
CREATE TABLE SystemLog(
  id NUMERIC(19,0) IDENTITY NOT NULL,
  IP VARCHAR(100),
  userID NUMERIC(19,0),  --操作用户ID
  userName VARCHAR(100), --操作用户名字
  operTime DATETIME, --操作时间
  operTable VARCHAR(100), --操作的表
  operTableDataID NUMERIC(19,0), --操作表的数据ID
  operContent VARCHAR(255), --操作内容
  primary key (id) 
)

create table dbChange(majorVersion int,minorVersion int,fixVersion int,opDate datetime,fileName varchar(50));

go

ALTER TABLE TUserRole ADD CONSTRAINT FK_7ljjoo9teyx56hpa7hkfnms35 FOREIGN KEY (userID) REFERENCES TUser
ALTER TABLE Expert_Batch_dz ADD CONSTRAINT FK_qnr4gb0loftqnuvhdqwnooh84 FOREIGN KEY (expertID) REFERENCES TExpert
ALTER TABLE Expert_Batch_dz ADD CONSTRAINT FK_m1hdma7o7f21x1c8si54om0m4 FOREIGN KEY (batchID) REFERENCES TExpertBatch

go
--添加用户和角色
INSERT INTO TUSER(loginName,name,password,phoneNum,createTime,createUserID,updateLastTime,availability) 
values('admin','超级管理员','$2a$10$12Z..XV9M2zxsAmqLV9YAOUsZsGwvWUVdwTfCA46B7sbO/vjAsRqS','',CONVERT(varchar(100), GETDATE(), 20),1,CONVERT(varchar(100), GETDATE(), 20),1);

insert into tuserRole(userId,role) values(1,'ROLE_ADMIN');
insert into tuserRole(userId,role) values(1,'ROLE_EXPERT');
insert into tuserRole(userId,role) values(1,'ROLE_SALES');


insert into dbChange(majorVersion,minorVersion,fixVersion,fileName,opdate) values(1,0,0,'1.0.sql',GETDATE());



