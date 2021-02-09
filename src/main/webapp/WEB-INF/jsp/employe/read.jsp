<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/xqxtags" prefix="xqx"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">

</script>
</head>
<body id="main">
	<pre>教师库>教师详情</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
			
		</div>
		<div class="panel-body">
	
<table id="table-charge-history" class="table table-bordered table-condensed text-center">
				<tbody><tr>
					<td rowspan="10" style="display:table-cell; vertical-align:middle ; width: 30px;" align="center">基本情况</td>
					<td>姓名</td>
					<td>${employe.employeName}</td>
					<td colspan="2">性别</td>
					<xqx:property name="sexTypes" bId="${employe.sexType}"></xqx:property>
					<td>${sexTypes.fValue}
					
					</td>
					<td rowspan="5" width="160">

					<img src="${ctx}/employe/${employe.id}/findFile" alt="照片" width="160" height="180" border=0/>
					
					
					</td>
				</tr>

				<tr>
					<td>出生日期</td>
					<td>${employe.beginToWorkDate}</td>
					<td colspan="2">民族</td>
					<td>${employe.nation}</td>
				</tr>
				<tr>
					<td>身份证号</td>
					<td colspan="4" align="left">${employe.jobNumber}</td>
				</tr>
				<tr>
					<td>学历</td>
					<td>
					
					<xqx:property name="academicQualification" bId="${employe.academicQualification}"></xqx:property>
					${academicQualification.fValue}
					
					
					</td>
					<td colspan="2">毕业学校</td>
					<td>${employe.graduateSchool}</td>
				</tr>
				<tr>
					<td>毕业时间</td>
					<td>${employe.beginToWorkDate}</td>
					<td colspan="2">参加工作时间</td>
					<td></td>
				</tr>
                 <tr>
					<td>通讯地址</td>
					<td colspan="5">${employe.dwellingPlace}</td>
				</tr>
                 <tr>
					<td>联系电话</td>
					<td colspan="3">${employe.phoneNum}</td>
					<td>婚姻状况</td>
					<td>未婚</td>
				</tr> 
				<tr>
					<td>紧急联络人</td>
					<td></td>
					<td>与本人关系</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>联系电话</td>
					<td></td>
				</tr> 
               <tr>
					<td>工资卡号</td>
					<td colspan="3">${employe.workAttendanceCardNumber}</td>
					<td>银行</td>
					<td></td>
				</tr>
				 <tr>
					<td>备注</td>
					<td colspan="5">&nbsp;<br>&nbsp;<br></td>
				</tr>
				<tr>
					<td rowspan="5" style="display:table-cell; vertical-align:middle ; width: 30px;" align="center">入职情况</td>
					<td>所属部门</td>
					<td>教务处</td>
					<td>担任职务</td>
					<td>卫生检查</td>
					<td>职务级别</td>
					<td>一级</td>
				</tr>
				<tr>
					<td>入职时间</td>
					<td>${employe.inGartenDate}</td>
					<td>试用时间</td>
					<td></td>
					<td>转正时间</td>
					<td></td>
				</tr>
				<tr>
					<td>聘用方式</td>
					<td>主班老师</td>
					<td>试用薪酬</td>
					<td></td>
					<td>正式薪酬</td>
					<td></td>
				</tr>
				<tr>
					<td>在职标志</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>备注</td>
					<td colspan="5">&nbsp;<br>&nbsp;<br></td>
				</tr>
                
                <tr>
					<td rowspan="2" style="display:table-cell; vertical-align:middle ; width: 30px;" align="center">个人简历</td>
					<td colspan="6">
						&nbsp;<br>&nbsp;<br>&nbsp;<br>
						&nbsp;<br>&nbsp;<br>&nbsp;<br>
						&nbsp;<br>&nbsp;<br>&nbsp;<br>
						&nbsp;<br>&nbsp;<br>&nbsp;<br>
						&nbsp;<br>&nbsp;<br>&nbsp;<br>
					</td>
				</tr>
				 <tr>
				 	<td>备注</td>
					<td colspan="5"></td>
				</tr>
              </tbody></table>


	
		</div>
	</div>

</body>
</html>