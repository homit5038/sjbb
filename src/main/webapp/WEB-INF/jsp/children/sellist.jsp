<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/xqxtags" prefix="xqx"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>

	<body>
			<form id="expertForm" class="form-inline row" action="${ctx}/children/selectAllChildren" method="post">
				<table>
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left">
						<input type="text" id="kinder-search" name="childName"  style="width: 180px" class="form-control" placeholder="请输入学生姓名">
                        </td>
						<td align="left">
							<button class="btn btn-primary" type="button" id="submitBtn" >查询</button>

						</td>
						
					
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-body">
			<table id="kinder-select-table" class="table table-bordered table-condensed">
				<c:choose>
					<c:when test="${num==0}">
						<tr>
							<td>没有符合该条件的查询结果......</td>
						</tr>
					</c:when>
					<c:otherwise>
<thead>
<tr>

<th>学号</th>
<th>姓名</th>
<th>年级</th>
<th>班级</th>
<th>家长</th>
<th>操作</th>


							</tr>
						</thead>
						<c:forEach items="${children}" var="data">
					 <tr class="childsearch">
								
<td>${data.childSchoolId}</td>	  
<td>${data.childName }</td>
<td>${data.grade.gradename}</td>
<td>${data.classe.classesname}</td>
<td>${data.parentName}</td>
<td><a href="#" id="${data.id}" class="select-kinder">选择</a></td>
					
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>	
		</div>
	</div>
	
	
	

	
	
	
	
	

</body>
</html>
<script type="text/javascript">

	
    $(document).on('click', '#submitBtn', searchChild);
	$("#kinder-search").bind('input propertychange', searchChild);
	function searchChild(){
		var searchName = $("#kinder-search").val();
	
		//if(searchName!=""){
			$.ajax({
				url:"queryChildren?searchName="+searchName,
				type:'post',
				success:function(data){
				//alert(data[i][0])
					$("#kinder-select-table .childsearch").remove();
					for(var i in data){  
						items = '<tr class="childsearch">' +
						"<td>" + data[i][2] + "</td>" +
						"<td>" + data[i][1] + "</td>" +
						"<td>" + data[i][5] + "</td>" +
						"<td>" + data[i][4] + "</td>" +
						"<td>" + data[i][3] + "</td>" +
						'<td><a href="#" id="'+ data[i][0] +'"  class="select-kinder">选择</a></td>' +
						"</tr>";
						$('#kinder-select-table').append(items);
					}
				}
			
			});
		//}
	}
	$(document).on('click', '.select-kinder', tocopy);
	
	function tocopy(){
	
		var childId = $(this).attr("id");
		//alert($(this).attr("id"));


		$.ajax({
			url:"selectChildById?childId="+childId,
			type:'post',
			success:function(data){

					$("#kinder-name").val(data.childName);
					$("#gender").val(data.childSex);
					$("#birthday").val(data.childBirthday);
					$("#grade").val(data.grade);
				console.log("22222===",data.grade,data.classe)
					$("#class").val(data.classe);
					$("#in-date").val(data.childInGartenDate);

					$("#kinder-type").val(1);
					$("#childId").val(data.childId);
					$("#childSchoolId").val(data.childSchoolId);
					$(".formItem").attr("disabled",true);
					
			}
		});
		
		$("#select-kinder-btn").data("zui.modaltrigger").close();
	}
	
</script>