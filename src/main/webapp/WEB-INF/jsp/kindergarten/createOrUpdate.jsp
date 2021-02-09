<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/public/header.jsp"></jsp:include>
    <style type="text/css">

    </style>
    <script type="text/javascript">
        $(function () {

            var msg = "${msg}";
            if (msg == "exist") {
                $("#expertNameErr").html("该专家已在库中存在");
            }
            if (msg == "editOk") {
                parent.refresh();
            }
            var availableTags = ["大理鹏程房地产土地评估咨询有限公司"];
            $("#fidname").autocomplete({
                source: availableTags
            });

            $('#select-kinder').modalTrigger({
                'shown': function () {
                    $('#kinder-search').focus();
                }
            });
            $("#noselect-kinder").click(function(e){
                $("#fid").val("")
                $("#fidname").val("")
            });

        });

        function ismail(str) {
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            return reg.test(str);
        }

        function submitForm() {
            var Kindergartenname = $("#Kindergartenname").val();
            var Addresea = $("#Addresea").val();
            var KinderPhoneNumber = $("#KinderPhoneNumber").val();
            var Principal = $("#Principal").val();
            $(".err").html("");
            var pgjg = $("#pgjg").val();
            if (Kindergartenname.length == 0) {
                $("#KindergartennameErr").html("请填幼儿园名称");
                return false;
            }
            if (Addresea.length == 0) {
                $("#AddreseaErr").html("请填幼儿园地址");
                return false;
            }
            if (Principal.length == 0) {
                $("#PrincipalErr").html("请填幼儿园长名字");
                return false;
            }
            if (KinderPhoneNumber.length == 0) {
                $("#KinderPhoneNumberErr").html("请填联系电话");
                return false;
            }


            $("#Kindergarten").submit();

        }

        function importData() {
            var url = "${ctx}/classe/importExpert";
            $("#signImgFrame").attr('src', url);
            $("#signImgModal").modal();
        }

        function rese() {
            $(':input', '#expertForm').not(':button, :submit, :reset, :hidden').val('')
                .removeAttr('checked').removeAttr('selected');
        }

    </script>
</head>
<body>
<c:if test="${msg!='edit' }"><pre>幼儿园》新增幼儿园
<button type="button" onclick="importData();" class="btn btn-link">导入</button></pre>
</c:if>
<c:if test="${msg=='edit' }">
    <pre>幼儿园》修改幼儿园</pre>
</c:if>
<form:form modelAttribute="kindergarten" id="Kindergarten" method="post">

    <form:input path="createUserID"  type="hidden" />

    <div class="panel panel-info" style="width: 99%">
        <div class="panel-body">
            <table class="table table-condensed">
                <tr>
                    <td width="220" align="right">
                        <span class="err">*</span>幼儿园名称:
                    </td>
                    <td>
                        <form:input path="Kindergartenname" id="Kindergartenname" cssClass="form-control"
                                    style="width:320px"/>
                        <span id="KindergartennameErr" class="err"></span>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">
                        <span class="err">*</span>幼儿园地址:
                    </td>
                    <td>
                        <form:input path="Addresea" id="Addresea" cssClass="form-control" style="width:320px"/>
                        <span id="AddreseaErr" class="err"></span>
                    </td>
                </tr>

                <tr>
                    <td width="220" align="right">
                        <span class="err">*</span>园长:
                    </td>
                    <td>
                        <form:input path="Principal" id="Principal" cssClass="form-control" style="width:320px"/>
                        <span id="PrincipalErr" class="err"></span>
                    </td>
                </tr>
                <c:if test="${kindergarten.fisSubKingdergarten =='1'|| empty kindergarten.id }">
                <tr>
                    <td width="220" align="right">
                        <span class="err">*</span>是否分园:
                    </td>
                    <td>
                        <form:radiobutton path="fisSubKingdergarten" data-toggle="modal"
                                          data-remote="./selectKindergarten"  id="select-kinder" value="1" label="是"/>
                        <form:radiobutton path="fisSubKingdergarten"   id="noselect-kinder"  value="0" label="否"/>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">
                        <span class="err">*</span>总园名称:
                    </td>
                    <td>
                        <form:input path="fid" id="fid"  type="hidden"  cssClass="form-control" style="width:320px"/>
                        <input name="fidname" id="fidname"   value="${fidname}"    class="form-control" style="width:320px"/>

                    </td>
                </tr>
                </c:if>
                <tr>
                    <td width="220" align="right">
                        <span class="err">*</span>联系电话:
                    </td>
                    <td>
                        <form:input path="KinderPhoneNumber" id="KinderPhoneNumber" cssClass="form-control"
                                    style="width:320px"/>
                        <span id="KinderPhoneNumberErr" class="err"></span>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">
                        <span class="err">*</span>联系QQ:
                    </td>
                    <td>
                        <form:input path="KinderQq" id="KinderQq" cssClass="form-control" style="width:320px"/>
                        <span id="KinderQqErr" class="err"></span>
                    </td>
                </tr>

            </table>
        </div>
        <table width="99%">
            <tr>
                <td align="center">
                    <button type="button" onclick="submitForm();"
                            class="btn btn-primary">保存
                    </button>
                    <button type="button" onclick="rese();" class="btn btn-default">重置</button>

                </td>
            </tr>
        </table>
    </div>
</form:form>
<jsp:include page="/WEB-INF/jsp/public/modal.jsp"></jsp:include>
</body>
</html>

