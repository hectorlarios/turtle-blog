<%@ taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc' %>
<csc:template id="/WEB-INF/pages/template/dashboard.jsp">
	<csc:html name="content">
		<div class="page-title-container page-title">Dashboard</div>
<!-- 		<div style="width:425px;" class="page-description-container page-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sodales varius lectus sit amet ultricies. Curabitur vulputate lectus ac mi tincidunt et bibendum quam consectetur. In ut porttitor velit.</div> -->
<!-- 		<div style="height:30px;"></div> -->
		<div style="width:910px;left:20px;position:relative;">
			<div class="page-dashboard-container">
				<div class="page-dashboard-header">
					<jsp:include page="/WEB-INF/pages/common/dashboard-nav.jsp" />				
				</div>
				<div class="page-dashboard-body">
					<div style="padding:20px;" class="page-description">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sodales varius lectus sit amet ultricies. Curabitur vulputate lectus ac mi tincidunt et bibendum quam consectetur. In ut porttitor velit.
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sodales varius lectus sit amet ultricies. Curabitur vulputate lectus ac mi tincidunt et bibendum quam consectetur. In ut porttitor velit.
					</div>				
				</div>	
			</div>			
		</div>			
	</csc:html>	
	<%--getServletContext().getRealPath("/") --%>	
</csc:template>