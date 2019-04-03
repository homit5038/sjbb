<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<link rel="stylesheet" media="screen"
	href="${ctx}/resources/css/extractExpert.css">
<style type="text/css">

</style>
<script type="text/javascript">
    $(function(){
    	$.ajax({
			type : "post",
			url : "${ctx}/extract/${batch.id}/initExtractExpert",
			success : function(data) {
				setExpertList(data);
			}
		});
    });
	function randomExpert(bacthId, extractPepleNum){
		$.ajax({
			type : "post",
			url : "${ctx}/extract/"+bacthId+"/randomExtractExpert",
			data : {
				extractPepleNum : extractPepleNum,
			},
			success : function(data) {
				setExpertList(data);
			}
		});
	}
	function setExpertList(data){
		jQuery('#expertList').showLoading();
		setTimeout(function () {
			  $("#expertList").html(data);
			  setAllNoteDisable();
			  jQuery('#expertList').hideLoading();
		}, 500);
	}
	
	//获取抽取的专家ID
	function setExtractExpertIds(){
		var expertIds = '';
		$(".expertIds").each(function(){
			expertIds += $(this).val()+",";
		});
		return expertIds;
	}
	
	//重选
	function reelectExpert(bacthId, expertId){
		jQuery('#expertReelectOneMain'+expertId).showLoading();
		var newExpertId = $("#id"+expertId).val();
		var expertFlag = $("#flag"+expertId).val();
		$.ajax({
			type : "post",
			url : "${ctx}/extract/"+bacthId+"/reelectExpert",
			data : {
				expertId : newExpertId,
				expertIds : setExtractExpertIds(),
				expertFlag : expertFlag
			},
			success : function(data) {
				if(data.id){
					setTimeout(function () { 
						showReelectData(data,expertId);
						jQuery('#expertReelectOneMain'+expertId).hideLoading();
					}, 400);
				}else {
					alert("无可以选择的专家，请确认结果。");
					
					$(".reelectButton"+expertFlag).each(function(){
						$(this).attr("disabled","disabled");
					});
					
					jQuery('#expertReelectOneMain'+expertId).hideLoading();
				}
			}
		});
	}
	
	function showReelectData(data,expertId){
		$("#id"+expertId).val(data.id);
		$("#flag"+expertId).val(data.flag);
		$("#expertName"+expertId).html(data.expertName);
		if(data.assessmentStructure.length > 8){
			$("#assessmentStructure"+expertId).attr("title",data.assessmentStructure);
			$("#assessmentStructure"+expertId).html(data.assessmentStructure.substring(0,8)+"...");
		} else{
			$("#assessmentStructure"+expertId).html(data.assessmentStructure);
		}
		$("#phoneNum"+expertId).html(data.phoneNum);
		if(data.expertEmail.length > 8){
			$("#expertEmail"+expertId).attr("title",data.expertEmail);
			$("#expertEmail"+expertId).html(data.expertEmail.substring(0,8)+"...");
		} else{
			$("#expertEmail"+expertId).html(data.expertEmail);
		}
	}
	
	function submitExtract(batchId){
		window.location = ("${ctx}/extract/"+batchId+"/confirmExtract");
	}
	
	function impExtract(batchId){
		window.location = ("${ctx}/batch/"+batchId+"/exportExpertBatch");
	}
	//通知
	function manSendNote(bacthId, expertId, noteType){
		var expertIds = "";
		if(noteType == "all"){
			expertIds = setExtractExpertIds();
		} else{
			expertIds = expertId;
		}
		$.ajax({
			type : "post",
			url : "${ctx}/extract/"+bacthId+"/updateExtractExpertNoteStatus",
			data : {
				expertIds : expertIds
			},
			success : function(data) {
				if(data==true){
					if(noteType == "all"){
						$(".noteButton").each(function(){
							$(this).html("已通知");
							$(this).attr("disabled","disabled");
						});
						$("#noteAllButton").hide();
					} else{
						$("#noteButton"+expertId).html("已通知");
						$("#noteButton"+expertId).attr("disabled","disabled");
						setAllNoteDisable();
					}
				} else {
					alert("通知失败");
				}
			}
		});
	}
	
	function setAllNoteDisable(){
		var length = $(".noteButton").length - 1;
		var disableNum = 0;
		$(".noteButton").each(function(){
			if($(this).attr("disabled")){
				disableNum ++;
			}
		});
		if(length == disableNum){
			$("#noteAllButton").hide();
		}
	}
	
</script>
</head>
<body id="main">
	<pre>批次管理>批次维护</pre>
	<div class="panel panel-info"  align="center" style="width: 99%">
		<div class="panel-heading">
			 <h3 class="panel-title">
			        <span align="right">批次号：</span>
					<span class="left" align="left">${batch.id }</span>
					<span align="right">批次名称：</span>
					<span class="left" align="left">${batch.batchName }</span>
					<span align="right">抽取人数：</span>
					<span class="left" align="left">${batch.extractPepleNum }</span><br/><br/>
					<span align="right">批次有效性：</span>
					<span class="left" align="left"><c:choose>
							<c:when test="${batch.availability==1 }">
								<b style="color: red;">有效</b>
							</c:when>
							<c:otherwise>
								<b style="color: gray;">无效</b>
							</c:otherwise>
						</c:choose></span>
					<span align="right">最后操作时间：</span>
					<span class="left" align="left"><fmt:formatDate
							value="${batch.updateLastTime }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</span>
		     </h3>
		</div>
		<!-- 抽取结果列表 -->
		<div id="expertList"></div>
	</div>
</body>
</html>