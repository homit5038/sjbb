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
	
	
	
	function connected(id){
		 jQuery('#contoned').html("loading...")
		$.post("${ctx}/chargeitem/" + id + "/conne", {
			id : id
		}, function(data) {
			json = eval(data)  
			//alert(json.length)
			if (json) {
				console.log(json)
				
				var bbs="";
				for(var i = 0; i < json.length; i++){
					  bbs +=(json[i].itemName + "-" + json[i].amount+"<br/>");
					}
				
				
				 jQuery('#contoned').html(bbs)
				
				
				
			} else {
				//
			}
		});
		
	}

	//删除信息
	function del(Id) {
		if (confirm('确定要删除该条信息吗?')) {
			jQuery('#main').showLoading();
			$.post("${ctx}/chargeitem/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location = ("${ctx}/chargeitem/list");
				} else {
					//
				}
			});
		}
	}
	// 编辑专家信息
	function edit(id){
		var url = "${ctx}/chargeitem/" + id + "/edit";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/chargeitem/list");
	}
</script>
</head>
<body id="main">



											
<xqx:property name="departList" fId="15"></xqx:property>



<select id="flag" name="flag" class="form-control" style="width: 320px">
				
<c:forEach items="${departList}" var="department">
												<option value="${department.id }">${department.fValue}</option>
											</c:forEach>
						</select>


	<pre>收费项设置0000</pre>
	
	${sessionScope.loginName}
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			<form id="expertForm" action="${ctx}/chargeitem/list" method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left">
						<input type="text" name="ItemName" value="${name}" style="width: 180px" class="form-control" placeholder="请输入专家姓名">
						<input type="text" name="mtop" value="${mtop}" style="width: 180px" class="form-control" placeholder="请输入专家姓名">	
							
							</td>
				
						
						
						<td align="left" width="160px">
							<button class="btn btn-primary" type="button"
								 id="submitBtn" onclick="submitForm()">查询</button>
							<input type="button" onclick="rese()"
							value="清空" class="btn btn-default">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-body">
		
		<button type="button" onclick="startUpdateConnect();" class="btn btn-warning" >增量关联</button>
		<button type="button" onclick="startConnect();" class="btn btn-warning" >覆盖关联</button>
		
		
		<input type="text" id="childIdList">
		<input type="text" id="chargeIdList">
		
		
	
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
						<c:forEach items="${data.content }" var="data">
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
			
		
	
		
<div id="contoned">====</div>
<table class="table table-bordered table-condensed" id="charge-item-table">
			
					<thead>
							<tr>
								<th width="100px">操&nbsp;&nbsp;&nbsp;作</th>
								<th>年级</th>
							</tr>
						</thead>
						
						<c:forEach items="${datas}" var="datas">
							 <tr data-id="${datas.id}">
								<td> 
								   <a class="btn btn-primary  btn-xs" href="#" onclick="edit('${datas.id}')">修改 </a>
								   <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="del('${datas.id}')">删除 </a>
								 <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="connected('${datas.id}')">关联</a>
								</td>
								<td>${datas.gradename}</td>
							</tr>
						</c:forEach>
		
			</table>	
			
		</div>
	</div>
	<script type="text/javascript" src="https://www.jqiyun.cn/theme/plugins/zui/js/zui.min.js"></script>
<script type="text/javascript" src="https://www.jqiyun.cn/theme/js/app.js"></script>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
	
	<c:if test="${num>0}">
		<tags:pagination page="${data }" formId="expertForm"></tags:pagination>
	</c:if>
</body>
</html>
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
			   url:'${ctx}/grade/charge',
			   data:'childIdList='+chargeIdList+'&chargeIdList='+childIdList,
			   method:'post',
			   dataType:'json',
			   success:function(data){
				   if(data==2){
					   alert("请选择幼儿！");
				   }else{
					   alert("修改成功！");
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
			   url:'${ctx}/grade/charge',
			   data:'childIdList='+childIdList+'&chargeIdList='+chargeIdList,
			   method:'post',
			   dataType:'json',
			   success:function(data){
				   if(data==2){
					   alert("请选择幼儿！");
				   }else{
					   alert("修改成功！");
					   setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
						   window.location.reload();//页面刷新
						   },1000); 
				   }
				   
			   }
		   });
	}
	
</script>