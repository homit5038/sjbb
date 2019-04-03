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
		var availability = "${availability}";
		if(availability) $("#availability").val(availability);
	});
	//锁定用户
	function lockedUser(id, opeater) {
		if (confirm("确定要" + opeater + "用户吗？")) {
			jQuery('#main').showLoading();
			var oper = (opeater == "解锁" ? 1 : 2);
			$.post("${ctx}/admin/user/lockedUser", {
				id : id,
				oper : oper
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data == "ok") {
					alert("操作成功");
					window.location = ("${ctx}/admin/user/list");
				} else alert("操作失败");
			});
		}
	}

	//删除信息
	function updateAvail(Id, availability) {
		var msg = "确定要删除记录吗?";
		if(availability == 1){
			msg = "确定要恢复用户有效吗?";
		}
		if (confirm(msg)) {
			jQuery('#main').showLoading();
			$.post("${ctx}/admin/user/" + Id + "/updateAvail", {
				availability : availability
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("操作成功");
					window.location = ("${ctx}/admin/user/list");
				} else {
					//
				}
			});
		}
	}

	function submitForm() {
		jQuery('#main').showLoading();
		$("#userForm #size").val(10);
		$("#userForm #page").val(0);
		$("#userForm").submit();
	}

	function rese() {
		$(':input', '#userForm').not(':button, :submit, :reset, :hidden').val(
				'').removeAttr('checked').removeAttr('selected');
		$(':input', '#search').not(':button, :submit, :reset, :hidden').val('')
				.removeAttr('checked').removeAttr('selected');
	}

	function signImg(id) {
		var url = "${ctx}/admin/user/" + id + "/signImg";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	// 编辑用户
	function editUser(id){
		var url = "${ctx}/user/" + id + "/edit";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	//分配角色
	function grantRole(id){
		var url = "${ctx}/admin/user/" + id + "/userRoleManage";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/admin/user/list");
	}
</script>
</head>
<body id="main">
	<pre>用户管理>用户维护</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			<form id="userForm" action="${ctx}/admin/user/search"
				method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="80px">锁定状态：</td>
						<td align="left"><select name="availability" id="availability"
							style="width: 120px" class="form-control">
								<option value="all" selected="selected">全部</option>
								<option value="2">已锁定</option>
								<option value="1">有效</option>
						</select></td>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr height="40px">
						<td align="right">添加时间：</td>
						<td align="left" colspan="4">
							<table>
								<tr>
									<td><input type="text" name="beginTime" id="beginTime"
										value="${beginTime }"
										onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')"
										readonly="readonly" class="form-control"></td>
									<td>-</td>
									<td><input type="text" name="endTime" id="endTime"
										value="${endTime }" readonly="readonly"
										onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')"
										class="form-control"></td>
								</tr>
							</table>
						</td>
						<td align="right" width="70px">登录名：</td>
						<td align="left"><input type="text" name="loginName" value="${loginName}"
							style="width: 180px" class="form-control" placeholder="请输入登录名"></td>
						<td align="right" width="70px">姓名：</td>
						<td align="left">
							<div class="col-lg-6" style="width: 20%">
								<div class="input-group">
									<input type="text" name="name" value="${name}" style="width: 180px"
										class="form-control" placeholder="请输入姓名"> <span
										class="input-group-btn">
										<button class="btn btn-primary" type="button"
											 id="submitBtn" onclick="submitForm()">查询</button>
									</span>
								</div>
							</div>
						</td>
						<td align="left"><input type="button" onclick="rese()"
							value="清空" class="btn btn-default"></td>
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
							    <th width="60px">序号</th>
								<th>登录名</th>
								<th>姓名</th>
								<th>部门</th>
								<th>有效性</th>
								<th>修改时间</th>
								<th>操&nbsp;&nbsp;&nbsp;作</th>
							</tr>
						</thead>
						<c:set var="index" value="0"/>
						<c:forEach items="${users.content }" var="user">
						     <c:set var="index" value="${index + 1 }"/>
							 <tr>
							    <td>${index }</td>
								<td>${user.loginName }</td>
								<td>${user.name }</td>
								<td>${user.deptName }+${user.roles }</td>
								<td><c:choose>
										<c:when test="${user.availability eq 1 }"><p style="color: red;">有效</p></c:when>
										<c:when test="${user.availability eq 2 }"><p style="color: blue;">已锁定</p></c:when>
										<c:otherwise><p style="color: gray;">无效</p></c:otherwise>
									</c:choose></td>
								<td><fmt:formatDate value="${user.updateLastTime }"
										pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</td>
							    <td><c:choose>
										<c:when test="${user.availability ne 0 }">
											<a class="btn btn-primary  btn-xs" href="#"
												onclick="editUser('${user.id}')">修改 </a>
											<a class="btn btn-primary  btn-xs" href="javascript:void(0)"
												onclick="updateAvail('${user.id}','0')">删除 </a>
											<c:choose>
												<c:when test="${user.availability ne 2 }">
													<a class="btn btn-primary  btn-xs"
														href="javascript:lockedUser(${user.id },'锁定')">锁定</a>
												</c:when>
												<c:otherwise>
													<a class="btn btn-primary  btn-xs"
														href="javascript:lockedUser(${user.id },'解锁')">解锁</a>
												</c:otherwise>
											</c:choose>
											<a class="btn btn-primary  btn-xs"
												href="javascript:grantRole(${user.id })">分配角色 </a>
										</c:when>
										<c:otherwise>
											<a class="btn btn-primary  btn-xs" href="javascript:void(0)"
												onclick="updateAvail('${user.id}','1')">恢复有效 </a>
										</c:otherwise>
									</c:choose>
								 </td>

							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<c:if test="${num>0}">
				 <tags:pagination page="${users }" formId="userForm" />
			</c:if>
		</div>
	</div>
    <jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
</body>
</html>