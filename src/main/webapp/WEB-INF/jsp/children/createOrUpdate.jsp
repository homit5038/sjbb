<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/xqxtags" prefix="xqx"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<link href="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="${ctx}/resources/zui/lib/datatable/zui.datatable.min.css" rel="stylesheet">
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
		var expertName = $("#childName").val();
		var phone = $("#parentPhoneNumber").val();
		if (expertName.length==0) {
			$("#gradeNameErr").html("请填写专家姓名");
			return false;
		}
		
		$("#childrenForm").submit();
	}  

	function importData(){
		var url = "${ctx}/employe/importExpert";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	
	function rese() {
		$(':input', '#childrenForm').not(':button, :submit, :reset, :hidden').val('')
		.removeAttr('checked').removeAttr('selected');
	}
	

</script>
</head>
<body>

	<form:form modelAttribute="children" id="childrenForm" method="post" enctype="multipart/form-data">

		<span id="gradeNameErr" class="err"></span>
		<div class="panel panel-info" style="width: 99%">
			<div class="panel-body">
				<table class="table table-condensed">
				
					<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>独生子女否:</td>
					<td >


						<form:input path="kindergarten.id"  type="hidden"/>

					<form:radiobutton path="onlyChild" value="1"   label="是"/>
                    <form:radiobutton path="onlyChild" value="0"  label="否"/>
 
							  
						</td>
					</tr>
					<tr height="40px">
					<td  align="right"><span class="err">*</span>健康档案是否在园:</td>
					<td >
					<form:radiobutton path="healthRecod" value="1"  label="是"/>
                    <form:radiobutton path="healthRecod" value="0"  label="否"/>
					 </td>
					</tr>
					<tr height="40px">
					<td  align="right"><span class="err">*</span>是否在园:</td>
					<td >
					<form:radiobutton path="status"  value="1"  label="是"/>
                    <form:radiobutton path="status" value="0"   label="否"/>
					</td>
					</tr>
				<tr height="40px">
					<td  align="right"><span class="err">*</span>头像 :
					<td >					
			<input type="file" id="photoDir" class="form-control"  name="photoDir" onchange="javascript:setImagePreview(this);" />
			<img src="${ctx}/children/${children.id}/findFile" width="100" border=0/>
			</td>
				</tr>
                   <tr height="40px">
					<td  align="right"><span class="err">*</span>性别:
					<td >
						<xqx:property name="sexTypes" fId="40601022"></xqx:property>
							<c:forEach items="${sexTypes}" var="ci">
								<form:radiobutton path="childSex"  value="${ci.id}"  label="${ci.fValue}"/>
							</c:forEach>			
					</td>
					</tr>
					<tr height="40px">
					<td  align="right"><span class="err">*</span>姓名:</td>
					<td >
					<form:input path="childName" id="childName"
								cssClass="form-control" style="width:320px" />
								<span id="gradeNameErr" class="err"></span></td>
					</tr>
                  <tr height="40px">
					<td  align="right"><span class="err">*</span>家长姓名:</td>
					<td >
					<form:input path="parentName" id="parentName"
								cssClass="form-control" style="width:320px" />
								<span id="parentNameErr" class="err"></span></td>
					</tr>
		   <tr height="40px">
					<td  align="right"><span class="err">*</span>年级:
					<td >
			   <form:select path="grade.id" id="childGradeId"   onchange="getClass()"
							cssClass="form-control" style="width:320px">
							<form:option value="-1">请选择</form:option>
							<c:forEach items="${grade}" var="grade">
								<form:option value="${grade.id}">${grade.gradename }</form:option>
							</c:forEach>
						</form:select>
	</td>
					</tr>	
					
                   <tr height="40px">
					<td  align="right"><span class="err">*</span>班级:
					<td >
			   <form:select path="classe.id" id="childClassId" onchange=""
							cssClass="form-control" style="width:320px">
							<form:option value="-1">请选择</form:option>
							<c:forEach items="${classe}" var="classe">
								<form:option value="${classe.id}">${classe.classesname }</form:option>
							</c:forEach>
						</form:select>
	</td>
					</tr>
		           
		           <tr height="40px">
					<td  align="right"><span class="err">*</span>学号:
					<td ><form:input path="childSchoolId"  id="childSchoolId"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
					
                   <tr height="40px">
					<td  align="right"><span class="err">*</span>生日:
					<td ><form:input path="childBirthday"  id="childBirthday"
								cssClass="form-control form-date"  type="" style="width:320px" /></td>
					</tr>	
                	
	
    	
				<tr height="40px">
					<td  align="right"><span class="err">*</span>贯籍:
					<td ><form:input path="birthPlace"  id="birthPlace"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>				
					
				<tr height="40px">
					<td  align="right"><span class="err">*</span>身份证号:
					<td ><form:input path="childIdcardNumber"  id="childIdcardNumber"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
			<tr height="40px">
					<td  align="right"><span class="err">*</span>家长身份证:
					<td ><form:input path="parentIdcardNumber"  id="parentIdcardNumber"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
					
					
				<tr height="40px">
					<td  align="right"><span class="err">*</span>家庭住址:
					<td ><form:input path="homeAddress"  id="homeAddress"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
	
			
				<tr height="40px">
					<td  align="right"><span class="err">*</span>手机号码:
					<td ><form:input path="parentPhoneNumber"  id="parentPhoneNumber"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>	
				<tr height="40px">
					<td  align="right"><span class="err">*</span> 入园时间:
					<td ><form:input path="childInGartenDate"   id="childInGartenDate"
								cssClass="form-control form-date" readonly="true" style="width:320px" /></td>
					</tr>	
				<tr height="40px">
					<td  align="right"><span class="err">*</span>考勤卡号:
					<td ><form:input path="attendanceIdcardNumber"  id="attendanceIdcardNumber"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>
				<tr height="40px">
					<td  align="right"><span class="err">*</span>生日:
					<td >
					
					
					<form:input path="childBirthday"  id="childBirthday"
								cssClass="form-control form-date" readonly="true" style="width:320px" />

								
								
								
								
								</td>
					</tr>

				<tr height="40px">
					<td  align="right"><span class="err">*</span>邮箱:
					<td ><form:input path="parentEmail"  id="parentEmail"
								cssClass="form-control" type="" style="width:320px" /></td>
					</tr>
					
				<tr height="40px">
					<td  align="right"><span class="err">*</span>备注:
					<td >
					
<form:textarea rows="3" cols="60" path="remarks" cssClass="form-control" />

			
						</td>
					</tr>
	
				<tr height="40px">
					<td  align="right"><span class="err">*</span>收费项:
					<td >
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					<form:input   type="text" path="chargConnection" id="chargConnection"	/>
					<table class="table table-bordered table-condensed" id="kinder-list-table">
				<c:choose>
					<c:when test="${num==0}">
						<tr>
							<td>没有符合该条件的查询结果......</td>
						</tr>
					</c:when>
					<c:otherwise>
						<thead>
							<tr>
							    
								<th>收费名称</th>
					            <th>收费金额</th>
	                            <th>收费周期</th>
	                            <th>小票等级</th>
							</tr>
						</thead>
						<c:forEach items="${Itemlist}" var="data">
						
 <tr data-id="${data.id}" <c:if test="${fn:contains(children.chargConnection,data.id)}"> data-checked="true"</c:if>>
								<td>
								<input  type="hidden"  class="charge-item" name="chargeitem" value=${data.id}>
								
								${data.itemName}</td>
							    <td>${data.amount}
							   <input type="hidden" class="charge-amount" value="${data.amount}">
							    </td>
								 <td>${data.periodic.displayName}</td>
								 <td>${data.ticketLevel.displayName}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>	
					
					
					
					

						</td>
					</tr>
					</table>
			</div>
			<table width="99%">
				<tr>
					<td align="center">
						<button type="button" onclick="submitForm();"
							class="btn btn-primary">保存</button>
						<button type="button" onclick="rese();" class="btn btn-default">重置</button>
						<button type="button" onclick="allv();" class="btn btn-default">选择</button>
					<!--  <button type="button" onclick="importData();" class="btn btn-default">导入</button> -->
					</td>
				</tr>
			</table>
		</div>
	</form:form>
<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/zui/lib/datatable/zui.datatable.js"></script>
<script src="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/zui/js/zui.minn.js"></script>

</body>
</html>



<script type="text/javascript">


$("#kinder-list-table").datatable({
		checkable : true,
		storage:false,
		checksChanged: function(e) {
	    	$("#chargConnection").val(e.checks.checks); 
	    }
	 
	}
);


function getClass(){
	var gradeId = document.getElementById("childGradeId").value;
	var classId = document.getElementById('childClassId');
	jQuery.ajax({
		   url: '/youer/classe/queryclassbygradeid',
		   data:'gradeId='+gradeId,
		   method:'post',
		   dataType:'json',
		   success:function(datas){
			   $("#childClassId").empty();
                var msg = datas;
                for(var i in msg.data){
                	classId.options.add(new Option(msg.data[i][1],msg.data[i][0]));//根据返回的数据新增option标签并且赋值 
				   }
		   }
	   });
}
	</script>