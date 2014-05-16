package com.csc.web.services.post;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.web.data.PostData;
import com.csc.web.constants.DataConstants;

/**
 * @author Hector
 * @date Aug 5, 2011
 */

//
public class PostController extends HttpServlet
{
	//-------------------------
	//numbers
	//-------------------------
	private static final long serialVersionUID = 1L;
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public PostController()
	{
	}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************
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
	
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	{		
		HttpSession _session = request.getSession(true);
		
		PostModel _model = new PostModel();

    PostData _blog = new PostData();

    _blog.setId(UUID.fromString(request.getParameter(DataConstants.ID)));

    _model.initialize(_blog);

    if(_blog.success)
    {
      _session.setAttribute(DataConstants.POST_DATA, _blog);
    }
	}
	// ****************************************************************************************************
	// End - Private Methods
	// ****************************************************************************************************	
}
