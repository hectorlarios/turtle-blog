<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@ taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc' %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String _basePath = UserPagesUtil.getServletHost(request) + "/";
%>
   
<csc:template id="/WEB-INF/pages/template/default.jsp">
	<csc:set name="title" value="404 Page"/>
	<csc:html name="css">
		<link href="<%=_basePath%>css/site/error.css" rel="stylesheet" type="text/css" />	
	</csc:html>	
	<csc:html name="content">
		<div class="page-title-container page-title"><img class="nav-site-logo-icon-64" src="<%=_basePath%>/images/logo-64.png"/> ERROR 404</div>
<!-- 		<div class="page-title-container page-title">ERROR 404</div> -->
		<div class="page-description-container page-description">The requested page was not found.</div>		
	</csc:html>
	<csc:html name="footer">
		<jsp:include page="/WEB-INF/pages/common/footer-nav.jsp" />
	</csc:html>
</csc:template>