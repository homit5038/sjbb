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
	href="${ctx}/resources/bootstrap-3.3.5/css/dataTables.bootstrap.min.css">
<script type="text/javascript" charset="utf-8"
	src="${ctx}/resources/js/jquery-ui-1.12.1/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(function(){
	
	 var table = $("#charge-item-table").DataTable(
			 {
				 
				 "ajax": "${ctx}/property/lists",
				    "columns": [
			            { "data": "fName" },
			            { "data": "fValue" },
			            { "data": "fId" },
			            { "data": "id" }
			        ],
			        "iDisplayLength":10,//初始默认10条
			        "oLanguage":
			        {
			           "sProcessing": "正在加载中......",
			             "sLengthMenu": "每页显示 _MENU_ 条记录",
			             "sZeroRecords": "对不起，查询不到相关数据！",
			              "sEmptyTable": "表中无数据存在！",
			            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			            "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
			            "sSearch": "搜索",
			             "oPaginate": {
			                "sFirst": "首页",
			                 "sPrevious": "上一页",
			                 "sNext": "下一页",
			                "sLast": "末页"
			        }
			       
			        
			        
			        }
			 }
	 );
	 
	 $("#charge-item-table tbody").on( 'click', 'tr', function () {
	
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	    } );
	 
	    $('#button').click( function () {
	        table.row('.selected').remove().draw( false );
	    });
	
});
/* 	$(".charge-item-table").datatable({
	    checkable:true,
	    checksChanged: function(e) {
	    	$("#chargConnection").val(e.checks.checks); 
	    }
	});  */
/* 	$("#shoufxlist").dataTable(
			{
				checkable:true,
			    checksChanged: function(e) {
			    	$("#chargConnection").val(e.checks.checks); 
			    }
		    }
	); */
	






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
			$.post("${ctx}/grade/" + Id + "/del", {
				id : Id
			}, function(data) {
				jQuery('#main').hideLoading();
				if (data) {
					alert("删除成功");
					window.location = ("${ctx}/grade/list");
				} else {
					//
				}
			});
		}
	}
	// 编辑专家信息
	function edit(id){
		var url = "${ctx}/grade/" + id + "/edit";
		$("#signImgFrame").attr('src', url);
		$("#signImgModal").modal();
	}
	
	
	
	// 刷新页面
	function refresh(){
		window.location = ("${ctx}/grade/list");
	}

	
	 function batchDeletes(){
      
		
	
			
		 
         
         var checkedNum = $("input[name='subcheck']:checked").length;
         if(checkedNum==0){
             alert("请至少选择一项!");
             return false;
         }
         if(confirm("确定删除所选项目?")){
         var checkedList = new Array();
         $("input[name='subcheck']:checked").each(function(){
             checkedList.push($(this).val());
         });
         $.ajax({
             type:"POST",
             url:"del",
             data:{"delitems":checkedList.toString()},
             datatype:"html",
             success:function(data){
            	 alert(data)
            	 if(data=="ok"){
            		 alert("操作成功！")
            		 location.reload();//页面刷新
            	 }else{
            		 alert("操作失败！")
            	 }
             },
             error:function(data){
            	 alert("操作失败！")
                 
             }
         });
         }
 }

	
	
</script>
<style>

.selected{
  background:#ddd;
}
</style>
</head>
<body id="main">
	<pre>年级>年级管理列表</pre>
	
	
	
	
	
	
	
	
		
