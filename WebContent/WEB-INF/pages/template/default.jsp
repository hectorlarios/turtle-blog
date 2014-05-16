<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc'%>
<%
	String _basePath = UserPagesUtil.getServletHost(request) + "/";

	String _themeName = "charcoal";
	
	if(_themeName==null)_themeName = "default";
%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<csc:get name="meta"/>
	<title>TurtleBLOG - <csc:get name="title" /></title>
	<link href="<%=_basePath%>css/themes/<%=_themeName%>/page.css" rel="stylesheet" type="text/css" />
	<link href="<%=_basePath%>css/site/nav.css" rel="stylesheet" type="text/css" />
	<link href="<%=_basePath%>css/site/footer.css" rel="stylesheet" type="text/css" />
	<link href="<%=_basePath%>favicon.ico" rel="shortcut icon" />
	<script type="text/javascript" src="<%=_basePath%>/js/utils.js"></script>
	<csc:get name="css"/>
	<csc:get name="script"/>
</head>
<body>
	<div style="position:absolute;width:100%;height:100%;" align="center">		
		<table border="0" cellpadding="0" cellspacing="0" style="height:100%">
<!-- 			<tr> -->
<!-- 				<td style="height:20px;"> -->
<!-- 				</td> -->
<!-- 			</tr> -->
			<tr>
				<td class="main-container main-container-bg">
					<div style="height:15px"></div>
					<csc:get name="content" />
					<div style="height:15px"></div>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td style="height:15px;" class="main-container-bg"> -->
<%-- 					<csc:get name="footer"/> --%>
<!-- 				</td> -->
<!-- 			</tr>								 -->
		</table>	
	</div>	
</body>
</html>