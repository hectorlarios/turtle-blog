<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@ taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc'%>
<%

String _basePath = UserPagesUtil.getServletHost(request) + "/";

String _themeName = "charcoal";
%>
<csc:template id="/WEB-INF/pages/template/default.jsp">
	<csc:set name="nav-logo-visible" value="inline"/>
	<csc:set name="title" value="Hector Larios"/>
	<csc:html name="css">
		<link href="<%=_basePath%>css/themes/<%=_themeName%>/blog.css" rel="stylesheet" type="text/css" />
		<link href="<%=_basePath%>css/themes/<%=_themeName%>/archive.css" rel="stylesheet" type="text/css" />
		<link href="<%=_basePath%>css/themes/<%=_themeName%>/paginator.css" rel="stylesheet" type="text/css" />	
	</csc:html>
	<csc:html name="content">
		<div class="page-title-container page-title">Donec turpis massa</div>
		<div class="page-description-container page-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam dignissim bibendum leo, sit amet elementum ligula vestibulum ut. Duis at diam dapibus urna fringilla aliquet at at sem. Aliquam erat volutpat. Cras quis dignissim erat. Pellentesque ullamcorper sagittis dui vestibulum adipiscing.</div>
		
		<div style="height:50px;"></div>
		
		<table style="border: 0px;" cellpadding="0" cellspacing="0" width="950">
		    <tr valign="top">
		        <td style="width:300px;">
		        	<jsp:include page="/WEB-INF/pages/blog/archive-nav.jsp"></jsp:include>		
		        </td>
		        <td><div style="height:5px;"></div></td>
		        <td style="width:650px" align="left">
		        	<jsp:include page="/WEB-INF/pages/blog/post-list-view.jsp"></jsp:include>		        		            
		        </td>
		    </tr>
		</table>
	</csc:html>
</csc:template>