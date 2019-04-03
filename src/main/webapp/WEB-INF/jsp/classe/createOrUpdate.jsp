<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<style type="text/css">

</style>
<script type="text/javascript">
	$(function() {
		var msg = "${msg}";
		if(msg=="exist"){
			$("#expertNameErr").html("该专家已在库中存在");
		}
		if(msg=="editOk"){
			parent.refresh();
		}
	    var availableTags = ["大理鹏程房地产土地评估咨询有限公司","大理鹏程房地产土地评估咨询有限公司",
	                         "红河恒信房地产土地评估有限公司","昆明诚跃房地产评估有限责任公司",
	                         "昆明滇信房地产评估有限公司","昆明决策房地产评估有限公司",
	                         "昆明名杰信房地产土地评估咨询有限公司","昆明三阳开泰房地产土地资产评估有限公司",
	                         "普洱诚宇房地产价值评估有限责任公司","曲靖恒信房地产估价经纪有限公司",
	                         "文山同心联宜房地产评估有限公司","西双版纳中兴房地产评估有限公司",
	                         "云南帮克房地产评估有限公司","云南鼎立房地产土地评估有限责任公司",
	                         "云南广地房地产土地评估勘测有限公司","云南颢杨房地产估价有限公司",
	                         "云南弘力房地产土地评估有限公司","云南洪业房地产评估有限责任公司",
	                         "云南精正房地产土地评估有限公司","云南瑞尔房地产土地资产评估有限公司",
	                         "云南耀迪房地产土地资产评估有限公司","云南银河房地产评估有限责任公司",
	                         "云南银信房地产价格评估有限公司"];
	    $( "#pgjg" ).autocomplete({
	      source: availableTags
	    });
	});
    
	function ismail(str){
	    var reg= /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	    return reg.test(str);
	}
	
	function submitForm() {
		var expertName = $("#expertName").val();
		var phone = $("#phone").val();
		var email = $("#email").val();
		var pgjg = $("#pgjg").val();
		if (expertName.length==0) {
			$("#expertNameErr").html("请填写专家姓名");
			return false;
		}
	
		$("#expertForm").submit();

	}  

	function importData(){
		var url = "${ctx}/classe/importExpert";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	
	function rese() {
		$(':input', '#expertForm').not(':button, :submit, :reset, :hidden').val('')
		.removeAttr('checked').removeAttr('selected');
	}
</script>
</head>
<body>
	<c:if test="${msg!='edit' }"><pre>班级》新增班级&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" onclick="importData();" class="btn btn-link">导入</button></pre></c:if>
	<c:if test="${msg=='edit' }"><pre>班级》修改班级信息</pre></c:if>
	<form:form modelAttribute="classes" id="expertForm" method="post">
		<div class="panel panel-info" style="width: 99%">
			<div class="panel-body">
				<table class="table table-condensed">
					<tr height="40px">
						<td width="220px" align="right"><span class="err">*</span>班级名称:</td>
						<td width="320px"><form:input path="classesname" id="expertName"
								cssClass="form-control" style="width:320px" />
								<span id="expertNameErr" class="err"></span></td>
					</tr>
					<tr height="40px">
						<td width="220px" align="right"><span class="err">*</span>年级:</td>
						<td width="320px">
					
						
		
						
			            <form:select path="grade.id" id="type" onchange=""
							cssClass="form-control" style="width:320px">
							<form:option value="-1">请选择</form:option>
							<c:forEach items="${grade}" var="grade">
								<form:option value="${grade.id}">${grade.gradename }</form:option>
							</c:forEach>
						</form:select>
						
						
						
						
<%-- 
								    <c:forEach items="${grade}" var="grade">
								
								        ${grade.gradename}
								    </c:forEach>
								 --%>
						
						
						
						
						
						
						
						
						
						
						
						
								<span id="expertNameErr" class="err"></span></td>
					</tr>
				</table>
			</div>
			<table width="99%">
				<tr>
					<td align="center">
						<button type="button" onclick="submitForm();"
							class="btn btn-primary">保存</button>
						<button type="button" onclick="rese();" class="btn btn-default">重置</button>
						<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" onclick="importData();" class="btn btn-default">导入</button> -->
					</td>
				</tr>
			</table>
		</div>
	</form:form>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
</body>
</html>