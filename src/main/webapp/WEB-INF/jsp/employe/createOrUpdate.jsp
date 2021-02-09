<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/xqxtags" prefix="xqx"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<link href="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<script src="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
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
	
	
		$(".form-date").datetimepicker(
				{
				    language:  "zh-CN",
				    weekStart: 1,
				    todayBtn:  1,
				    autoclose: 1,
				    todayHighlight: 1,
				    startView: 2,
				    minView: 2,
				    forceParse: 0,
				    format: "yyyy-mm-dd"
				});
	
	
	});
    
	function ismail(str){
	    var reg= /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	    return reg.test(str);
	}
	
	function submitForm() {
		var expertName = $("#employeName").val();
		var phone = $("#phoneNum").val();
		if (expertName.length==0) {
			$("#gradeNameErr").html("请填写专家姓名");
			return false;
		}
		
		$("#employeForm").submit();
	}  

	function importData(){
		var url = "${ctx}/employe/importExpert";
		$("#myModalLabel").html("编辑学生信息");
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	
	function rese() {
		$(':input', '#gradeForm').not(':button, :submit, :reset, :hidden').val('')
		.removeAttr('checked').removeAttr('selected');
	}
</script>
</head>
<body>
	
	<form:form modelAttribute="employe" id="employeForm" method="post" enctype="multipart/form-data">

		<div class="panel panel-info" style="width: 99%">
			<div class="panel-body">
				<table class="table table-condensed">
					<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>教师名称:${advLinks}</td>
					<td width="320px">
					<form:input path="employeName" id="employeName"
								cssClass="form-control" style="width:320px" />
								<span id="gradeNameErr" class="err"></span></td>
					</tr>
		           
		           <tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>员工工号:
					<td width="320px"><form:input path="jobNumber"  id="jobNumber"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
					
                   <tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>民族:
					<td width="320px"><form:input path="nation"  id="nation"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
                   <tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>学历:
					<td width="320px">
			
								
		
						<xqx:property name="academicQualification" fId="40601018"></xqx:property>
						<form:select id="academicQualification" path="academicQualification"  cssClass="bs-select form-control" >		
							<form:option value="-1">请选择</form:option>
							<c:forEach items="${academicQualification}" var="academicQualification">
							<form:option value="${academicQualification.id}">${academicQualification.fValue}</form:option>
							</c:forEach>			
						</form:select>				
										
								
								
								</td>
					</tr>	
                   <tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>性别:
					<td width="320px">
					
				
		
				

						<xqx:property name="sexTypes" fId="40601022"></xqx:property>
						<%-- <form:select id="sexType" path="sexType"  cssClass="bs-select form-control" onchange="changeThi(0);">		
							<form:option value="-1">请选择</form:option> --%>
							<c:forEach items="${sexTypes}" var="ci">
							   <label><input type="radio"  value="${ci.id}" name="sexType"
							   <c:if test="${ci.id==employe.sexType}">checked="checked"</c:if>
							   
							   >${ci.fValue}</label>
						<%-- 	<form:option value="${ci.id}">${ci.fValue}</form:option> --%>
							</c:forEach>			
					<%-- 	</form:select>	 --%>			
										
											
										
										
								
								</td>
					</tr>	
                   <tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>毕业院校:
					<td width="320px"><form:input path="graduateSchool"  id="graduateSchool"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>贯籍:
					<td width="320px"><form:input path="birthPlace"  id="birthPlace"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>				
					
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>政治面貌:
					<td width="320px"><form:input path="politicalBackground"  id="politicalBackground"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
					
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>住址:
					<td width="320px"><form:input path="dwellingPlace"  id="dwellingPlace"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>身份证号码:
					<td width="320px"><form:input path="idcardNumber"  id="idcardNumber"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>头像 :
					<td width="320px">

								
								
			<input type="file" id="photoDir" class="form-control"  name="photoDir" onchange="javascript:setImagePreview(this);" />
								
								
								
								
				
			<img src="${ctx}/employe/${employe.id}/findFile" width="100" border=0/>
								
								</td>
					</tr>	
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>手机号码:
					<td width="320px"><form:input path="phoneNum"  id="phoneNum"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span> 毕业时间:
					<td width="320px"><form:input path="beginToWorkDate"   id="beginToWorkDate"
								cssClass="form-control form-date" readonly="true" style="width:320px" /></td>
					</tr>	
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>考勤卡号:
					<td width="320px"><form:input path="workAttendanceCardNumber"  id="workAttendanceCardNumber"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>进园时间:
					<td width="320px">
					
					
					<form:input path="inGartenDate" id="inGartenDate"
								cssClass="form-control form-date" readonly="true" style="width:320px" />
								</td>
					</tr>
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>生日:
					<td width="320px"><form:input path="birthday"  id="birthday"
								cssClass="form-control form-date"  readonly="true" style="width:320px" /></td>
					</tr>
				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>邮箱:
					<td width="320px"><form:input path="employeEmail"  id="employeEmail"
								cssClass="form-control" type="" style="width:320px" /></td>
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