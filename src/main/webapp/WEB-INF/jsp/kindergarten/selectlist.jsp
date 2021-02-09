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
			$.post("${ctx}/kindergarten/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location = ("${ctx}/kindergarten/list");
				} else {
					//
				}
			});
		}
	}
	// 编辑专家信息
	function edit(id){
		//var url = "${ctx}/goHome/" + id + "/edit";
		window.location= "${ctx}/goHome/" + id ;
		//$("#signImgFrame").attr('src', url);
		//$("#signImgModal").modal();
	}
	function addem(){
		var url = "${ctx}/kindergarten/new";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/kindergarten/list");
	}
</script>
</head>
<body id="main">
	<pre>幼儿园>幼儿园列表</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			<form id="expertForm" action="${ctx}/kindergarten/selectList" method="post">
				<input type="hidden" id="size" name="size" value="${size}">
				<input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="100px">幼儿园名称：</td>
						<td align="left">
						<input type="text" name="Kindergartenname" value="${name}"
							style="width: 180px" class="form-control" placeholder="幼儿园名称">
							</td>

						<td align="right" width="100px">注册人：</td>
						<td align="left">
						<input type="text" name="loginName" 
							style="width: 180px" class="form-control" placeholder="注册人名称">
							</td>
						
						<td align="left" >
							<button class="btn btn-primary" type="button" id="submitBtn" onclick="submitForm()">查询</button>
							<input type="button" onclick="rese()"value="清空" class="btn btn-default">
						
						    <input type="button" onclick="addem()"	value="添加幼儿园" class="btn btn-default">
							
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-body">

	
		<table class="table table-bordered table-condensed" id="charge-item-table">
		<thead>
							<tr>
								<th width="100px">操&nbsp;&nbsp;&nbsp;作</th>
								<th>名称</th>
					            <th>地址</th>
	                             <th>园长</th>
							</tr>
						</thead>
						
						<tr>
						<td align="center">
							<c:forEach items="${data.content}" var="data" varStatus="rowCount">
								<%-- <c:forEach items="${data}" var="datas"> --%>
							 <tr data-id="${data.id}">
								<td> 
								   <a class="btn btn-primary  btn-xs" href="#" onclick="edit('${data.id}')">进入管理 </a>

								</td>
								<td>${data.kindergartenname}</td>
								<td>${data.addresea}</td>
								<td>${data.principal}</td>
							</tr>
						</c:forEach>
						
				

			</table>

			<tags:pagination page="${data}" formId="expertForm"></tags:pagination>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
</body>
</html>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/zui/js/zui.mine.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/appo.js"></script>
<script type="text/javascript">

	
	
	
</script>