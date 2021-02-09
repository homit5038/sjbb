<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/xqxtags" prefix="xqx"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<link href="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<script src="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script type="text/javascript">
$(function(){
	
	alert(3)
}


)
</script>
</head>
<body id="main">
	<pre>学生库>学生列表</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">


		<div class="panel-body">
			<table class="table table-bordered table-condensed">
			
<thead>
			<tr>
								
								<th>姓名</th>
								<th>年级</th>
								<th>班级</th>
								<th>付款方式</th>
								<th>实付金额</th>
								<th>应付金额</th>
								<th>小票打印日期</th>
								<th>付款日期</th>
								<th>收费详情</th>
								<th>收费项</th>
								<th>备注</th>
							</tr>
						</thead>


	<c:forEach items="${data}" var="data">
					 <tr>

								
<td>${data.children.childName}</td>
<td>${data.children.grade.gradename}</td>
<td>${data.children.classe.classesname}</td>					
<td>${data.paytype.describe}</td>
<td>${data.chargerealpay}</td>
<td>${data.chargeshouldpay}</td>
<td>${data.timeb}</td>
<td>${data.payableDsate}</td>

<td id="chargtext">${data.chargConnectiontext}



</td>
<td>${data.chargConnection}</td>				
<td>${data.remarks}</td>			


					
							</tr>
						</c:forEach>
			</table>	
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
	
</body>
</html>