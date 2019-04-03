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

	<div class="layui-form" id="company-edit" lay-filter="company-edit">
  
<form:form modelAttribute="employe" enctype="multipart/form-data" id="up_form" > 
   
	<table class="layui-table">
        <tr>
					<tr>
					<td width="220px" align="right"><span class="err">*</span>教师名称:</td>
					<td width="320px">
					
					<form:input path="employeName" id="employeName"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       cssClass="layui-input"/>
					
								
								</td>
					</tr>
				<tr>
					<td width="220px" align="right"><span class="err">*</span>考勤卡号:
					<td width="320px"><form:input path="workAttendanceCardNumber"  id="workAttendanceCardNumber"
								cssClass="layui-input" type=""  /></td>
					</tr>
		           
		           <tr>
					<td width="220px" align="right"><span class="err">*</span>员工工号:
					<td width="320px">
					
		<form:input path="jobNumber" id="employeName"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       cssClass="layui-input"/>
					</td>
					</tr>	

				<tr height="40px">
					<td width="220px" align="right"><span class="err">*</span>身份证号码:
					<td width="320px">
					<form:input path="idcardNumber"  id="idcardNumber"
								cssClass="layui-input"  /></td>
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
						<form:select id="academicQualification" path="academicQualification"  cssClass="bs-select form-control" >		
							<form:option value="-1">请选择</form:option>
							<c:forEach items="${academicQualification}" var="academicQualification">
							<form:option value="${academicQualification.id}">${academicQualification.fValue}</form:option>
							</c:forEach>			
						</form:select>				
										
								
								
								</td>
					</tr>	
					
				<tr>
					<td width="220px" align="right"><span class="err">*</span>手机号码:
					<td width="320px"><form:input path="phoneNum"  id="phoneNum"
								cssClass="layui-input" type=""  /></td>
					</tr>
                   <tr>
					<td width="220px" align="right"><span class="err">*</span>民族:
					<td width="320px">
				
		<form:input path="nation" id="employeName"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       cssClass="layui-input"/>			
								
	
				
								
								
								</td>
					</tr>	
                   	
                   
                   <tr>
                   	<td width="220px" align="right"><span class="err">*</span>毕业院校:
			
					<td width="220px" align="right">
		<form:input path="graduateSchool" id="employeName"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       cssClass="layui-input"/>				
	
								
								</td>
					</tr>	
				<tr>
					<td width="220px" align="right"><span class="err">*</span>贯籍:
					<td width="320px">
							<form:input path="birthPlace" id="employeName"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       cssClass="layui-input"/>
				
					
					</td>
					</tr>				
					
				<tr>
					<td width="220px" align="right"><span class="err">*</span>政治面貌:
					<td width="320px">
		<form:input path="politicalBackground" id="employeName"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       cssClass="layui-input"/>
		
					
				
					</td>
					</tr>
	<tr >
					<td width="220px" align="right"><span class="err">*</span> 毕业时间:
					<td width="320px"><form:input path="beginToWorkDate" id="beginToWorkDate"
								 cssClass="layui-input "  readonly="true"  /></td>
	</tr>	
<tr>
					<td width="220px" align="right"><span class="err">*</span>进园时间:
					<td width="320px">
					<form:input path="inGartenDate"  id="inGartenDate"
							 cssClass="layui-input time2" readonly="true"  />		
					</td>
					</tr>
<tr>
					<td width="220px" align="right"><span class="err">*</span>生日:
					<td width="320px">
<form:input path="birthday" id="birthday" 
								cssClass="layui-input time3"  readonly="true"  /></td>
					</tr>
				<tr>
					<td width="220px" align="right"><span class="err">*</span>邮箱:
					<td width="320px"><form:input path="employeEmail"  id="employeEmail"
								cssClass="layui-input" type=""  /></td>
				</tr>
					
				<tr>
					<td width="220px" align="right"><span class="err">*</span>住址:
					<td width="320px">
		<form:input path="dwellingPlace" id="employeName"
							lay-verify="required" placeholder="请输入" autocomplete="off"
                       cssClass="layui-input"/>
			
					
</td>
</tr>	

