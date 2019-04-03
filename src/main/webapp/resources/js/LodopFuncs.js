var test = {};
test.browser = (function () {
	var ua = navigator.userAgent.toLowerCase();
	return {
		_IE : ua.indexOf('msie') > -1 && ua.indexOf('opera') == -1,
		_NEWIE : ua.indexOf('msie') == -1 && ua.indexOf('trident') > -1,
		_GECKO : ua.indexOf('gecko') > -1 && ua.indexOf('khtml') == -1,
		_WEBKIT : ua.indexOf('applewebkit') > -1,
		_OPERA : ua.indexOf('opera') > -1
	};
})();
test._is64IE = (test.browser._IE || test.browser._NEWIE) && (navigator.userAgent.indexOf('x64')>=0);
var createdLodop = null;
function getLodop(oOBJECT){
	var LODOP;

	if (!test.browser._IE && !test.browser._NEWIE) {
		// bootbox.alert('如使用快速打印（无需预览），请使用IE浏览器并安装打印控件！');
		return false;
	}

	try {
		if (oOBJECT != undefined) {
			LODOP = oOBJECT;
		}
		else {
			if (createdLodop == null) {
				LODOP = document.createElement("object"); 
				LODOP.setAttribute("width",0); 
				LODOP.setAttribute("height",0); 
				LODOP.setAttribute("style","position:absolute;left:0px;top:-100px;width:0px;height:0px;");  		     
				LODOP.setAttribute("classid","clsid:2105C259-1E0C-4534-8141-A753534CB4CA"); 
				document.documentElement.appendChild(LODOP); 
				createdLodop = LODOP;
			}
			else {
				LODOP = createdLodop;
			}
		}

		//LODOP.SET_LICENSES("","642454552515055626156606467561","688858710010010811411756128900","");
		//2016-9-12 购买 域名：http://www.jqiyun.cn/
		LODOP.SET_LICENSES("","F5BE77848D3F58DCB835FA07F0CAD8A3","C94CEE276DB2187AE6B65D56B3FC2848","4324F567B8F037EC25B8996ADC2D65AC");
		if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
		        if (test.browser._IE || test.browser._NEWIE)   {
		        	// bootbox.alert('打印控件未安装!不能正常使用打印功能。<br />请<a href=\"#32\">下载</a>并执行安装,安装后请刷新页面或重新进入。');
		        	return false;
		        }
		        
		}
		return LODOP;
	}
	catch(e) {
		//console.log(e);
	}
}

function browserTip() {
	if (!test.browser._IE && !test.browser._NEWIE) {
		$.zui.messager.warning('如使用打印功能，请使用IE浏览器并安装打印控件！');
		return false;
	}
	else if (test._is64IE) {
    	bootbox.alert('打印控件未安装! 不能正常使用打印功能。<br />请<a href=\"../download/install_lodop64.exe\">下载</a>并执行安装,安装后请刷新页面或重新进入。');
    	return false;
    }
    else if (test.browser._IE || test.browser._NEWIE)   {
    	bootbox.alert('打印控件未安装! 不能正常使用打印功能。<br />请<a href=\"../download/install_lodop32.exe\">下载</a>并执行安装,安装后请刷新页面或重新进入。');
    	return false;
    }
}