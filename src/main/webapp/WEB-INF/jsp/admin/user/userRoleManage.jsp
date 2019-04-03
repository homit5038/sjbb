<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">
	//删除用户角色
	function delUserRole(userId,roleId) {
		if (confirm('确定要删除用户权限吗?')) {
			jQuery('#main').showLoading();
			$.post("${ctx}/admin/user/" + userId + "/delUserRole", {
				roleID : roleId
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location ="${ctx}/admin/user/" + userId + "/userRoleManage";
				} else {
					//
				}
			});
		}
	}
	//增加用户角色
	function addUserRole(userId, roleNum) {
		if (confirm('确定要赋予用户权限吗?')) {
			jQuery('#main').showLoading();
			$.post("${ctx}/admin/user/" + userId + "/addUserRole", {
				roleNum : roleNum
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("添加成功");
					window.location ="${ctx}/admin/user/" + userId + "/userRoleManage";
				} else {
					//
				}
			});
		}
	}
</script>
</head>
<body id="main">
	<pre>用户管理>用户角色分配</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			<b>登录名：</b><span>${user.loginName }</span>&nbsp;&nbsp;&nbsp;
			<b>姓名：</b><span>${user.name }</span>&nbsp;&nbsp;&nbsp;
			<c:if test="${!empty user.deptName }"><b>部门名称：</b><span>${user.deptName }</span></c:if>
		</div>
		<div class="panel-body">
			<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th width="60px">操&nbsp;&nbsp;&nbsp;作</th>
								<th>编码</th>
								<th>名称</th>
								<th>备注</th>
							</tr>
						</thead>
						<c:forEach items="${roleTypes }" var="role">
						     <c:set value="true" var="flag" />
							 <tr>
							    <td>
								   <c:if test="${fn:length(user.roles) > 0 }">
								    <c:forEach items="${user.roles }" var="userRole">
								      <c:choose>
								       <c:when test="${userRole.role eq role.name }">
								         <a class="btn btn-primary  btn-xs" style="background-color: gray;" href="javascript:void(0)" onclick="delUserRole('${user.id }','${userRole.id}')">删除 </a>
								         <c:set value="false" var="flag" />
								        </c:when>
								       </c:choose>
								    </c:forEach>
								   </c:if>
								   <c:if test="${flag }">
								      <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="addUserRole('${user.id }','${role.name}')">增加 </a>
								   </c:if>
								 </td>
								<td>${role.name }</td>
								<td>${role.displayName }</td>
								<td>${role.describe }</td>
							</tr>
						</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>