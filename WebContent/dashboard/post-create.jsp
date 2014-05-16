
<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@ page import="com.csc.web.constants.DataConstants" %>
<%@ taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc' %>
<%
String _basePath = UserPagesUtil.getServletHost(request)+"/";

session.removeAttribute(DataConstants.POST_DATA_EDIT);
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
		<div class="page-title-container page-title">Posts</div>
<!-- 		<div style="width:425px;" class="page-description-container page-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sodales varius lectus sit amet ultricies. Curabitur vulputate lectus ac mi tincidunt et bibendum quam consectetur. In ut porttitor velit.</div> -->
<!-- 		<div style="height:30px;"></div> -->
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