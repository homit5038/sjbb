<%@page import="com.xqx.frame.config.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${siteTitle }</title>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<base target="${base_target==null?'_self':base_target }">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="${ctx}/resources/css/style-v1.css" rel="stylesheet">
<link href="${ctx}/resources/css/style-feng.css" rel="stylesheet">
<link href="${ctx}/resources/bootstrap-3.3.5/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" media="screen"href="${ctx}/resources/css/zjk.css">

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
<div class="container" style="margin-top: 20px;width:1122px">
    <div class="row">
        <div class="col-md-12">

        </div>
    </div>
    <div class="row" style="margin-top: 20px;">
        <div class="col-md-8">
            <div class="flexslider">
                <ul class="slides">
                    <li style="background:url(${ctx}/resources/images/222.png) 50% 0 no-repeat;"></li>
                    <li style="background:url(${ctx}/resources/images/banner1.png) 50% 0 no-repeat;"></li>
                    <li style="background:url(${ctx}/resources/images/banner2.png) 50% 0 no-repeat;"></li>
                    <li style="background:url(${ctx}/resources/images/banner3.png) 50% 0 no-repeat;"></li>
                    <li style="background:url(${ctx}/resources/images/banner4.png) 50% 0 no-repeat;"></li>

                </ul>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default" >

                <div class="panel-body" style="padding: 40px 20px;">
                    <form action="j_spring_security_check" id="loginForm" method="post" >
                        <h4 class="login-title">登录</h4>

                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon large"><span class="glyphicon glyphicon-user"></span></div>
                                <input type="text" placeholder="输入用户名" id="j_username" name="j_username" value="" class="form-control large">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon large"><span class="glyphicon glyphicon-lock"></span></div>
                                <input type="password" id="j_password" name="j_password" placeholder="输入密码" id="exampleInputPassword2" class="form-control large">

                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon large"><span class="glyphicon glyphicon-eye-open"></span></div>
                                <input type="text" id="fCode" name="fCode" placeholder="验证码" id="exampleInputPassword2" style="width:30%" class="form-control large">
                            <img alt="code..." name="randImage"
									id="randImage" src="${ctx}/image.jsp" width="70" height="50"
									border="1" align="middle" onclick="javascript:loadimage();"
									style="cursor: pointer;" />

                            </div>
                        </div>

                        <div class="checkbox" style="text-align: right;">
	                       	<span style="color:red" id="error"><b>	${SPRING_SECURITY_LAST_EXCEPTION.message}</b></span>

                        </div>
                        <input type="hidden" name="referer" value="http://manage.linktrust-edu.com/linktrust/sys/site/spacetologin" />
                        <button class="btn btn-danger btn-lg" type="submit" style="width:100%;">登 陆</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container footer" style=" margin:auto; border-top:1px solid #DDD; padding-top:20px; box-shadow:0px -1px 0px #fff;">
    <div class="row">
        <div class="col-md-12">Copyright @ 2017-2020 昆明新奇星科技有限公司</div>
    </div>
</div>

<style type="text/css">

	.flex-direction-nav {
	display:none;
	}
</style>



<!-- 2014“暑假班”JS公用库 -->


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script type="text/javascript" src="/linktrust/static/libs/bootstrap/js/jquery.min.js?version=1.0"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript" src="/linktrust/static/libs/bootstrap/js/bootstrap.min.js?version=1.0"></script>
<script type="text/javascript" src="/linktrust/static/libs/bootstrap/js/docs.min.js?version=1.0"></script>

<!-- angular JS 文件 -->
<script  type="text/javascript" src="/linktrust/static/libs/angularjs/js/angular.min.js?version=1.0"></script>

<!-- select2 JS 文件 -->
<script type="text/javascript" src="/linktrust/static/libs/select2/select2.js?version=1.0"></script>

<!-- 日期选择器 -->
<script type='text/javascript' src='/linktrust/static/libs/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js?version=1.0'></script>
<!-- <script type='text/javascript' src='/linktrust/static/libs/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js?version=1.0'></script> -->

<!-- 自定义JS -->
<script type="text/javascript" src="/linktrust/static/js/plug-in.js?version=1.0"></script>

<!-- rootpath 文件 -->
<script type="text/javascript" src="/linktrust/jslib/root.js?version=1.0"></script>

<!-- 渐隐轮播图 文件 -->
<script type="text/javascript" src="/linktrust/static/libs/jquery.flexslider/js/jquery.flexslider-min.js?version=1.0"></script>
<script type="text/javascript">
$(document).ready(function(){
    $('.flexslider').flexslider({
        directionNav: true,
        pauseOnAction: false
    });
});
</script>
</body>
</html>