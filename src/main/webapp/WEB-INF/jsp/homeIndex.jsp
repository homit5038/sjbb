<%@page import="com.xqx.frame.config.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link href="${ctx}/resources/bootstrap-3.3.5/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" media="screen" href="${ctx}/resources/css/homeIndex.css">
<script type="text/javascript"
	src="${ctx}/resources/js/jquery-1.12.1.min.js"></script>
<script type="text/javascript">
   function locationMenuFrame(url){
	  window.open(url,"menuFrame");
   }
   $(function(){

		$('.home-index-oper ul li').hover(function () {
			$(this).addClass('on');
		},
		function () {
			$(this).removeClass('on');
		});
		
	});
   
   
</script>
</head>
<body style="text-align: center;">
<div class="home-index-oper">
	<ul>
		<sec:authorize access="hasRole('ROLE_SALES')">
			<li>
			   <a href="javascript:void(0)" onclick="locationMenuFrame('${ctx}/batch/new')"><span class="glyphicon glyphicon-folder-open" ></span>新建抽取批次</a>
			   </li>
			<li>
			    <a href="javascript:void(0)" onclick="locationMenuFrame('${ctx }/extract/zj/statistics')">
			  <span class="glyphicon glyphicon-th" ></span>  
			    按专家统计</a></li>
				<li>
			    <a href="javascript:void(0)" onclick="locationMenuFrame('${ctx }/extract/jg/statistics')">
			  <span class="glyphicon glyphicon-th-list" ></span>  
			    按机构统计</a></li>
				<li>
			    <a href="javascript:void(0)" onclick="locationMenuFrame('${ctx }/batch/batchlist?batchStatus=-1')">
			  <span class="glyphicon glyphicon-zoom-in" ></span>  
			    未完项目查询</a></li>
				<li>
			    <a href="javascript:void(0)" onclick="locationMenuFrame('${ctx }/batch/batchlist?batchStatus=2')">
			  <span class="glyphicon glyphicon-hdd" ></span>  
			    历史项目查询</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_EXPERT')">
		<li>
			    <a href="javascript:void(0)" onclick="locationMenuFrame('${ctx }/expert/list')">
			  <span class="glyphicon glyphicon-inbox" ></span>  
			    专家库查询</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li>
			  <a href="javascript:void(0)" onclick="locationMenuFrame('${ctx}/admin/user/list')">
			 <span class="glyphicon glyphicon-user" ></span> 
			  用户管理</a></li>
		</sec:authorize>
	</ul>
</div>
</body>
