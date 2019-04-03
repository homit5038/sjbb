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
		var batchStatus = "${map['batchStatus']}";
		if(batchStatus) $("#batchStatus").val(batchStatus);
		$("#signImgModal .close,#closesignImgModal").bind("click",function(){
			refresh();
		});
	});
	//删除信息
	function delBatch(Id) {
		if (confirm('确定要删除记录吗?')) {
			jQuery('#main').showLoading();
			$.post("${ctx}/batch/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("操作成功");
					window.location = ("${ctx}/batch/batchlist");
				} else {
					//
				}
			});
		}
	}

	function submitForm() {
		jQuery('#main').showLoading();
		$("#batchForm").submit();
	}

	function rese() {
		$(':input', '#batchForm').not(':button, :submit, :reset, :hidden').val(
				'').removeAttr('checked').removeAttr('selected');
		$(':input', '#search').not(':button, :submit, :reset, :hidden').val('')
				.removeAttr('checked').removeAttr('selected');
	}

	// 编辑批次
	function editbatch(id){
		var url = "${ctx}/batch/" + id + "/edit";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}

	function extract(id){
		var url = "${ctx}/extract/"+id+"/init";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/batch/batchlist");
	}
</script>
</head>
<body id="main">
	<pre>批次管理>批次维护</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			<form id="batchForm" action="${ctx}/batch/search" method="post">
			<input type="hidden" name="size" value="${size}"> 
			<input type="hidden" name="page" value="${page}">
				<table>
					<tr height="40px">
						<!-- <td align="right" width="80px">锁定状态：</td>
						<td align="left"><select name="availability" id="availability"
							style="width: 120px" class="form-control">
								<option value="all">全部</option>
								<option value="1" selected="selected">有效</option>
								<option value="0">无效</option>
						</select></td> -->
						<td align="right" width="120px">批次名称：</td>
						<td align="left"><input type="text" name="batchName" value="${map['batchName']}"
							style="width: 180px" class="form-control" placeholder="请输入批次名"></td>
						<td align="right" width="120px">批次号：</td>
						<td align="left"><input type="text" name="batchId" value="${map['batchId']}"
							style="width: 180px" class="form-control" placeholder="请输入批次号"></td>
						<td align="right" width="120px">专家姓名：</td>
						<td align="left"><input type="text" name="expertName" value="${map['expertName']}"
							style="width: 180px" class="form-control" placeholder="请输入专家姓名"></td>
						<td align="right" width="120px">专家所属机构：</td>
						<td align="left"><input type="text" name="pgjg" value="${map['pgjg']}"
							style="width: 180px" class="form-control" placeholder="请输入机构"></td>
					</tr>
					<tr height="40px">
						<td align="right" width="80px">抽取状态：</td>
						<td align="left"><select name="batchStatus" id="batchStatus"
							style="width: 120px" class="form-control">
								<option value="all">全部</option>
								<option value="0">待抽取</option>
								<option value="1">已抽取未确定</option>
								<option value="2">已确定</option>
						</select></td>
						<td align="right">添加时间：</td>
						<td align="left" colspan="4">
							<table>
								<tr>
									<td><input type="text" name="beginTime" id="beginTime"
										value="${map['beginTime']}"
										onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')"
										readonly="readonly" class="form-control"></td>
									<td>-</td>
									<td><input type="text" name="endTime" id="endTime"
										value="${map['endTime']}" readonly="readonly"
										onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')"
										class="form-control"></td>
								</tr>
							</table>
						</td>
						<td align="left">
						 <button class="btn btn-primary" type="button"  id="submitBtn" onclick="submitForm()">查询</button>
						 <input type="button" onclick="rese()" value="清空" class="btn btn-default">
						 <input type="button" onclick="refresh()" value="刷新" class="btn btn-default"></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-body">
			<table class="table table-bordered table-condensed">
				<c:choose>
					<c:when test="${map['total']==0}">
						<tr>
							<td>没有符合该条件的查询结果......</td>
						</tr>
					</c:when>
					<c:otherwise>
						<thead>
							<tr>
							    <th width="60px">序号</th>
							    <th>批次号</th>
								<th>批次名称</th>
								<th>抽取人数</th>
								<th>抽取状态</th>
								<th>有效性</th>
								<th>最后操作时间</th>
								<th>备注</th>
								<th>操&nbsp;&nbsp;&nbsp;作</th>
							</tr>
						</thead>
						<c:set var="index" value="0"/>
						<c:forEach items="${map['data'] }" var="data">
						     <c:set var="index" value="${index + 1 }"/>
							 <tr>
							    <td>${index }</td>
							    <td>${data.id }</td>
								<td>${data.batchName }</td>
								<td>${data.extractPepleNum }</td>
								<td><c:choose>
										<c:when test="${data.batchStatus eq 2 }"><p style="color: red;">已确定</p></c:when>
										<c:when test="${data.batchStatus eq 1 }"><p style="color: blue;">已抽取未确定</p></c:when>
										<c:when test="${data.batchStatus eq 0 }"><p style="color: gray;">待抽取</p></c:when>
										<c:otherwise>无效状态</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${data.availability eq 1}"><p style="color: red;">有效</p></c:when>
										<c:otherwise><p style="color: gray;">无效</p></c:otherwise>
									</c:choose></td>
								<td><fmt:formatDate value="${data.updateLastTime }"
										pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</td>
								<td>${data.remark }</td>
							    <td>
  								   <c:if test="${data.availability eq 1}">
  								       <c:if test="${data.batchStatus eq -1}">
  								         <a class="btn btn-primary  btn-xs" href="#" onclick="editbatch('${data.id}')">修改 </a>
  								       </c:if>
  								        <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="delBatch('${data.id}')">删除 </a>
  								        <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="extract('${data.id}')">抽取结果 </a>
								   </c:if>
								   <c:if test="${data.availability ne 1 }">
								     <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="extract('${data.id}')">查看 </a>
								   </c:if>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</div>
	<c:if test="${map['total']>0}">
		<tags:paginationMap map="${map}" formId="batchForm"></tags:paginationMap>
	</c:if>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
</body>
</html>