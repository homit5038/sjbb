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
			$.post("${ctx}/expert/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location = ("${ctx}/expert/list");
				} else {
					//
				}
			});
		}
	}
	// 编辑专家信息
	function edit(id){
		var url = "${ctx}/expert/" + id + "/edit";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/expert/list");
	}
</script>
</head>
<body id="main">
	<pre>专家库>专家名单</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			<form id="expertForm" class="form-inline row" action="${ctx}/expert/list" method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left"><input type="text" name="expertName" value="${name}"
							style="width: 180px" class="form-control" placeholder="请输入专家姓名"></td>
						<td align="right" width="100px">评估机构：</td>
						<td align="left"><input type="text" name="assessmentStructure" value="${pgjg}"
							style="width: 180px" class="form-control" placeholder="请输入评估机构"></td>
						<!-- <td align="right" width="70px">有效性:</td>
						<td align="left"><select name="availability" id="yxx" class="form-control" style="width: 180px">
							<option value="">请选择</option>
							<option value="1">有效</option>
							<option value="0">无效</option>
						</select></td> -->
						<td align="right" width="100px">抽取类型:</td>
						<td align="left"><select name="flag" id="flag" class="form-control" style="width: 180px">
							<option value="">请选择</option>
							<option value="1">必选</option>
							<option value="0">可选</option>
							<option value="-1">不可选</option>
						</select></td>
						<td align="right" width="100px">添加时间：</td>
						<td align="left"><input type="text" name="beginTime"
							id="beginTime" value="${beginTime}" style="width: 122px"
							onclick="SelectDate(this,'yyyy-MM-dd')" readonly="readonly"
							class="form-control">-<input type="text" name="endTime" 
							id="endTime" value="${endTime}" style="width: 122px"
							onclick="SelectDate(this,'yyyy-MM-dd')" readonly="readonly"
							class="form-control"></td>
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
								<th width="100px">操&nbsp;&nbsp;&nbsp;作</th>
								<th>专家姓名</th>
								<th>评估机构</th>
								<th>电话</th>
								<th>邮箱</th>
								<th>抽取类型</th>
	
							</tr>
						</thead>
						<c:forEach items="${data.content }" var="data">
							 <tr>
								<td> 
								   <a class="btn btn-primary  btn-xs" href="#" onclick="edit('${data.id}')">修改 </a>
								   <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="del('${data.id}')">删除 </a>
								</td>
								<td>${data.expertName }</td>
								<td>${data.assessmentStructure }</td>
								<td>${data.phoneNum }</td>
								<td>${data.expertEmail }</td>
								<td><c:choose>
										<c:when test="${data.flag==1 }">必选</c:when>
										<c:when test="${data.flag==0 }">可选</c:when>
										<c:when test="${data.flag==-1 }">不可选</c:when>
									</c:choose></td>
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