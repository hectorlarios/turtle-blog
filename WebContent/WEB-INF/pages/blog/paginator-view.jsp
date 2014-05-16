<%@ page import="com.csc.web.constants.DataConstants" %>
<%@ page import="com.csc.web.data.PaginatorData" %>
<%
PaginatorData _paginator = (PaginatorData)request.getAttribute(DataConstants.PAGINATOR_DATA);

if(_paginator==null)_paginator = new PaginatorData();

String _display = _paginator.totalPages>1?"":"none";
%>
<div class="paginator-container" style="display:<%=_display%>">
	<ul class="paginator-nav-ul">
<%
String _href = "?p=";

_href = _href.replaceAll("&*$", "");

if(_paginator.page==1)
{%>
		<li id="disabled_prev">PREV</li>
<%}
else
{
	String _prev = _href.replaceAll("(p=)\\d*", "$1"+(_paginator.page-1));
%>	
		<li><span id="prev"><a href='<%=_prev%>'>PREV</a></span></li>
<%}
int i;
	
if(_paginator.totalPages>10)
{
	int _displayCount = 8;
	
	int _halfCount = (_displayCount/2)-1;
	
	int _page = _paginator.page;
	
	int _totalPages = _paginator.totalPages;
	
	Boolean _isMin = _page<(_displayCount-3);
	
	Boolean _isMax =_page>(_totalPages-(_displayCount-4));
	
	String _seperatorA = "";
	
	String _seperatorB = "";
	
	_displayCount--;
	
	for(i=0;i<_displayCount;i++)
	{
		int _currentPage=1;
		
		if(i==0)
		{
			_currentPage=1;
			
			if(!_isMin)_seperatorA = "&nbsp...";
		}			
		else if(i==(_displayCount-1))
		{
			_currentPage=_paginator.totalPages;
			
			if(!_isMax)_seperatorB = "...&nbsp";
		}			
		else
		{
			_seperatorA = "";
			
			_seperatorB = "";
			if(_isMin)
			{
				_currentPage=i+1;
			}
			else if(_isMax)
			{
				_currentPage=(_totalPages-(_displayCount))+(i+1);
			}
			else
			{
				_currentPage=(_page-(_halfCount-i));
			}				
		}		
		
		String _link = _href.replaceAll("(p=)\\d*", "$1"+_currentPage);	
					
		if(_paginator.page==_currentPage)
		{%>		
				<%=_seperatorB%><li id="selected"><%=_currentPage%></li><%=_seperatorA%>	
	  <%}
		else
		{%>
				<%=_seperatorB%><li><span><a href='<%=_link%>'><%=_currentPage%></a></span></li><%=_seperatorA%>	
	  <%}
	}
}
else
{
	for(i=0;i<_paginator.totalPages;i++)
	{
		int _currentPage = i+1;			

		String _link = _href.replaceAll("(p=)\\d*", "$1"+_currentPage);%>
				
	  <%if(_paginator.page==_currentPage)
		{%>	
			<li id="selected"><%=_currentPage%></li>
	  <%}
		else
		{%>
			<li><span><a href='<%=_link%>'><%=_currentPage%></a></span></li>
	  <%}
	}		
}
if(_paginator.page==_paginator.totalPages)
{
%>	
			<li id="disabled_next">NEXT</li>
<%	
}
else
{
	String _next = _href.replaceAll("(p=)\\d*", "$1"+(_paginator.page+1));
%>	
			<li><span id="next"><a href='<%=_next%>'>NEXT</a></span></li>
<%}%>
	</ul>
</div>