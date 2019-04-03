USE [master]
GO
/****** Object:  Database [zjk]    Script Date: 07/26/2018 15:52:02 ******/
CREATE DATABASE [zjk] ON  PRIMARY 
( NAME = N'zjk', FILENAME = N'D:\soft\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\zjk.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'zjk_log', FILENAME = N'D:\soft\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\zjk_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [zjk] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [zjk].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [zjk] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [zjk] SET ANSI_NULLS OFF
GO
ALTER DATABASE [zjk] SET ANSI_PADDING OFF
GO
ALTER DATABASE [zjk] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [zjk] SET ARITHABORT OFF
GO
ALTER DATABASE [zjk] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [zjk] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [zjk] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [zjk] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [zjk] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [zjk] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [zjk] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [zjk] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [zjk] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [zjk] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [zjk] SET  DISABLE_BROKER
GO
ALTER DATABASE [zjk] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [zjk] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [zjk] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [zjk] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [zjk] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [zjk] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [zjk] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [zjk] SET  READ_WRITE
GO
ALTER DATABASE [zjk] SET RECOVERY FULL
GO
ALTER DATABASE [zjk] SET  MULTI_USER
GO
ALTER DATABASE [zjk] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [zjk] SET DB_CHAINING OFF
GO
EXEC sys.sp_db_vardecimal_storage_format N'zjk', N'ON'
GO
USE [zjk]
GO
/****** Object:  Table [dbo].[TUser]    Script Date: 07/26/2018 15:52:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TUser](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[loginName] [varchar](32) NULL,
	[name] [varchar](100) NULL,
	[password] [varchar](100) NULL,
	[phoneNum] [varchar](14) NULL,
	[deptName] [varchar](100) NULL,
	[email] [varchar](100) NULL,
	[availability] [int] NULL,
	[createTime] [datetime] NULL,
	[createUserID] [numeric](19, 0) NULL,
	[creatorIp] [varchar](100) NULL,
	[lastUpdaterIp] [varchar](100) NULL,
	[updateLastTime] [datetime] NULL,
	[updateUserID] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[TUser] ON
INSERT [dbo].[TUser] ([id], [loginName], [name], [password], [phoneNum], [deptName], [email], [availability], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(1 AS Numeric(19, 0)), N'admin', N'超级管理员', N'$2a$10$HZbLuofmKsmN4e4FEIliCOQRDQrAGhAI5vsoUvlSgaxxpCICV9Jl2', N'', NULL, NULL, 1, CAST(0x0000A73E00994260 AS DateTime), CAST(1 AS Numeric(19, 0)), NULL, NULL, CAST(0x0000A73E00994260 AS DateTime), NULL)
INSERT [dbo].[TUser] ([id], [loginName], [name], [password], [phoneNum], [deptName], [email], [availability], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(2 AS Numeric(19, 0)), N'123', N'123', N'$2a$10$Uh4iNAlO3dTiJm7DfeJcjuzRk3dkPH1Lp6Bx2Bq0SJ.k2plfaaWeS', NULL, N'', NULL, 2, CAST(0x0000A73E0103C45C AS DateTime), CAST(1 AS Numeric(19, 0)), N'127.0.0.1', NULL, CAST(0x0000A9290103005E AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TUser] ([id], [loginName], [name], [password], [phoneNum], [deptName], [email], [availability], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(3 AS Numeric(19, 0)), N'wsy', N'王莎媛', N'$2a$10$RFhrSI1Y/ltAT85rURdW2.iWbJW6C3EsNB1Gx2NF4cECfMiMTMGLW', NULL, N'', NULL, 1, CAST(0x0000A73E0103DC9D AS DateTime), CAST(1 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', NULL, CAST(0x0000A73E0103DC9D AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TUser] ([id], [loginName], [name], [password], [phoneNum], [deptName], [email], [availability], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(4 AS Numeric(19, 0)), N'111', N'111', N'$2a$10$FTMSkMTEfZF9cZwMwQsEsO.WtDzefigc4Pq6VZfDhgl3ctI2/jeUq', NULL, N'', NULL, 0, CAST(0x0000A73E01042FA1 AS DateTime), CAST(1 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', NULL, CAST(0x0000A73E010434F4 AS DateTime), CAST(1 AS Numeric(19, 0)))
SET IDENTITY_INSERT [dbo].[TUser] OFF
/****** Object:  Table [dbo].[TExpertBatch]    Script Date: 07/26/2018 15:52:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TExpertBatch](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[batchName] [varchar](100) NULL,
	[extractPepleNum] [int] NULL,
	[remark] [varchar](255) NULL,
	[availability] [int] NULL,
	[batchStatus] [int] NULL,
	[createTime] [datetime] NULL,
	[createUserID] [numeric](19, 0) NULL,
	[creatorIp] [varchar](100) NULL,
	[lastUpdaterIp] [varchar](100) NULL,
	[updateLastTime] [datetime] NULL,
	[updateUserID] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[TExpertBatch] ON
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(13 AS Numeric(19, 0)), N'超级管理员 2017年03月31日 16时41分37秒', 4, N'', 0, 2, CAST(0x0000A74701132016 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', NULL, CAST(0x0000A75500B918E1 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(14 AS Numeric(19, 0)), N'超级管理员 2017年03月31日 17时12分01秒', 4, N'', 1, 2, CAST(0x0000A747011B79E3 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.2.202', NULL, CAST(0x0000A747011B79E3 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(15 AS Numeric(19, 0)), N'超级管理员 2017年04月10日 08时58分05秒', 3, N'', 0, 1, CAST(0x0000A7510093CC98 AS DateTime), CAST(1 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', NULL, CAST(0x0000A75500B467BB AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(16 AS Numeric(19, 0)), N'超级管理员 2017年04月14日 10时53分53秒', 5, N'5', 0, 2, CAST(0x0000A75500B3A21C AS DateTime), CAST(1 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', NULL, CAST(0x0000A75500B473B5 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(17 AS Numeric(19, 0)), N'超级管理员 2017年04月14日 11时13分27秒', 5, N'', 1, 2, CAST(0x0000A75500B8FC3B AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', NULL, CAST(0x0000A75500B8FC3B AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(18 AS Numeric(19, 0)), N'超级管理员 2017年04月14日 17时12分14秒', 4, N'', 1, 2, CAST(0x0000A755011B8BA6 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.2.227', NULL, CAST(0x0000A755011B8BA6 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(19 AS Numeric(19, 0)), N'超级管理员 2017年06月09日 17时09分47秒', 4, N'', 1, 1, CAST(0x0000A78D011AE638 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.2.147', NULL, CAST(0x0000A78D011AE638 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(20 AS Numeric(19, 0)), N'超级管理员 2017年09月08日 17时06分42秒', 4, N'', 1, 1, CAST(0x0000A7E8011A1A25 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.2.86', NULL, CAST(0x0000A7E8011A1A25 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(21 AS Numeric(19, 0)), N'超级管理员 2017年11月10日 17时08分46秒', 4, N'', 1, 1, CAST(0x0000A827011A9A6E AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.2.117', NULL, CAST(0x0000A827011A9A6E AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpertBatch] ([id], [batchName], [extractPepleNum], [remark], [availability], [batchStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(22 AS Numeric(19, 0)), N'超级管理员 2018年01月19日 17时05分23秒', 4, N'', 1, 1, CAST(0x0000A86D0119ACB4 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.2.117', NULL, CAST(0x0000A86D0119ACB4 AS DateTime), CAST(1 AS Numeric(19, 0)))
SET IDENTITY_INSERT [dbo].[TExpertBatch] OFF
/****** Object:  Table [dbo].[TExpert]    Script Date: 07/26/2018 15:52:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TExpert](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[expertName] [varchar](100) NOT NULL,
	[assessmentStructure] [varchar](200) NOT NULL,
	[expertEmail] [varchar](50) NULL,
	[phoneNum] [varchar](14) NULL,
	[expertTypeID] [numeric](19, 0) NULL,
	[flag] [int] NULL,
	[availability] [int] NULL,
	[noteStatus] [int] NULL,
	[createTime] [datetime] NULL,
	[createUserID] [numeric](19, 0) NULL,
	[creatorIp] [varchar](100) NULL,
	[lastUpdaterIp] [varchar](100) NULL,
	[updateLastTime] [datetime] NULL,
	[updateUserID] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[TExpert] ON
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(37 AS Numeric(19, 0)), N'陈梦华', N'新奇星科技有限公司', N'626506226@qq.com', N'13708411844', NULL, 0, 1, NULL, CAST(0x0000A74700C15892 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C15892 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(38 AS Numeric(19, 0)), N'陈艳', N'新奇星科技有限公司', N'1491797884@qq.com', N'13888186677', NULL, -1, 1, NULL, CAST(0x0000A74700C15893 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.2.147', CAST(0x0000A78D011ACEF3 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(39 AS Numeric(19, 0)), N'代绍丽', N'新奇星科技有限公司', N'1342735758@qq.com', N'13888224277', NULL, -1, 1, NULL, CAST(0x0000A74700C15893 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A7E80118C093 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(40 AS Numeric(19, 0)), N'何任清', N'新奇星科技有限公司', N'626506226@qq.com', N'13108714164', NULL, -1, 1, NULL, CAST(0x0000A74700C15893 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A7E80118DB6E AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(41 AS Numeric(19, 0)), N'黄邦荣', N'新奇星科技有限公司', N'752974082@qq.com', N'13708858181', NULL, -1, 1, NULL, CAST(0x0000A74700C15893 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A7E80118A84D AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(42 AS Numeric(19, 0)), N'郎恒', N'新奇星科技有限公司', N'547250107@qq.com', N'13888188068', NULL, -1, 1, NULL, CAST(0x0000A74700C15894 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A82701162CAF AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(43 AS Numeric(19, 0)), N'李朝胜', N'新奇星科技有限公司', N'1289529565@qq.com', N'13608854967', NULL, -1, 1, NULL, CAST(0x0000A74700C15894 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.2.227', CAST(0x0000A755011B68ED AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(44 AS Numeric(19, 0)), N'李留平', N'新奇星科技有限公司', N'1258357208@qq.com', N'13888708895', NULL, -1, 1, NULL, CAST(0x0000A74700C15894 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C310B1 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(45 AS Numeric(19, 0)), N'李正伟', N'新奇星科技有限公司', N'', N'13987659058', NULL, -1, 1, NULL, CAST(0x0000A74700C15894 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.2.147', CAST(0x0000A78D011AA89D AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(46 AS Numeric(19, 0)), N'龙云舟', N'新奇星科技有限公司', N'191003881@qq.com', N'13888877276', NULL, -1, 1, NULL, CAST(0x0000A74700C15895 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A86D01173AD8 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(47 AS Numeric(19, 0)), N'路伟琼', N'新奇星科技有限公司', N'18150540@qq.com', N'15368069117', NULL, 0, 1, NULL, CAST(0x0000A74700C15895 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C15895 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(48 AS Numeric(19, 0)), N'吕维华', N'新奇星科技有限公司', N'361477273@qq.com', N'15911603152', NULL, 0, 1, NULL, CAST(0x0000A74700C15895 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C15895 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(49 AS Numeric(19, 0)), N'孟祥燕', N'新奇星科技有限公司', N'350039675@qq.com', N'13808730256', NULL, -1, 1, NULL, CAST(0x0000A74700C15895 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C2F596 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(50 AS Numeric(19, 0)), N'彭玫君', N'新奇星科技有限公司', N'yndlqengcheng@163.com', N'13187688692', NULL, -1, 1, NULL, CAST(0x0000A74700C15895 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A7E8011876ED AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(51 AS Numeric(19, 0)), N'宋江灿', N'新奇星科技有限公司', N'yndlqengcheng@163.com', N'13887291186', NULL, -1, 1, NULL, CAST(0x0000A74700C15896 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A8270116384F AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(52 AS Numeric(19, 0)), N'孙冲伟', N'新奇星科技有限公司', N'1464892473@qq.com', N'13308784090', NULL, -1, 1, NULL, CAST(0x0000A74700C15896 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A8270115E324 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(53 AS Numeric(19, 0)), N'唐荣伟', N'新奇星科技有限公司', N'kmjcpg@126.com', N'15808841814', NULL, -1, 1, NULL, CAST(0x0000A74700C15896 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A86D01176952 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(54 AS Numeric(19, 0)), N'唐玥', N'新奇星科技有限公司', N'kmjcpg@126.com', N'13708429620', NULL, -1, 1, NULL, CAST(0x0000A74700C15896 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.2.147', CAST(0x0000A78D011A924F AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(55 AS Numeric(19, 0)), N'王莎媛', N'新奇星科技有限公司', N'747116371@qq.com', N'13629646680', NULL, 0, 1, NULL, CAST(0x0000A74700C15896 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C15896 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(56 AS Numeric(19, 0)), N'王涛', N'新奇星科技有限公司', N'750225567@qq.com', N'13888165025', NULL, -1, 1, NULL, CAST(0x0000A74700C15897 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A86D0117544D AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(57 AS Numeric(19, 0)), N'王晓丽', N'新奇星科技有限公司', N'285838508@qq.com', N'15198801010', NULL, -1, 1, NULL, CAST(0x0000A74700C15897 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.2.227', CAST(0x0000A755011B7633 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(58 AS Numeric(19, 0)), N'王艳', N'新奇星科技有限公司', N'1067668271@qq.com', N'13529464071', NULL, -1, 1, NULL, CAST(0x0000A74700C15897 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C2DB32 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(59 AS Numeric(19, 0)), N'魏学荣', N'新奇星科技有限公司', N'ynzhyu2008@163.com', N'13808706976', NULL, -1, 1, NULL, CAST(0x0000A74700C15897 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.2.147', CAST(0x0000A78D011ABFF0 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(60 AS Numeric(19, 0)), N'肖慈越', N'新奇星科技有限公司', N'798157209@qq.com', N'18669053367', NULL, -1, 1, NULL, CAST(0x0000A74700C15897 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A86D0117CE62 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(61 AS Numeric(19, 0)), N'严敏', N'新奇星科技有限公司', N'923932954@qq.com', N'13887411658', NULL, -1, 1, NULL, CAST(0x0000A74700C15898 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C2D145 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(62 AS Numeric(19, 0)), N'杨超', N'新奇星科技有限公司', N'', N'13888898053', NULL, -1, 1, NULL, CAST(0x0000A74700C15898 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A755011AC453 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(63 AS Numeric(19, 0)), N'杨金菊', N'新奇星科技有限公司', N'279345125@qq.com', N'13518708831', NULL, -1, 1, NULL, CAST(0x0000A74700C15898 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.2.227', CAST(0x0000A755011B5864 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(64 AS Numeric(19, 0)), N'张凌云', N'新奇星科技有限公司', N'', N'13887965586', NULL, -1, 1, NULL, CAST(0x0000A74700C15898 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C2E332 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(65 AS Numeric(19, 0)), N'张文佳', N'新奇星科技有限公司', N'kmdlqg@163.com', N'13888161706', NULL, -1, 1, NULL, CAST(0x0000A74700C15898 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A827011614FA AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(66 AS Numeric(19, 0)), N'必选test', N'新奇星科技有限公司', N'', N'13222222222', NULL, 1, 0, NULL, CAST(0x0000A74700C192B6 AS DateTime), CAST(1 AS Numeric(19, 0)), N'192.168.1.252', N'192.168.1.252', CAST(0x0000A74700C47DD2 AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TExpert] ([id], [expertName], [assessmentStructure], [expertEmail], [phoneNum], [expertTypeID], [flag], [availability], [noteStatus], [createTime], [createUserID], [creatorIp], [lastUpdaterIp], [updateLastTime], [updateUserID]) VALUES (CAST(67 AS Numeric(19, 0)), N'刘德华', N'234534', N'342234@qq.com', N'345r32534', NULL, 0, 0, NULL, CAST(0x0000A80801120C1C AS DateTime), CAST(1 AS Numeric(19, 0)), N'127.0.0.1', N'127.0.0.1', CAST(0x0000A80801121C13 AS DateTime), CAST(1 AS Numeric(19, 0)))
SET IDENTITY_INSERT [dbo].[TExpert] OFF
/****** Object:  Table [dbo].[SystemLog]    Script Date: 07/26/2018 15:52:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SystemLog](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[IP] [varchar](100) NULL,
	[userID] [numeric](19, 0) NULL,
	[userName] [varchar](100) NULL,
	[operTime] [datetime] NULL,
	[operTable] [varchar](100) NULL,
	[operTableDataID] [numeric](19, 0) NULL,
	[operContent] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[SystemLog] ON
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(1 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A15 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：马啸南，专家所属机构：昆明名杰信房地产土地评估咨询有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(2 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A1A AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：刘红芸，专家所属机构：云南瑞尔房地产土地资产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(3 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A1A AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：蒋丽，专家所属机构：昆明诚跃房地产评估有限责任公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(4 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A1E AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：杜克武，专家所属机构：云南颢杨房地产估价有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(5 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A1E AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：唐雅丽，专家所属机构：云南瑞尔房地产土地资产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(6 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A23 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：陈斌，专家所属机构：云南瑞尔房地产土地资产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(7 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A23 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：李洪涛，专家所属机构：云南瑞尔房地产土地资产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(8 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A28 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：邓贵乾，专家所属机构：云南瑞尔房地产土地资产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(9 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A28 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：马旻，专家所属机构：云南帮克房地产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(10 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A2C AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：应俊，专家所属机构：云南帮克房地产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(11 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A2C AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：刘威，专家所属机构：云南帮克房地产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(12 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A2C AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：黄高，专家所属机构：昆明诚跃房地产评估有限责任公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(13 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A31 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：金次非，专家所属机构：昆明名杰信房地产土地评估咨询有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(14 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A31 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：杨晓宇，专家所属机构：大理鹏程房地产土地评估咨询有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(15 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A31 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：杨枝鹏，专家所属机构：大理鹏程房地产土地评估咨询有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(16 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A36 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：施敬，专家所属机构：云南银河房地产评估有限责任公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(17 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A36 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：李宇红，专家所属机构：昆明决策房地产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(18 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A3A AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：陈云川，专家所属机构：昆明决策房地产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(19 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A3A AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：管莉，专家所属机构：昆明滇信房地产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(20 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A3F AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：黄骏，专家所属机构：昆明滇信房地产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(21 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A3F AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：张莹姝，专家所属机构：云南银信房地产价格评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(22 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A3F AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：郭文兵，专家所属机构：红河恒信房地产土地评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(23 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A44 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：郑宇，专家所属机构：西双版纳中兴房地产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(24 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A44 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：段傈津，专家所属机构：昆明三阳开泰房地产土地资产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(25 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A44 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：苏红琳，专家所属机构：曲靖恒信房地产估价经纪有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(26 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A48 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：缪祥波，专家所属机构：云南精正房地产土地评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(27 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A48 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：蒙树琼，专家所属机构：云南精正房地产土地评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(28 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A48 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：杨忠文，专家所属机构：普洱诚宇房地产价值评估有限责任公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(29 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A4D AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：宗云，专家所属机构：云南鼎立房地产土地评估有限责任公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(30 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A4D AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：杨义彪，专家所属机构：云南鼎立房地产土地评估有限责任公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(31 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A52 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：黄景艳，专家所属机构：文山同心联宜房地产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(32 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A52 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：林伟，专家所属机构：云南洪业房地产评估有限责任公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(33 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A57 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：刘娟，专家所属机构：云南耀迪房地产土地资产评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(34 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A57 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：李德虎，专家所属机构：云南广地房地产土地评估勘测有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(35 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73E00DB1A57 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：郭一江，专家所属机构：云南弘力房地产土地评估有限公司;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(36 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(3 AS Numeric(19, 0)), N'王莎媛', CAST(0x0000A73E010A4860 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：李德虎，专家所属机构：云南广地房地产土地评估勘测有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(37 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(3 AS Numeric(19, 0)), N'王莎媛', CAST(0x0000A73E010A4DC2 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：郭一江，专家所属机构：云南弘力房地产土地评估有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(38 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(3 AS Numeric(19, 0)), N'王莎媛', CAST(0x0000A73E010A51A5 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：管莉，专家所属机构：昆明滇信房地产评估有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(39 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73F00A9D4D0 AS DateTime), NULL, NULL, N'新增专家信息，专家姓名：123，专家所属机构：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(40 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73F00A9E193 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：123，专家所属机构：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(41 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A73F00A9E633 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：123，专家所属机构：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(42 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A743009888AA AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(43 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300B109DA AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(44 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300B43416 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(45 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300B45C39 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(46 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300B7223A AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(47 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300B749C0 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(48 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300B88CC0 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(49 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300B8A2DF AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(50 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300B8C71E AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(51 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300E57285 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(52 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300F2229E AS DateTime), NULL, NULL, N'修改用户信息，用户姓名：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(53 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300FAC3C9 AS DateTime), NULL, NULL, N'修改用户信息，用户姓名：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(54 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74300FAECBB AS DateTime), NULL, NULL, N'修改用户信息，用户姓名：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(55 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7430101F6F2 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(56 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74301020044 AS DateTime), NULL, NULL, N'修改用户信息，用户姓名：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(57 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7430103063B AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(58 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74301035F51 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(59 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A743011FD554 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(60 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C07E2A AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(61 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C0F4E2 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：管莉，专家所属机构：昆明滇信房地产评估有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(62 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C0F9A4 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：苏红琳，专家所属机构：曲靖恒信房地产估价经纪有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(63 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C0FECF AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：郭一江，专家所属机构：云南弘力房地产土地评估有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(64 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1032B AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：李德虎，专家所属机构：云南广地房地产土地评估勘测有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(65 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C10526 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：李宇红，专家所属机构：昆明决策房地产评估有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(66 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1079F AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：陈云川，专家所属机构：昆明决策房地产评估有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(67 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C109E4 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：黄骏，专家所属机构：昆明滇信房地产评估有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(68 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C10BA8 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：缪祥波，专家所属机构：云南精正房地产土地评估有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(69 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C10D42 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：蒙树琼，专家所属机构：云南精正房地产土地评估有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(70 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C10EF3 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：杨忠文，专家所属机构：普洱诚宇房地产价值评估有限责任公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(71 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C111CE AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：宗云，专家所属机构：云南鼎立房地产土地评估有限责任公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(72 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15865 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：陈梦华，专家所属机构：1;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(73 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15866 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：陈艳，专家所属机构：2;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(74 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15868 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：代绍丽，专家所属机构：3;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(75 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15869 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：何任清，专家所属机构：4;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(76 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1586B AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：黄邦荣，专家所属机构：5;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(77 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1586C AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：郎恒，专家所属机构：6;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(78 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1586E AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：李朝胜，专家所属机构：7;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(79 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1586F AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：李留平，专家所属机构：8;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(80 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15871 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：李正伟，专家所属机构：9;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(81 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15872 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：龙云舟，专家所属机构：0;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(82 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15877 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：路伟琼，专家所属机构：1;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(83 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15878 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：吕维华，专家所属机构：2;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(84 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1587A AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：孟祥燕，专家所属机构：3;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(85 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1587C AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：彭玫君，专家所属机构：4;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(86 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1587D AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：宋江灿，专家所属机构：5;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(87 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1587E AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：孙冲伟，专家所属机构：6;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(88 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15880 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：唐荣伟，专家所属机构：7;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(89 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15881 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：唐玥，专家所属机构：8;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(90 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15883 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：王莎媛，专家所属机构：9;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(91 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15884 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：王涛，专家所属机构：0;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(92 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15885 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：王晓丽，专家所属机构：1;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(93 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15887 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：王艳，专家所属机构：2;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(94 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15888 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：魏学荣，专家所属机构：3;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(95 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15889 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：肖慈越，专家所属机构：4;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(96 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1588A AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：严敏，专家所属机构：5;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(97 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1588C AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：杨超，专家所属机构：6;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(98 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1588D AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：杨金菊，专家所属机构：7;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(99 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1588E AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：张凌云，专家所属机构：8;')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(100 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C15890 AS DateTime), NULL, NULL, N'导入专家信息操作，专家姓名：张文佳，专家所属机构：9;')
GO
print 'Processed 100 total records'
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(101 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C192A9 AS DateTime), NULL, NULL, N'新增专家信息，专家姓名：必选test，专家所属机构：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(102 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C199C9 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：必选test，专家所属机构：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(103 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1A2CD AS DateTime), NULL, NULL, N'抽取了批次号为：7 的专家，专家姓名列表：必选test  黄邦荣  魏学荣  杨金菊  王莎媛  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(104 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1AC8D AS DateTime), NULL, NULL, N'重抽了批次号为：7 的专家，新抽专家名：唐玥')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(105 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C1D65A AS DateTime), NULL, NULL, N'抽取了批次号为：8 的专家，专家姓名列表：必选test  张文佳  陈梦华  严敏  王艳  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(106 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C2D140 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：严敏，专家所属机构：5；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(107 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C2DB2C AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：王艳，专家所属机构：2；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(108 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C2E32E AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：张凌云，专家所属机构：8；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(109 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C2F592 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：孟祥燕，专家所属机构：3；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(110 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C310AE AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：李留平，专家所属机构：8；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(111 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C32DE6 AS DateTime), NULL, NULL, N'抽取了批次号为：9 的专家，专家姓名列表：必选test  杨金菊  唐玥  代绍丽  陈梦华  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(112 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C47DC9 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：必选test，专家所属机构：123；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(113 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C486AD AS DateTime), NULL, NULL, N'抽取了批次号为：10 的专家，专家姓名列表：王莎媛  陈梦华  孙冲伟  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(114 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700C49270 AS DateTime), NULL, NULL, N'抽取了批次号为：11 的专家，专家姓名列表：代绍丽  黄邦荣  杨超  郎恒  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(115 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D0E70E AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(116 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D12946 AS DateTime), NULL, NULL, N'抽取了批次号为：12 的专家，专家姓名列表：何任清  代绍丽  王晓丽  龙云舟  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(117 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D131DD AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：唐玥')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(118 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D1343E AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：郎恒')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(119 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D135C8 AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：陈梦华')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(120 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D1374C AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：陈艳')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(121 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D13904 AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：吕维华')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(122 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D13A8B AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：路伟琼')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(123 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D13C0E AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：李朝胜')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(124 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D13DAF AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：黄邦荣')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(125 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D13F40 AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：代绍丽')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(126 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D140C3 AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：李朝胜')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(127 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D1431F AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：杨超')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(128 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D144D8 AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：王涛')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(129 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D1465D AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：杨金菊')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(130 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D147BD AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：王莎媛')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(131 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D1493F AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：唐荣伟')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(132 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700D14AD1 AS DateTime), NULL, NULL, N'重抽了批次号为：12 的专家，新抽专家名：杨金菊')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(133 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A74700DF22EC AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(134 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A747010E8F05 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(135 AS Numeric(19, 0)), N'192.168.1.123', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7470111B8DC AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(136 AS Numeric(19, 0)), N'192.168.2.202', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A747011B510A AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(137 AS Numeric(19, 0)), N'192.168.2.202', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A747011B8817 AS DateTime), NULL, NULL, N'抽取了批次号为：14 的专家，专家姓名列表：李朝胜  杨超  杨金菊  王晓丽  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(138 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7510093C3E5 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(139 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7510093CF10 AS DateTime), NULL, NULL, N'抽取了批次号为：15 的专家，专家姓名列表：王莎媛  吕维华  杨超  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(140 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7510093F4A9 AS DateTime), NULL, NULL, N'重抽了批次号为：15 的专家，新抽专家名：宋江灿')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(141 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A75100947291 AS DateTime), NULL, NULL, N'重抽了批次号为：15 的专家，新抽专家名：张文佳')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(142 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A75500AC4E2C AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(143 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A75500B36782 AS DateTime), NULL, NULL, N'抽取了批次号为：13 的专家，专家姓名列表：杨金菊  吕维华  代绍丽  孙冲伟  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(144 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A75500B3A3AC AS DateTime), NULL, NULL, N'抽取了批次号为：16 的专家，专家姓名列表：宋江灿  唐荣伟  杨金菊  吕维华  王涛  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(145 AS Numeric(19, 0)), N'0:0:0:0:0:0:0:1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A75500B4C817 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(146 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A75500B7CFCE AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(147 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A75500B8FE22 AS DateTime), NULL, NULL, N'抽取了批次号为：17 的专家，专家姓名列表：郎恒  吕维华  肖慈越  唐玥  杨金菊  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(148 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A755011AB79B AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(149 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A755011AC449 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：杨超，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(150 AS Numeric(19, 0)), N'192.168.2.227', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A755011B33B7 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(151 AS Numeric(19, 0)), N'192.168.2.227', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A755011B585A AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：杨金菊，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(152 AS Numeric(19, 0)), N'192.168.2.227', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A755011B68E3 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：李朝胜，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(153 AS Numeric(19, 0)), N'192.168.2.227', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A755011B762A AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：王晓丽，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(154 AS Numeric(19, 0)), N'192.168.2.227', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A755011B9A12 AS DateTime), NULL, NULL, N'抽取了批次号为：18 的专家，专家姓名列表：陈梦华  李正伟  陈艳  魏学荣  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(155 AS Numeric(19, 0)), N'192.168.2.227', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A755011BB67B AS DateTime), NULL, NULL, N'重抽了批次号为：18 的专家，新抽专家名：唐玥')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(156 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A78D0115EDE6 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(157 AS Numeric(19, 0)), N'192.168.2.147', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A78D011A6C52 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(158 AS Numeric(19, 0)), N'192.168.2.147', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A78D011A9238 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：唐玥，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(159 AS Numeric(19, 0)), N'192.168.2.147', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A78D011AA885 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：李正伟，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(160 AS Numeric(19, 0)), N'192.168.2.147', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A78D011ABFD9 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：魏学荣，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(161 AS Numeric(19, 0)), N'192.168.2.147', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A78D011ACEDB AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：陈艳，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(162 AS Numeric(19, 0)), N'192.168.2.147', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A78D011AEC09 AS DateTime), NULL, NULL, N'抽取了批次号为：19 的专家，专家姓名列表：陈梦华  代绍丽  何任清  彭玫君  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(163 AS Numeric(19, 0)), N'192.168.2.147', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A78D011AFCAB AS DateTime), NULL, NULL, N'重抽了批次号为：19 的专家，新抽专家名：黄邦荣')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(164 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7E8010C1B32 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(165 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7E8010D6013 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(166 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7E8011866E2 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(167 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7E8011876D7 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：彭玫君，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(168 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7E80118A83F AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：黄邦荣，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(169 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7E80118C085 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：代绍丽，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(170 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7E80118DB61 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：何任清，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(171 AS Numeric(19, 0)), N'192.168.2.86', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7E80119F634 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(172 AS Numeric(19, 0)), N'192.168.2.86', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A7E8011A2054 AS DateTime), NULL, NULL, N'抽取了批次号为：20 的专家，专家姓名列表：孙冲伟  宋江灿  郎恒  张文佳  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(173 AS Numeric(19, 0)), N'127.0.0.1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A80801120C05 AS DateTime), NULL, NULL, N'新增专家信息，专家姓名：刘德华，专家所属机构：234534；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(174 AS Numeric(19, 0)), N'127.0.0.1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A808011217A7 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：刘德华，专家所属机构：234534；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(175 AS Numeric(19, 0)), N'127.0.0.1', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A80801121C02 AS DateTime), NULL, NULL, N'删除专家信息，专家姓名：刘德华，专家所属机构：234534；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(176 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A8270115BA12 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(177 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A8270115E2F1 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：孙冲伟，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(178 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A827011614EA AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：张文佳，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(179 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A82701162CA0 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：郎恒，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(180 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A82701163840 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：宋江灿，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(181 AS Numeric(19, 0)), N'192.168.2.117', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A827011A8BB6 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(182 AS Numeric(19, 0)), N'192.168.2.117', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A827011A9F90 AS DateTime), NULL, NULL, N'抽取了批次号为：21 的专家，专家姓名列表：唐荣伟  龙云舟  王涛  肖慈越  ')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(183 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A827011AF9F0 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(184 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A827011DC537 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(185 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A86D0116EF7C AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(186 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A86D01173AC7 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：龙云舟，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(187 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A86D0117543E AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：王涛，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(188 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A86D01176945 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：唐荣伟，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(189 AS Numeric(19, 0)), N'192.168.1.252', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A86D0117CE54 AS DateTime), NULL, NULL, N'修改专家信息，专家姓名：肖慈越，专家所属机构：新奇星科技有限公司；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(190 AS Numeric(19, 0)), N'192.168.2.117', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A86D01199F05 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(191 AS Numeric(19, 0)), N'192.168.2.117', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A86D01199F06 AS DateTime), NULL, NULL, N'登录系统；')
INSERT [dbo].[SystemLog] ([id], [IP], [userID], [userName], [operTime], [operTable], [operTableDataID], [operContent]) VALUES (CAST(192 AS Numeric(19, 0)), N'192.168.2.117', CAST(1 AS Numeric(19, 0)), N'超级管理员', CAST(0x0000A86D0119D8FD AS DateTime), NULL, NULL, N'抽取了批次号为：22 的专家，专家姓名列表：王莎媛  陈梦华  吕维华  路伟琼  ')
SET IDENTITY_INSERT [dbo].[SystemLog] OFF
/****** Object:  Table [dbo].[dbChange]    Script Date: 07/26/2018 15:52:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[dbChange](
	[majorVersion] [int] NULL,
	[minorVersion] [int] NULL,
	[fixVersion] [int] NULL,
	[opDate] [datetime] NULL,
	[fileName] [varchar](50) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[dbChange] ([majorVersion], [minorVersion], [fixVersion], [opDate], [fileName]) VALUES (1, 0, 0, CAST(0x0000A73E00994310 AS DateTime), N'1.0.sql')
/****** Object:  Table [dbo].[TUserRole]    Script Date: 07/26/2018 15:52:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TUserRole](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[role] [varchar](100) NULL,
	[roleName] [varchar](100) NULL,
	[userId] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[TUserRole] ON
INSERT [dbo].[TUserRole] ([id], [role], [roleName], [userId]) VALUES (CAST(1 AS Numeric(19, 0)), N'ROLE_ADMIN', NULL, CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TUserRole] ([id], [role], [roleName], [userId]) VALUES (CAST(2 AS Numeric(19, 0)), N'ROLE_EXPERT', NULL, CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TUserRole] ([id], [role], [roleName], [userId]) VALUES (CAST(3 AS Numeric(19, 0)), N'ROLE_SALES', NULL, CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[TUserRole] ([id], [role], [roleName], [userId]) VALUES (CAST(5 AS Numeric(19, 0)), N'ROLE_EXPERT', N'专家管理员', CAST(3 AS Numeric(19, 0)))
INSERT [dbo].[TUserRole] ([id], [role], [roleName], [userId]) VALUES (CAST(6 AS Numeric(19, 0)), N'ROLE_SALES', N'业务员', CAST(3 AS Numeric(19, 0)))
INSERT [dbo].[TUserRole] ([id], [role], [roleName], [userId]) VALUES (CAST(7 AS Numeric(19, 0)), N'ROLE_SALES', N'业务员', CAST(4 AS Numeric(19, 0)))
INSERT [dbo].[TUserRole] ([id], [role], [roleName], [userId]) VALUES (CAST(8 AS Numeric(19, 0)), N'ROLE_ADMIN', N'管理及分配所有用户及权限', CAST(3 AS Numeric(19, 0)))
SET IDENTITY_INSERT [dbo].[TUserRole] OFF
/****** Object:  Table [dbo].[Expert_Batch_dz]    Script Date: 07/26/2018 15:52:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Expert_Batch_dz](
	[batchID] [numeric](19, 0) NOT NULL,
	[expertID] [numeric](19, 0) NOT NULL,
	[extractStatus] [int] NULL,
	[noteStatus] [int] NULL
) ON [PRIMARY]
GO
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(15 AS Numeric(19, 0)), CAST(55 AS Numeric(19, 0)), 0, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(15 AS Numeric(19, 0)), CAST(48 AS Numeric(19, 0)), 0, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(15 AS Numeric(19, 0)), CAST(62 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(15 AS Numeric(19, 0)), CAST(51 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(15 AS Numeric(19, 0)), CAST(65 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(13 AS Numeric(19, 0)), CAST(63 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(13 AS Numeric(19, 0)), CAST(48 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(13 AS Numeric(19, 0)), CAST(39 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(13 AS Numeric(19, 0)), CAST(52 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(16 AS Numeric(19, 0)), CAST(51 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(16 AS Numeric(19, 0)), CAST(53 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(17 AS Numeric(19, 0)), CAST(42 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(17 AS Numeric(19, 0)), CAST(48 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(17 AS Numeric(19, 0)), CAST(60 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(17 AS Numeric(19, 0)), CAST(54 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(17 AS Numeric(19, 0)), CAST(63 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(18 AS Numeric(19, 0)), CAST(54 AS Numeric(19, 0)), 1, 1)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(19 AS Numeric(19, 0)), CAST(37 AS Numeric(19, 0)), 0, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(19 AS Numeric(19, 0)), CAST(39 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(19 AS Numeric(19, 0)), CAST(40 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(19 AS Numeric(19, 0)), CAST(50 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(20 AS Numeric(19, 0)), CAST(52 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(20 AS Numeric(19, 0)), CAST(51 AS Numeric(19, 0)), 0, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(20 AS Numeric(19, 0)), CAST(42 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(20 AS Numeric(19, 0)), CAST(65 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(20 AS Numeric(19, 0)), CAST(37 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(21 AS Numeric(19, 0)), CAST(53 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(21 AS Numeric(19, 0)), CAST(46 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(21 AS Numeric(19, 0)), CAST(56 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(21 AS Numeric(19, 0)), CAST(60 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(22 AS Numeric(19, 0)), CAST(55 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(22 AS Numeric(19, 0)), CAST(37 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(22 AS Numeric(19, 0)), CAST(48 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(22 AS Numeric(19, 0)), CAST(47 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(16 AS Numeric(19, 0)), CAST(63 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(19 AS Numeric(19, 0)), CAST(41 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(14 AS Numeric(19, 0)), CAST(43 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(14 AS Numeric(19, 0)), CAST(62 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(14 AS Numeric(19, 0)), CAST(63 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(14 AS Numeric(19, 0)), CAST(57 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(16 AS Numeric(19, 0)), CAST(48 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(16 AS Numeric(19, 0)), CAST(56 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(18 AS Numeric(19, 0)), CAST(37 AS Numeric(19, 0)), 0, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(18 AS Numeric(19, 0)), CAST(45 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(18 AS Numeric(19, 0)), CAST(38 AS Numeric(19, 0)), 1, 0)
INSERT [dbo].[Expert_Batch_dz] ([batchID], [expertID], [extractStatus], [noteStatus]) VALUES (CAST(18 AS Numeric(19, 0)), CAST(59 AS Numeric(19, 0)), 1, 0)
/****** Object:  Default [DF__Expert_Ba__extra__29572725]    Script Date: 07/26/2018 15:52:03 ******/
ALTER TABLE [dbo].[Expert_Batch_dz] ADD  DEFAULT ((0)) FOR [extractStatus]
GO
/****** Object:  Default [DF__Expert_Ba__noteS__2A4B4B5E]    Script Date: 07/26/2018 15:52:03 ******/
ALTER TABLE [dbo].[Expert_Batch_dz] ADD  DEFAULT ((0)) FOR [noteStatus]
GO
/****** Object:  ForeignKey [FK_7ljjoo9teyx56hpa7hkfnms35]    Script Date: 07/26/2018 15:52:03 ******/
ALTER TABLE [dbo].[TUserRole]  WITH CHECK ADD  CONSTRAINT [FK_7ljjoo9teyx56hpa7hkfnms35] FOREIGN KEY([userId])
REFERENCES [dbo].[TUser] ([id])
GO
ALTER TABLE [dbo].[TUserRole] CHECK CONSTRAINT [FK_7ljjoo9teyx56hpa7hkfnms35]
GO
/****** Object:  ForeignKey [FK_m1hdma7o7f21x1c8si54om0m4]    Script Date: 07/26/2018 15:52:03 ******/
ALTER TABLE [dbo].[Expert_Batch_dz]  WITH CHECK ADD  CONSTRAINT [FK_m1hdma7o7f21x1c8si54om0m4] FOREIGN KEY([batchID])
REFERENCES [dbo].[TExpertBatch] ([id])
GO
ALTER TABLE [dbo].[Expert_Batch_dz] CHECK CONSTRAINT [FK_m1hdma7o7f21x1c8si54om0m4]
GO
/****** Object:  ForeignKey [FK_qnr4gb0loftqnuvhdqwnooh84]    Script Date: 07/26/2018 15:52:03 ******/
ALTER TABLE [dbo].[Expert_Batch_dz]  WITH CHECK ADD  CONSTRAINT [FK_qnr4gb0loftqnuvhdqwnooh84] FOREIGN KEY([expertID])
REFERENCES [dbo].[TExpert] ([id])
GO
ALTER TABLE [dbo].[Expert_Batch_dz] CHECK CONSTRAINT [FK_qnr4gb0loftqnuvhdqwnooh84]
GO
