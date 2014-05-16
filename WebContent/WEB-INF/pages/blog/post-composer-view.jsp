<%@page import="com.csc.web.data.PostData"%>
<%@page import="com.csc.web.utils.DateUtil"%>
<%@page import="com.csc.web.utils.UserPagesUtil"%>
<%@ page import="com.csc.web.constants.DataConstants" %>
<%@ taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc'%>
<%

String _basePath = UserPagesUtil.getServletHost(request)+"/";

PostData _post = (PostData)session.getAttribute(DataConstants.POST_DATA_EDIT);

String _date = DateUtil.getShortDate();

String _title = "";

String _blog=null;

String _id=null;

String _submitLabel = "Publish";

String _published = "false";

String _postURL = UserPagesUtil.getPostCreateService(request);

if(_post!=null)
{
	_title = _post.getTitle();
	
	_date = _post.getDateString();
	
	_blog = _post.getBlog(); 
	
	_id = _post.getId(false);
	
	_published = _post.getPublished().toString();
	
	_postURL = UserPagesUtil.getEditService(request);
	
	if(_post.getPublished())_submitLabel = "Save";
}
%>

<div class="blog-composer-container">
	<form id="blogComposerForm" action="<%=_postURL%>" method="post">	
	  <div class="blog-composer-form-container">	  	
			<table cellspacing="0" cellpadding="0" border="0">	
				<tr>				
					<td class="blog-input-label">
						Title</span><span id="composerTitleValidationError" style="display:none;"> - <span style="color:#AF0000;">The title field cannot be empty.</span>
					</td>
					<td class="blog-input-label">
						Date
					</td>								
				</tr>
				<tr>				
					<td style="width:400px;">
						<input id="composerTitle" name="title" class="blog-input-field-composerTitle" value="<%=_title%>"/>
					</td>	
					<td><input id="composerDate" name="date" type="text" readonly="readonly" value="<%=_date%>" onclick='openCalendar(this)' style='width:125px;border:1px solid #7AA3CC;'/></td>		
				</tr>
			</table>
			<div style="height:20px"></div>
			<div>
				<span id="composerIFrameValidationError" style="display:none;" class="blog-input-label"><span style="color:#AF0000;">The blog field cannot be empty.</span></span>&nbsp;
			</div>															
			<div class="blog-composer-controls">
				<a href="#" tabindex="-1" id="menuItemBold"><img border="0" width="16" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-bold.png"/></a>
				<a href="#" tabindex="-1" id="menuItemItalic"><img border="0" width="13" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-italic.png"/></a>
				<a href="#" tabindex="-1" id="menuItemUnderline"><img border="0" width="16" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-underline.png"/></a>
				<a href="#" tabindex="-1" id="menuItemStrike"><img border="0" width="43" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-strike.png"/></a>		
				<img border="0" width="2" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-seperator.png"/>
				<a href="#" tabindex="-1" id="menuItemSuperScript"><img border="0" width="24" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-super-script.png"/></a>
				<a href="#" tabindex="-1" id="menuItemSubScript"><img border="0" width="26" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-sub-script.png"/></a>										
				<img border="0" width="2" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-seperator.png"/>	
				<a href="#" tabindex="-1" id="menuItemOrderedList"><img border="0" width="22" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-ordered-list.png"/></a>
				<a href="#" tabindex="-1" id="menuItemUnorderedList"><img border="0" width="22" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-unordered-list.png"/></a>
				<img border="0" width="2" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-seperator.png"/>	
				<a href="#" tabindex="-1" id="menuItemFontColor"><img border="0" width="34" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-font-color.png"/></a>
				<a href="#" tabindex="-1" id="menuItemHighlightColor"><img border="0" width="35" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-highlight-color.png"/></a>	
				<img border="0" width="2" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-seperator.png"/>
				<a href="#" tabindex="-1" id="menuItemFont"><img border="0" width="32" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-font.png"/></a>
				<a href="#" tabindex="-1" id="menuItemSize"><img border="0" width="34" height="16" src="<%=_basePath%>images/blog-entry-composer-view/menu-item-size.png"/></a>		 
			</div>					
			<div id="composerDiv">
				<textarea id="composerTextarea" name="body" style="font-size:12px;font-family:Arial;color:#7D96AF;">
			  	<%if(_post!=null){
			  		out.println(_blog);	
			  	}else{%>				
					<div style="font-size:12px;font-family:Arial;color:#7D96AF;">&nbsp;
<!-- 						<span style="font-size:10px;font-family:Arial;color:#FF6600">Scripture</span> -->
<!-- 						<br> -->
<!-- 						<span style="font-size:12px;font-family:Arial;color:#7D96AF">Add content here...</span> -->
<!-- 						<br> -->
<!-- 						<br> -->
<!-- 						<span style="font-size:10px;font-family:Arial;color:#FF6600">Observation</span> -->
<!-- 						<br> -->
<!-- 						<span style="font-size:12px;font-family:Arial;color:#7D96AF">Add content here...</span> -->
<!-- 						<br> -->
<!-- 						<br> -->
<!-- 						<span style="font-size:10px;font-family:Arial;color:#FF6600">Application</span> -->
<!-- 						<br> -->
<!-- 						<span style="font-size:12px;font-family:Arial;color:#7D96AF">Add content here...</span> -->
<!-- 						<br> -->
<!-- 						<br> -->
<!-- 						<span style="font-size:10px;font-family:Arial;color:#FF6600">Prayer</span> -->
<!-- 						<br> -->
<!-- 						<span style="font-size:12px;font-family:Arial;color:#7D96AF">Add content here...</span> -->
<!-- 						<br> -->
<!-- 						<br>					 -->
					</div>
					
				<%} %>	
				</textarea>	
				<iframe id="composerIFrame" class="blog-input-field-composerIFrame"></iframe>					
			</div>		
	  </div>
	  <div class="blog-composer-form-controls">
	  	<input id="published" name="published" type="hidden" value="<%=_published%>"/>
	  <%if(_id!=null){ %>
	  	<input id="id" name="id" type="hidden" value="<%=_id%>"/>
	  <%}%>	
	  	<input id="submit" type="submit" value="<%=_submitLabel%>" class="blog-composer-submit-button"/>	
		<input id="preview" type="submit" class="blog-composer-submit-button" value="Preview"/>  
	  </div>
	</form>					
