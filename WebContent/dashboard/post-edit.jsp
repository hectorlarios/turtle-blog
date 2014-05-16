<%@page import="com.csc.web.data.PostData"%>
<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@page import="java.util.UUID"%>
<%@page import="com.csc.web.services.post.PostModel"%>
<%@ page import="com.csc.web.constants.DataConstants" %>
<%@ taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc' %>
<%
String _selectedId = request.getParameter(DataConstants.ID);

PostModel _model = new PostModel();		

PostData _post = new PostData();
	
_post.setId(UUID.fromString(request.getParameter(DataConstants.ID)));

_model.initialize(_post);
			
if(_post.success)
{
	_post.success = false;
	
	session.setAttribute(DataConstants.POST_DATA_EDIT, _post);
}

String _basePath = UserPagesUtil.getServletHost(request)+"/";
%> 
<csc:template id="/WEB-INF/pages/template/dashboard.jsp">
	<csc:html name="css"><link href="<%=_basePath%>css/dashboard/post-nav.css" rel="stylesheet" type="text/css" />
		<link href="<%=_basePath%>css/dashboard/post-composer-view.css" rel="stylesheet" type="text/css" />
	</csc:html>
	<csc:html name="script">	
	<script src="<%=_basePath%>js/dashboard/post-composer-view.js" type="text/javascript"></script>
	<script src="<%=_basePath%>js/dashboard/post-composer-color-picker.js" type="text/javascript"></script>
	<script src="<%=_basePath%>js/dashboard/post-composer-font-selector.js" type="text/javascript"></script>
	<script src="<%=_basePath%>js/calendar-view.js" type="text/javascript"></script>	
	</csc:html>
	<csc:html name="content">
		<div class="page-title-container page-title">Edit</div>
		<div style="width:910px;left:20px;position:relative;">
			<div class="page-dashboard-container">
				<div class="page-dashboard-header">
					<jsp:include page="/WEB-INF/pages/common/dashboard-nav.jsp" />				
				</div>
				<div class="page-dashboard-body">
					<jsp:include page="/WEB-INF/pages/common/post-nav.jsp" />
					<jsp:include page="/WEB-INF/pages/blog/post-composer-view.jsp" />
				</div>	
			</div>			
		</div>		
	</csc:html>
</csc:template>