<%@page import="com.xqx.frame.config.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${siteTitle }</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" charset="utf-8"
	src="${ctx}/resources/js/jquery-1.12.1.min.js"></script>

<script type="text/javascript">
	$(function() {
		$(document).keydown(function(event) {
			if (event.keyCode == 13) {
				$("#btnSignCheck").click();
			}
		});

		$("#btnSignCheck").click(function() {
			submitForm();
		});
	})
	function submitForm() {
		var username = $("#j_username").val();
		var password = $("#j_password").val();
		var fCode = $("#fCode").val();
		if (username == null || username == "") {
			$("#error").text("请输入用户名");
			return false;
		} else {
			$("#error").text("");
		}
		if (password == null || password == "") {
			$("#error").text("请输入密码");
			return false;
		} else {
			$("#error").text("");
		}
		if (fCode == null || fCode == "") {
			$("#error").text("请输入验证码");
			return false;
		} else {
			$("#error").text("");
		}
		var fCode = $("#fCode").val();
		$.post("${ctx}/validateCode", {
			fCode : fCode
		}, function(data) {
		
			if (data == "ok") {
				$("#loginForm").submit();
			} else {
				$("#error").text("验证码错误");
				loadimage();
				return false;
			}
		});
	}

	function loadimage() {
		document.getElementById("randImage").src = "${ctx}/image.jsp?"
				+ Math.random();
		//alert(document.getElementById("randImage").src.text);
		//document.getElementById("rand").value = "image.jsp?"+Math.random();
	}
</script>
</head>
<body>
	<div class="beij">
		<table width="681" border="0">
			<tr>
				<td width="273" height="142" valign="top">&nbsp;</td>
				<td width="156" valign="middle"><table width="152" height="84"
						border="0">
						<tr>
							<td width="146" height="15">&nbsp;</td>
						</tr>
					</table></td>
				<td width="238" valign="top">&nbsp;</td>
			</tr>

			<tr>
				<td height="217">&nbsp;</td>
				<td height="217" colspan="2" valign="top">
					<form action="j_spring_security_check" id="loginForm"  method="post">
						<table height="146" align="center" border="0">
							<tr>
								<td height="28" class="STYLE1"></td>
								<td class="error">&nbsp;&nbsp;<span id="error"></span>
									${SPRING_SECURITY_LAST_EXCEPTION.message}
								</td>
							</tr>
							<tr>
								<td height="28" class="STYLE1">用户名：</td>
								<td><input name="j_username" id="j_username"
									class="denglukv"></td>
							</tr>
							<tr>
								<td height="28" class="STYLE1">密 &nbsp;&nbsp;码：</td>
								<td><input name="j_password" id="j_password"
									type="password" class="denglukv"></td>
							</tr>
							<tr>
								<td height="28" class="STYLE1">验证码：</td>
								<td><input name="fCode" id="fCode" type="text"
									class="denglukv"> <img alt="code..." name="randImage"
									id="randImage" src="${ctx}/image.jsp" width="70" height="28"
									border="1" align="middle" onclick="javascript:loadimage();"
									style="cursor: pointer;" /></td>
							</tr>
							<tr>
								<td height="47" colspan="2"><input id="btnSignCheck"
									type="button" class="annk" value="登 &nbsp;录" /></td>

							</tr>

						</table>
					</form>


				</td>
			</tr>
		</table>
	</div>

</body>
</html>