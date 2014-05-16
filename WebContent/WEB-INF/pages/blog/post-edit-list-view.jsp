<%@page import="com.csc.web.data.PostData"%>
<%@page import="com.csc.web.data.PostDataList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@ page import="com.csc.web.constants.DataConstants" %>
<%@ taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc'%>
<%
HttpSession _session = request.getSession();

PostDataList _blogList = (PostDataList)request.getAttribute(DataConstants.BLOG_LIST);

String _basePath = UserPagesUtil.getServletHost(request)+"/";
String _blogPath = _basePath;
%>

<div class="blog-container">
  <div class="blog-table-container">
	<table cellspacing="0" cellpadding="0" border="0" width="100%">
<% 
ArrayList<PostData> _list = _blogList!=null?_blogList.getList():new ArrayList<PostData>();

int i;

int l = Math.min(25, _list.size());  
for (i=0; i < l; i++)
{
	PostData _post = _list.get(i);
	String _title = _post.getTitle()!=null&&_post.getTitle().length()>0?_post.getTitle():"BLOG";
%>
			<tr>				
				<td id="blog_title">
					<a href="<%=_blogPath+"?id="+_post.getId()%>" target="_blank"><%=_title%></a>
				</td>	
				<td style="width:100px;color:#0066FF;">
					COMMENTS <%=_post.commentCount %>
				</td>
				<td style="width:75px;">
					<%=_post.getDateString()%>
				</td>
				<td style="width:50px;">
					<a href="<%=UserPagesUtil.getPostEditPage(request)+"?id="+_post.getId()%>">Edit</a>
<%-- 				<%if(_post.getPublished()){ %> --%>
<%-- 					<a href="<%=UserPagesUtil.getEditPage(request)+"?id="+_post.getId()%>">Edit</a> --%>
<%-- 				<%}else{ %> --%>
<%-- 					<a href="<%=UserPagesUtil.getEditPage(request)+"?id="+_post.getId()%>">Edit</a> --%>
<%-- 				<%} %>					 --%>
				</td>
				<td style="width:50px;">					
				<%if(_post.getPublished()){ %>
					<a href="<%=_blogPath+"?id="+_post.getId()%>" target="_blank">View</a>
				<%}else{ %>
					<span class="blog-draft-label">Draft</span>
				<%} %>										
				</td>
				<td style="width:75px;">
					<img style="border:0px;width:11px;height:13px;" src="<%=_basePath%>/images/trash-can-icon.png"/>
					<a href="#" onclick="confirmDelete('<%=_title%>','<%=_post.getId()%>')" id="delete">Delete</a>
				</td>				
			</tr>				
<% 
}
%>		
	</table>
  </div>
</div>	
<form id="deleteForm"  action="<%=UserPagesUtil.getDeleteService(request)%>" method="post">
<input id="deleteId" name="id" type="hidden">
</form>
<script type="text/javascript">
<!--
function confirmDelete(title,id)
{
	var _confirm = new ConfirmDialog("Blog Entry - Confirm Delete", 
			"<b>Delete this entry:</b> "+title+"<b>?</b>",
			function()
			{
				document.getElementById('deleteId').value=id;
				document.getElementById('deleteForm').submit();
			});
	
	_confirm.show();
}
//-->
</script>

