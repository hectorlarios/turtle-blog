<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@page import="com.csc.web.constants.DataConstants" %>
<%@taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc'%>
<%
HttpSession _session = request.getSession();

String _requestUrl = request.getRequestURL().toString();//.replaceFirst("/$", "");

if(request.getAttribute(DataConstants.BLOG_PAGE_REQUEST)!=null)_requestUrl = (String)request.getAttribute(DataConstants.BLOG_PAGE_REQUEST);

String _url;
%>
<div class="posts-nav-control-container">
	<ul class="posts-nav-ul">
		<%
		_url = UserPagesUtil.getPostCreatePage(request);		
		if(_url.indexOf(_requestUrl)==-1){
		%>
			<li><span><a href="<%=_url%>">Create</a></span></li>
		<%}else{%>
			<li id="selected">Create</li>
		<%} %>
		|
		<%
			_url = UserPagesUtil.getPostEditListPage(request);
				if(_requestUrl.indexOf(_url)==-1){
		%>
			<li><span><a href="<%=_url%>">Edit</a></span></li>
		<%}else{%>
			<li id="selected">Edit</li>
		<%} %>				
	</ul>
</div>
		