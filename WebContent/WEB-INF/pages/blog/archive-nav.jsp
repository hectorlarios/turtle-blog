<%@page import="com.csc.web.data.PostData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@page import="com.csc.web.data.PostDataList"%>
<%@ page import="com.csc.web.constants.DataConstants" %>
<%
PostDataList _list = (PostDataList)request.getAttribute(DataConstants.BLOG_HISTORY);

String _selectedDevoId = (String)request.getAttribute(DataConstants.SELECTED_BLOG_ID);

String _basePath = UserPagesUtil.getServletHost(request) + "/";

String _postPath = _basePath;

String _postHistory = "";

if(request.getParameter(DataConstants.PAGE)!=null)
{
	_postPath += "?" + DataConstants.PAGE+"="+request.getParameter(DataConstants.PAGE);
}

try
{
	_postHistory = getFormatedDevoHistory(_list, _selectedDevoId, _basePath,request,_postPath);	
}
catch(Exception e){/*throw new RuntimeException(e);*/}
%>

<%!
public String TAG_IMG_EXPAND = "<img width='9' id='${ID}' border='0' height='9' src='${basePathUrl}/images/expand-icon.png'/>";

public String TAG_IMG_COLLAPSE = "<img width='9' id='${ID}' border='0' height='9' src='${basePathUrl}/images/collapse-icon.png'/>";

HttpServletRequest _request;

PostDataList _data;

String _blogPath = "";

public String getFormatedDevoHistory(PostDataList data, String selectedDevoId, String basePathUrl, HttpServletRequest request,String blogPath)
{
	_data = data;
	
	_blogPath = blogPath;
	
	_request = request;
	
    ArrayList<String> _yearList = _data.getYearList();

    int i;

    int l = _yearList.size();

    String _value = "<div class='__sdsTopLeft'>";
    
    _value +="<ul style='left:10px;width:210px;' class='__sdsContainer ul_li'>";   
    
    for (i=0; i < l; i++)
    {
    	String _year = _yearList.get(i);               

    	String _yearId = "devoHistoryYear" + i;
        
        String _title = _year;
        
        String _display = "";

        String _imageIcon = TAG_IMG_COLLAPSE;    

        _value +="<li class='ul_li'>\n";
        _value +="	<div class='__sdsTopLeft'>\n";
		_value +="		<a href='javascript:void(\"\");' onclick='__sdsContainerDisplay(\""+_yearId+"\");' class='archive-list-title'>";
		_value += _imageIcon.replace("${ID}", _yearId + "ExpandIcon") + "&nbsp;"+_title;
		_value +="</a>\n";
		_value +="	</div>\n";
		_value +="	<ul id='"+_yearId+"' style='"+_display+"left:10px;width:205px;' class='__sdsContainer ul_li'>\n";
			
		_value += getMonthData(_year, _yearId, selectedDevoId);
		
        _value +="	</ul>\n";			
        _value +="</li>\n"; 
        _value +="<li style='height:5px;'></li>\n"; 
    }      		
    _value +="	</ul>\n";
    _value +="</div>\n";  
    
    _value = _value.replace("${basePathUrl}", basePathUrl);
        
    return _value;
}

public String getMonthData(String year, String instanceName, String selectedDevoId)
{
    String _value = "";
    
    ArrayList<String> _monthList = _data.getMonthListByYear(year);

    int i;

    int l = _monthList.size();

    for (i = 0; i < l; i++)
    {
        String _month = _monthList.get(i);

        String _monthId = instanceName + "Month" + (i);

        String _display = "display:none;";

        Boolean _hasSelected = false;
        
        ArrayList<PostData> _postList = _data.getBlogEntryDataListByMonthYear(_month, year);
        
        if (selectedDevoId != null && selectedDevoId.length() > 0)
        {
            _hasSelected = _data.getHasSelectedId(selectedDevoId, _postList);

            if (_hasSelected) _display = "";
        }        
        
        String _imageIcon = _hasSelected ? TAG_IMG_COLLAPSE : TAG_IMG_EXPAND;
        
        String _devoCount = " (" +_postList.size()+ ")";
        
        String _title = _month + _devoCount;
                
        _value +="<li class='ul_li'>\n";
		_value +="	<div class='__sdsTopLeft'>\n";
		_value +="		<a href='javascript:void(\"\");' onclick='__sdsContainerDisplay(\""+_monthId+"\");' class='archive-list-title'>";
		_value += _imageIcon.replace("${ID}", _monthId + "ExpandIcon") + "&nbsp;"+_title;
		_value +="</a>\n";
		_value +="	</div>\n";
		_value +="	<ul id='"+_monthId+"' style='"+_display+"left:10px;width:205px;' class='__sdsContainer ul_li'>\n";
		
		_value += getPostData(_postList,selectedDevoId);		
		
		_value +="	</ul>\n";
		_value +="</li>\n";               
    }

    return _value;
}

