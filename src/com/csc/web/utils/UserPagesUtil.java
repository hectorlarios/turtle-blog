package com.csc.web.utils;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hector
 * @date Aug 2, 2011
 */

//
public class UserPagesUtil
{	
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public UserPagesUtil()
	{
	}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************
	//****************************************************************************************************
	//Begin  - Get/Set Methods
	//****************************************************************************************************
	public static String getServletHost(HttpServletRequest request)
	{
		String _value = "";
		
		if(request!=null)_value=getServletHost(request.getRequestURL().toString(), request.getServletPath());
		
		return _value;		
	}
	
	public static String getServletHost(String requestURL, String servletPath)
	{
		servletPath = servletPath.replaceFirst("(/\\w+).+", "$1");
		
		String _value = requestURL.replaceFirst(servletPath+".*", "");
		
		_value = _value.replaceFirst("/$", "");
		
		return _value;			
	}
	
	public static String getProfileDataService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/profile/data";		
		
		return _value;
	}
	
	public static String getLoginPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/profile/login.jsp";		
		
		return _value;
	}
	
	public static String getLoginService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/profile/login";		
		
		return _value;
	}	
	public static String getLogoutPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/profile/logout.jsp";	
		
		_value = _value.replaceAll("^https", "http");
		
		return _value;
	}	
	public static String getLogoutService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/profile/logout";		
		
		return _value;
	}	
	public static String getRegisterIdValidationService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/profile/validateRegisterId";		
		
		return _value;
	}		
	public static String getRegisterPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/profile/register.jsp";		
		
		return _value;
	}	
	public static String getRegisterService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/profile/register";		
		
		return _value;
	}
	
	/********************************************
	 *	DASHBOARD
	 *******************************************/

	public static String getDashboardPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/";		
		
		return _value;		
	}	
	
	/********************************************
	 *	DASHBOARD - BLOG
	 *******************************************/
	public static String getBlogPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/blog.jsp";		
		
		return _value;
	}	
	
	public static String getBlogCreatePage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/blog-create.jsp";		
		
		return _value;
	}
	
	public static String getBlogCreateService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/blog/create";		
		
		return _value;
	}	
	
	public static String getBlogEditPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/blog-edit.jsp";		
		
		return _value;
	}
	
	public static String getBlogEditService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/blog/update";		
		
		return _value;
	}	
	
	public static String getBlogThemePage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/blog-theme.jsp";		
		
		return _value;
	}
	
	public static ArrayList<String> getBlogPages(HttpServletRequest request)
	{
		ArrayList<String> _value = new ArrayList<String>();
		
		_value.add(getBlogPage(request));
		
		_value.add(getBlogCreatePage(request));
		
		_value.add(getBlogEditPage(request));
		
		_value.add(getBlogThemePage(request));
		
		return _value;
	}	
	/********************************************
	 *	DASHBOARD - POST
	 *******************************************/	
	
	public static String getPostCreatePage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/post-create.jsp";		
		
		return _value;
	}	
	
	public static String getPostCreateService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/blog/post/create";		
		
		return _value;
	}	
	
	public static String getPostEditPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/post-edit.jsp";		
		
		return _value;		
	}

	public static String getEditService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/blog/post/update";		
		
		return _value;		
	}	
	
	public static String getPostEditListPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/post-edit-list.jsp";		
		
		return _value;		
	}	
	
	public static String getDeleteService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/blog/post/delete";
		
		return _value;
	}
	
	public static ArrayList<String> getPostPages(HttpServletRequest request)
	{
		ArrayList<String> _value = new ArrayList<String>();
		
		_value.add(getPostCreatePage(request));
		
		_value.add(getPostEditListPage(request));
		
		_value.add(getPostEditPage(request));
		
		return _value;
	}		
	/********************************************
	 *	DASHBOARD - COMMENTS
	 *******************************************/	
	
	public static String getCommentPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/comments.jsp";		
		
		return _value;		
	}
	
	public static String getCreateCommentService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/blog/comment/create";		
		
		return _value;		
	}
	
	public static String getUserCreatePage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/user-create.jsp";		
		
		return _value;		
	}
	
	public static String getUserCreateService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/profile/user/create";		
		
		return _value;		
	}
	
	public static String getUserInvitePage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/user-invite.jsp";		
		
		return _value;		
	}
	
	public static String getUserInviteService(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/services/profile/user/invite";		
		
		return _value;		
	}
	
	public static String getUserInviteSuccessPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/user-invite-success.jsp";		
		
		return _value;		
	}	
	
	public static String getUserEditPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/user-edit.jsp";		
		
		return _value;		
	}		
	
	public static String getUserListPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/user-list.jsp";		
		
		return _value;		
	}	
	
	public static String getUserInviteListPage(HttpServletRequest request)
	{
		String _value = getServletHost(request)+"/dashboard/user-invite-list.jsp";		
		
		return _value;		
	}

	public static ArrayList<String> getUserPages(HttpServletRequest request)
	{
		ArrayList<String> _value = new ArrayList<String>();
		
		_value.add(getUserCreatePage(request));
		
		_value.add(getUserInvitePage(request));
		
		_value.add(getUserInviteSuccessPage(request));
		
		_value.add(getUserEditPage(request));
		
		_value.add(getUserListPage(request));
		
		return _value;
	}
	//****************************************************************************************************
	//End - Get/Set Methods
	//****************************************************************************************************
}
