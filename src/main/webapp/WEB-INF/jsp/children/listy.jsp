<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/xqxtags" prefix="xqx"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<!DOCTYPE html >
<html>
<head>
    <title>临时收据</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
 

<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<link href="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<script src="${ctx}/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script type="text/javascript">
$(function(){ 
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
})

	function submitForm() {
		jQuery('#main').showLoading();
		$("#expertForm #size").val(10);
		$("#expertForm #page").val(0);
		$("#expertForm").submit();
	}

	function rese() {
		$(':input', '#expertForm').not(':button, :submit, :reset, :hidden').val(
				'').removeAttr('checked').removeAttr('selected');
	}

	//删除信息
	function del(Id) {
		if (confirm('确定要删除该条信息吗?')) {
			jQuery('#main').showLoading();
			$.post("${ctx}/children/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location = ("${ctx}/children/list");
				} else {
					//
				}
			});
		}
	}
	// 编辑学生信息
	function edit(id){
		
		var url = "${ctx}/children/" + id + "/edit";
		$("#signImgFrame").attr('src', url);
		$("#myModalLabel").html("编辑学生信息");
		$("#signImgModal").modal();
	}
	// 编辑学生信息
	function historys(id,names){
		var url = "${ctx}/charge/historyinfo?id=" + id+"&names="+names;
	
		//var url = "${ctx}/charge/historyinfo?id=4&names=55";
		$("#signImgFrame").attr('src', url);
		$("#myModalLabel").html(names+"历史缴费记录");
		$("#signImgModal").modal();
	}
	
	// 添加学生信息
	function expert(){
		window.location = ("${ctx}/children/export");
	}

	
	// 添加学生信息
	function addem(){
		var url = "${ctx}/children/new";
		$("#signImgFrame").attr('src', url);
		$("#myModalLabel").html("添加学生信息");
		$("#signImgModal").modal();
	}
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/children/list");
	}
</script>
</head>
<body id="main">