<table class="table table-condensed charge-item-table" id="charge-item-table" >
                                    	
                                        <thead>
											<tr>
												<th scope="col">收费编码</th>
												<th scope="col">收费项目名称</th>
												<th scope="col">金额</th>
												<th scope="col">收费周期</th>
												<!-- <th scope="col">备注</th> -->
											</tr>
										</thead>
										
                                    </table>
		
		
	
	
	
	
	
	
	
	
	
	<div class="panel panel-info" style="width: 99%">
		<div class="panel-heading">
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			<form id="expertForm" action="${ctx}/grade/list" method="post">
				<input type="hidden" id="size" name="size" value="${size}"> 
			    <input type="hidden" id="page" name="page" value="${page}">
				<table>
					<tr height="40px">
						<td align="right" width="100px">姓名：</td>
						<td align="left"><input type="text" name="gradeName" value="${name}"
							style="width: 180px" class="form-control" placeholder="请输入专家姓名"></td>
				
						
						
						<td align="left">
							<button class="btn btn-primary" type="button" id="submitBtn" onclick="submitForm()">查询</button>
							<button class="btn btn-primary" type="button" id="newsbt" onclick="submitForms()">新增</button>
							<input type="button" onclick="rese()"
							value="清空" class="btn btn-default">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-body">
		     <input type="hidden" name="chargConnection"  id="chargConnection" >
		<table class="table table-bordered table-condensed" >
		<thead><tr><th width='40'>操作</th><th>类型</th><th>费用</th><th>时间</th></tr></thead>
		<tbody id="shoufxlist">
		</tbody>
		</table>
		</div>
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
								<th align="center"   width="40px"><div class="xuanzhe" >选择</div></th>
								<th width="100px">操&nbsp;&nbsp;&nbsp;作</th>
								<th>年级</th>
					
	
							</tr>
						</thead>
						<c:forEach items="${data.content }" var="data">
							 <tr onclick="shoufeiS('${data.id}')">
							 
							   <td align="center">
<input name="subcheck" class="id"  type="checkbox" value="${data.id}" />

</td>
								<td> 
								   <a class="btn btn-primary  btn-xs" href="#" onclick="edit('${data.id}')">修改 </a>
								   <a class="btn btn-primary  btn-xs" href="javascript:void(0)" onclick="del('${data.id}')">删除 </a>
								</td>
								<td>
								${data.gradename}
								${data.chargeitem}
								
								</td>
							
								
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<tr>
				<td colspan=3>
				
				<button class="btn btn-primary" type="button" id="selectAll" >请选择</button>
				
				</td></tr>
			</table>	
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
	<c:if test="${num>0}">
		<tags:pagination page="${data }" formId="expertForm"></tags:pagination>
	</c:if>
</body>
</html>
<script type="text/javascript">
function shoufei(selectedId){
	//$("shoufxlist").html(id);
	

			$.ajax({
				url : "/zjk/chargeitem/"+selectedId+"/gradeitem",
				type : "get",
				data : {
					id : selectedId
				},
				dataType : "text",
				success : function(data){
					 var arr = $.parseJSON(data)
					    str="";
					    for (var i = 0, len = arr.length; i < len; i++) {
					        str += "<tr><td></td><td>"+arr[i].itemName+"</td><td>"+arr[i].amount+"</td><td>"+arr[i].periodic+"</td></tr>";
					    }				
					$("#shoufxlist").html(str);  
				},
				error : function(){
					alert("删除失败！");
				}
			});
	
} 

function fu(arr) {
	alert(arr.length)
    var str = "";
    for (var i = 0, len = arr.length; i < len; i++) {
        str += "<li>"+arr[i].id+"</li>";
        if (arr[i].list)
            str += fu(arr[i].list);
    }
    return "<ul>"+str+"</ul>";
}

$(document).ready(function() { 
	
		$("#yxx").val("${yxx}");
		$("#flag").val("${flag}");
        //反选


 $("#xuanzhess").bind("click",function(){ 

     //显示删除按钮
     /*if(this.checked == true){
        $("input[name='Delete'").css("display",'block');
     }else{
        $("input[name='Delete'").css("display",'none');
     }*/           
  }); 
 $(".xuanzhe").click(function() {
	 
     $("input:checkbox").each(function () {
         this.checked = !this.checked;

      })
	 
	 
 }) 
		$(".xuanzhes").click(function() {
			// var ids = document.getElementsByName("subcheck");                            
             //for(var i=0;i<ids.length;i++){
             //    ids[i].checked=true;
            // }
			
             
          
                 var ids = document.getElementsByName("subcheck");                            
                 for(var i=0;i<ids.length;i++){
                     if(ids[i].checked)
                         ids[i].checked=false ;
                     else
                         ids[i].checked=true ;
                 }
             
             
		}); 
        
		$("#selectAll").click(function() { 
			batchDeletes()
		}); 
		$("#newsbt").click(function() { 
			var url = "${ctx}/grade/new";
			$("#signImgFrame").attr('src', url);
			$("#signImgModal").modal();
		}); 
		
	});
	</script>