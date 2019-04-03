--����ʡ���ز�ҵЭ����۷ֻ�ר�ҿ�ϵͳ   �����
go
--1 �û���
CREATE TABLE TUser(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	loginName VARCHAR(32), --��¼��
	name VARCHAR(100), --�û���
	password VARCHAR(100), --����
	phoneNum VARCHAR(14), --��ϵ����
	deptName VARCHAR(100), --��������
	email VARCHAR(100), --����
	availability INT,  --��Ч��	
	createTime DATETIME, --����ʱ��
	createUserID NUMERIC(19,0), --������id
	creatorIp VARCHAR(100), --������ip
	lastUpdaterIp VARCHAR(100), --�޸���ip
	updateLastTime DATETIME, --����޸�ʱ��
	updateUserID NUMERIC(19, 0), --�޸��û�id
	primary key (id)  --����
)

--2 �û���ɫ���ձ�
CREATE TABLE TUserRole(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	role VARCHAR(100), --��ɫ����
	roleName VARCHAR(100), --��ɫ����
	userId NUMERIC(19,0), --��ɫ��Ӧ�û�id
	primary key (id)  --����
)


--2 �꼶���ձ�
CREATE TABLE TGrade(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	gradename VARCHAR(100), --��ɫ����
	primary key (id)  --����
)

--2  �༶���ձ�
CREATE TABLE TClasses(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	cassesname VARCHAR(100), --��ɫ����
	pradeid  NUMERIC(19,0), --�꼶��Ӧid
	primary key (id)  --����
)


--3 ר�ұ�
CREATE TABLE TExpert(
	id NUMERIC(19,0) IDENTITY NOT NULL, 
	expertName VARCHAR(100) NOT NULL, --ר������
	assessmentStructure  VARCHAR(200) NOT NULL, --��������
	expertEmail VARCHAR(50), --����
	phoneNum VARCHAR(14), --�绰����
	expertTypeID NUMERIC(19,0), --ר������ID
	flag INT, --��ȡ���� 1����ѡ 0����ѡ  -1������ѡ
	availability INT,--��Ч��
	noteStatus INT,
	createTime DATETIME, --����ʱ��
	createUserID NUMERIC(19,0), --������id
	creatorIp VARCHAR(100), --������ip
	lastUpdaterIp VARCHAR(100), --�޸���ip
	updateLastTime DATETIME, --����޸�ʱ��
	updateUserID NUMERIC(19, 0), --�޸��û�id
	primary key (id)  --����
)

--4 ��ȡ���α�
CREATE TABLE TExpertBatch(
	id NUMERIC(19,0) IDENTITY NOT NULL,
	batchName VARCHAR(100), --��������
	extractPepleNum INT, --��ȡ����
	remark VARCHAR(255), --��ע
	availability INT,--��Ч��
	batchStatus INT, --״̬  0������ȡ  1���ѳ�ȡ  2����ȷ��
	createTime DATETIME, --����ʱ��
	createUserID NUMERIC(19,0), --������id
	creatorIp VARCHAR(100), --������ip
	lastUpdaterIp VARCHAR(100), --�޸���ip
	updateLastTime DATETIME, --����޸�ʱ��
	updateUserID NUMERIC(19, 0), --�޸��û�id
    primary key (id)  --����
)

--5 ���κ�ר�Ҷ��ձ�
CREATE TABLE Expert_Batch_dz(
	batchID NUMERIC(19,0) not null,
    expertID NUMERIC(19,0) not null,
    extractStatus INT DEFAULT 0,
	noteStatus INT DEFAULT 0
)

--6 ������־��
CREATE TABLE SystemLog(
  id NUMERIC(19,0) IDENTITY NOT NULL,
  IP VARCHAR(100),
  userID NUMERIC(19,0),  --�����û�ID
  userName VARCHAR(100), --�����û�����
  operTime DATETIME, --����ʱ��
  operTable VARCHAR(100), --�����ı�
  operTableDataID NUMERIC(19,0), --�����������ID
  operContent VARCHAR(255), --��������
  primary key (id) 
)

create table dbChange(majorVersion int,minorVersion int,fixVersion int,opDate datetime,fileName varchar(50));

go

ALTER TABLE TUserRole ADD CONSTRAINT FK_7ljjoo9teyx56hpa7hkfnms35 FOREIGN KEY (userID) REFERENCES TUser
ALTER TABLE Expert_Batch_dz ADD CONSTRAINT FK_qnr4gb0loftqnuvhdqwnooh84 FOREIGN KEY (expertID) REFERENCES TExpert
ALTER TABLE Expert_Batch_dz ADD CONSTRAINT FK_m1hdma7o7f21x1c8si54om0m4 FOREIGN KEY (batchID) REFERENCES TExpertBatch

go
--����û��ͽ�ɫ
INSERT INTO TUSER(loginName,name,password,phoneNum,createTime,createUserID,updateLastTime,availability) 
values('admin','��������Ա','$2a$10$12Z..XV9M2zxsAmqLV9YAOUsZsGwvWUVdwTfCA46B7sbO/vjAsRqS','',CONVERT(varchar(100), GETDATE(), 20),1,CONVERT(varchar(100), GETDATE(), 20),1);

insert into tuserRole(userId,role) values(1,'ROLE_ADMIN');
insert into tuserRole(userId,role) values(1,'ROLE_EXPERT');
insert into tuserRole(userId,role) values(1,'ROLE_SALES');


insert into dbChange(majorVersion,minorVersion,fixVersion,fileName,opdate) values(1,0,0,'1.0.sql',GETDATE());



