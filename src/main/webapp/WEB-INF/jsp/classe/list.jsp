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
							style="width: 180px" class="form-control" placeholder="请输入专家姓名"></td>
				
						
						
						<td align="left" >
							<button class="btn btn-primary" type="button" id="submitBtn" onclick="submitForm()">查询</button>
							<input type="button" onclick="rese()"value="清空" class="btn btn-default">
						
						    <input type="button" onclick="addem()"	value="添加班级" class="btn btn-default">
							<button type="button" onclick="startConnect();" class="btn btn-warning" >覆盖关联</button>
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
								<th width="100px">操&nbsp;&nbsp;&nbsp;作</th>
								<th>班级</th>
					            <th>所属年级</th>
	                             <th>班级人数</th>
							</tr>
						</thead>
						<c:forEach items="${data.content }" var="data">
							 <tr  data-id="${data.id}">
								<td> 
								   <a class="btn btn-primary  btn-xs" href="#" onclick="edit('${data.id}')">修改 </a>
								   <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="del('${data.id}')">删除 </a>
								</td>
								<td>${data.classesname}</td>
							   <td>${data.grade.gradename}</td>
							
								<td>${fn:length(data.children)}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>	
			
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
								<th width="100px">操&nbsp;&nbsp;&nbsp;作</th>
								<th>收费名称</th>
					            <th>收费金额</th>
	                            <th>收费周期</th>
	                            <th>小票等级</th>
							</tr>
						</thead>
						<c:forEach items="${Itemlist}" var="data">
							 <tr data-id="${data.id}">
								<td> 
								   <a class="btn btn-primary  btn-xs" href="#" onclick="edit('${data.id}')">修改 </a>
								   <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="del('${data.id}')">删除 </a>

								</td>
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
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/zui/js/zui.mine.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/appo.js"></script>
<script type="text/javascript">
	$("#kinder-list-table").datatable({
		checkable : true,
		storage:false,
		checksChanged: function(e) {
	    	$("#childIdList").val(e.checks.checks); 
	    	$("#childIdList").val(e.checks.checks); 
	    	//$(this+"tr>td").find(".icon-check-empty").remove() 
	         // var nRow = $(this).parents('tr')[0];//得到这一行
	          //console.log($(this+" >tr>td"))
	    }
	 
	}
	
	
	);
	$("#charge-item-table").datatable({
		checkable : true,
		storage:false,
		checksChanged: function(e) {
	    	$("#chargeIdList").val(e.checks.checks); 
	    }
	});
	
	
	function startConnect(){
		var childIdList=$("#childIdList").val();
		var chargeIdList=$("#chargeIdList").val();
		jQuery.ajax({
			   url:'${ctx}/classe/charge',
			   data:'childIdList='+chargeIdList+'&chargeIdList='+childIdList,
			   method:'post',
			   dataType:'json',
			   success:function(data){
				   if(data=="no"){
					   alert("请选择要设置收费项的班级！");
				   }else{
					   alert("设置成功！");
					   setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
						   window.location.reload();//页面刷新
						   },1000); 
				   }
				   
			   }
		   });
	}
	
	function startUpdateConnect(){
		var childIdList=$("#childIdList").val();
		var chargeIdList=$("#chargeIdList").val();
		 
		jQuery.ajax({
			   url:'${ctx}/classe/charge',
			   data:'childIdList='+childIdList+'&chargeIdList='+chargeIdList,
			   method:'post',
			   dataType:'json',
			   success:function(data){
				   if(data=="no"){
					   alert("请选择要设置收费项的班级！");
				   }else{
					   alert("设置成功！");
					   setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
						   window.location.reload();//页面刷新
						   },1000); 
				   }
				   
			   }
		   });
	}
	
</script>