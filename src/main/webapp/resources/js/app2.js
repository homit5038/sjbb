var printWidth = $("#printerWidth").val();
if (printWidth == 199 || printWidth == 249 || printWidth == 2414 || printWidth == 1214 || printWidth == 129 || printWidth == 1914) {
    //横版打印机
    var printBillType = 1;
}
else {
    //竖版打印机
    var printBillType = 0;
}
//var myDate = new Date();
//var getFullYear=myDate.getFullYear(); 
//var datast=getFullYear+"-"+myDate.getMonth()
$("#datast").html(getTime());
$(function () {
	
	$("#childId").val("")
function checkSelArr(tarr,selected){
   var len=tarr.length+1;
   for(var i=0;i<len;i++){
      if(selected == tarr[i]){
         tarr.splice(i,1);
         return;//利用函数的返回功能中断push操作
      }
   };
   tarr.push(selected)
}
	
	
	
	$(document).on('click', '.charge-item', function () {
         var listt=$("#chargConnection").val();
         var a = listt.split(',')
			//if($(this).prop('checked')){
		 checkSelArr(a,$(this).val())
	     var strs = a.join(',');
	     if(strs.indexOf(',')==0){
	     	strs=strs.replace(',','')
	     }
	      $("#chargConnection").val(strs);
	     
	     
	     
});
$(document).on('click', '#kinder-list-table input[type=checkbox]', function (e) {
        e.stopPropagation();
        if (!$(this).prop("checked")) {
            $(this).parent().parent().removeClass("active");
        }
        else {
            $(this).parent().parent().addClass("active");
        }
       
        chargeCount();
    });
    
    
    
    
//收费项表格，点击行即选中本行
$(document).on('click', '#charge-item-table tr:not(:first)', function () {

	var thist=$(this)
	thist.addClass("active").siblings("tr").removeClass("active")
	//	$('#kinder-list-table tr').empty(); 

	$("#childIdList").val(thist.attr("data-id")); 
	//var choose_name_2 = $(this).children(".childId").html();
	var choose_name_1=thist.find(".childId").html();
	thist.attr("data-id")
	
	$("#childId").val(thist.attr("data-id")); 
	$("#childIdList").val(thist.attr("data-id")); 
	thist.attr("data-id")
	$('#selected-kinder').html(choose_name_1)
	var chooseid=thist.children(":first").attr("data-id") 

			
  	$("#kinder-list-table tr:not(:first)").each(function(){
  		var thistt=$(this)
  	    	thistt.addClass("hidden");
  		    thistt.find("input[type=checkbox]").prop("checked", false);
  		 

  		   
  	  });		
			
		
	var arr = chooseid.split(','),dda=[];
	for(var i in arr){
		dda.push(kinderlist(arr[i]))
		//darta+=kinderlist(arr[i]);
		}
	var strs = dda.join(',');
	if(strs){
		$("#chargConnection").val(strs)
	}else{
		$("#chargConnection").val("")
	}

  
	//allv()
	chargeCount()
	//connected($(this).attr("data-id"))
	
	
	
	  //查询缴费历史
        var weChatIsSet=$("#weChatIsSet").val();
        var alipayIsSet=$("#alipayIsSet").val();
        
        
        
        jQuery.ajax({
            url: 'findChildHistory',
           // data: 'childId=1' + $("#childId").val(),
            data: 'childId=1',
            method: 'get',
            dataType: 'json',
            success: function (data) {
                $("#table-charge-history tr:not(.table-head)").remove();
                for (var i in  data) {
                    var tr = $('<tr></tr>');
                    var flowCode = $('<td width="20%">' + data[i].flowCode + '</td>');
                    var amount = $('<td  width="20%">' + data[i].payed + '</td>');
                    var payDate = $('<td  width="20%">' + new Date(data[i].payDate).format("yyyy-MM-dd") + '</td>');
                    var payType = '';
                    var col = $('<td  width="20%"><a cid="' + data[i].cid + '" name="' + data[i].flowCode + '" class="printbill" href="javascript:void(0);">补打小票</a>' +
                        ' &nbsp;&nbsp;&nbsp;<a name="' + data[i].cid + '" class="delhistory" href="#">删除</a></td>');
                    var col2=$('<td  width="20%"><a cid="' + data[i].cid + '" name="' + data[i].flowCode + '" class="printbill" href="javascript:void(0);">补打小票</a></td>');
                    if (data[i].payType == '1') {
                        payType = $('<td  width="20%">现金</td>');
                        tr.append(flowCode, amount, payDate, payType, col);
                    }
                    if (data[i].payType == '2') {
                        payType = $('<td  width="20%">刷卡</td>');
                        tr.append(flowCode, amount, payDate, payType, col);
                    }
                    if (data[i].payType == '3'&&weChatIsSet!=0){
                        payType = $('<td  width="20%">微信</td>');
                        tr.append(flowCode, amount, payDate, payType, col2);
                        // tr.append(flowCode, amount, payDate, payType, col);
                    }
                    if (data[i].payType == '3'&&weChatIsSet==0){
                        payType = $('<td  width="20%">微信</td>');
                        tr.append(flowCode, amount, payDate, payType, col);
                    }
                    if (data[i].payType == '4'&&alipayIsSet==0) {
                        payType = $('<td  width="20%">支付宝</td>');
                        tr.append(flowCode, amount, payDate, payType, col);
                    }
                    if (data[i].payType == '4'&&alipayIsSet!=0) {
                        payType = $('<td  width="20%">支付宝</td>');
                        tr.append(flowCode, amount, payDate, payType, col2);
                    }
                    $("#table-charge-history").append(tr);
                }
            }
        });
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});



 //删除历史缴费
    $(document).on('click', '.delhistory', function (e) {
        e.preventDefault();
        var cid = $(this).attr("name");
        var initChildId = $("#initChildId").val();
        bootbox.confirm("是否确定删除？", function (result) {
            if (result) {
                jQuery.ajax({
                    url: $("#path").val() + '/charge/delChargingInfoHistory.do',
                    data: 'cid=' + cid,
                    method: 'post',
                    success: function (data) {
                        if ('norlue' == data) {
                            bootbox.alert("无权限！");
                        }
                        if ('success' == data) {
                            bootbox.alert("删除成功！");
                            $("#initChildId").val(initChildId);
                            setTimeout(function () {
                                location.reload();
                            }, 1500)
                        }
                        if ('false' == data) {
                            bootbox.alert("历史月份费用已结算，无法删除！");
                        }
                    }
                });
            }
        })


    });




//修改收费合计
function chargeCount() {
		  var amount = 0;
		  $("#kinder-list-table tr").each(function () {
		      if ($(this).find(".charge-item").prop("checked") == true) {
		          amount += parseFloat($(this).find(".charge-amount").val());
		         // alert(amount)
		      }
		  })
		  $("input[name=charge-should-pay]").val(parseFloat(amount).toFixed(2));
		  $("input[name=charge-real-pay]").val(parseFloat(amount).toFixed(2));
		
		  var realpay = parseFloat($("input[name=charge-real-pay]").val());
		  if (realpay == null) {
		      realpay = 0;
		  }
		  var chargeReturn = realpay - parseFloat($("input[name=charge-should-pay]").val());
		  if (isNaN(chargeReturn)) {
		      $("input[name=charge-return]").val(parseFloat("0").toFixed(2));
		  }
		  else {
		      if (chargeReturn >= 0) {
		          $("input[name=charge-return]").val(parseFloat(chargeReturn).toFixed(2));
		      } else {
		          $("input[name=charge-return]").val(parseFloat("0").toFixed(2));
		      }
		  }
		  
		

}

//“实交”文本框处理
$("input[name=charge-real-pay]").focus(function () {
    $(this).val() == 0 ? $(this).val("") : "";
}).keyup(function () {
    $(this).val($(this).val().replace(/[^\.|\d]|^0/g, ''));
    var chargeReturn = parseFloat($(this).val()) - parseFloat($("input[name=charge-should-pay]").val());
    if (isNaN(chargeReturn)) {
        $("input[name=charge-return]").val(parseFloat("0").toFixed(2));
    }
    else {
        if (chargeReturn >= 0) {
            $("input[name=charge-return]").val(parseFloat(chargeReturn).toFixed(2));
        } else {
            $("input[name=charge-return]").val(parseFloat("0").toFixed(2));
        }
    }

}).bind("paste", function () {
    $(this).val($(this).val().replace(/[^\.|\d]|^0/g, ''));
});

function allv(){
	
	var text="";  
        $("input[name='chargeitem']").each(function() {  
            if ($(this).prop("checked")) { 
            	
                text += ","+$(this).val();  
            }  
        });  
         alert(text);
}








function kinderlist(x){
	//$("#kinder-list-table tr:not(:first)").removeClass("active").addClass("hidden")
	var lists="";
  	$("#kinder-list-table tr").each(function(){
  		var tss=$(this)
  	    if(x==tss.attr("data-id")){
  	    	// alert(arr[i]+"==="+$(this).attr("data-id"))
  	    	 //console.log(tss.parents("table"))
  	    	tss.find("input[type=checkbox]").prop("checked", true);
  	    	tss.removeClass("hidden");
  	    	lists=tss.attr("data-id")
  	    }
  	    
  	  });
	return lists;
}


function connected(id){
	// jQuery('#contoned').html("loading...")
		$('#contoned tr').empty(); 
		
	    $.post("${ctx}/chargeitem/" + id + "/conne", {
		id : id
	}, function(data) {
		//alert(data.length)
	
		
		 $("#kinder-list-table tr:not(:first)").addClass("hidden");
		if(data.length>1000){
			
			//alert(0)
			  
			
		}else{
			  json = eval(data); 
			for(var i = 0; i < json.length; i++){
				
				 // var tr = $('<tr></tr>');
	             // var cid = $('<td width="10%" class="td-select"><input type="checkbox" class="charge-item"  value="' + json[i].id + '" /></td>');
	              //var chargingName = $('<td width="30%" class="charge-name">' + json[i].amount + '</td>');
	              //var chargingPeriod = $('<td width="20%">' + json[i].periodic + '</td>');
	             // var chargingAmount = "";

	             // tr.append(cid, chargingName, chargingPeriod);
	             // $("#contoned").append(tr);
	            
	           
				
	         
				
	            //  $("#kinder-list-table tr").each(function(){
	            	 //   if(json[i].id==$(this).attr("data-id")){
	            	    //	
	            	    	//$(this).addClass("active").removeClass("hidden");;
	            	   // }
	            	    
	            	 // });
	              
	              
				
				 // bbs +=("<tr><td>"+json[i].itemName + "</td><td>" + json[i].amount+"</td></tr>");
				}
		
			//$('#contoned').append(bbs); 
			
	
		}
	});
	
}






$(document).on('click', "#display-all-items", function () {
	 $("#kinder-list-table tr:not(:first)").removeClass("hidden");
})


$(document).on('click', "#doCharge", function () {
	 var doflag = true;
	
    if (doflag) {
    	var chargConnection=$("#chargConnection").val()
        var shouldpay = parseFloat($("#shouldpay").val());
        var realpay = parseFloat($("#realpay").val());
        var childId = $("#childId").val()
        if (isNaN(realpay)) {
            bootbox.alert("实收费用不能为空！");
            return false;
        }
       // alert(childId)
       if (!childId) {
            bootbox.alert("请先选择孩子！");
            return false;
        }
       if (!chargConnection) {
            bootbox.alert("请选择收费项！");
            return false;
        }
        
        if (shouldpay > realpay) {
        	
            bootbox.dialog({
                message: "实收金额小于应收金额请选择操作：",
                title: "提示",
                buttons: {
                    success: {
                        label: "优惠",
                        className: "btn-success",
                        callback: function () {
                            $("#favourable").val("1");
                            $(".btn-success").attr("disabled",true);
                            //addCharge(doflag);

                        }
                    },
                    danger: {
                        label: "欠费",
                        className: "btn-danger",
                        callback: function () {
                            $("#favourable").val("0");
                            $(".btn-danger").attr("disabled",true);
                            //addCharge(doflag);
                        }
                    }
                }
            });
        } else {
        	
            addCharge(doflag);
        }
    }
    
    function addCharge(flag) {
        var payType=$("input[name='paytype']:checked").val();
        var weChatIsSet=$("#weChatIsSet").val();
        if("3"==payType &&weChatIsSet!=0){
            //接入扫描枪
            $("#add-pay-code-modal").modal("show");
            $("#payCode").val("");
            $("#payCode").focus();
        }else{
            addChargeToReal(flag);
        }
    }
    });

});
function addChargeToReal(flag){
if (flag) {
    doflag = false;
    var oid = [];
    var temp = [];
    var chargeItemToPrint = [];
    var colCount = 0;
    $("#kinder-list-table tr").each(function () {
        if ($(this).find(".charge-item").prop("checked") == true) {
        
            var chargingItemName = $(this).find(".charge-name").html();
            var chargingAmount = $(this).find(".charge-amount").val();
            var chargeItemId = $(this).find(".charge-item").val();
            var printGroup = $(this).find(".print-group").val();
            var flowCode = $("#flowCode").val();
            var inOutFlag = "1";
            if (parseFloat(chargingAmount) < 0) {
                var inOutFlag = "0";
            }
      
            temp.push("{'chargingItemName':");
            temp.push("'" + chargingItemName + "'");
            temp.push(",'chargingAmount':");
            temp.push(chargingAmount);
            temp.push(",'chargeItemId':");
            temp.push("'" + chargeItemId + "'");
            temp.push(",'flowCode':");
            temp.push("'" + flowCode + "'");
            temp.push(",'inOutFlag':");
            temp.push("'" + inOutFlag + "'");
            temp.push("},");

            //添加打印小票
            if (chargeItemToPrint[printGroup] == undefined) {
                if (printBillType == 0) {
                    chargeItemToPrint[printGroup] = "";
                }
                else if(printBillType == 1) {
                    chargeItemToPrint[printGroup] = '<tr class="othertr">';
                }
            }
            if (printBillType == 0) {
                chargeItemToPrint[printGroup] += '<tr class="othertr">';
                chargeItemToPrint[printGroup] += '<td>' + chargingItemName + '</td>';
                chargeItemToPrint[printGroup] += '<td>' + chargingAmount + '元</td>';
                chargeItemToPrint[printGroup] += '</tr>';
            }else {
                if (colCount % 2 == 0){
                    chargeItemToPrint[printGroup] += '</tr><tr class="othertr">';
                }
                chargeItemToPrint[printGroup] += "<td>" + chargingItemName + "：" + chargingAmount + "元 </td>";
                colCount++;
            }
        }
    });
    colCount = 0;
   
    oid.push(temp.join(""));
    // $("#doCharge").attr("disabled",true);
 
    jQuery.ajax({

        url: 'addchargepay?' + $('#myform').serialize(),
        data: {"oid": oid},
        method: 'post',
        async: false,

        error: function (request) {
            bootbox.alert("缴费失败！");
            doflag = true;
        },
        success: function (data) {
        	
            if (data != null) {
                bootbox.alert("缴费成功！");
                setPrint(data);
                var printGroup = $("#printGroup").val();
               
                for (var i = 1; i <= printGroup; i++) {
                	 
                    if (chargeItemToPrint[i] == "" || chargeItemToPrint[i] == undefined) {
                        continue;
                    }
                  
                    $("#printtable .othertr").remove();
                    $("#printtable").append($(chargeItemToPrint[i]));
                    	
                    printTicket($("#printerName").val(), "#wrap", $("#printerWidth").val());
                }
                doflag = true;

            } else {
                bootbox.alert("缴费失败！");
                doflag = true;
            }

            setTimeout(function () {
                //window.location.reload();
                var childId = $("#childId").val();
                var pyInput =$(".kinder-search").val();
               window.location.href ="chargemain?childId=" + childId+"&pinYin="+pyInput;
                // window.location.href = $("#path").val() + "/charge/chargeMain.do?childId=" + childId;
            }, 2000)
        }
    });
}
}

//给小票赋值
function setPrint(flowCode) {
  //小票号
  $("#kidFlowCode").html(flowCode);
  //时间
  var time = getTime();
  $("#kidTime").html(time);
  //总共实收应收找零
  $("#kidTotalMoney").html($("#shouldpay").val());
  $("#kidRealMoney").html($("#realpay").val());

  if (printBillType == 1) {
      $("#kidRealMoneyUppercase").html(digit_uppercase($("#realpay").val()));
  }
  var checkedPaytype = $("input[name='paytype']:checked").val();
  var paytype =  '';
  if(checkedPaytype==1){
      paytype="现金";
  }
  if(checkedPaytype==2){
      paytype="刷卡";
  }
  if(checkedPaytype==3){
      paytype="微信"
  }
  if(checkedPaytype==4){
      paytype="支付宝";
  }
  $("#kidPaytype").html(paytype);
  $("#kidShouldMoney").html($("#shouldpay").val());
  $("#kidReturnMoney").html($("#returnpay").val());
  $("#kidBz").html($("#bz").val());
}