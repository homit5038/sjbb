<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="formId" required="true"%>
<%@ attribute name="page" required="true" rtexprvalue="true"
	type="org.springframework.data.domain.Page"%>

<script type="text/javascript">
function _onPageSelected(page,size,formId){
	var form = document.getElementById(formId);
	form.elements["page"].value=page;
	form.elements["size"].value=size;
	form.submit();
}
function _Selected(obj,size,formId){
	var form = document.getElementById(formId);
	var page = obj.value-1;
	form.elements["page"].value=page;
	form.elements["size"].value=size;
	form.submit();
}
</script>
<div class="pagination" align="center">
	<br>
	<c:if test="${page.totalElements <=0 || page.number <= 0}">
		<a class="current next">&lt;&lt;</a>
		<a class="current next">&lt;</a>
	</c:if>
	
	<c:if test="${page.totalElements >0 && page.number > 0}">
			<a href="###" onclick="_onPageSelected(0,${page.size},'${formId}')">&lt;&lt;</a>
			<a href="###" onclick="_onPageSelected(${page.number - 1},${page.size},'${formId}')">&lt;</a>
	</c:if>
		<span class="info">${page.size*page.number+1}-${page.size*page.number+page.numberOfElements}[共${page.totalElements }条记录]&nbsp;&nbsp;[第${page.number + 1}页/共${page.totalPages}页]</span>
	<c:if
		test="${page.totalElements == 0 || page.number + 1 >= page.totalPages}">
		<a class="current prev">&gt;</a>
			<a class="current prev">&gt;&gt;</a>
	</c:if>

	<c:if test="${page.number + 1 < page.totalPages}">
		<a href="###"
			onclick="_onPageSelected(${page.number + 1},${page.size},'${formId}')">&gt;</a>
		<a href="###"
			onclick="_onPageSelected(${page.totalPages - 1},${page.size},'${formId}')">&gt;&gt;</a>
	</c:if>
		<span class="info">
		<select onchange="_Selected(this,'${page.size}','${formId}')">
		<c:set value="1" var="num"/>
			<c:forEach begin="1" end="${page.totalPages}">
			<c:choose>
				<c:when test="${page.number+1==num}">
					<option value="${num}" selected="selected">${num}</option>
				</c:when>
				<c:otherwise>
					<option value="${num}">${num}</option>
				</c:otherwise>
			</c:choose>
			<c:set value="${num+1 }" var="num"/>
			</c:forEach>
		</select>
	</span>

</div>