<!-- <tr>
					<td width="220px" align="right"><span class="err">*</span>头像 :
					<td width="320px">
 <div class="layui-upload uploadbuchong" id="fileshow" style="border:1px solid #00F7DE; border-radius: 2px;">
                
        <button class="layui-btn layui-btn-normal" type="button">选择文件</button>
         <input type="text" id="docTempId" name="photoDir" value="">      
    </div>
</td>
</tr>	 -->
		
<tr>
<td width="220px" align="right"><span class="err">*</span>头像 :
<td width="320px">

<button type="button" class="layui-btn" id="upload1">上传图片</button> 
<input type="hidden" id="img_url" name="photoDir" value=""/> 
 <div class="layui-upload-list"> 
 <img src="${ctx}/employe/${employe.id}/findFile" width="100" border=0/>
 <img class="layui-upload-img"  width="100px" height="80px" id="demo1"/> 
 <p id="demoText"></p> 
 </div> 
 </div>
 
</td>
</tr>	
	
	
				<tr>
					<td width="220px" align="right"><span class="err">*</span>头像 :
					<td width="320px">

          <button type="button" class="layui-btn" id="upload">
                <i class="layui-icon">&#xe67c;</i>上传模板
            </button>
            <label id="uoloadName"></label>
						
			<%-- <input type="file" id="photoDir" cssClass="layui-input"  name="photoDir" ay-verify="required" placeholder="请输入" autocomplete="off" onchange="javascript:setImagePreview(this);" />	
			<img src="${ctx}/employe/${employe.id}/findFile" width="100" border=0/>
			 --%>					
				</td>
					</tr>	
					
					</table>
		</form:form>
		
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
    }).use(['form', 'laydate','upload'], function () {
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
/*         upload.render({
            elem: '#upload'
            , url: '${ctx}/employe/addFile'
            , accept: 'file'
            , auto: false
            // , bindAction: '#upfile' //关闭的上传按钮   html中此id所在元素也被注释
            ,multiple: true
            , done: function (res) {
                alert("上传成功");
            }
        }); */

        
        var uploadInst = upload.render({ 
        	elem: '#upload1' //绑定元素 
        	,url: '${ctx}/employe/addFile'
        	,before: function(obj){ //预读本地文件示例，不支持ie8 
        		
        		obj.preview(function(index, file, result){ 
        			$('#demo1').attr('src', result); //图片链接（base64） 
        			}); 
        	} 
        ,done: function(res){ 
        	//如果上传失败 
        	alert(res.message)
        	
        	if(res.code > 0){
        		$("#img_url").value = res.result; 
        		} 
        } 
        ,error: function(){ //演示失败状态，并实现重传 
        	var demoText = $('#demoText'); 
        demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>'); 
        demoText.find('.demo-reload').on('click', function(){ 
        	uploadInst.upload(); 
        	}); 
        } 
       });
  

        /* 
          upload.render({
            elem: '#upload'
            , url: '${ctx}/employe/addFile'
            , auto: false //选择文件后不自动上传
            , bindAction: '#file-submit' //指向一个按钮触发上传
            , field: 'printFile'
            , accept: 'file'
            , choose: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#uoloadName").text(file.name);
                });
  
            }
            ,multiple: true
             , data: {
            	 photoDir: function () {
                	//alert(0)
                    return "789789788888888888888888888";
                }
            }
            , done: function (res) {
                alert("上传成功");
            }
            
        }); */
         /*  function fsubmit(fd) {
              $.ajax({
                  url: "php",
                  type: "POST",
                  data: fd,
                  async : false,
                  contentType: false,   //jax 中 contentType 设置为 false 是为了避免 JQuery 对其操作，从而失去分界符，而使服务器不能正常解析文件
                  processData: false,   //当设置为true的时候,jquery ajax 提交的时候不会序列化 data，而是直接使用data
                  error : function(request) {
                      parent.layer.alert("网络超时");
                  },
                  success: function (data) {
                      alert("上传成功！");
                  }
              });
              return false;
          }
       
          $("#LAY-user-front-submit").on("click",function () {
        	  alert(0)
              var formSatellite = document.getElementById("up_form");//获取所要提交form的id
              var fs1 = new FormData(formSatellite);  //用所要提交form做参数建立一个formdata对象
              fsubmit(fs1);//调用函数
          }) */
  
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