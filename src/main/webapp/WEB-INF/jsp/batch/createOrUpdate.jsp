<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">
	var validateExtractPepleNum = false;
	$(function() {
		if("${result}" == "true"){
			$("#updateMsg").html("操作成功");
			parent.refresh();
		}
		var extractStatus = "${batch.batchStatus}";
		if(extractStatus == 2 || extractStatus == 1){
			$("#extractPepleNum").attr("disabled","disabled");
		}
		
		var extractPepleNum = "${batch.extractPepleNum}";
		if(extractPepleNum){
			$("#extractPepleNum").val(extractPepleNum);
		} else {
			$("#extractPepleNum").val(3);
		}
		
		$('#extractPepleNum').blur(function() {
			var extractPepleNum = $(this).val();
			if ( extractPepleNum && /^\+?[1-9][0-9]?$/.test(extractPepleNum)) {
				$.ajax({
					type : "post",
					url : "${ctx}/extract/validateExtractPepleNum",
					data : {
						extractPepleNum : extractPepleNum
					},
					success : function(data) {
						if (data.msg == "OK") {
							validateExtractPepleNum = false;
							$("#loginNameErr").html("");
						} else {
							validateExtractPepleNum = true;
							$("#extractPepleNumErr").html("抽取的专家数比库存中的专家数多，专家总数："+data.count+"，请重新输入");
						}
					}
				});
			}
		});
	});

	function submitForm() {
	    var batchName = $("#batchName").val();
		if ($.trim(batchName)) {
			$("#batchNameErr").html("");
		} else {
			if(confirm("新增批次名称为空，默认为登录名加当前时间，确定添加吗？")){
				$("#batchName").val("");
			} else{
				$("#batchNameErr").html("新增批次名称为空，默认为登录名加当前时间");
				return false;
			}
		}
		var extractPepleNum = $("#extractPepleNum").val();
		if (extractPepleNum == null || extractPepleNum == "") {
			$("#extractPepleNumErr").html("抽取人数不能为空");
			return false;
		} else {
			if(!/^\+?[1-9][0-9]?$/.test(extractPepleNum)){
				$("#extractPepleNumErr").html("抽取人数须为1到99");
				return false;
			}
			$("#extractPepleNumErr").html("");
		}
		if(!validateExtractPepleNum){
			$("#batchForm").submit();
		}
	}

	function rese() {
		$('#batchName').val('');
		$('#remark').val('');
	}
	
	function extract(id){
		window.location = "${ctx}/extract/"+id+"/init";
	}
</script>
</head>
<body>
	<pre>批次管理><c:if test="${!empty batch.id }">修改</c:if><c:if test="${empty batch.id }">新建</c:if>批次</pre>
	<form:form modelAttribute="batch" id="batchForm" method="post">
		<div class="panel panel-info" style="width: 99%">
			<div class="panel-heading">批次信息
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="updateMsg" class="err"></span>
			<input type="hidden" name="availability" id="availability" value="${batch.availability }">
			</div>
			<div class="panel-body">
				<table class="table table-bordered table-condensed">
					<tr height="40px">
						<td width="35%" align="right"><span class="err">*</span>
							批次名称:</td>
						<td width="320px"><form:input path="batchName"
								cssClass="form-control" style="width:320px" /></td>
						<td align="left">&nbsp;&nbsp;<span id="batchNameErr" class="err"></span></td>
					</tr>
					<tr height="40px">
						<td align="right"><span class="err">*</span>
							抽取专家人数:</td>
						<td width="320px"><form:input path="extractPepleNum"
								cssClass="form-control" style="width:320px" /></td>
						<td align="left">&nbsp;&nbsp;<span id="extractPepleNumErr" class="err"></span></td>
					</tr>
					<tr height="40px">
						<td align="right"><!-- <span class="err">*</span> -->
							备注:</td>
						<td width="320px"><form:textarea rows="3" cols="60" path="remark"
								cssClass="form-control" /></td>
						<td align="left">&nbsp;&nbsp;<span id="extractPepleNumErr" class="err"></span></td>
					</tr>
				</table>
			</div>
			<table width="80%">
				<tr>
					<td align="center">
						<button type="button" onclick="submitForm();"
							class="btn btn-primary">提 交</button>
						<button type="button" onclick="rese();" class="btn btn-default">清空</button>
					</td>
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>