<!-- 新增临时收据模态框 -->
<div class="modal" id="addTmpCharge">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                        class="sr-only">关闭</span></button>
                <h4 class="modal-title">新增临时收据</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                
                
	<form:form modelAttribute="children" id="myform" class="form-inline form-condensed">
                
                
                   
                        <input type="hidden" name="payStatus" value="LS">
                        <input type="hidden" name="chargetype" value="3">
                        <input type="hidden" id="childId" name="childId">
                        <input type="hidden" id="favourable" name="favourable" value="0">
                        <input type="hidden" id="childSchoolId" name="childSchoolId">

                        <div class="row">
                            <div class="col-md-5">
                                <label for="kinder-type">幼儿类别</label>
                                <select class="form-control" id="kinder-type" name="kinderType" style="width:50%">
                                    <option value="0">新生</option>
                                    <option value="1">老生</option>
                                </select>
                                <button type="button" id="select-kinder-btn" class="btn btn-warning" data-toggle="modal"
                                        data-remote="/charge/selectAllChildren.do">选择幼儿
                                </button>
                            </div>
                        </div>
                        <section>
                            <!-- <header><h3>收费项</h3></header> -->
                            <article>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">幼儿信息</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <label for="kinder-name">姓名</label>
                                          
                                           
                                       
 <form:input path="childName"  styleid="childName" cssClass="formItem form-control " style="width:60%" />
                                                
                                                
                                           
                                           
           
                                           
                                            </div>
                                            <div class="col-md-4">
                                                <label for="gender">性别</label>
                                               
                                               
                                               
  	<xqx:property name="sexTypes" fId="40601022"></xqx:property>                     
	   <form:select path="childSex" id="childSex" onchange=""
							cssClass="formItem form-control"   style="width:60%">
							<form:option value="-1">请选择</form:option>
							<c:forEach items="${sexTypes}" var="Sex">
								<form:option value="${Sex.id}">${Sex.fValue }</form:option>
							</c:forEach>
						</form:select>
                                               
                                               
                                               
                                               
                                               
                                    
                                       
                                            </div>
                                            <div class="col-md-4">
                                                <label for="birthday">出生日期</label>
                                           
                                           
                                           
                                           
 <form:input path="childBirthday"  styleid="birthday" cssClass="formItem form-control time" readonly="true" style="width:60%" />
                                                
                                                
                                           
                                           
                                           
                                           
                                            </div>
                                        </div>
                                        

                                        
                                        
                                        
                                        <div class="row">
                                            <div class="col-md-4">
                                                <label for="grade">年级</label>
                                                
                                                
						                          <form:select path="grade.id" onchange=""
													cssClass="formItem formItem form-control" id="grade" style="width:80%">
													<form:option value="-1">请选择</form:option>
													<c:forEach items="${grade}" var="grade">
														<form:option value="${grade.id}">${grade.gradename }</form:option>
													</c:forEach>
												</form:select>

                                            </div>
                                            <div class="col-md-4">
                                                <label for="class">班级</label>
                                        
                                                
	                                       <form:select path="classe" 
													cssClass="formItem form-control" id="classe"  style="width:60%">
													<form:option value="-1">请选择</form:option>
													<c:forEach items="${classe}" var="classe">
														<form:option value="${classe.id}">${classe.classesname }</form:option>
													</c:forEach>
												</form:select>
                                                
                                                
                                            </div>
                                            <div class="col-md-4">
                                                <label for="in-date">入园时间</label>
                                                
                 <form:input path="childInGartenDate"  styleid="childInGartenDate"
								cssClass="formItem form-control time time-now" readonly="true" style="width:60%" />
                                                
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                        </section>

                        <section>
                            <!-- <header><h3>收费项</h3></header> -->
                            <article>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">收费项</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <label for="charge-template">收费项模板</label>


                                                <select id="charge-template" class="form-control" style="width:70%">
                                                    

                                                   <option value="-1">请选择</option>
													<c:forEach items="${ticketTemp}" var="ticketTemp">
														<option value="${ticketTemp.id}">${ticketTemp.tempname }</option>
													</c:forEach>
                                                    

                                                    
                                                </select>

                                            </div>
                                            <div class="col-md-5">
                                                <button type="button" id="add-charge-btn" class="btn btn-warning">
                                                    新增收费项
                                                </button>
                                                <button type="button" id="add-charge-tmp" class="btn btn-warning">存为模板
                                                </button>
                                                <button type="button" id="delete-charge-tmp" class="btn btn-warning">删除当前模板
                                                </button>
                                            </div>
                                        </div>
                                        <div class="row">

                                            <input name="tempName" id="tempName" type="hidden" value="">
                                            <div style="height:150px;overflow:auto;">
                                                <table id="kinder-list-table"
                                                       class="table table-bordered table-hover table-condensed text-center">
                                                    <thead>
                                                    <tr>
                                                        <th>收费项</th>
                                                        <th>金额（元）</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr class="columntr">
                                                    
                                     <td>
                                     <input type="text" name="chargeName" class="moneyName form-control" readonly=”readonly” value="入园费"/>
                                    </td>
                                    <td><input type="text" name="chargeAmount"  class="money form-control NumDecText" readonly=”readonly”
                                                                   value="100"/></td>
                                                        <td>
                                                            <button type="button" class="btn charge-item-edit-btn">编辑
                                                            </button>
                                                            <button type="button" class="btn charge-item-del-btn">删除
                                                            </button>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                        </section>

                        <div class="row">
                            <div class="col-md-12 charge-items">

                                <span>应收&nbsp;<input id="shouldpay" type="text" name="amount"
                                                           class="pay form-control temp_charge-input"
                                                           readonly="readonly" /></span>
                                <span>实收&nbsp;<input id="realpay" type="text" name="payed"
                                                            class="pay NumDecText form-control temp_charge-input" value="0" /></span>
                                <span>找零&nbsp;<input id="returnpay" type="text"
                                                            class="pay form-control temp_charge-input" readonly="readonly"/></span>
                                <span>备注&nbsp;<input id="bz" type="text" name="bz"
                                                            class="form-control temp_charge-input" /></span>
                                <input id="paytypeCash" name="paytype" class="paytype" type="radio" value="1" checked>
                                <font style="font-size: 13px;">现金&nbsp;&nbsp;</font>

                                <input id="paytypeCard" name="paytype" class="paytype" value="2" type="radio">
                                <font style="font-size: 13px;">刷卡&nbsp;&nbsp;</font>
                                <input id="paytypeWeChat" name="paytype" class="paytype" type="radio" value="3">
                                <font style="font-size: 13px;">微信&nbsp;&nbsp;</font>
                                <input id="paytypeAliPay" name="paytype" class="paytype" value="4"  type="radio" >
                                <font style="font-size: 13px;">支付宝</font>

                            </div>
                        </div>
                </div>
            </div>
           	</form:form>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveBtn">收费并打印</button>
                <button type="button" class="btn btn-default" id="closeTempCharge" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- 新增临时收据模态框END -->



















	<pre>学生库>学生列表</pre>
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
<c:forEach items="${grade}" var="dat">

