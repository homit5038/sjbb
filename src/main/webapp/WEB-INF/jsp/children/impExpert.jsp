<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		var message = "${map['message']}";
		var rowNum = "${map['rowNum']}";
		var cellNum = "${map['cellNum']}";
		var str = "表格第"+rowNum+"行，第"+cellNum+"列";
		if(message=='isEmpty'){
			$("#filesErr").html("导入数据的文档为空 ，请填写后再导入");
		}else if(message=='noname'){
			$("#filesErr").html("专家姓名不能为空，"+str);
		}else if(message=='emailError'){
			$("#filesErr").html("导入数据中，有邮箱格式不对（如xxx@xx.xx），"+str);
		}else if(message=='phoneError'){
			$("#filesErr").html("导入数据中，手机号码只能为数字，"+str);
		}else if(message=='noPGJG'){
			$("#filesErr").html("评估机构不能为空，"+str);
		}else if(message=='FormatErro'){
			$("#filesErr").html("导入失败，文档格式有误，请参照系统提供的导入模板格式");
		} else {
			$("#filesErr").html(message);
		}
	});


	function submitForm() {
		if ($("#files").length > 0) {
			var files = $("#files").val();
			if (files == null || files == "") {
				$("#filesErr").html("请选择导入的文件");
				return false;
			} else {
				var fileext=files.substring(files.lastIndexOf("."),files.length)  
			    fileext=fileext.toLowerCase()  
			    if (fileext!='.xls' && fileext!='.xlsx'){
			    	$("#filesErr").html("对不起，导入数据格式必须是execl格式文件");
			        return false;  
			    }else{
				$("#filesErr").html("");
			    }  
			}
		}
		$("#impExpert").submit();
		jQuery('#main').showLoading();
	}
	function impExpertFrameDown(){
		window.location = ("${ctx}/expert/downloadExcel");
	}
</script>
</head>

<body id="main">
	<div class="panel panel-default" style="width: 99%">
		<div class="panel-heading" id="panel-heading">导入数据</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${map['message'] eq 'ok' }">数据导入成功</c:when>
				<c:otherwise>
					<form id="impExpert" method="post" enctype="multipart/form-data"
						class="form-horizontal">
						<div class="row">
							<div class="form-group col-sm-7">
								<label for="inputEmail3" class="col-sm-2 control-label">选择文件</label>
								<div class="col-sm-9">
									<input type="file" name="files" id="files" class="form-control">
								</div>
							</div>
							<div class="form-group col-sm-5">
								<label for="inputEmail3" class="control-label"> <span
									id="filesErr" class="err"></span>
								</label>
							</div>
						</div>
						<div class="form-group">
							<table style="width: 30%">
								<tr align="center">
								   <td align="right"><button
											class="btn btn-primary" type="button" style="margin-right: 10px;"
											onclick="impExpertFrameDown()">模板下载</button>
								   <td align="left"><button
											class="btn btn-primary" type="button"
											onclick="submitForm()">导入</button>
								</tr>
							</table>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
		<c:if test="${map.expertList != null && fn:length(map.expertList) > 0 }">
		  <div class="panel-heading" id="panel-heading">成功导入数据:</div>
		  <div class="panel-body">
		  <table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th>专家姓名</th>
								<th>评估机构</th>
								<th>电话</th>
								<th>邮箱</th>
								<th>抽取类型</th>
	
							</tr>
						</thead>
						<c:forEach items="${map.expertList }" var="data">
							 <tr>
								<td>${data.expertName }</td>
								<td>${data.assessmentStructure }</td>
								<td>${data.phoneNum }</td>
								<td>${data.expertEmail }</td>
								<td><c:choose>
										<c:when test="${data.flag==1 }">必选</c:when>
										<c:when test="${data.flag==0 }">可选</c:when>
										<c:when test="${data.flag==-1 }">不可选</c:when>
									</c:choose></td>
							</tr>
						</c:forEach>
			</table>	
		</div>
	  </c:if>
	  <c:if test="${map.repExpert != null && fn:length(map.repExpert) > 0 }">
		  <div class="panel-heading" id="panel-heading">如下数据，数据库中已经存在，无需重复导入:</div>
		  <div class="panel-body">
		  <table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th>专家姓名</th>
								<th>评估机构</th>
								<th>电话</th>
								<th>邮箱</th>
								<th>抽取类型</th>
	
							</tr>
						</thead>
						<c:forEach items="${map.repExpert }" var="data">
							 <tr>
								<td>${data.expertName }</td>
								<td>${data.assessmentStructure }</td>
								<td>${data.phoneNum }</td>
								<td>${data.expertEmail }</td>
								<td><c:choose>
										<c:when test="${data.flag==1 }">必选</c:when>
										<c:when test="${data.flag==0 }">可选</c:when>
										<c:when test="${data.flag==-1 }">不可选</c:when>
									</c:choose></td>
							</tr>
						</c:forEach>
			</table>	
		</div>
	  </c:if>
	</div>
</body>