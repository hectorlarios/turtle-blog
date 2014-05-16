<%@page import="java.util.ArrayList"%>
<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@ page import="com.csc.web.constants.DataConstants" %>
<%@taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc'%>
<%

String _requestUrl = request.getRequestURL().toString();//.replaceFirst("/$", "");

if(request.getAttribute(DataConstants.BLOG_PAGE_REQUEST)!=null)_requestUrl = (String)request.getAttribute(DataConstants.BLOG_PAGE_REQUEST);

String _url;

ArrayList<String> _list;
%>
<div class="dashboard-nav-control-container">
	<ul class="dashboard-nav-ul">
		<%
		_url = UserPagesUtil.getDashboardPage(request)+"index.jsp";		
		if(_url.indexOf(_requestUrl)==-1){
			_url = UserPagesUtil.getDashboardPage(request);
		%>
			<li><span><a href="<%=_url%>">Dashboard</a></span></li>
		<%}else{%>
			<li id="selected">Dashboard</li>
		<%} %>
		<%
		_url = UserPagesUtil.getPostCreatePage(request);
		_list = UserPagesUtil.getPostPages(request);
		if(_list.indexOf(_requestUrl)==-1){%>
			<li><span><a href="<%=_url%>">Posts</a></span></li>
		<%}else{%>
			<li id="selected">Posts</li>
		<%} %>
<%-- 		<% --%>
<%--  		_url = UserPagesUtil.getCommentPage(request);--%>
<%-- 		if(_requestUrl.indexOf(_url)==-1){%> --%>
<%-- 			<li><span><a href="<%=_url%>">Comments</a></span></li> --%>
<%-- 		<%}else if(true){%> --%>
<!-- 			<li id="disabled">Comments</li>	 -->
<%-- 		<%}else{%> --%>
<!-- 			<li id="selected">Comments</li> -->
<%-- 		<%} %> --%>
	</ul>
</div>
		