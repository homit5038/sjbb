 var printWidth = $("#printerWidth").val();
 
  //$.zui.messager.success("保存成功！");
    if (printWidth == 199 || printWidth == 249) {
        //横版打印机
        var printBillType = 1;
    }
    else {
        //竖版打印机
        var printBillType = 0;
    }

    $(function () {
        var doflag = true;
        templetChange();
        getClass();
        $(".time").datetimepicker({
            language: "zh-CN",
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: "yyyy-mm-dd"
        });

        $(".time-now").val(getTime());

        //收费
        $('#saveBtn').click(function () {
            thinkPriceForPay();
            var flagForm = true;
            var flagMoney = true;
            $(".formItem").each(function () {
                if ($(this).val() == null || $(this).val() == "") {
                    flagForm = false;
                }
            });

            $(".money").each(function () {
                if ($(this).val() == null || $(this).val() == "") {
                    flagMoney = false;
                }
            });
            $(".moneyName").each(function () {
                if ($(this).val() == null || $(this).val() == "") {
                    flagMoney = false;
                }
            });

            if (!flagForm) {
                bootbox.alert("幼儿信息不能为空！");
                return;
            }
            if (!flagMoney) {
                bootbox.alert("收费信息不能为空！");
                return;
            }
            var realpay = parseFloat($("#realpay").val());
            if (isNaN(realpay)) {
                bootbox.alert("实收费用不能为空！");
                return false;
            }

            if (doflag) {
                var shouldpay = parseFloat($("#shouldpay").val());
                var realpay = parseFloat($("#realpay").val());
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
                                    addChargeTemp(doflag);
                                }
                            },
                            danger: {
                                label: "欠费",
                                className: "btn-danger",
                                callback: function () {
                                    $("#favourable").val("0");
                                    addChargeTemp(doflag);


                                }
                            }
                        }
                    });
                } else {
                    addChargeTemp(doflag);
                }
            }

        });
        //添加临时收费时调用的方法
        function addChargeTemp(doflag) {
            var payType=$("input[name='paytype']:checked").val();
            if("3"==payType){
                jQuery.ajax({
                    url: $("#path").val() + '/finance/openSet.do?payType='+payType,
                    method:'post',
                    success: function (data) {
                        if("isSetted"==data){
                            //接入扫描枪
                            $("#add-pay-code-modal").modal("show");
                            $("#payCode").val("");
                            $("#payCode").focus();
                        }else{
                            addTempChargeReal(doflag);
                        }
                    }
                });
            }else{
                addTempChargeReal(doflag);
            }

        }
        $(document).on("keydown" ,"#payCode",function (e) {
            e = e||event;
            var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
            if(keyCode==13){
                e.preventDefault();
                keyDownCode(true);
            }
        });
        $(document).on("click","#pay-code-save-btn",function () {
            keyDownCode(true);
        });
        //添加临时收费
        $("#add-charge-btn").click(function () {

            var tr = $('<tr class="columntr"></tr>');
            var chargeNameTd = $('<td><input name="chargeName" type="text" class="moneyName form-control" focus="focus" value=""/></td>');
            var chargeCountTd = $('<td><input name="chargeAmount"  type="text" class="money NumDecText form-control" value="0" /></td>');
            var editTd = $('<td><button type="button" class="btn btn-primary charge-item-edit-btn">保存</button> <button type="button" class="btn charge-item-del-btn">删除</button></td>');
            tr.append(chargeNameTd, chargeCountTd, editTd);
            $("#kinder-list-table").prepend(tr);
            tr.find("input:first").focus();
            thinkPrice();
        });

        //编辑收费项
        $(document).on("click", ".charge-item-edit-btn", function () {
            if ($(this).html() == "编辑") {
                $(this).parent().parent().find("input").removeAttr("readonly");
                $(this).parent().parent().find("input:first").focus();
                $(this).addClass("btn-primary");
                $(this).html("保存");
            }
            else if ($(this).html() == "保存") {
                $(this).parent().parent().find("input").attr("readonly", "true");
                $(this).removeClass("btn-primary");
                $(this).html("编辑");
                $.zui.messager.success("保存成功！");
            }
        });


        //删除临时收费项
        $(document).on("click", ".charge-item-del-btn", function () {
            $(this).parent().parent().remove();
            thinkPrice();
        });


        $('#select-kinder-btn').modalTrigger({
            'shown': function () {
                $('#kinder-search').focus();
            }
        });


    })

    //绑定 计算找零
    $('.pay').bind('input propertychange', thinkReturn);
    //绑定 添加表格中收费项改变
    $('#kinder-list-table').bind('input propertychange', thinkPrice);

    //计算应收费用
    function thinkPrice() {
        var total = 0.0;
        $(".money").each(function (i) {
            //alert($(this).val());
            total += parseFloat($(this).val());
        });
        //alert(total);
        $("#shouldpay").val(total);
        $("#realpay").val(total);
        thinkReturn();
    }

    //计算应收费用
    function thinkPriceForPay() {
        var total = 0.0;
        $(".money").each(function (i) {
            //alert($(this).val());
            total += parseFloat($(this).val());
        });
        //alert(total);
        $("#shouldpay").val(total);

        thinkReturn();
    }

    //计算找零
    function thinkReturn() {
        var shouldpay = parseFloat($("#shouldpay").val()).toFixed(2);
        var realpay = parseFloat($("#realpay").val()).toFixed(2);
        var returnpay = parseFloat($("#returnpay").val()).toFixed(2);
        var cha = realpay - shouldpay;

        if (cha >= 0) {
            $("#returnpay").val(parseFloat(cha).toFixed(2));
        } else if (isNaN(cha)) {
            $("#returnpay").val(parseFloat("0").toFixed(2));
        } else {
            $("#returnpay").val(parseFloat("0").toFixed(2));
        }
    }

    //现金刷卡
    $(".paytype").change(function () {
        var value = $("input[name='paytype']:checked").val();
        //如果为刷卡 则需要判断是否绑定
        if (value == '2') {
            jQuery.ajax({
                url: '/ticket/selectBankCardPosFlagByKid.do',
                method: 'post',
                async: false,
                success: function (data) {
                    if (data == 'success') {
                    } else if (data == 'false') {
                        $.zui.messager.warning("未绑定银行卡 请先绑定银行卡！");
                        $("#paytypeCash").click();
                    }
                }
            });
        }
        value = $("input[name='paytype']:checked").val();
        $("#paytype").val(value);
    });
    //select 切换触发事件
    $(document).on('change', '#charge-template', templetChange);
    $(document).on('change', '#grade', getClass);

    function templetChange() {
        //alert($("#templet").val());
        $("#kinder-list-table  .columntr").remove();
        $.ajax({
            cache: true,
            type: "POST",
            url: "/youer/ticket/selecttemporarychargeinfo",
            data: "pid=" + $("#charge-template").val(),
            async: false,
            error: function (request) {
                bootbox.alert("链接失败！");
            },
            success: function (data) {
                for (var i in data) {

                    var tr = $('<tr class="columntr"></tr>');
                    var chargeNameTd = $('<td><input name="chargeName" readonly=”readonly” type="text" class="form-control " focus="focus" value="' + data[i].chargeName + '"/></td>');
                    var chargeCountTd = $('<td><input name="chargeAmount" readonly=”readonly”  type="text" class="money NumDecText form-control" value="' + data[i].chargeAmount + '" /></td>');
                    var editTd = $('<td><button type="button" class="btn charge-item-edit-btn">编辑</button> <button type="button" class="btn charge-item-del-btn">删除</button></td>');
                    tr.append(chargeNameTd, chargeCountTd, editTd);
                    $("#kinder-list-table").append(tr);
                }
            }
        });
        thinkPrice();
    }

    $("#add-charge-tmp").click(addtemp);

    //添加模板
    function addtemp() {
        bootbox.prompt("请输入模板名称:", function (name) {
        
            if (name) {

                $("#tempName").val(name);
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: "/youer/ticket/chargeaddtemporary",
                    data: $('#myform').serialize(),// 你的formid
                    async: false,
                    error: function (request) {
                        bootbox.alert("链接失败！");
                    },
                    success: function (data) {
                    	
                    	
         //for (var i in  data) {
                    
                 //alert(data[i].chargeName)
               
                    	
                    	//console.log("==========="+data.tempname)
                     //console.log("==========="+data[0].tempname)
                    	
                    	// // var  testJson = eval(data);  
                    	 // alert(data.chargeName)
                        $('#charge-template').append('<option value=' + data.id + ' selected="selected">' + data.tempname + '</option>');
                        bootbox.alert("添加成功!");
                    } 
                    }
                    
                //}
                );
            }
        });
    }

    //获取class

    function getClass() {
//alert(3)
        var gradeId = $("#grade").val();
        var classId = $("#class").val();
       // alert(data.classe)
        if(gradeId!=null){
            $.ajax({
                url: '/youer/classe/queryclassbygradeid',
                data: 'gradeId=' + gradeId,
                method: 'post',
                async: false,
                dataType: 'json',
                success: function (dat) {
                    $("#class").empty();
                  
                     var count=Object.keys(dat.data).length
                    if(count==0){
	                    bootbox.alert("该年级下面还没有班级，请添加班级！");
	                    
                    }else{
	                    for (var i in dat.data) {
	                        $("#class").append('<option value=' + dat.data[i][0] + '>' + dat.data[i][1] + '</option>');
	                    }
	                 }
	         

                    }
                   
                }
            );
        }

    }

    //选择幼儿类别
    $("#kinder-type").change(changeChildType);
    function changeChildType() {

        if ($("#kinder-type").val() == 1) {
            $(".formItem").attr("disabled", true);
            $("#select-kinder-btn").click();
        } else if ($("#kinder-type").val() == 0) {
            $(".formItem").attr("disabled", false);
            $(".formItem").val(null);
            $("#childId").val(null);
        }
    }

    //关闭之后刷新当前页面
    $("#closeTempCharge").click(function () {
        window.location.reload();
    });
 $("#clearFrom").click(function () {
     return false
         //this.from.reset();
 });



    $('#addTmpCharge').on('hidden.zui.modal', function () {

        //window.location.reload();

    })

    //根据日期查询
    function docheck() {

        $("#checkform").attr("action", "/ticket/chargeTemporaryList.do");
    }

    //给小票赋值
    function setPrint(flowCode) {
        //小票号
        $("#kidFlowCode").html(flowCode);
        //时间
        var time = getTime();
        $("#kidTime").html(time);
        $("#payTime").html(time);
        $("#kidNum").html($("#childSchoolId").val());
        if($("#childSchoolId").val() == '' || $("#childSchoolId").val() == null){
           // findChildSchoolIdByflowCode


            jQuery.ajax({
                url: '/ticket/findChildSchoolIdByflowCode.do',
                data: 'flowCode=' + flowCode,
                method: 'post',
                async: false,
                dataType: 'json',
                success: function (data) {
                    $("#kidNum").html(data);
                }
            });


        }
        //置空
        $("#childSchoolId").val("");
        //总共实收应收找零
        $("#kidTotalMoney").html($("#shouldpay").val());
        $("#kidRealMoney").html($("#realpay").val());
        $("#kidShouldMoney").html($("#shouldpay").val());
        $("#kidReturnMoney").html($("#returnpay").val());
        $("#kidBz").html($("#bz").val());

        if (printBillType == 1) {
            $("#kidRealMoneyUppercase").html(digit_uppercase($("#realpay").val()));
        }
        var paytype="现金";
        var checkedType=$("input[name='paytype']:checked").val();
        if(checkedType==2){
            paytype="刷卡";
        }else if(checkedType==3){
            paytype="微信";
        }else if(checkedType==4){
            paytype="支付宝";
        }

        $("#kidPaytype").html(paytype);
    }

    $("span[name='page']").click(function () {
        var p = $(this).attr("id");
        var pageNum = p.split('_')[1];
        $("#pageNum").val(pageNum);
        $("#sForm").attr("action", "chargeTemporaryList.do").submit();
    });

    $(document).on("click", "#export", function () {
        var headtemp = [];
        var temp = [];

        var content = [];

        var all = "";
        var templete = "";
        //获取表头
        $("#charge-history-table tr.firstcol").each(function () {
            $(this).find("th").each(function (i) {
                templete += $(this).text() + ",";
            });
            templete = templete.substr(0, templete.length - 1)
            all += templete + "@";
        })


        //获取内容
        $("#charge-history-table tr.columntr").each(function () {
            templete = "";
            $(this).find("td").each(function (i) {
                templete += $(this).text() + ",";
            });
            templete = templete.substr(0, templete.length - 1);
            all += templete + "@";
        })

        var bd = $("#start-time").val()
        var ed = $("#end-time").val()

        $("#Formall").val(all)
        $("#Formbd").val(bd)
        $("#Formed").val(ed)
        var exlname = "临时收据"

        $("#Formexlname").val(exlname);
        document.formx1.submit();

    });


    //删除当前模板
    $("#delete-charge-tmp").click(function() {

        bootbox.confirm("是否确定删除？", function (result) {
            if (result) {
                var pid = $("#charge-template").val();
                var param = {
                    "pid": pid
                };
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: "/youer/ticket/deletetemporarypid",
                    data: param,
                    dataType:"json",
                    async: true,
                    error: function (request) {
                        bootbox.alert("删除失败！");
                    },
                    success: function (data) {
                        if (data.res == '1') {
                            $("#charge-template option[value="+pid+"]").remove();
                            templetChange();
                            $.zui.messager.success('删除成功');
                        } else {
                            $.zui.messager.danger('删除失败');
                        }
                    }
                });
            }
        })
    })

    function keyDownCode(doflag) {
        //对接微信接口
        var authCode=$("#payCode").val();
        var shouldPay=$("#shouldpay").val();
        var totalFee=$("#realpay").val();
        if(shouldPay<=totalFee){
            totalFee=shouldPay;
        }
        if(totalFee==0){
            bootbox.alert("缴费金额错误！");
            return;
        }
        var flowCode = $("#flowCode").val();
        jQuery.ajax({
            url: $("#path").val() + '/common/micropay.do?authCode='+authCode+"&totalFee="+totalFee,
            method: 'post',
            success: function (data) {
                if("0"==data){
                    $("#add-pay-code-modal").modal("hide");
                    $.zui.messager.warning("缴费失败！");
                }else if("1"==data){
                    $("#add-pay-code-modal").modal("hide");
                    addTempChargeReal(doflag);
                }else if("2P"==data.substr(0,2)){
                    var outTradeNo=data.substr(2,data.lenth);
                    $.zui.messager.warning("请等待用户支付。。。");
                    jQuery.ajax({
                        url: $("#path").val() + '/common/micropayPassWord.do?outTradeNo='+outTradeNo,
                        method: 'post',
                        success: function (data) {
                            if(data=='1'){
                                $("#add-pay-code-modal").modal("hide");
                                addTempChargeReal(doflag);
                            }else{
                                $("#add-pay-code-modal").modal("hide");
                                $.zui.messager.warning("缴费失败！");
                            }
                        }
                    });
                }else{
                    $("#payCode").val("");
                    $("#payCode").focus();
                    $.zui.messager.warning(data);

                }
            }

        });

    }

    function addTempChargeReal(flag) {
        //添加
        if (flag) {
            doflag = false;
            $.ajax({
                cache: true,
                type: "POST",
                //url: "/zjk/ticket/addchargetemporary",
                url: "/youer/ticket/addchargetemporary",
                data: $('#myform').serialize(),// 你的formid
                async: false,
                error: function (request) {
                    bootbox.alert("缴费失败！");
                    doflag = true;
                },
                success: function (data) {
                    doflag = true;
                    if (data != null) {
                        $.zui.messager.show("收费成功！", {'type': 'success', 'placement': 'center'});

                        $("#kidName").html($("#kinder-name").val());
                        //$("#kidClass").html($("#class").html())
                        $("#kidClass").html($('#class option:selected').text());


                        if (printBillType == 1) {
                            var printItemCount = 2;
                            var printTr = '<tr class="othertr">';
                        }

                        $("#kinder-list-table tr.columntr").each(function () {
                            var chargingItemName = $(this).find("input[name=chargeName]").val();
                            var chargingAmount = $(this).find("input[name=chargeAmount]").val();

                            //添加打印小票，竖版
                            if (printBillType == 0) {
                                var tr = $('<tr class="othertr"></tr>');
                                var cName = $('<td>' + chargingItemName + '</td>');
                                var cAmount = $('<td>' + chargingAmount + '元</td>');
                                tr.append(cName, cAmount);
                                $("#printtable").append(tr);
                            }
                            else if (printBillType == 1) {
                                printTr += "<td>" + chargingItemName + "：" + chargingAmount + '</td>';
                                if (printItemCount % 2 == 1) {
                                    printTr += '</tr><tr class="othertr">';
                                }
                                printItemCount++;
                            }
                        });

                        if (printBillType == 1) {
                            printTr += "</tr>";
                            $("#printtable").append($(printTr));
                        }
                        setPrint(data);
                        //记录广告打印次数
                        printCount();
                        //打印小票
                        printTicket($("#printerName").val(), "#wrap", $("#printerWidth").val());

                        $("#printtable .othertr").remove();

                    } else {
                        $.zui.messager.show("收费失败！", {'type': 'danger', 'placement': 'center'});
                    }
                    $("#kinder-type").val("0");
                    changeChildType();
                    $("#realpay").val(0);
                    $("#bz").val("");
                    thinkPrice();
                    $("input[name='paytype']").eq(0).click();
                }
            });
        }
    }