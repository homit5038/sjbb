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
		$("#expertForm #size").val(10);
		$("#expertForm #page").val(0);
		$("#expertForm").submit();
	}

	function rese() {
		$(':input', '#expertForm').not(':button, :submit, :reset, :hidden').val(
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
			<form id="expertForm" action="${ctx}/classe/list" method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left"><input type="text" name=classesname value="${name}"
							style="width: 180px" class="form-control" placeholder="请输入姓名"></td>
				
						
						
						<td align="left" >
							<button class="btn btn-primary" type="button" id="submitBtn" onclick="submitForm()">查询</button>
							<input type="button" onclick="rese()"value="清空" class="btn btn-default">
						
						    <input type="button" onclick="addem()"	value="添加班级" class="btn btn-default">
							<button type="button" onclick="startConnect();" class="btn btn-warning" >覆盖关联</button>
							<button type="button" onclick="allshow();" class="btn btn-warning" >全部显示</button>
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
							<td class="childId">
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
               
                   <span id="selected-kinder">张征睿<input type="hidden" class="childId" value="487ECD5B866442A5A64D99A3C556923E"></span> 2019年03月份 缴费详情
                
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
								<td><input type="checkbox" class="charge-item" value=${data.id}></td>
								<td>${data.itemName}</td>
							    <td>${data.amount}</td>
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
<script type="text/javascript" src="https://www.jqiyun.cn/theme/plugins/zui/js/zui.min.js"></script>
<script type="text/javascript" src="https://www.jqiyun.cn/theme/js/app.js"></script>
<script type="text/javascript">
//收费项表格，点击行即选中本行
$(document).on('click', '#charge-item-table tr', function () {
	$(this).addClass("active").siblings("tr").removeClass("active")
	$("#childIdList").val($(this).attr("data-id")); 
	//var choose_name_2 = $(this).children(".childId").html();
	var choose_name_1= $(this).find(".childId").html();
			//console.log(choose_name_2)
	$('#selected-kinder').html(choose_name_1)
	connected($(this).attr("data-id"))
});

function allshow(){
	 $("#kinder-list-table tr:not(:first)").removeClass("hidden");
}

function connected(id){
	// jQuery('#contoned').html("loading...")
		$('#contoned tr').empty(); 
	    $.post("${ctx}/chargeitem/" + id + "/conne", {
		id : id
	}, function(data) {
		//alert(data.length)
	
		
		 $("#kinder-list-table tr:not(:first)").addClass("hidden");
		if(data.length>1000){
			
			alert(0)
			  
			
		}else{
			  json = eval(data); 
			for(var i = 0; i < json.length; i++){
				
				  var tr = $('<tr></tr>');
	              var cid = $('<td width="10%" class="td-select"><input type="checkbox" class="charge-item"  value="' + json[i].id + '" /></td>');
	              var chargingName = $('<td width="30%" class="charge-name">' + json[i].amount + '</td>');
	              var chargingPeriod = $('<td width="20%">' + json[i].periodic + '</td>');
	              var chargingAmount = "";

	              tr.append(cid, chargingName, chargingPeriod);
	              $("#contoned").append(tr);
	            
	           
				
	         
				
	              $("#kinder-list-table tr").each(function(){
	            	    if(json[i].id==$(this).attr("data-id")){
	            	    	
	            	    	$(this).addClass("active").removeClass("hidden");;
	            	    }
	            	    
	            	  });
	              
	              
				
				 // bbs +=("<tr><td>"+json[i].itemName + "</td><td>" + json[i].amount+"</td></tr>");
				}
		
			//$('#contoned').append(bbs); 
			
	
		}
	});
	
}


</script>