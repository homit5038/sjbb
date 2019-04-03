<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">

	function submitForm() {
		jQuery('#main').showLoading();
		$("#logForm").submit();
	}

	function rese() {
		$(':input', '#log').not(':button, :submit, :reset, :hidden').val(
				'').removeAttr('checked').removeAttr('selected');
	}
</script>
</head>
<body id="main">
	<pre>日志中心>系统日志</pre>
	<div class="panel_bs panel_bs-default" style="width: 99%">
		<div class="panel_bs-heading">
			<div class="text-muted bootstrap-admin-box-title">
				<form:form modelAttribute="SystemLog" method="post" id="logForm" action="${ctx}/log/findAllLog">
					<table>
						<tr height="40px">
							<td align="right" width="100px">创建时间：</td>
							<td align="left">
								<table>
									<tr>
										<td><input type="text" name="beginTime" id="beginTime"
											value="${beginTime}"
											onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')"
											readonly="readonly" class="form-control"></td>
										<td>-</td>
										<td><input type="text" name="endTime" id="endTime"
											value="${endTime}" readonly="readonly"
											onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')"
											class="form-control"></td>
									</tr>
								</table>
							</td>
							<td align="right" width="80px">操作人：</td>
							<td align="left"><form:input path="userName"
									style="width: 180px" class="form-control" placeholder="请输入操作人" />
							</td>
							<td align="right" width="80px"></td>
							<td align="right">
								<button class="btn btn-primary" type="button"
									onclick="submitForm()">查询</button>&nbsp;&nbsp;&nbsp;<input
								type="button" onclick="rese()" value="清空"
								class="btn btn-default">
							</td>
							<td align="left"></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<div class="panel_bs-body">
			<div class="tablelistdiv">
				<table class="table table-bordered table-condensed" style="margin-left: 10px;">
					<c:choose>
						<c:when test="${num==0 }">
							<td width="100%"><c:if test="${method=='get'}">暂无系统日志记录</c:if>
								<c:if test="${method=='post'}">没有符合该查询条件的系统日志记录</c:if></td>
						</c:when>
						<c:otherwise>
							<c:forEach items="${logs.content}" var="log">
								<tr>
									<td width="100%"><fmt:formatDate value='${log.operTime }' pattern='yyyy-MM-dd HH:mm:ss' />:
										用户：${log.userName }<c:if test="${!empty log.operTable }">对表：${log.operTable }</c:if>
										进行了：${log.operContent }
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</table>
				<c:if test="${num>0}">
					<table width="98%" style="margin-bottom: 8px">
						<tr>
							<td width="100%"><c:if test="${method=='get' }">
								<form id="list" action="${ctx}/log/findAllLog"
									method="get">
									<input type="hidden" name="size" value="${size}"> <input
										type="hidden" name="page" value="${page }">
								</form>
								<tags:pagination page="${logs }" formId="list" />
							</c:if> <c:if test="${method=='post' }">
								<form id="search" action="${ctx}/log/findAllLog"
									method="post">
									<input type="hidden" name="size" value="${size}"> <input
										type="hidden" name="page" value="${page }">
									<input type="hidden" name="beginTime" value="${beginTime}">
									<input type="hidden" name="endTime" value="${endTime}">
								</form>
								<tags:pagination page="${logs }" formId="search" />
							</c:if></td>
						</tr>
					</table>
				</c:if>
			</div>
		</div>
	</div>

</body>
</html>