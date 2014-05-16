<%@page import="com.csc.web.constants.DataConstants" %>
<%@page import="com.csc.web.data.PostDataList" %>
<%@page import="com.csc.web.services.post.HistoryModelList" %>
<%@page import="com.csc.web.services.post.PostModelList" %>
<%@ page import="com.csc.web.utils.UserPagesUtil" %>
<%@ taglib uri='/WEB-INF/tlds/csc.tld' prefix='csc' %>
<%
    int _page = 1;

    String _pageStr = request.getParameter(DataConstants.PAGE);

    if (_pageStr != null) _page = Math.max(_page, Integer.parseInt(_pageStr));

    HistoryModelList _historyModel = new HistoryModelList();

    _historyModel.page = _page;

    _historyModel.pageSize = 35;

    _historyModel.publishedOnly = false;

    _historyModel.initialize();

    PostDataList _historyList = _historyModel.getBlogEntryDataList();

    PostModelList _postModel = new PostModelList();

    _postModel.pageSize = 35;

    _postModel.publishedOnly = false;

    _postModel.initialize(_historyList);

    PostDataList _postList = _postModel.getBlogEntryDataList();

    request.setAttribute(DataConstants.PAGINATOR_DATA, _historyList.getPaginatorData());

    request.setAttribute(DataConstants.BLOG_LIST, _postList);

    String _basePath = UserPagesUtil.getServletHost(request) + "/";
%>
<csc:template id="/WEB-INF/pages/template/dashboard.jsp">
    <csc:html name="css">
        <link href="<%=_basePath%>css/dashboard/post-list-view.css" rel="stylesheet" type="text/css"/>
        <link href="<%=_basePath%>css/dashboard/post-nav.css" rel="stylesheet" type="text/css"/>
        <link href="<%=_basePath%>css/dashboard/paginator.css" rel="stylesheet" type="text/css"/>
        <link href="<%=_basePath%>css/site/confirm-dialog.css" rel="stylesheet" type="text/css"/>
    </csc:html>
    <csc:html name="script">
        <script src="<%=_basePath%>js/confirm-dialog.js" type="text/javascript"></script>
    </csc:html>
    <csc:html name="content">
        <div class="page-title-container page-title">Posts List</div>
        <div style="width:910px;left:20px;position:relative;">
            <div class="page-dashboard-container">
                <div class="page-dashboard-header">
                    <jsp:include page="/WEB-INF/pages/common/dashboard-nav.jsp"/>
                </div>
                <div class="page-dashboard-body">
                    <jsp:include page="/WEB-INF/pages/common/post-nav.jsp"/>
                    <jsp:include page="/WEB-INF/pages/blog/post-edit-list-view.jsp"/>
                </div>
                <div class="page-dashboard-footer">
                    <jsp:include page="/WEB-INF/pages/blog/paginator-view.jsp"/>
                </div>
            </div>
        </div>
    </csc:html>
</csc:template>