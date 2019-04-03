<%@page import="com.xqx.frame.config.Config,com.xqx.frame.security.SecurityUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<c:set var="loginName" value="<%=SecurityUtil.getCurrentUser().getLoginName()%>" />
<c:set var="id" value="<%=SecurityUtil.getCurrentUser().getId()%>" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	var url = "${ctx}/homeIndex";
	$("#menuFrame").attr("src",url);
});
//修改frame只适应高度
function changeFrameWidth(){
    //获取浏览器窗口高度
    var winHeight=0;
    if (window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;

    if (document.documentElement && document.documentElement.clientHeight)
        winHeight = document.documentElement.clientHeight;

    document.getElementById("menuFrame").style.height= (winHeight - 210) +"px";
}
window.onresize=changeFrameWidth; 

//修改信息
function editPwd() {
	var url = "${ctx}/editPwd";
	window.open(url, "menuFrame");
}
</script>
</head>
<body>
	<div class="header-and-operMenu">
		<!--顶部-->
		<div class="layout_top_header header-wrapper">
			<div class="top">
				<table border=0 width="100%">
					<tr>
						<td width="100" align="center" height="90"><img
							src="${ctx}/resources/img/logo.png"  height="40" /></td>
						<td  align="left"><h1>估价分会专家库系统</h1></td>
						<td align="right" class="useredit">
						<span class="glyphicon glyphicon-user"></span>${loginName }，欢迎你
						</td>
						<td align="right" class="useredit">
							<span class="glyphicon glyphicon-lock"></span><a href="javascript:editPwd();">修改密码</a>
						<span class="glyphicon glyphicon-edit"></span><a href="${ctx}/user/${id }/edit" target="menuFrame">信息修改</a>
<span class="glyphicon glyphicon-off">
</span><a href="<c:url value='/j_spring_security_logout'/>" target="_top">退出</a>	
					
					</td>
					</tr>
				</table>
			</div>
		</div>
		<!--顶部结束-->
		<nav class="navbar navbar-default" style="background-color:#ececec;border-top:1px gray solid;border-radius: 0" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<b class="navbar-brand"><a href="${ctx}/home">操作目录</a></b>
				</div>
				<div id="myexample">
					<ul class="nav navbar-nav">
					   <sec:authorize access="hasRole('ROLE_EXPERT')">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">专家库<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx }/expert/new" target="menuFrame">新增专家</a></li>
								<li class="divider"></li>
								<li><a href="${ctx }/expert/list" target="menuFrame">专家名单管理</a></li>
								
							</ul></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_SALES')"> 
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">专家抽取<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a id="" href="${ctx}/batch/new" target="menuFrame">新建抽取批次</a></li>
								<li class="divider"></li>
								<li><a href="${ctx}/batch/batchlist" target="menuFrame">抽取批次查询</a></li>
							</ul></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_SALES')"> 
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">统计信息<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx }/extract/jg/statistics" target="menuFrame">按机构统计</a></li>
								<li class="divider"></li>
								<li><a href="${ctx }/extract/zj/statistics" target="menuFrame">按专家统计</a></li>
							</ul></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')"> 
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">用户管理<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx}/admin/user/list" target="menuFrame">用户管理</a></li>
								<li class="divider"></li>
								<li><a href="${ctx}/admin/user/new" target="menuFrame">新增用户</a></li>
							</ul></li>
							<li class="nodown"><a href="${ctx}/log/findAllLog" target="menuFrame">系统日志</a></li>
						</sec:authorize>
						
	            
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">班级管理<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx }/classe/new" target="menuFrame">新增班级</a></li>
								<li class="divider"></li>
								<li><a href="${ctx }/classe/list" target="menuFrame">班级列表</a></li>
							</ul></li>
						
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">收费项管理<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx }/chargeitem/new" target="menuFrame">新增班级</a></li>
								<li class="divider"></li>
								<li><a href="${ctx }/chargeitem/list" target="menuFrame">收费项列表</a></li>
							</ul></li>
	       
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">年级管理<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx }/grade/new" target="menuFrame">新增年级</a></li>
								<li class="divider"></li>
								<li><a href="${ctx }/grade/list" target="menuFrame">年级列表</a></li>
								
							</ul></li>
			
	    
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">属性管理<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx }/property/new" target="menuFrame">新增属性</a></li>
								<li class="divider"></li>
								<li><a href="${ctx }/property/list" target="menuFrame">属性列表</a></li>	
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">职工管理<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx }/employe/new" target="menuFrame">新增职工</a></li>
								<li class="divider"></li>
								<li><a href="${ctx }/employe/list" target="menuFrame">职工列表</a></li>	
								<li><a href="${ctx }/children/list" target="menuFrame">学生列表</a></li>
							</ul>
							</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	
	
	
	<div id="layout_right_content" class="layout_right_content">
		<div class="mian_content">
			<div id="page_content">
				<iframe id="menuFrame" name="menuFrame" onload="changeFrameWidth()"
					style="overflow: visible;"  scrolling="yes" frameborder="no"
					width="100%"></iframe>
			</div>
		</div>
	</div>
	<div class="panel-footer">
	<jsp:include page="/WEB-INF/jsp/public/footer.jsp"></jsp:include>
	</div>
	</body>
</html>