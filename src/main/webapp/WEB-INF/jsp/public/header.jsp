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
<link rel="stylesheet" media="screen" href="${ctx}resources/css/zjk.css">
<link rel="stylesheet" media="screen"	href="${ctx}resources/js/showLoading/showLoading.css">
<link rel="stylesheet" href="${ctx}resources/zui/css/zui.css" />
<%-- <link rel="stylesheet" media="screen" href="${ctx}/resources/js/jquery-ui-1.12.1/jquery-ui.min.css"> --%>
<script type="text/javascript" src="${ctx}resources/js/jquery-1.12.1.min.js"></script>
<script type="text/javascript" src="/youer/resources/zui/js/zui.minn.js"></script>
<%--<script type="text/javascript" charset="utf-8" src="${ctx}resources/bootstrap-3.3.5/js/bootstrap.min.js"></script>--%>
<script type="text/javascript" charset="utf-8" src="${ctx}resources/Highcharts-4.2.3/js/highcharts.src.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}resources/js/showLoading/jquery.showLoading.js"></script>
<script type="text/javascript"  src="${ctx}resources/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>