public String getPostData(ArrayList<PostData> _list, String selectedDevoId)
{    	
    String _value = "";

    int i;

    int l = _list.size();

    for (i = 0; i < l; i++)
    {
        PostData _post = _list.get(i);
        
        String _guid = _post.getId(false);
        
        String _title = _post.getTitle()!=null&&_post.getTitle().length()>0?_post.getTitle():"BLOG";
        
        _value +="<li class='ul_li ul_li_padding'>\n";
        _value +="	<div class='__sdsTopLeft'>\n";
		
        if(_title.length()>35)_title = _title.substring(0, 32)+"...";//+_title.substring(_title.length()-5, _title.length());
        
        String _href = _blogPath;
        
        if(_guid.equals(selectedDevoId))
        {        	
	        _value +="		<a href='"+_blogPath+"' class='archive-list-item-selected'>";
	        _value +="<img width='9' border='0' height='9' src='${basePathUrl}/images/close-icon.png'/>";
	        _value +=" "+_title;
	        _value +="</a>\n";
        }
        else
        {
        	if(_request.getParameter(DataConstants.PAGE)!=null)
        	{
        		_href += "&";
        	}
        	else
        	{
        		_href += "?";
        	}
        	
        	_href += "id="+_guid;
        	
        	_value +="		<a href='"+_href+"' target='_top' class='archive-list-item-link'>";
	        _value +="<img width='9' border='0' height='9' src='${basePathUrl}/images/clear-icon.png'/>";
	        _value +=" "+_title;
	        _value +="</a>\n";        	
        }
        
        _value +="	</div>\n";
        _value +="</li>";
    }

    return _value;
}
%>
<style type='text/css'>
.__sdsTopLeft,.__sdsContainer{text-align:left;vertical-align:top;}
.__sdsContainer{position:relative;}
.__sdsHeader,.__sdsTitle{font-size:11px;font-weight:bold;color:#4b647d;}
.__sdsHeader,.__link{text-decoration:none;}
.__sdsTitle{font-size:12px;}
.__sdsDevoTitle{font-size:22px;color:#0099FF;}
.__sdsDevoSubTitle{font-size:11px;color:#4b647d;}
.__sdsDevoSoapTitle{color:#FF9933;font-size:10px;}
.__sdsDevoSoapBody{font-size:12px;color:#7D96AF;}
.__sdsLinkSelected{font-size:10px;color:#0099FF;}
.__linkBox,.__linkBoxSelected,.__linkBoxDisabled
{
	list-style:none;
	border:solid;
	border-width:thin;
	border-color:#FF9933;
	width:20px;
	display:inline;
	padding-left:2px;
	padding-right:2px;
}
.__linkBox:hover{border-color:#0099FF;color:#0099FF;}
.__linkBoxSelected{border-color:#0099FF;color:#0099FF;}
.__linkBoxDisabled{border-color:#bebebe;color:#bebebe;}
.ul_li{list-style:none;margin:0px;padding:0px;text-align:left;vertical-align:top;color:#0099FF;}
.ul_li_padding{padding-bottom:5px;}

</style>

<script language='javascript' type='text/javascript'>
function __sdsContainerDisplay(id)
{
	var e=document.getElementById(id);
	e.style.display=e.style.display=="none"?"":"none";
	var i=document.getElementById(id+'ExpandIcon');
	i.src=e.style.display=="none"?"<%=_basePath%>/images/expand-icon.png":"<%=_basePath%>/images/collapse-icon.png";
}
</script>
<div style="padding-left:10px;">
	<div class="archive-container">
		<div class='archive-header-container archive-header-title'>ARCHIVE</div>
		<div style='left:5px;width:270px;' class='__sdsContainer'>
			<div style='height:10px;'></div>
			<table cellpadding="0" cellspacing="0" border="0" style="width:270px;">
				<tr>
					<td align="left"><jsp:include page="/WEB-INF/pages/blog/paginator-view.jsp" /></td>
				</tr>
				<tr>
					<td>
						<div style='height:10px;'></div>
					</td>
				</tr>
				<tr>
					<td valign="top"><%=_postHistory%></td>
				</tr>
			</table>
		</div>	        		
		<div style='height:15px;'></div>
	</div>	
</div>