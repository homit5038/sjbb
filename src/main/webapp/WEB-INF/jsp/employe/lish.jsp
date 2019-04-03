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
								<input type="text" name="employeName" placeholder="请输入" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-inline">
							<label class="layui-form-label">全称</label>
							<div class="layui-input-block">
								<input type="text" name="jobNumber" placeholder="请输入" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-inline">
							<label class="layui-form-label">&nbsp;</label>
							<button class="layui-btn layuiadmin-btn-bankAdmin" lay-submit lay-filter="LAY-user-front-search">
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
					
					<table class="layui-hide" id="bank-list" lay-filter="bank-list"></table>
				</div>
			</div>
		</div>
	</div>
</div>





</body>
</html>
<script src="${ctx}/resources/layuiadmin/layui/layui.js"></script>
<script type="text/html" id="barAction">
<!--<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>  -->    
<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>    
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>

<script>
	layui.config({
		base: '${ctx}/resources/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'form', 'upload','table'], function () {
		var $ = layui.$;
		var form = layui.form;
		var upload = layui.upload;
		var newVar = [
			{type: 'checkbox'},
			  {field: 'id', title: 'ID', width: 100, sort: true}
			, {field: 'employeName', title: '姓名'}
			, {field: 'jobNumber', title: '工号'}
			, {field: 'graduateSchool', title: '毕业院校'}
			, {field: 'inGartenDate', title: '进园时间', sort: true}
			, {field: 'nation', title: '民族'}
			, {field: 'employeEmail', title: '电子邮箱', minWidth: 150}
			, {field: 'birthPlace', title: '贯籍:'}
			, {templet: '<div>{{d.locked?"锁定":"未锁定"}}</div>', title: '是否锁定'}
			, {title: '操作', fixed: 'right', align: 'center', toolbar: '#barAction'}
		];
		var table = layui.table;
		table.render({
			elem: '#bank-list',
			url: '${ctx}/employe/listaja',
			height: '500',
			cellMinWidth: 10,
			page: true,
			page: {limit: 5},
			cols: [newVar]
		});
		
		
	
		/**
		 * 新增或修改
		 * @param id Long- id
		 * @param action String- edit ,show ,add
		 */
		function addOrUpdateBank(id, action) {
			
			layer.open({
				type: 2,
				title: '职工信息',
				//var url = "${ctx}/employe/" + id + "/edit";
				content: '${ctx}/employe/' + action + '?id=' + id,
				//content:"${ctx}/employe/" + id + "/edit",
				maxmin: true,
				area: ['900px', '500px'],
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					var iFrameWindow = window['layui-layer-iframe' + index],
						submitID = 'LAY-user-front-submit',
						
					     submit = layero.find('iframe').contents().find('#' + submitID);
					//监听提交
					iFrameWindow.layui.form.on('submit(' + submitID + ')', function (result) {
					//	alert(0)
						var field = result.field; //获取提交的字段
						//提交 Ajax 成功后，静态更新表格中的数据
						
						$.post("${ctx}/employe/"+ action +"/"+id, field, function (result) {
					
							if (result.success) {
								layer.msg(result.result, {icon: 1, time: 1500});
								table.reload('bank-list'); //数据刷新
								layer.close(index); //关闭弹层
							} else {
								layer.msg(result.message, {icon: 2, time: 1500});
							}

						});
					});
					submit.trigger('click');
				}
			});
		}

		table.on('tool(bank-list)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var result = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的DOM对象
			switch (layEvent) {
				case "edit":
					addOrUpdateBank(result.id, "edit");
					break;
				case "delete":
					layer.confirm('确定删除吗?', {icon: 0, title: '提示'}, function (index) {
						$.post("${ctx}/employe/delete/" + result.id, function (result) {
							if (result.success) {
								layer.msg(result.result, {icon: 1, time: 1000});
							} else {
								layer.msg(result.message, {icon: 2, time: 1000});
							}
							table.reload('bank-list'); //数据刷新
							layer.close(index); //关闭弹窗
						});
					});
					break;
				case "add":
					break;
				default :
					break;

			}
			 table.reload('bank-list', {
				    initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
				    ,where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
				      field: obj.field //排序字段   在接口作为参数字段  field order
				      ,order: obj.type //排序方式   在接口作为参数字段  field order
				    }
				  });
			
		});

		form.on('submit(LAY-user-front-search)', function (result) {
			var field = result.field;
			//执行重载
			console.log(field)
			table.reload('bank-list', {
				 page: {
					 curr: 1 
				 },
				where: field
			});
		});

		$('.layui-btn.layuiadmin-btn-bankAdmin').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

		var active = {
			batchdel: function () {
				var checkStatus = table.checkStatus('bank-list'),
					checkData = checkStatus.data; //得到选中的数据

				if (checkData.length === 0) {
					return layer.msg('请选择数据');
				}
				layer.prompt({
					formType: 1,
					title: '批量操作，请验证用户密码'
				}, function (value, index) {
					layer.close(index);
					$.post("/validUser", {password: value}, function (result) {
						if (result.success) {
							layer.confirm('确定删除吗？', function (index) {
								$.post("/yszjjg/bank/deleteAll", {'data': JSON.stringify(checkData)}, function (result) {
									if (result.success) {
										table.reload('bank-list');
										layer.msg(result.result, {icon: 1, time: 1000});
									} else {
										layer.msg(result.message, {icon: 2, time: 1000});
									}
								});
								layer.close(index);
							});
						}
					});
				});
			},
			add: function () {
				
				addOrUpdateBank(-1, "add");
			}
		}
	});

</script>