</div>
<script type="text/javascript">
	var _bcv = new BlogComposerView();
	
	_bcv.initialize();
</script>
<!-- 								<span style="font-size:10px;font-family:Arial;color:#FF6600">Scripture</span> -->
<!-- 								<br> -->
<!-- 								<span style="font-size:12px;font-family:Arial;color:#7D96AF">Add content here...</span> -->
<!-- 								<br> -->
<!-- 								<br> -->
<!-- 								<span style="font-size:10px;font-family:Arial;color:#FF6600">Observation</span> -->
<!-- 								<br> -->
<!-- 								<span style="font-size:12px;font-family:Arial;color:#7D96AF">Add content here...</span> -->
<!-- 								<br> -->
<!-- 								<br> -->
<!-- 								<span style="font-size:10px;font-family:Arial;color:#FF6600">Application</span> -->
<!-- 								<br> -->
<!-- 								<span style="font-size:12px;font-family:Arial;color:#7D96AF">Add content here...</span> -->
<!-- 								<br> -->
<!-- 								<br> -->
<!-- 								<span style="font-size:10px;font-family:Arial;color:#FF6600">Prayer</span> -->
<!-- 								<br> -->
<!-- 								<span style="font-size:12px;font-family:Arial;color:#7D96AF">Add content here...</span> -->
<!-- 								<br> -->
<!-- 								<br> -->

