<%@page import="com.csc.web.data.PostData"%>
<%@page import="com.csc.web.data.PostDataList"%>
<%@page import="com.csc.web.utils.DateUtil"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@ page import="com.csc.web.constants.DataConstants" %>
<%@ taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc'%>
<%
PostDataList _blogList = (PostDataList)request.getAttribute(DataConstants.BLOG_LIST);

String _basePath = UserPagesUtil.getServletHost(request)+"/";

String _blogUrl;

String _currentDate = null;

String _title = "";//_blog!=null?_blog.getTitle():"";

String _description = "";//_blog!=null?_blog.getDescription():"";

ArrayList<PostData> _list = _blogList.getList();

if(_list.size()==1)
{
	PostData _be = _blogList.getList().get(0);
	
	_title = _be.getTitle();
	
	_description = _be.getBlog().replaceAll("<[^>]+>","");
	
	if(_description.length()>250)
	{
		_description = _description.substring(0, 247)+"...";
	}
%>
<csc:html name="meta">
	<meta property="og:title" content="<%=_title%>" />
	<meta property="og:description" content="<%=_description %>" />		
</csc:html>
<%} %>

<div class="blog-container">
<%
PostData _blogEntry = null;

Boolean _isPublished = false;
    	
String _queryString = request.getQueryString()==null?"?":"?"+request.getQueryString().replaceAll("id=((\\w+-?)+)&*", "");

_queryString = _queryString.replaceAll("&*$", "");    	

int i;

int l = Math.min(15, _list.size());  

for (i=0; i < l; i++)
{
	_blogEntry = _list.get(i);	
	
	_isPublished = false;//_blogEntry.getPublished();
	
	String _param = _basePath+_queryString+"id="+_blogEntry.getId(false);
	
	_param = _param.replaceFirst("/\\?&|/&", "/?");
	
	try{_param = URLEncoder.encode(_param,"UTF-8");}        
	catch(UnsupportedEncodingException e){throw new RuntimeException(e);}
	
	String _fbShare = "http://www.facebook.com/sharer.php?u="+_param+"&t="+_blogEntry.getTitle();
	
	String _twrShare = "http://twitter.com/share?text="+_blogEntry.getTitle()+"&url="+_param;
	
	_blogUrl = _queryString+"&id="+_blogEntry.getId(false);
    
	_blogUrl = _blogUrl.replaceFirst("/\\?&|/&", "/?");	
	
	String _time = DateUtil.getTime(_blogEntry.getDate());
	
	_time = _time.replaceFirst("^0", "");
	
	String _comments = "Comments "+ _blogEntry.commentCount;
	
	String _blogTitle = _blogEntry.getTitle()!=null&&_blogEntry.getTitle().length()>0?_blogEntry.getTitle():"BLOG";
	%>	
        <div class='blog-title-container blog-title'> 
        	<%if(l>1){%>  
        		<a href='<%=_blogUrl%>'><%=_blogTitle%></a>
        	<%}else{
        		out.println(_blogTitle);
        	  } %>	
        </div>         
        <%if(!_blogEntry.getDateString().equals(_currentDate))
        {
        	String _blogDate = _blogEntry.getDayName()+", "+_blogEntry.getMonthName()+" "+_blogEntry.getDay()+", "+_blogEntry.getYear();	
        %>        
        <div class='blog-date-container blog-date-title'><%=_blogDate%></div>
        <%}
        _currentDate = _blogEntry.getDateString();%>
        
        <div class='blog-body-container'><%=_blogEntry.getBlog()%></div> 
        <div style='height:7px;'>&nbsp;</div>
        <div class='blog-footer-container'>                
            <table cellpadding='0' cellspacing='0' border="0" width="100%">        
                <tr>        
                    <td align='left' style='padding-left:5px;'>
                    	<a href='javascript:void();' onclick='open("<%=_twrShare%>","","width=565,height=485")'><img src='<%=_basePath%>images/network/twitter_icon.png' style='width:20px;height:20px;border:0px;'></a>
                    	<a href='javascript:void();' onclick='open("<%=_fbShare%>","","width=530,height=350")'><img src='<%=_basePath%>images/network/facebook_icon.png' style='width:20px;height:20px;border:0px;'></a>
                    </td>
                	<td align='right' style='padding-right:5px;' class="blog-time">
<%--                 		<%if(l>1){%> --%>
<%-- 			        		<a href='<%=_blogUrl%>#comments' class="blog-comments-link"><%=_comments%></a>&nbsp;&nbsp;&nbsp; --%>
<%-- 			        	<%}else{%> --%>
<%-- 			        		<span class="blog-comments-link"><%=_comments%></span>&nbsp;&nbsp;&nbsp; --%>
<%-- 			        	<%} %>	 --%>
                		Posted at <%=_time%>
                	</td>                
                </tr>        
            </table>        	    
        </div>
        <div style='height:40px;'>&nbsp;</div>
<%}%>
<div style='height:20px;'>&nbsp;</div>
<%if(_isPublished){ %>
<csc:html name="css">
	<link href="<%=_basePath%>css/site/login.css" rel="stylesheet" type="text/css" />
	<link href="<%=_basePath%>css/site/confirm-dialog.css" rel="stylesheet" type="text/css" />
<csc:get name="css"/></csc:html>
<csc:html name="script">
	<script src="<%=_basePath%>js/login-dialog.js" type="text/javascript"></script>
	<script src="<%=_basePath%>js/comment-util.js" type="text/javascript"></script>
	<script src="<%=_basePath%>js/confirm-dialog.js" type="text/javascript"></script>
</csc:html>	
<span class="blog-comment-title">Post a Comment</span>
<form id="commentForm"  action="<%=UserPagesUtil.getCreateCommentService(request)%>" method="post">
	<textarea id="comment" name="comment" class="blog-comment-textarea"></textarea>
	<input id="formID" name="id" type="hidden" value="<%=_blogEntry.getId(false)%>"/> 
	<input id="commentCount" name="commentCount" type="hidden" value="<%=_blogEntry.commentCount%>"/>
	<div style='height:10px;'>&nbsp;</div>
	<input id="submitButton" type="submit" value="Submit" class="blog-comment-submit-button"/>
</form>
<script type="text/javascript">
	var _commentUtil = new CommentUtil();
	
	_commentUtil.setLoginServicePath("<%=UserPagesUtil.getLoginService(request)%>");
	
	_commentUtil.setProfileDataServicePath("<%=UserPagesUtil.getProfileDataService(request)%>");
	
	_commentUtil.initialize();
	
	function confirmDelete(title,id)
	{
		var _confirm = new ConfirmDialog("Comment - Confirm Delete", 
				"<b>Delete this comment:</b> "+title+"<b>?</b>",
				function()
				{
					document.getElementById('deleteId').value=id;
					document.getElementById('deleteForm').submit();
				});
		
		_confirm.show();
	}	
</script>
<%}%>
</div>	

<!-- trash-can-icon.png -->
