<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<form id ="firstUpdateForm" action="fileuploads" method="post"
        enctype="multipart/form-data" class="form-horizontal" role="form" target="hidden_frame">


		<input name="file" id="strPhoto" style="width:100px; height:26px" type="file">
			
		    <input name="Submit" value="修 改" class="submit" type="submit">
		
		<button class="edit_bt btn btn-success btn-sm" type="submit" >上传</button>
		
		</form>
     
     
 
     
     