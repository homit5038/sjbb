<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$("#yxx").val("${yxx}");
		$("#flag").val("${flag}");
		 
	});

	function submitForm() {
		jQuery('#main').showLoading();
		$("#chargemainFrom #size").val(10);
		$("#chargemainFrom #page").val(0);
		$("#chargemainFrom").submit();
	}

	function rese() {
		$(':input', '#chargemainFrom').not(':button, :submit, :reset, :hidden').val(
				'').removeAttr('checked').removeAttr('selected');
	}

	//删除信息
	function del(Id) {
		if (confirm('确定要删除该条信息吗?')) {
			jQuery('#main').showLoading();
			$.post("${ctx}/classe/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location = ("${ctx}/classe/list");
				} else {
					//
				}
			});
		}
	}
	// 编辑专家信息
	function edit(id){
		var url = "${ctx}/classe/" + id + "/edit";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	function addem(){
		var url = "${ctx}/classe/new";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/classe/list");
	}
</script>
</head>
<body id="main">
	<pre>班级>班级列表</pre>
	
	
	<div id="wrap" style="display: none">
        <div id="bill">
            
                <div class="billlogo">
                    <img src="/upload/895E5A06B39140E09585D078E462B32F_zip.png"/>
                </div>
            
            <p class="kindergarden-name">
                    育英三幼
            </p>
            
                <p class="telphone">电话：123456789</p>
            
            <table class="total-list">
                <tr>
                    <th>姓名：</th>
                    <td><span id="kidName"></span></td>
                </tr>
                <tr>
                    <th>班级：</th>
                    <td><span id="kidClass"></span></td>
                </tr>
                
                    <tr>
                        <th>学号：</th>
                        <td><span id="kidNum"></span></td>
                    </tr>
                
                
                    <tr>
                        <th>考勤天数：</th>
                        <td><span id="kidAttend"></span></td>
                    </tr>
                
            </table>
            <p>************************</p>
            <div>
                <table id="printtable">
                    <tr class="firsttr">
                        <th>收费项目</th>
                        <th>金额</th>
                    </tr>

                </table>
            </div>
            <p>************************</p>
            <table class="total-list">
                <tr>
                    <th><b>总金额</b>：</th>
                    <td><span id="kidTotalMoney"></span>元</td>
                </tr>
                <tr>
                    <th><b>打印日期</b>：</th>
                    <td><span id="kidTime"></span></td>
                </tr>
                
                <tr>
                    <th><b>缴费月份</b>：</th>
                    <td><span id="payTime">2019年04月</span></td>
                </tr>
                
                <tr>
                    <th><b>应收</b>：</th>
                    <td><span id="kidShouldMoney"></span>元</td>
                </tr>
                <tr>
                    <th><b>实收</b>：</th>
                    <td><span id="kidRealMoney"></span>元</td>
                </tr>
                <tr>
                    <th><b>找零</b>：</th>
                    <td><span id="kidReturnMoney"></span>元</td>
                </tr>
                <tr>
                    <th><b>收款人</b>：</th>
                    <td>园长</td>
                </tr>
                
                    <tr>
                        <th><b>小票号</b>：</th>
                        <td><span id="kidFlowCode"></span></td>
                    </tr>
                
                <tr>
                    <th><b>缴费方式</b>：</th>
                    <td><span id="kidPaytype"></span></td>
                </tr>
            </table>
            <p>************************</p>
            <p>备注：<span id="kidBz"></span></p>
            
                <p>通知：<span id="kidNotice">收费凭据</span></p>
            
            
        </div>
    </div>

    <!-- 补打小票 -->
    <div id="wrapb" style="display:none;">
        <div id="bill">
            
                <div class="billlogo">
                    <img src="/upload/895E5A06B39140E09585D078E462B32F_zip.png"/>
                </div>
            
            <h1 class="kindergarden-name">
                    育英三幼
            </h1>
            
                <h3>电话：123456789</h3>
            
            <table class="total-list">
                <tr>
                    <th>姓名：</th>
                    <td><span id="kidNameb"></span></td>
                </tr>
                <tr>
                    <th>班级：</th>
                    <td><span id="kidClassb"></span></td>
                </tr>
                
                    <tr>
                        <th>学号：</th>
                        <td><span id="kidNumb"></span></td>
                    </tr>
                
                
                    <tr>
                        <th>考勤天数：</th>
                        <td><span id="kidAttendb"></span></td>
                    </tr>
                
            </table>
            <p>************************</p>
            <div>
                <table id="printtableb">
                    <tr class="firsttr">
                        <th>收费项目</th>
                        <th>金额</th>
                    </tr>

                </table>
            </div>
            <p>************************</p>
            <table class="total-list">
                <tr>
                    <th><b>总金额</b>：</th>
                    <td><span id="kidTotalMoneyb"></span>元</td>
                </tr>
                <tr>
                    <th><b>打印日期</b>：</th>
                    <td><span id="kidTimeb"></span></td>
                </tr>
                
                <tr>
                    <th><b>缴费月份</b>：</th>
                    <td><span id="kidPayableDate"></span></td>
                </tr>
                
                <tr>
                    <th><b>应收</b>：</th>
                    <td><span id="kidShouldMoneyb"></span>元</td>
                </tr>
                <tr>
                    <th><b>实收</b>：</th>
                    <td><span id="kidRealMoneyb"></span>元</td>
                </tr>
                <tr>
                    <th><b>收款人</b>：</th>
                    <td><span id="kidUsernameb"></span></td>
                </tr>
                
                    <tr>
                        <th><b>小票号</b>：</th>
                        <td><span id="kidFlowCodeb"></span></td>
                    </tr>
                
                <tr>
                    <th><b>缴费方式</b>：</th>
                    <td><span id="kidPaytypeb"></span></td>
                </tr>
            </table>
            <p>************************</p>
            <p>备注：<span id="kidBzb"></span></p>
            
                <p>通知：<span id="kidNoticeb">收费凭据</span></p>
            
            
        </div>
    </div>
	
	
	
	
	
	
	
	
	
	
	
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
		<input type="hidden" id="printerWidth" value="58" />
		<input type="hidden" id="printGroup" value="1" />
        <input type="hidden" id="printerName" value="0" />
			<form id="chargemainFrom" action="${ctx}/charge/chargemain" method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table border=0 width="100%">
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left"><input type="text" name=childName value="${name}"
							style="width: 180px" class="form-control" placeholder="请输入姓名"></td>
				
						
						
						<td align="left" >
							<button class="btn btn-primary" type="button" id="submitBtn" onclick="submitForm()">查询</button>
							<input type="button" onclick="rese()" value="清空" class="btn btn-default">
						
						    <input type="button" onclick="addem()"	value="添加班级" class="btn btn-default">
							<button type="button" onclick="startConnect();" class="btn btn-warning" >覆盖关联</button>
							<button type="button" onclick="allshow();" class="btn btn-warning" >全部显示</button>
						<button type="button" onclick="allv();" class="btn btn-warning" >选中</button>
						</td>
					</tr>
	
				</table>
			</form>
		</div>
		<div class="panel-body">
<!-- 		<input type="text" id="childIdList">
		<input type="text" id="chargeIdList"> -->
		
			<table class="table table-bordered table-condensed" id="charge-item-table">
				<c:choose>
					<c:when test="${num==0}">
						<tr>
							<td>没有符合该条件的查询结果......</td>
						</tr>
					</c:when>
					<c:otherwise>
						<thead>
							<tr>
								<th>姓名</th>
					            <th>联系电话</th>
	                         
							</tr>
						</thead>
						<c:forEach items="${lists}" var="data">
							 <tr  data-id="${data.id}">
							<td  class="childId" data-id="${data.chargConnection}">
							<span class="kinderName">${data.childName}</span>
							<%-- <input type="hidden" class="childId" value="${data.id}"> --%>
							</td>
							<td>${data.parentPhoneNumber}</td>
							
								
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			
	
<div class="col-md-12 charge-detail"></div>	
<form id="myform" class="form-inline">
<div class="row">
<div class="col-md-12 charge-items text-center">
<span id="selected-kinder">张征睿</span><span id="datast"></span>月份 缴费详情 
                        <label class="radio-inline"> <input id="display-all-items" type="checkbox">显示全部收费项 </label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <label class="radio-inline"> <input id="paytypeCash" class="paytype" value="1" type="radio" name="paytype" checked="checked">现金 </label>
                        <label class="radio-inline"> <input id="paytypeCard" class="paytype" value="2" type="radio" name="paytype">刷卡 </label>
                        <label class="radio-inline"> <input id="paytypeWet" class="paytype" value="3" type="radio" name="paytype">微信 </label>
                        <label class="radio-inline"><input id="paytypePay" class="paytype" value="4" type="radio" name="paytype">支付宝 </label>
                    </div> </div>

<div class="row">
<div class="col-md-12 charge-items text-center">
  
  
  
  
                    <input type="hidden" class="childId" >
                    <input type="hidden" name="payStatus" value="CG">
                    <input type="hidden" id="favourable" name="favourable" value="0">
                    <input type="hidden" name="flowCode" id="flowCode" value="">
                    <input type="hidden" name="chargetype" id="chargingType" value="1">
                    <input type="hidden" name="childId" id="childId">
                
                    <input type="text" name="chargConnection" id="chargConnection" value="0">
                    
			
			
				
                    
                    
                    <span>应收&nbsp;&nbsp;<input type="text" id="shouldpay" readonly="" name="charge-should-pay" class="form-control charge-input" value="0"></span>
                    <span>实收&nbsp;&nbsp; <input type="text" id="realpay" name="charge-real-pay" class="form-control charge-input" value="0"></span>
                    <span>找零&nbsp;&nbsp; <input type="text" id="returnpay" readonly="" name="charge-return" class="form-control charge-input" value="0"></span>
                    <span>备注&nbsp;&nbsp; <input type="text" id="bz" name="bz" class="form-control charge-input"></span>
                    <span><input id="doCharge" type="button" class="btn btn-warning" value="缴费打印"></span>


            </div>
        </div>
        
         </form>
        
			<table class="table table-bordered table-condensed" id="contoned"></table>
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
							    <th>选择</th>
								<th>收费名称</th>
					            <th>收费金额</th>
	                            <th>收费周期</th>
	                            <th>小票等级</th>
							</tr>
						</thead>
						<c:forEach items="${Itemlist}" var="data">
							 <tr data-id="${data.id}">
								<td><input type="checkbox" class="charge-item" name="chargeitem" value=${data.id}></td>
								<td>${data.itemName}</td>
							    <td>${data.amount}
							   <input type="hidden" class="charge-amount" value="${data.amount}">
							       <input type="hidden" class="print-group" value="1">
							    </td>
								 <td>${data.periodic.displayName}</td>
								 <td>${data.ticketLevel.displayName}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>	
			
			<div class="row" style="min-height:107px;height: 107px; overflow: auto; width: 100%">
            <table id="table-charge-history" class="table table-bordered table-condensed-mine text-center">
                <thead>
                <tr class="table-head">
                    <th width="20%">小票号</th>
                    <th width="20%">缴费金额（元）</th>
                    <th width="20%">缴费时间</th>
                    <th width="20%">缴费方式</th>
                    <th width="20%">操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
			
			
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
</body>
</html>
<script type="text/javascript" charset="utf-8" src="https://www.jqiyun.cn/theme/plugins/zui/js/zui.min.js"></script>
<script type="text/javascript" charset="utf-8" src="https://www.jqiyun.cn/theme/js/app.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/LodopFuncs.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/print.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/app2.js"></script>