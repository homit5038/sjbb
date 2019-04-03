<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<h4>专家随机抽取结果</h4>
<hr>
<div class="panel-body expert-list">
	<ul>
		<c:choose>
			<c:when test="${empty experts && msg == true}">
				<li>该批次还未进行抽取</li>
			</c:when>
			<c:when test="${msg != true}">
				<li>${msg }</li>
			</c:when>
			<c:otherwise>
				<c:set var="index" value="0" />
				<c:forEach items="${experts }" var="expert">
					<c:set var="index" value="${index+1 }" />
					<li id="expertReelectOneMain${expert.id }">
						<div class="expert-view">
							<input type="hidden" class="expertIds" id="id${expert.id }"
								value="${expert.id }"> <input type="hidden"
								id="flag${expert.id }" value="${expert.flag }">
							<div class="expert-content  equired${expert.flag }">
								<h4>专家&nbsp;${index }</h4>
								<h5 id="expertName${expert.id }">${expert.expertName }</h5>
								<span>评估机构： <i id="assessmentStructure${expert.id }"
									class="icoFontlist" title="${expert.assessmentStructure }">
										<c:choose>
											<c:when test="${fn:length(expert.assessmentStructure) > 8 }">
								       ${fn:substring(expert.assessmentStructure,0,8) }...
								    </c:when>
											<c:otherwise>
								       ${expert.assessmentStructure }
								    </c:otherwise>
										</c:choose>
								</i>
								</span><br> <span>手机号码：<i id="phoneNum${expert.id }">${expert.phoneNum }</i></span><br>
								<span>邮箱： <i id="expertEmail${expert.id }"
									class="icoFontlist" title="${expert.expertEmail }"> <c:choose>
											<c:when test="${fn:length(expert.expertEmail) > 8 }">
								           ${fn:substring(expert.expertEmail,0,8) }...
								      </c:when>
											<c:otherwise>
								         ${expert.expertEmail }
								       </c:otherwise>
										</c:choose>
								</i>
								</span>
							</div>
							<div class="">
								<c:if test="${batch.availability eq 1 }">
									<c:choose>
										<c:when test="${batch.batchStatus eq 1 }">
											<p>
												<button class="btn btn-primary reelectButton${expert.flag }" type="button"
													onclick="reelectExpert('${batch.id}','${expert.id }')">重选</button>
											</p>
										</c:when>
										<c:when test="${batch.batchStatus eq 2 }">
											<p>
												<!-- <button class="btn btn-primary" style="margin-left: 5px;"
												type="button" onclick="sendNote()">短信通知</button> -->
												<c:choose>
													<c:when test="${expert.noteStatus eq 1 }">
														<button class="btn btn-primary noteButton" type="button"
															disabled="disabled">已通知</button>
													</c:when>
													<c:otherwise>
														<button class="btn btn-primary noteButton"
															id="noteButton${expert.id }" type="button"
															onclick="manSendNote('${batch.id}','${expert.id }','one')">通知</button>
													</c:otherwise>
												</c:choose>
											</p>
										</c:when>
									</c:choose>
								</c:if>
							</div>
						</div>
					</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<br>
<div class="extract-oper">
  <c:if test="${batch.availability eq 1 }">
	<c:choose>
		<c:when test="${empty experts}">
			<button class="btn btn-danger btn-lg" type="button"
				onclick="randomExpert(${batch.id},${batch.extractPepleNum })">开始随机抽取</button>
		</c:when>
		<c:when test="${batch.batchStatus eq 1 }">
			<button class="btn btn-danger btn-lg" type="button"
				onclick="submitExtract(${batch.id})">确定</button>
		</c:when>
		<c:when test="${batch.batchStatus eq 2 && batch.availability eq 1}">
			<button class="btn btn-danger btn-lg noteButton" id="noteAllButton"
				type="button" onclick="manSendNote('${batch.id}','','all')">一键通知</button>
			<button class="btn btn-danger btn-lg" type="button"
				onclick="impExtract(${batch.id})">导出</button>
		</c:when>
	</c:choose>
  </c:if>
</div>