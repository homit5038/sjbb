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
			$.post("${ctx}/property/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location = ("${ctx}/property/list");
				} else {
					//
				}
			});
		}
	}
	// 编辑专家信息
	function edit(id){
		var url = "${ctx}/property/" + id + "/edit";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}

	// 编辑专家信息
	function chiurds(id){
		var url = "${ctx}/property/" + id + "/list";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/property/list");
	}
</script>
</head>
<body id="main">
	<pre>配置>系统属性配置</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			<form id="expertForm" action="${ctx}/property/${fname}/list" method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left"><input type="text" name=fValue value="${fname}"
							style="width: 180px" class="form-control" placeholder="请输入专家姓名"></td>
				
						
						
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
								<th width="200px">操&nbsp;&nbsp;&nbsp;作</th>
								<th>属性名称</th>
					            <th>父级属性</th>
					            <th>链接</th>
	                            <th>属性值</th>
	                            <th>备注</th>
							</tr>
						</thead>
						<c:forEach items="${data.content }" var="data">
							 <tr>
								<td> 
								   <a class="btn btn-primary  btn-xs" href="#" onclick="edit('${data.id}')">修改 </a>
								   <a class="btn btn-primary  btn-xs" href="#" onclick="chiurds('${data.id}')">修改 </a>
								   <a class="btn btn-primary  btn-xs" href="${ctx}/property/new?fid=${data.id}" >在此新增 </a>
								   <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="del('${data.id}')">删除 </a>
								</td>
								<td>${data.fName}</td>
							    <td>${data.fId}</td>
							    <td>${data.fRemak}</td>
								<td>${data.fValue}</td>
								<td>${data.fMemo}</td>
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