<a href="${ctx}/children/list?gid=${dat.id}"><strong>${dat.gradename}</strong></a>

(
<c:forEach items="${dat.classt}" var="datas">
<a href="${ctx}/children/list?cid=${datas.id}">->${datas.classesname}</a>-
</c:forEach>
)

</c:forEach>
			<form id="expertForm" class="form-inline row" action="${ctx}/children/list" method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left"><input type="text" name="childName" value="${name}"
							style="width: 180px" class="form-control" placeholder="请输入学生姓名"></td>
						
						
					
						
						<td align="right" width="100px">进园时间：</td>
						<td align="left"><input type="text" name="beginTime"
							id="beginTime" value="${beginTime}" style="width: 122px" readonly="readonly"
							class="form-control form-date">-<input type="text" name="endTime" 
							id="endTime" value="${endTime}" style="width: 122px" readonly="readonly"
							class="form-control form-date"></td>
						<td align="left">
							<button class="btn btn-primary" type="button" id="submitBtn" onclick="submitForm()">查询</button>
							<button class="btn btn-primary" type="button" id="newadd" onclick="addem()">新增</button>
							<button class="btn btn-primary" type="button" id="experts" onclick="expert()">导出EXCL</button>
							
						
							<input type="button" onclick="rese()"
							value="清空" class="btn btn-default">
						</td>
						
					<td>
					
					 <button id="add-tmp-charge-btn" type="button" class="btn btn-warning" data-toggle="modal" data-target="#addTmpCharge">新增临时收据 </button>
                  
                                               
					
					</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-body">
			<table class="table table-bordered table-condensed">
				<c:choose>
					<c:when test="${num==0}">
						<tr>
							<td>没有符合该条件的查询结果......</td>
						</tr>
					</c:when>
					<c:otherwise>
<thead>
<tr>
<th width="210px">操&nbsp;&nbsp;&nbsp;作</th>
<th>姓名</th>
<th>出生地</th>
<th>邮箱</th>
<th>性别</th>
<th>年级</th>
<th>班级</th>
<th>住址</th>
<th>联系电话</th>
<th>身份证号</th>
<th>家长姓名</th>
<th>考勤卡号</th>
<th>进园时间</th>

							</tr>
						</thead>
						<c:forEach items="${data.content }" var="data">
					 <tr>
								<td> 
								   <a class="btn btn-primary  btn-xs" href="#" onclick="edit('${data.id}')">修改 </a>
								   <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="del('${data.id}')">删除 </a>
								   <a class="btn btn-primary  btn-xs"  target="_blank" href="${data.id}/read">查看详细</a>
								   <a class="btn btn-primary  btn-xs"  target="_blank" onclick="historys('${data.id}','${data.childName}')" >缴费记录</a>
								</td>
<td>${data.childName }</td>
<td>${data.birthPlace }</td>
<td>${data.parentEmail}</td>
<xqx:property name="childSex" bId="${data.childSex}"></xqx:property>		
<td>${childSex.fValue}</td>
<td>${data.grade.gradename}</td>
<td>${data.classe.classesname}</td>
<td>${data.homeAddress}</td>
<td>${data.parentPhoneNumber}</td>
<td>${data.childIdcardNumber}</td>
<td>${data.parentName}</td>
<td>${data.attendanceIdcardNumber}</td>				
<td>${data.childInGartenDate}</td>						


					
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>	
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
	<!-- <base href="https://www.jqiyun.cn"/> -->
	
	<c:if test="${num>0}">
		<tags:pagination page="${data }" formId="expertForm"></tags:pagination>
	</c:if>
</body>


<script type="text/javascript" src="${ctx}/resources/zui/js/zui.minn.js"></script>

<script type="text/javascript" src="${ctx}/resources/js/app.js"></script>
<script language="javascript" src="${ctx}/resources/js/LodopFuncs.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/print.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ticket.js"></script>
</html>
