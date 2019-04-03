<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	var xArray = ${map['xArray']};
	var yArray = ${map['yArray']};
	var type = "${type}";
	var subtitle,x;
	if(type=='zj'){
		subtitle="按专家统计";
		x="专家";
	}else if(type=='jg'){
		subtitle="按专家所属机构统计";
		x="专家所属机构";
	}
	$("#container").highcharts({
		chart: {
            type: 'column'
        },
        title: {
            text: '抽取结果统计'
        },
        subtitle:{
        	text:subtitle
        },
        xAxis: {
            categories: xArray,
        title:{
        	text:x
        }
        },
        yAxis: {
        	title:{
        		text:'抽取次数'
        	},
        	min: 0,
        },
        plotOptions: {
            column: {
                dataLabels: {
                    enabled: true
                },
                colorByPoint:true,
                enableMouseTracking: false
            }
        },
        series:[{
           	name:x,
           	data:yArray
         }]
	});
})

function submitForm() {
	$("#tjForm").submit();
}

function rese() {
	$(':input', '#cxfxForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	// 		var form = document.getElementById("search");
}

function bodyLoad() {
	$("#panel").css({
		"min-height" : document.documentElement.clientHeight - 125
	});
}
</script>
<body>
	<pre>统计信息》按<c:if test="${type eq 'zj' }">专家</c:if><c:if test="${type eq 'jg'}">专家所属机构</c:if>统计</pre>
	<div class="panel-heading" id="panel-heading">
		<form:form id="tjForm" method="post" style="margin: 0">
			<table>
				<tr>
					<td width="80px" align="right">时间:</td>
					<td align="left" width="260px">
						<input type="text" name="beginTime" style="width: 120px"
							onclick="SelectDate(this,'yyyy-MM-dd')" readonly="readonly"
							value="${map['beginTime']}" class="form-control"> - <input
							type="text" name="endTime" style="width: 120px"
							onclick="SelectDate(this,'yyyy-MM-dd')"
							readonly="readonly" value="${map['endTime']}" class="form-control">
					</td>
					<td align="left"><button class="btn btn-primary" type="button"
							onclick="submitForm()">查询</button>&nbsp;&nbsp;
						<input  type="button" onclick="rese()" value="清空" class="btn btn-default">
				    </td>
				</tr>
			</table>
		</form:form>
	</div>
	<div id="container" style="min-width: 700px; height: 400px"></div>
</body>
</html>