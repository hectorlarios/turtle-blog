package com.csc.web.services;

import com.csc.web.constants.DataConstants;
import com.csc.web.data.PostData;
import com.csc.web.data.PostDataList;
import com.csc.web.services.post.HistoryModelList;
import com.csc.web.services.post.PostModelList;
import com.csc.web.utils.UserPagesUtil;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UserHomePage
 */

public class UserHomePageController extends HttpServlet 
{
	// -------------------------
	// numbers
	// -------------------------
	private static final long serialVersionUID = 1L;
	// ****************************************************************************************************
	// Begin - Constructor
	// ****************************************************************************************************
	/**
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public UserHomePageController()
	{
	}
	// ****************************************************************************************************
	// End - Constructor
	// ****************************************************************************************************
	// ****************************************************************************************************
	// Begin - Private Methods
	// ****************************************************************************************************
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response);
	}
	
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		String _redirectURL = "/WEB-INF/pages/error/404.jsp";

    int _page = 1;

    String _pageStr = request.getParameter(DataConstants.PAGE);

    if(_pageStr!=null&&_pageStr!="")_page = Math.max(_page, Integer.parseInt(_pageStr));

    HistoryModelList _historyModel = new HistoryModelList();

    _historyModel.page = _page;
    
    _historyModel.pageSize = 350;

    _historyModel.initialize();

    PostDataList _historyList = _historyModel.getBlogEntryDataList();

    String _selectedId = request.getParameter(DataConstants.ID);

    PostModelList _postModelList = new PostModelList();

    _postModelList.pageSize = 15;

    if(_selectedId!=null&&_selectedId.length()>0)
    {
      PostData _postData = new PostData();

      _postData.setId(UUID.fromString(_selectedId));

      _postModelList.initialize(_postData);

      request.setAttribute(DataConstants.SELECTED_BLOG_ID, _selectedId);
    }
    else
    {
      _postModelList.initialize(_historyList);
    }

    PostDataList _blogList = _postModelList.getBlogEntryDataList();

    if(_blogList.getList().size()>0)
    {
      String _returnUrl = UserPagesUtil.getServletHost(request)+request.getServletPath()+"/";

      request.setAttribute(DataConstants.BLOG_HISTORY, _historyList);

      request.setAttribute(DataConstants.BLOG_LIST, _blogList);

      request.setAttribute(DataConstants.PAGINATOR_DATA, _historyList.getPaginatorData());

      request.setAttribute(DataConstants.RETURN_URL, _returnUrl);

      request.setAttribute(DataConstants.BLOG_PAGE_REQUEST, request.getRequestURL().toString());

      _redirectURL = "/WEB-INF/pages/profile/user-blog.jsp";
    }
		
		request.getRequestDispatcher(_redirectURL).forward(request, response);
	}
	// ****************************************************************************************************
	// End - Private Methods
	// ****************************************************************************************************
}
