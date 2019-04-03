<%@ page import="com.xqx.frame.config.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<base target="${base_target==null?'_self':base_target }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=Config.getString("siteTitle")%></title>
<c:url var="ctx" value="/" scope="request" />
<meta charset="utf-8">
<!-- css -->
<link rel="stylesheet" media="screen" href="${ctx}resources/layuiadmin/layui/css/layui.css">