<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="formId" required="true"%>
<%@ attribute name="map" required="true" rtexprvalue="true"
	type="java.util.HashMap"%>
<script type="text/javascript">
function _onPageSelected(page,size,formId){
	var form = document.getElementById(formId);
	form.elements["page"].value=page;
	form.elements["size"].value=size;
	jQuery('#main').showLoading();
	form.submit();
}
function _Selected(obj,size,formId){
	var form = document.getElementById(formId);
	var page = obj.value-1;
	form.elements["page"].value=page;
	form.elements["size"].value=size;
	jQuery('#main').showLoading();
	form.submit();
}
</script>
<div class="pagin">
	<div class="message">
		${(map['pageNum']*map['size'])+1}-${(map['pageNum']*map['size'])+map['currentSize']}共<i class="blue">${map['total']}</i>条记录，当前显示第&nbsp;<i
			class="blue">[第${map['pageNum']+1}页/共${map['totalPage']}页]&nbsp;</i>页
	</div>
	<ul class="paginList">
		<c:if test="${map['total']  <=0 || map['pageNum'] <= 0}">
			<li class="paginItem"><a href="javascript:;" class="pagepre"></a></li>
		</c:if>
		<c:if test="${map['total'] >0 && map['pageNum'] > 0}">
			<li class="paginItem"><a href="###"
				onclick="_onPageSelected('${map['pageNum']-1}',${map['size']},'${formId}')"
				class="pagepre"></a></li>
		</c:if>

		<c:set value="1" var="numPage" />
		<c:forEach begin="1" end="10">
			<c:if test="${numPage<=map['totalPage']}">
				<c:choose>
					<c:when test="${map['pageNum']+1==numPage}">
						<li class="paginItem current"><a href="###"
							onclick="_onPageSelected('${numPage-1}','${map['size']}','${formId}')">${numPage}</a></li>
					</c:when>
					<c:otherwise>
						<li class="paginItem"><a href="###"
							onclick="_onPageSelected('${numPage-1}','${map['size']}','${formId}')">${numPage}</a></li>
					</c:otherwise>
				</c:choose>
				<c:set value="${numPage+1}" var="numPage" />
			</c:if>
		</c:forEach>
		<c:if test="${map['total'] == 0 || map['pageNum'] + 1 >= map['totalPage']}">
			<li class="paginItem"><a href="javascript:;" class="pagenxt"></a></li>
		</c:if>
		<c:if test="${map['pageNum'] + 1 < map['totalPage']}">
			<li class="paginItem"><a href="###"
				onclick="_onPageSelected('${map['pageNum']+ 1}','${map['size']}','${formId}')"
				class="pagenxt"></a></li>
		</c:if>
		<li class="paginItem"><select
			onchange="_Selected(this,'${map['size']}','${formId}')" class="select"
			style="width: 45px">
				<c:set value="1" var="num" />
				<c:forEach begin="1" end="${map['totalPage']}">
					<c:choose>
						<c:when test="${map['pageNum']+1==num}">
							<option value="${num}" selected="selected">${num}</option>
						</c:when>
						<c:otherwise>
							<option value="${num}">${num}</option>
						</c:otherwise>
					</c:choose>
					<c:set value="${num+1 }" var="num" />
				</c:forEach>
		</select></li>
	</ul>
</div>
