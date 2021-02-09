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
				<tbody>
				<tr>
					
					<th>姓名</th>
					<td class="txiv" >${children.childName}</td>
					<th colspan="2">性别</th>
					<xqx:property name="sexTypes" bId="${children.childSex}"></xqx:property>
					<td class="txiv" >${sexTypes.fValue}
					
					</td>
					<td rowspan="5" width="160">

					<img src="${ctx}/children/${children.id}/findFile" alt="照片" width="160" height="180" border=0/>
					
					
					</td>
				</tr>

				<tr>
					<th>出生日期</th>
					<td class="txiv" >${children.childBirthday}</td>
					<th colspan="2">民族</th>
					<td class="txiv" >---${children.childNation}</td>
				</tr>
				<tr>
					<th>身份证号</th>
					<td colspan="4"class="txiv" >${children.childIdcardNumber}</td>
				</tr>
				<tr>
					<th>学历</th>
					<td class="txiv">
					
					多大
					
					
					</td>
					<th colspan="2">家庭住址</th>
					<td class="txiv" >${children.homeAddress}</td>
				</tr>
				<tr>
					<th>家长姓名</th>
					<td class="txiv" >${children.parentName}</td>
					<th colspan="2">家长邮箱</th>
					<td class="txiv">${children.parentEmail}</td>
				</tr>
                 <tr>
					<th>家长身份证</th>
					<td colspan="5"class="txiv" >${children.parentIdcardNumber}</td>
				</tr>
                 <tr>
					<th>联系电话</th>
					<td colspan="3"class="txiv" >${children.parentPhoneNumber}</td>
					<th>家长工作单位</th>
					<td>${children.parentWorkAddress}</td>
				</tr> 
                 <tr>
					<th>是否医保</th>
					<td colspan="3"class="txiv" >${children.medicalInsurance}</td>
					<th>是否在园</th>
					<td>${children.status}</td>
				</tr>
				<tr>
					<th>健康档案是否在园</th>
					<td colspan="5"class="txiv" >${children.healthRecod}</td>
					
				</tr>  
				
				 <tr>
				 	<th>备注</th>
					<td colspan="5"  class="txiv">${children.remarks}</td>
				</tr>
				
			 <tr>
				 	<th>学号</th>
				    <td colspan="5"  class="txiv">${children.childSchoolId}</td>
				</tr>
				<tr>
				 	<th colspan="6">备注</th>
				
				</tr>
			<tr>
				 	<th colspan="6" style="text-align: center;">关联收费项</th>
				
				</tr>
				<tr>
				<th colspan="2">收费名称</th>
			    <th colspan="2">金额</th>
			    <th colspan="2">收费周期</th>
		
				</tr>
				<c:forEach items="${chargeitem}" var="chargeitem">
				<tr>
				<td colspan="2"  class="txiv">${chargeitem.itemName}</td>
				<td colspan="2"  class="txiv">${chargeitem.amount}</td>
				<td colspan="2"  class="txiv">${chargeitem.periodic.displayName}</td>
				</tr>
				</c:forEach>
				
              </tbody>
              </table>

	
		</div>
	</div>

</body>
</html>