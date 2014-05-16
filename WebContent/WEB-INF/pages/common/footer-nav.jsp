<%@page import="java.util.Date"%>
<%@page import="com.csc.web.utils.DateUtil"%>
<%@taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc'%>
<%
%>

<div class="footer-container">		
	<table border="0" cellpadding="0" cellspacing="0" class="footer-container-table">
		<tr>	
			<td><div class="footer-title-container">TurtleBLOG &copy; <%=DateUtil.getYear(new Date()) %> Patient Turtle</div></td>
			<td>
				<div class="footer-control-container">
					<ul class="footer-ul">
						<li><span><a href="#">Lorem</a></span></li>
						�
						<li><span><a href="#">Ipsum </a></span></li>
						�
						<li><span><a href="#">Dolor </a></span></li>
						�
						<li><span><a href="#">Amet</a></span></li>
						�
						<li><span><a href="#">Consectetur</a></span></li>
					</ul>			
				</div>
			</td>							
		</tr>
	</table>
</div>

		