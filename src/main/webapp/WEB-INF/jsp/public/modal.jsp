<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal fade" id="signImgModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 60%;">
		<div class="modal-content">
	        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">标题</h4>
            </div>
			<div class="modal-body panel-info">
				<iframe id="signImgFrame" name="signImgFrame" width="100%"
					height="450" style="" no" border="0" marginwidth="0"
					marginheight="0" scrolling="yes" frameborder="no" allowtransparency="yes"></iframe>
			</div>
			<div class="modal-footer">
				<button type="button" id="closesignImgModal" class="btn btn-default"
					data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>