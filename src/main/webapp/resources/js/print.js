/**
 * @param: printer 打印机名称
 * @param: container 被打印内容所在容器的选择器
 */

var LODOP = getLodop(document.getElementById('LODOP_OB'));
function printByA4(printer, container) {
    if (LODOP) {
        var noTrimLastTd = arguments[2] ? true : false;
        var title = arguments[3] ? arguments[3] : "";
        var trimFirstTd = arguments[4] ? true : false;
        var isDatatable = arguments[5] ? arguments[5] : false;
        var replaceLastTd = arguments[6] == true ? true : false;
        lodopPrint(printer, container, LODOP, noTrimLastTd, title, trimFirstTd, isDatatable, replaceLastTd);
    }
    else {
        browserTip();
    }
}
function lodopPrint(printer, container, LODOP, noTrimLastTd, title, trimFirstTd, isDatatable, replaceLastTd) {
    var time = $.now();
    var random = Math.floor(Math.random() * 100);
    LODOP.PRINT_INIT("打印表格" + time + random);
    if (!LODOP.SET_PRINTER_INDEXA(printer)) return;

    var printContent = $(container).clone();

    //去掉表格最后一列
    if (!noTrimLastTd) {
        printContent.find("tr").each(function () {
            $(this).find("th:last").remove();
            $(this).find("td:last").remove();
        });
    }

    //去掉表格第一列
    if (trimFirstTd) {
        printContent.find("tr").each(function () {
            $(this).find("th:first").remove();
            $(this).find("td:first").remove();
        });
    }

    //替换文本框为值
    printContent.find("td").each(function () {
        //$(this).find("input[type='hidden']").remove();
        $(this).find("input[class*='hidden-class']").remove();
        if ($(this).find("input")) {
            $(this).html($(this).find("input").val())
        }
    });

    //数据表格将原有表格恢复
    if (isDatatable != false) {
        printContent.find("#" + isDatatable).remove();
        printContent.html(printContent.find("table"));
        var thead = printContent.find("table:first").html();
        var tbody = printContent.find("table:eq(1)").html();
        printContent.find("table:eq(1)").html(thead + tbody);
        printContent.find("table:first").remove();
    }

    //最后一列替换为空白（用在库存盘点）
    if (replaceLastTd != false) {
        printContent.find("tr").each(function() {
            $(this).find("th:last").html("盘点数量");
            $(this).find("td:last").html("");
        });
    }

    var style = "<!doctype html><style type='text/css'>";
    style += "body{text-align:center;}";
    style += "table{font-size:12px;margin:0 auto;text-align:center;border-collapse:collapse;}";
    style += "table,td,th{border:1px solid black;}";
    style += "td,th{padding:5px;}";
    style += "</style>";

    LODOP.ADD_PRINT_HTM(20, 30, 730, 1000, style + title + printContent.html());

    //LODOP.PREVIEW();
    LODOP.PRINT();
}

function printTicket(printer, container, width) {

    if (!LODOP) {
        browserTip();
        return;
    }

    var time = $.now();
    var random = Math.floor(Math.random() * 100);
    LODOP.PRINT_INIT("打印小票" + time + random);

    if (!LODOP.SET_PRINTER_INDEXA(printer)) return;

    var posX = 0;
    var posY = 0;
    if (width == 58) {
        var css = "../css/printer.css";
        var height = (parseInt($(container).height()) + 5) * 2;
        var contentWidth = (parseInt(width) - 10) * 10;
    }
    else if (width == 80) {
        var css = "../css/printer80.css";
        var height = parseInt($(container).height()) * 2.5;
        var contentWidth = (parseInt(width) - 10) * 10;
    }
    else if (width == 2414) {
        var css = "../css/printer2414.css";
        var height = 1400;
        var contentWidth = 2410;
        posX = "20mm";
        posY = "35mm";
    }
    else if (width == 249) {
        var css = "../css/printer249.css";
        var height = 931;
        var contentWidth = 2410;
        posX = "2mm";
        posY = "35mm";
    }
    else if (width == 1914) {
        var css = "../css/printer1914.css";
        var height = 1400;
        var contentWidth = 1900;
        posX = "20mm";
        posY = "22mm";
    }
    else if (width == 199) {
        var css = "../css/printer199.css";
        var height = 931;
        var contentWidth = 1900;
        posX = "2mm";
        posY = "22mm";
    }
    var style = '<link rel="stylesheet" type="text/css" href="' + css + '">';
    LODOP.SET_PRINT_PAGESIZE(0, contentWidth, height, "bill");
    LODOP.ADD_PRINT_HTM(posX, posY, contentWidth, height, "<!doctype html>" + style + $(container).html());
    //LODOP.PREVIEW();
    LODOP.PRINT();
}

