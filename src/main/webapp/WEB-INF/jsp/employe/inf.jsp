<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/xqxtags" prefix="xqx"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/head.jsp"></jsp:include>
<style type="text/css">

</style>
</head>
<body>

	<div class="layui-form" id="company-edit" lay-filter="company-edit" >
  <form:form modelAttribute="employe" id="employeForm"  >

	<table class="layui-table">
        <tr>
					<tr>
					<td width="220px" align="right"><span class="err">*</span>名称:</td>
					<td width="320px">
					
					<input name="employeName"  id="employeName"
							lay-verify="required" value="${employe.employeName}" placeholder="请输入" autocomplete="off"
                       Class="layui-input"/>
					
								
								</td>
					</tr>
				<tr>
					<td width="220px" align="right"><span class="err">*</span>卡号:
					<td width="320px">
	<input name="workAttendanceCardNumber" value="${employe.workAttendanceCardNumber}" lay-verify="required"  id="workAttendanceCardNumber"	class="layui-input"  />
</td>
					</tr>
		           
		           <tr>
					<td width="220px" align="right"><span class="err">*</span>员工工号:
					<td width="320px">
					
		<input name="jobNumber" id="jobNumber" value="${employe.jobNumber}"lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input"/>
					</td>
					</tr>	

				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>身份证号码:
					<td width="320px">
					<input name="idcardNumber"  id="idcardNumber" value="${employe.idcardNumber}" class="layui-input"  /></td>
					</tr>	
 <tr>
					<td width="220px" align="right"><span class="err">*</span>性别:
					<td width="320px">


						<xqx:property name="sexTypes" fId="40601022"></xqx:property>
							<c:forEach items="${sexTypes}" var="ci">
							<input type="radio"  value="${ci.id}" name="sexType" title="${ci.fValue}"
							   <c:if test="${ci.id==employe.sexType}">checked="checked"</c:if>>
							</c:forEach>					
					</td>
					</tr>


		
<tr>
					<td width="220px" align="right"><span class="err">*</span>学历:
					<td width="320px">
			
								
		
						<xqx:property name="academicQualification" fId="40601018"></xqx:property>
						<select id="academicQualification" name="academicQualification"  class="bs-select form-control" >		
							<option value="-1">请选择</option>
							<c:forEach items="${academicQualification}" var="academicQualification">
							<option value="${academicQualification.id}">${academicQualification.fValue}</option>
							</c:forEach>			
						</select>				
										
								
								
								</td>
					</tr>	
					
				<tr>
					<td width="220px" align="right"><span class="err">*</span>手机号码:
					<td width="320px">
					<input name="phoneNum"   id="phoneNum"value="${employe.phoneNum}"	class="layui-input"  />
								</td>
					</tr>
                   <tr>
					<td width="220px" align="right"><span class="err">*</span>民族:
					<td width="320px">
				
		<input name="nation" id="nation" value="${employe.nation}" lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input"/>			
								
	
				
								
								
								</td>
					</tr>	
                   	
                   
                   <tr>
                   	<td width="220px" align="right"><span class="err">*</span>毕业院校:
			
					<td width="220px" align="right">
		<input name="graduateSchool" id="employeName" value="${employe.graduateSchool}"	lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input"/>				
	
								
								</td>
					</tr>	
				<tr>
					<td width="220px" align="right"><span class="err">*</span>贯籍:
					<td width="320px">
	<input name="birthPlace" id="employeName" value="${employe.birthPlace}"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input"/>
				
					
					</td>
					</tr>				
					
				<tr>
					<td width="220px" align="right"><span class="err">*</span>政治面貌:
					<td width="320px">
<input name="politicalBackground" id="employeName" value="${employe.politicalBackground}"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input"/>
		
					
				
					</td>
					</tr>
	<tr >
					<td width="220px" align="right"><span class="err">*</span> 毕业时间:
					<td width="320px">
	<input name="beginToWorkDate" id="beginToWorkDate"value="${employe.beginToWorkDate}"
								 class="layui-input "   /></td>
	</tr>	
<tr>
					<td width="220px" align="right"><span class="err">*</span>进园时间:
					<td width="320px">
<input name="inGartenDate"  id="inGartenDate" value="${employe.inGartenDate}"
							 class="layui-input time2" readonly="true"  />		
					</td>
					</tr>
<tr>
					<td width="220px" align="right"><span class="err">*</span>生日:
					<td width="320px">
<input name="birthday" id="birthday"  value="${employe.birthday}"
								class="layui-input time3" /></td>
					</tr>
				<tr>
					<td width="220px" align="right"><span class="err">*</span>邮箱:
					<td width="320px">
	<input name="employeEmail"  id="employeEmail" value="${employe.employeEmail}"
								class="layui-input"  /></td>
				</tr>
					
				<tr>
					<td width="220px" align="right"><span class="err">*</span>住址:
					<td width="320px">
		<input name="dwellingPlace" id="dwellingPlace" value="${employe.dwellingPlace}"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input"/>
			
					
</td>
</tr>	
	
				<tr>
					<td width="220px" align="right"><span class="err">*</span>头像 :
					<td width="320px">

          <button type="button" class="layui-btn" id="upload">
                <i class="layui-icon">&#xe67c;</i>上传模板
            </button>
            <label id="uoloadName"></label>
						
			<%-- <input type="file" id="photoDir" class="layui-input"  name="photoDir" ay-verify="required" placeholder="请输入" autocomplete="off" onchange="javascript:setImagePreview(this);" />	
			<img src="${ctx}/employe/${employe.id}/findFile" width="100" border=0/>
			 --%>					
				</td>
					</tr>	
					
					</table>
		</form>
		
			 <input type="button" lay-submit lay-filter="file-submit" id="file-submit">
			
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
			
		</div>


</body>
</html>

<script src="${ctx}/resources/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx}/resources/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
	}).use(['index', 'laydate', 'table','upload'], function (){

    	$ = layui.$
        , form = layui.form
        , upload = layui.upload;
        var laydate = layui.laydate;
        
		
      	laydate.render({
        	elem: "#beginToWorkDate"
        	});
      	laydate.render({
      		elem: "#inGartenDate"
        	});
      	laydate.render({
      		elem: "#birthday"
        	});
        //$('.time').each(function(){
        //	laydate.render({
        //	elem: this
        //	});
        //})
          upload.render({
            elem: '#upload'
            , url: '/docTemp/addFile'
            , auto: false //选择文件后不自动上传
            , bindAction: '#file-submit' //指向一个按钮触发上传
            , field: 'printFile'
            , accept: 'file'
            , choose: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#uoloadName").text(file.name);
                });
  
            }
            , data: {
                id: function () {
                    return $('#docTempId').val();
                }
            }
        });
        
        // laydate.render({
         	//elem: '.time'
         
        // 	// format: 'yyyy-MM-dd HH:mm:ss',
        // 	done: function (value, date) {
        // 		var startDate = $("#managePeriodStart").val();
        // 		if (startDate != null && startDate !== "") {
        // 			var timeStart = new Date(startDate).getTime();
        // 			var timeEnd = new Date(date.year, date.month - 1, date.date).getTime();
        // 			if (timeEnd < timeStart) {
        // 				layer.msg("时间选择错误是：结束时间不能小于开始时间", {icon: 2, time: 3000});
        // 				$("#managePeriodEnd").val("");
        // 			}
        // 		}
        // 		// console.log(value); //得到日期生成的值，如：2017-08-18
        // 		// console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
        // 	}
       // });
    })
</script>