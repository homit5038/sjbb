$(function(){
	setHeight();
});

$(document).resize(function () {
	$(".page-content").css({"height":""});
	setHeight();
});

//页面高度自适应
function setHeight() {
	var windowHeight = $(document.body).height()-84;
	var contentHeight = $(".page-content").height();
	height = Math.max(windowHeight, contentHeight);
	$(".menu").css({"height":height+"px"});
	$(".page-content").css({"height":height+"px"});
}

//全屏提醒
$("#fullscreen-btn").click(function(e){
	e.preventDefault();
	bootbox.alert("请使用键盘<b><font color='red'>F11</font></b>键，进入全屏模式使用。以获得更好的使用体验！");
});

//只读控件编辑时的提醒
$(document).on('focus', 'input[readonly]:not(.time,.form-date,.form-city)', function(){
	$.zui.messager.warning("请先点击“编辑”按钮，再进行编辑！");
	$(this).blur();
	$(this).parent().parent().find(".charge-item-edit-btn").focus();
});

//文本框输入数字限制
$(document).on("keyup", ".NumDecText", function(e){
	if (e.keyCode == 37 || e.keyCode == 38 || e.keyCode == 39 || e.keyCode == 40) {
		return ;
	}
	$(this).val($(this).val().replace(/[^0-9.-]/g,''));
}).bind("paste",function(){  //CTR+V事件处理
	$(this).val($(this).val().replace(/[^0-9.-]/g,''));
}).css("ime-mode", "disabled");

//文本框为空自动填充0
$(document).on("blur", ".NumDecText", function(){
	if(''==$(this).val()){
		$(this).val($(this).val().replace('','0'));
	}
});

//获取时间
function getTime() {
	var myDate = new Date();
	var time = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " ";
	//time += myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds();
	return time;
}

//记录票据广告打印次数
function printCount(){
	var cids = "";
	$(".advCid").each(function() {
		cids += $(this).val() + ",";
	});
	cids = cids.substr(0, cids.length-1);
	$.ajax({
		url: $("#path").val() + '/charge/advPrintCount.do',
		data: 'cid=' + cids,
		method: 'get',
		dataType: 'text',
		success: function (data) {
			return;
		}
	})
}

//回车键进入下一行
$(document).on("keyup", ".enter-to-next-col", function(e) {
	if (e.keyCode == 13) {
		$(this).parent().parent().next().find("input[type=text]:first").focus();
	}
});

//禁用ajax缓存
$.ajaxSetup({cache:false});

//金额转换为大写
function digit_uppercase(n) {
	var fraction = ['角', '分'];
	var digit = [
		'零', '壹', '贰', '叁', '肆',
		'伍', '陆', '柒', '捌', '玖'
	];
	var unit = [
		['元', '万', '亿'],
		['', '拾', '佰', '仟']
	];
	var head = n < 0? '负': '';
	n = Math.abs(n);
	var s = '';
	for (var i = 0; i < fraction.length; i++) {
		s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
	}
	s = s || '整';
	n = Math.floor(n);
	for (var i = 0; i < unit[0].length && n > 0; i++) {
		var p = '';
		for (var j = 0; j < unit[1].length && n > 0; j++) {
			p = digit[n % 10] + unit[1][j] + p;
			n = Math.floor(n / 10);
		}
		s = p.replace(/(零.)*零$/, '')
				.replace(/^$/, '零')
			+ unit[0][i] + s;
	}
	return head + s.replace(/(零.)*零元/, '元')
			.replace(/(零.)+/g, '零')
			.replace(/^整$/, '零元整');
}

//禁用文本框中回车键自动提交表单
$(document).on("keydown", ".enter-not-submit", function(e) {
	if (e.keyCode == 13) {
		return false;
	}
});