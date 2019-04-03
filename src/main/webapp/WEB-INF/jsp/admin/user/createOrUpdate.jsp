<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">
	var validateLoginName = false;
	var id = "${user.id}";
	$(function() {
		if("${result}" == "true"){
			parent.refresh();
		}
		$('#loginName').blur(function() {
			if ($('#loginName').val() != null) {
				$.ajax({
					type : "post",
					async : false,
					url : "${ctx}/user/validateLoginName",
					data : {
						loginName : $('#loginName').val()
					},
					success : function(msg) {
						if (msg == "ok") {
							validateLoginName = false;
							$("#loginNameErr").html("");
						} else {
							validateLoginName = true;
							$("#loginNameErr").html("该用户名已存在");
						}
					}
				});
			}
		});
		if(!id){
		   $("#ROLE_SALES").attr("checked","checked");
		} else {
			var userRoleStr = "${userRole}";
			if(userRoleStr){
				var userRoleArr = userRoleStr.substring(1,userRoleStr.length - 1).split(",");
				for(var i = 0 ; i < userRoleArr.length ; i++){
					$("#"+$.trim(userRoleArr[i])).attr("checked","checked");
				}
			}
		}
	});

	function submitForm() {
		
		if ($("#deptName").length > 0) {
			var deptName = $("#deptName").val();
			if (deptName == '-1') {
				$("#deptNameErr").html("请选择科室");
				return false;
			} else {
				$("#deptNameErr").html("");
			}
		}
		if ($("#loginName").length > 0) {
			var loginName = $("#loginName").val();
			if (loginName == null || loginName == "") {
				$("#loginNameErr").html("请填写登录名");
				return false;
			} else {
				$("#loginNameErr").html("");
			}
		}
		if ($("#name").length > 0) {
			var name = $("#name").val();
			if (name == null || name == "") {
				$("#NameErr").html("请填写姓名");
				return false;
			} else {
				$("#NameErr").html("");
			}
		}
		if(!id){//新建用户才验证密码
			if ($("#password").length > 0) {
				var password = $("#password").val();
				if (password == null || password == "") {
					$("#passwordErr").html("请填写密码");
					return false;
				} else {
					$("#passwordErr").html("");
				}
			}
			if ($("#password").val() != $("#password2").val()) {
				$("#userPwd").html("两次输入的密码不一样");
				return false;
			}
		}
		
		if (!validateLoginName) {
			$("#userForm").submit();
		}
	}

	function resePassword(){
		if (id && confirm("确定重置密码吗？")) {
			$.ajax({
				type : "post",
				url : "${ctx}/user/"+id+"/resePassword",
				success : function(msg) {
					if (msg == "ok") {
						alert("重置成功");
						window.location = "${ctx}/user/" + id + "/edit";
					} else {
						$("#loginNameErr").html("重置失败");
					}
				}
			});
		}
	}
	function rese() {
		$(':input', '#userForm').not(':button, :submit, :reset, :hidden').val(
				'').removeAttr('checked').removeAttr('selected');
	}
</script>
</head>
<body>
	<pre>用户管理><c:if test="${!empty user.id }">修改</c:if><c:if test="${empty user.id }">添加</c:if>用户</pre>
	<form:form modelAttribute="user" id="userForm" method="post">
		<div class="panel panel-info" style="width: 99%">
			<div class="panel-heading">用户信息
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="updateMsg" class="err"></span>
			<input type="hidden" name="availability" id="availability" value="${user.availability }">
			</div>
			<div class="panel-body">
				<table class="table table-condensed">
				    <tr height="40px">
						<td width="40%" align="right"><span class="err">*</span>
							登录名:</td>
						<td width="320px"><c:if test="${ empty user.id }"><form:input path="loginName"
								cssClass="form-control" style="width:320px" /></c:if>
								<c:if test="${ !empty user.id }">${user.loginName }</c:if>
						&nbsp;&nbsp;<form:errors cssClass="err"
								path="loginName"></form:errors><span id="loginNameErr" class="err"></span></td>
					</tr>
					<tr height="40px">
						<td align="right"><span class="err">*</span> 姓名:</td>
						<td><form:input path="name" cssClass="form-control"
								style="width:320px" />&nbsp;&nbsp;<form:errors cssClass="err"
								path="name"></form:errors></td>
					</tr>
					<tr height="40px">
						<td align="right">
							部门名称:</td>
						<td width="320px"><form:input path="deptName"
								cssClass="form-control" style="width:320px" />&nbsp;&nbsp;<span id="deptNameErr" class="err"></span></td>
					</tr>
					<c:if test="${empty user.id }">
						<tr height="40px">
							<td align="right"><span class="err">*</span> 密码:</td>
							<td><form:password path="password" cssClass="form-control"
									style="width:320px" />&nbsp;&nbsp;<form:errors cssClass="err"
									path="password"></form:errors><span id="passwordErr"
								class="err"></span></td>
						</tr>
						<tr height="40px">
							<td align="right">确认密码:</td>
							<td><input id="password2" type="password"
								class="form-control" style="width: 320px" />&nbsp;&nbsp;<span
								id="userPwd" class="err"></span></td>
						</tr>
					</c:if>
					<sec:authorize access="hasRole('ROLE_ADMIN')"> 
					<tr><td align="right">角色分配:</td>
					    <td>
							<c:forEach items="${roles}" var="role">
									<span>
									<input type="checkbox" id="${role.name }" value="${role.name }" name="role">&nbsp;${role.displayName }
									</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
						</td>
					</tr>
					</sec:authorize>
				</table>
			</div>
			<table width="99%">
				<tr>
					<td align="center">
					    <c:if test="${!empty user.id }">
					       <button type="button" onclick="resePassword();"
							class="btn btn-primary">重置密码</button>
					    </c:if>
						<button type="button" onclick="submitForm();"
							class="btn btn-primary">提 交</button>
						<button type="button" onclick="rese();" class="btn btn-default">清
							空</button>
					</td>
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>