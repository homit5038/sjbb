<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<style type="text/css">
.main {
	width: 420px;
	margin: 0 auto;
}

.cont {
	width: 400px;
	margin-top: 20px;
}

.STYLE11 {
	color: #0048a3;
	font: Arial, "黑体", Helvetica, sans-serif;
	font-weight: bold;
	font-size: 12px;
}

.STYLE3 {
	color: #3399ff;
	font: Arial, "黑体", Helvetica, sans-serif;
	font-weight: normal;
	font-size: 12px;
}

.butBox {
	text-align: center;
	width: 180px;
	margin-left: 25px;
	margin-top: 8px;
}

.err {
	color: red;
}
</style>
<script type="text/javascript">
	var validatepwd = true;
	function save() {
		var pwd2 = document.getElementById("pwd2");
		if (pwd2.value == "") {
			$("#pwd2Error").html("新密码不能为空!");
			return;
		} else if (pwd2.value.length < 4) {
			$("#pwd2Error").html("密码为4-16位任意字符!");
			return;
		} else {
			$("#pwd2Error").html("");
		}

		var pwd3 = document.getElementById("pwd3");
		if (pwd3.value == "") {
			$("#pwd3Error").html("确认密码不能为空!");
			return;
		} else {
			$("#pwd3Error").html("");
		}

		if (pwd3.value != pwd2.value) {
			$("#pwd3Error").html("两次输入的密码不一样");
			return;
		} else {
			$("#pwd3Error").html("");
		}

		$("#editPwdForm").submit();
	}
</script>
</head>
<body>
	<div class="panel panel-default"
		style="width: 95%; margin: 0 auto; margin-top: 12px;">
		<div class="panel-heading">
			<div class="text-muted bootstrap-admin-box-title">密码修改</div>
		</div>
		<div class="bootstrap-admin-panel-content">
			<form:form id="editPwdForm" method="post">
				<!-- 表单开始-->
				<table width="100%" height="82" border="0" cellpadding="0"
					cellspacing="0" class="line_table">
					<tr>
						<td colspan="2" valign="top">
							<!-- 最新动态 -->
							<table width="100%">
								<c:if test="${editPwd=='ok' }">
									<tr height="50px">
										<td width="100px" align="right">&nbsp;</td>
										<td colspan="2"><span class="STYLE1">密码修改成功</span></td>
									</tr>
								</c:if>
								<c:if test="${editPwd!='ok' }">
									<tr height="50px">
										<td width="100" align="right">新密码：</td>
										<td width="300px"><input type="password" class="form-control"
											name="password" value="" id="pwd2" /></td>
										<td>&nbsp;<span id="pwd2Error" style="color: red"></span></td>
									</tr>
									<tr height="50px">
										<td align="right">确认密码：</td>
										<td><input type="password" class="form-control" value=""
											id="pwd3" /></td>
										<td>&nbsp;<span id="pwd3Error" style="color: red"></span></td>
									</tr>
								</c:if>
							</table>
						</td>
					</tr>
				</table>
				<!-- 表单结束 -->
				<!-- 分页区 -->
				<table width="100%" height="30" style="border-top: 0px;" border="0"
					cellpadding="0" cellspacing="0" class="line_table">

					<tr>
						<td align="center" height="50px"><c:if
								test="${editPwd!='ok' }">
								<input type="button" class="btn btn-danger" value="保存" id="saveGryBtn"
									onclick="save()" />
							</c:if></td>
					</tr>
				</table>
				<!-- 分页区结束 -->
			</form:form>
		</div>
	</div>
</body>
</html>