<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public/head.jsp"></jsp:include>
</head>
<body>
<!-- 
 <div class="demoTable">
  搜索ID：
  <div class="layui-inline">
    <input class="layui-input" name="id" id="demoReload" autocomplete="off">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
</div>
 -->
	







<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">银行列表</div>

				<div class="layui-form layui-card-header layuiadmin-card-header-auto">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">银行代码</label>
							<div class="layui-input-block">
								<input type="text" name="code" placeholder="请输入" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-inline">
							<label class="layui-form-label">全称</label>
							<div class="layui-input-block">
								<input type="text" name="fullName" placeholder="请输入" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-inline">
							<label class="layui-form-label">&nbsp;</label>
							<button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
					</div>
				</div>

				<div class="layui-card-body">
					<div style="padding-bottom: 10px;">
						<button class="layui-btn layuiadmin-btn-bankAdmin" data-type="batchdel">删除</button>
						<button class="layui-btn layuiadmin-btn-bankAdmin" data-type="add">添加</button>
					</div>
					<table class="layui-hide" id="test"></table>
					
				</div>
			</div>
		</div>
	</div>
</div>





</body>
</html>
<script src="${ctx}/resources/layuiadmin/layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#test'
    ,url:'${ctx}/employe/listaja'
    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    ,limit:5
    ,cols: [[
    	{type: 'checkbox'}
      ,{field:'id', title: 'ID', sort: true}
      ,{field:'employeName', title: '用户名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'jobNumber', title: '性别', sort: true}
      ,{field:'employeEmail', title: '城市'}
      ,{field:'graduateSchool', title: '签名'}
      ,{field:'inGartenDate', title: '职业', align: 'center'} //单元格内容水平居中
      ,{field:'nation', title: '积分', sort: true, align: 'right'} //单元格内容水平居中
      ,{field:'birthPlace', title: '评分', sort: true, align: 'right'}
     
    ]]
  ,page: true
  ,id: 'testReload'
  });
  var $ = layui.$, active = {
		    reload: function(){
		      var demoReload = $('#demoReload');
		    //  alert(demoReload.val())
		      //执行重载
		      table.reload('testReload', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		        	 id: demoReload.val()
		        }
		      });
		    }
		  };
		  
		  $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
	
  
});
</script>

