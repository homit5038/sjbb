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
</head>
<body id="main">
<div class="panel panel-info" style="width: 99%">
<div class="panel-body">
<input type="text" id="storyinfoList">
<button type="button" id="deleteaply" class="btn btn-warning">删除记录</button>
<table id="selectchildhistoryinfo" class="table table-bordered table-condensed">		
<thead><tr>
								
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
<tr data-id="${data.id}">							
<td>${data.children.childName}</td>
<td>${data.children.grade.gradename}</td>
<td>${data.children.classe.classesname}</td>					
<td>${data.paytype.describe}</td>
<td>${data.chargerealpay}</td>
<td>${data.chargeshouldpay}</td>
<td>${data.timeb}</td>
<td>${data.payableDsate}</td>
<td id="chargtext">${data.chargConnectiontext}</td>
<td>${data.chargConnection}</td>				
<td>${data.remarks}</td>			
</tr>
</c:forEach>
</table>	
</div>
</div>
</body>
</html>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/zui/js/zui.mine.js"></script>
<script type="text/javascript">
	$("#selectchildhistoryinfo").datatable({
		checkable : true,
		storage:false,
		checksChanged: function(e) {
			//alert(e.checks.checks)
			$("#storyinfoList").val(e.checks.checks); 
	    	//$("#storyinfoList").val(e.checks.checks); 
	    }})
	    
	 $("#deleteaply").click(function(){
		     deleteaply()
     });   	
	    
	    
	function deleteaply(){
		var storyinfoList=$("#storyinfoList").val();
		if(typeof storyinfoList == "undefined" || storyinfoList == null || storyinfoList == ""){
			   alert("请选择要删除的信息！");
			return false
		}else{
			jQuery.ajax({
				   url:'${ctx}/charge/del',
				   data:'id='+storyinfoList,
				   method:'post',
				   dataType:'json',
				   success:function(data){
					   if(data=="ok"){
						   alert("操作成功！");
						   setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
							   window.location.reload();//页面刷新
							   },1000); 
					   }else{
						   alert("失败！");
						   
					   }
					   
				   }
			   });	
		}

	}
</script>