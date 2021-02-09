<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<link href="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<script src="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script type="text/javascript">
$(function(){ 
	$(".form-date").datetimepicker(
			{
			    language:  "zh-CN",
			    weekStart: 1,
			    todayBtn:  1,
			    autoclose: 1,
			    todayHighlight: 1,
			    startView: 2,
			    minView: 2,
			    forceParse: 0,
			    format: "yyyy-mm-dd"
			});
	
})

function getUsers(){
	var date=$("#statistics-date").val();
	var userId = document.getElementById('userId');//得到区域select值
	selectStatics();
} 


function selectStatics(param){
    var date=$("#statistics-date").val();
    var userId=$("#userId").val();
    var payType=$("#payType").val();
    if(typeof(param)=='undefined'){
        param='';
    }
    window.location.href="${ctx}/charge/statistics?date="+date+'&userId='+userId+'&pageNum='+param+'&paytype='+payType;
	
}



	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/charge/chargelist");
	}
	
	
</script>
</head>
<body id="main">
	<pre>收费统计>收费统计列表</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			<form id="payedinfoForm" class="form-inline row"action="${ctx}/charge/statistics" method="post">
				
				<table>
					<tr height="40px">
						
					
						
						<td align="right" >时间：</td>
						<td align="left">
						
						<input type="text" name="statistics-date"
							id="statistics-date"  style="width: 122px" onchange="getUsers()" class="form-control form-date" >
							
							</td>
						<td align="right" width="100px">付款方式：</td>
						<td align="left" width="100px" >
						
						
		                <select id="payType" Class="bs-select form-control" readonly="readonly" onchange="selectStatics();">		
							<option value="">请选择</option>
							<c:forEach items="${Payed}" var="Payed">
							<option value="${Payed.name}">${Payed.describe}</option>
							</c:forEach>			
						</select>	
						


                      </td>
						
						
						
						
					</tr>
				</table>
			</form>
		</div>
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
			<tr><td colspan="11" height="100" align="center" valign="middle">一共缴费人数：${counts} 人  一共缴费金额：${sumt}元</td></tr>
			</table>	
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>

</body>
</html>