<!-- 					<div style="font-family:Arial;font-size:12px;color:#000033;"> -->
<!-- 						<span style="color:#FF6C00; font-family: Arial; font-size: 10px;">Scripture</span> -->
<!-- 						<br> -->
<!-- 						<span style="color:#000033; font-family: Arial; font-size: 12px;" >Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor purus at nibh bibendum ullamcorper. In hac habitasse platea dictumst. Cras laoreet erat eget quam pretium aliquet. Proin euismod erat posuere libero hendrerit mollis. Sed ipsum orci, faucibus at tristique non, aliquam sed arcu. Proin volutpat turpis at nisi faucibus sollicitudin. Sed commodo, urna ut fermentum blandit, diam ligula tincidunt massa, et blandit lorem ligula non urna. Aenean pulvinar nisi eu dui ultricies auctor. Ut orci arcu, aliquam quis adipiscing a, accumsan sed tellus. Vivamus id odio quis erat fringilla vulputate. Integer vitae nisl ac elit gravida sagittis ac ut mauris. Phasellus nibh nisl, hendrerit ac gravida nec, feugiat a est. Suspendisse a interdum odio.</span> -->
<!-- 						<br> -->
<!-- 						<span style="color:#000033; font-family: Arial; font-size: 12px;" >Morbi adipiscing viverra fringilla. Suspendisse scelerisque vehicula fringilla. Ut nec cursus ligula. Quisque convallis rutrum justo vitae auctor. Quisque iaculis nisi ac enim lobortis eu suscipit velit convallis. Vivamus ultricies hendrerit aliquet. Mauris a sagittis neque. Mauris consequat consectetur metus at vehicula. Aliquam vestibulum, est nec blandit venenatis, urna diam faucibus sapien, ut tristique lacus dolor ut sem.</span> -->
<!-- 						<br> -->
<!-- 						<br> -->
<!-- 						<span style="color:#FF6C00; font-family: Arial; font-size: 10px;">Observation</span> -->
<!-- 						<br> -->
<!-- 						<span style="color:#000033; font-family: Arial;font-size: 12px;" >Suspendisse a libero mauris. Sed enim lectus, convallis vel accumsan id, hendrerit a tellus. Duis congue lorem et tortor viverra ut vehicula tortor consectetur. Morbi eget enim arcu. Pellentesque eleifend dolor tellus. Mauris rutrum accumsan erat, eget vehicula nunc blandit vitae. Pellentesque dui lorem, interdum sit amet placerat vitae, cursus et ligula. Mauris eleifend nulla elit, vitae lobortis ligula. Nam pulvinar ultricies est, eget dictum quam ornare et. Sed magna justo, dignissim sed faucibus vitae, vestibulum vel felis. Curabitur bibendum leo eu nulla dictum bibendum. Maecenas nibh eros, pellentesque sit amet facilisis ac, posuere eu libero. Morbi est lacus, pulvinar eget facilisis eu, iaculis vel enim.</span> -->
<!-- 						<br> -->
<!-- 						<span style="color:#FF6C00; font-family: Arial; font-size: 10px;">Application</span> -->
<!-- 						<br> -->
<!-- 						<span style="color:#000033; font-family: Arial;font-size: 12px;" >Aenean ultrices adipiscing tortor, eget vehicula dui interdum in. Nulla hendrerit blandit quam ac rhoncus. Nunc nisl tellus, laoreet non malesuada in, porttitor quis justo. Etiam diam quam, fringilla vitae pretium a, gravida ut enim. Nunc in urna elit, sit amet posuere massa. Suspendisse sit amet justo dolor. Suspendisse sed lorem purus, at imperdiet tellus. Ut et dolor ligula. Quisque tristique elit eget magna malesuada et congue nisl mollis. Cras et neque quis mi vehicula feugiat vitae non risus. Proin in tristique justo. Suspendisse interdum dapibus sapien, a placerat dui egestas nec. Curabitur non massa dolor, vitae bibendum massa. Praesent scelerisque varius est, eget dictum lectus porta ut. Vestibulum luctus metus et orci dignissim at aliquet nisl sollicitudin.</span> -->
<!-- 						<br> -->
<!-- 						<br> -->
<!-- 						<span style="color:#FF6C00; font-family: Arial; font-size: 10px;">Prayer</span> -->
<!-- 						<br> -->
<!-- 						<span style="color:#000033; font-family: Arial; font-size: 12px;">In in tortor neque. Mauris imperdiet ipsum et sem venenatis at faucibus felis fringilla. Proin mattis mauris sed velit congue tincidunt. Suspendisse potenti. Proin et tellus est. Vestibulum vel mauris nec purus semper convallis. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec pulvinar, tortor id accumsan tincidunt, nisi elit laoreet quam, ac tincidunt magna sapien mattis felis. Morbi neque purus, pharetra eget blandit suscipit, pulvinar id tortor. Pellentesque quis dignissim ligula. Aliquam eget nulla diam. Nulla arcu lectus, ultrices non laoreet id, cursus non libero.</span> -->
<!-- 						<br> -->
<!-- 						<br>					 -->
<!-- 					</div> -->