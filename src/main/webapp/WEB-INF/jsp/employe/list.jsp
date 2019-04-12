<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">

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
			$.post("${ctx}/employe/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location = ("${ctx}/employe/list");
				} else {
					//
				}
			});
		}
	}
	// 编辑职工信息
	function edit(id){
		var url = "${ctx}/employe/" + id + "/edit";
		$("#myModalLabel").html("编辑职工信息");
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	// 编辑职工信息
	function addem(){
		var url = "${ctx}/employe/new";
		$("#myModalLabel").html("编辑职工信息");
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/employe/list");
	}
</script>
</head>
<body id="main">
	<pre>教师库>教师名单</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			<form id="expertForm" class="form-inline row" action="${ctx}/employe/list" method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left"><input type="text" name="employeName" value="${name}"
							style="width: 180px" class="form-control" placeholder="请输入专家姓名"></td>
						
						
					
						
						<td align="right" width="100px">进园时间：</td>
						<td align="left"><input type="text" name="beginTime"
							id="beginTime" value="${beginTime}" style="width: 122px"
							onclick="SelectDate(this,'yyyy-MM-dd')" readonly="readonly"
							class="form-control">-<input type="text" name="endTime" 
							id="endTime" value="${endTime}" style="width: 122px"
							onclick="SelectDate(this,'yyyy-MM-dd')" readonly="readonly"
							class="form-control"></td>
						<td align="left">
							<button class="btn btn-primary" type="button" id="submitBtn" onclick="submitForm()">查询</button>
							<button class="btn btn-primary" type="button" id="newadd" onclick="addem()">新增</button>
							<input type="button" onclick="rese()"
							value="清空" class="btn btn-default">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-body">
			<table class="table table-bordered table-condensed">
				<c:choose>
					<c:when test="${num==0}">
						<tr>
							<td>没有符合该条件的查询结果......</td>
						</tr>
					</c:when>
					<c:otherwise>
						<thead>
							<tr>
								<th width="150px">操&nbsp;&nbsp;&nbsp;作</th>
								<th>姓名</th>
<th>工号</th>
<th>邮箱</th>
<th>学校</th>
<th>进园时间</th>
<th>民族</th>
<th>贯籍</th>
<th>政治面貌</th>
<th>考勤卡号</th>
<th>住址</th>

							</tr>
						</thead>
						<c:forEach items="${data.content }" var="data">
					 <tr>
								<td> 
								   <a class="btn btn-primary  btn-xs" href="#" onclick="edit('${data.id}')">修改 </a>
								   <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="del('${data.id}')">删除 </a>
								   <a class="btn btn-primary  btn-xs"  target="_blank" href="${data.id}/read">查看详细</a>
								</td>
<td>${data.employeName }</td>
<td>${data.jobNumber }</td>
<td>${data.employeEmail}</td>
<td>${data.graduateSchool}</td>
<td>${data.inGartenDate}</td>
<td>${data.nation}</td>
<td>${data.birthPlace}</td>
<td>${data.politicalBackground}</td>
<td>${data.workAttendanceCardNumber}</td>				
<td>${data.dwellingPlace}</td>						


					
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>	
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
	<c:if test="${num>0}">
		<tags:pagination page="${data }" formId="expertForm"></tags:pagination>
	</c:if>
</body>
</html>