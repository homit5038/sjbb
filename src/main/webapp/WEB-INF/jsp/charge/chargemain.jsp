<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$("#yxx").val("${yxx}");
		$("#flag").val("${flag}");
		 
	});

	function submitForm() {
		jQuery('#main').showLoading();
		$("#chargemainFrom #size").val(10);
		$("#chargemainFrom #page").val(0);
		$("#chargemainFrom").submit();
	}

	function rese() {
		$(':input', '#chargemainFrom').not(':button, :submit, :reset, :hidden').val(
				'').removeAttr('checked').removeAttr('selected');
	}

	//删除信息
	function del(Id) {
		if (confirm('确定要删除该条信息吗?')) {
			jQuery('#main').showLoading();
			$.post("${ctx}/classe/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location = ("${ctx}/classe/list");
				} else {
					//
				}
			});
		}
	}
	// 编辑专家信息
	function edit(id){
		var url = "${ctx}/classe/" + id + "/edit";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	function addem(){
		var url = "${ctx}/classe/new";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/classe/list");
	}
</script>
</head>
<body id="main">
	<pre>班级>班级列表</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
		<input type="hidden" id="printerWidth" value="58" />
		<input type="hidden" id="printGroup" value="1" />
        <input type="hidden" id="printerName" value="0" />
			<form id="chargemainFrom" action="${ctx}/charge/chargemain" method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left"><input type="text" name=childName value="${name}"
							style="width: 180px" class="form-control" placeholder="请输入姓名"></td>
				
						
						
						<td align="left" >
							<button class="btn btn-primary" type="button" id="submitBtn" onclick="submitForm()">查询</button>
							<input type="button" onclick="rese()"value="清空" class="btn btn-default">
						
						    <input type="button" onclick="addem()"	value="添加班级" class="btn btn-default">
							<button type="button" onclick="startConnect();" class="btn btn-warning" >覆盖关联</button>
							<button type="button" onclick="allshow();" class="btn btn-warning" >全部显示</button>
						<button type="button" onclick="allv();" class="btn btn-warning" >选中</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-body">
		<input type="text" id="childIdList">
		<input type="text" id="chargeIdList">
		
			<table class="table table-bordered table-condensed" id="charge-item-table">
				<c:choose>
					<c:when test="${num==0}">
						<tr>
							<td>没有符合该条件的查询结果......</td>
						</tr>
					</c:when>
					<c:otherwise>
						<thead>
							<tr>
								<th>姓名</th>
					            <th>联系电话</th>
	                         
							</tr>
						</thead>
						<c:forEach items="${lists}" var="data">
							 <tr  data-id="${data.id}">
							<td class="childId" data-id="${data.chargConnection}">
							${data.childName}
							<%-- <input type="hidden" class="childId" value="${data.id}"> --%>
							</td>
							<td>${data.parentPhoneNumber}</td>
							
								
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<div class="col-md-12 charge-detail">
               
                   <span id="selected-kinder">
                   张征睿</span>
                   <input type="hidden" class="childId" > 2019年03月份 缴费详情
                
            </div>	
			<div class="row">
            <div class="col-md-12 charge-items text-center">
                <form id="myform" class="form-inline">
                    <input type="hidden" name="payStatus" value="CG">
                    <input type="hidden" id="favourable" name="favourable" value="0">
                    <input type="hidden" name="flowCode" id="flowCode" value="">
                    <input type="hidden" name="chargetype" id="chargingType" value="1">
                    <input type="hidden" name="childId" id="childId">
                    <input type="text" name="chargConnection" id="chargConnection" value="0">
                    <input type="hidden" id="paytype" name="paytype" value="1">
                    <span>应收&nbsp;&nbsp;<input type="text" id="shouldpay" readonly="" name="charge-should-pay" class="form-control charge-input" value="0"></span>
                    <span>实收&nbsp;&nbsp; <input type="text" id="realpay" name="charge-real-pay" class="form-control charge-input" value="0"></span>
                    <span>找零&nbsp;&nbsp; <input type="text" id="returnpay" readonly="" name="charge-return" class="form-control charge-input" value="0"></span>
                    <span>备注&nbsp;&nbsp; <input type="text" id="bz" name="bz" class="form-control charge-input"></span>
                    <span><input id="doCharge" type="button" class="btn btn-warning" value="缴费打印"></span>
                </form>
            </div>
        </div>
			<table class="table table-bordered table-condensed" id="contoned"></table>
			<table class="table table-bordered table-condensed" id="kinder-list-table">
				<c:choose>
					<c:when test="${num==0}">
						<tr>
							<td>没有符合该条件的查询结果......</td>
						</tr>
					</c:when>
					<c:otherwise>
						<thead>
							<tr>
							    <th>选择</th>
								<th>收费名称</th>
					            <th>收费金额</th>
	                            <th>收费周期</th>
	                            <th>小票等级</th>
							</tr>
						</thead>
						<c:forEach items="${Itemlist}" var="data">
							 <tr data-id="${data.id}">
								<td><input type="checkbox" class="charge-item" name="chargeitem" value=${data.id}></td>
								<td>${data.itemName}</td>
							    <td>${data.amount}
							   <input type="hidden" class="charge-amount" value="${data.amount}">
							    </td>
								 <td>${data.periodic.displayName}</td>
								 <td>${data.ticketLevel.displayName}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>	
			
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
</body>
</html>
<script type="text/javascript" charset="utf-8" src="https://www.jqiyun.cn/theme/plugins/zui/js/zui.min.js"></script>
<script type="text/javascript" charset="utf-8" src="https://www.jqiyun.cn/theme/js/app.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/LodopFuncs.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/print.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/app2.js"></script>