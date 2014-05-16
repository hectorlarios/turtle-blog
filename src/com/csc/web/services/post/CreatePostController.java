package com.csc.web.services.post;

import com.csc.web.constants.DataConstants;
import com.csc.web.data.PostData;
import com.csc.web.utils.UserPagesUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Hector
 * @date Aug 4, 2011
 */

//
public class CreatePostController extends HttpServlet {
  //-------------------------
  //numbers
  //-------------------------
  private static final long serialVersionUID = 1L;

  //****************************************************************************************************
  //Begin - Constructor
  //****************************************************************************************************
  public CreatePostController() {
  }

  //****************************************************************************************************
  //End  - Constructor
  //****************************************************************************************************
  //****************************************************************************************************
  //Begin  - Private Methods
  //****************************************************************************************************
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    handleRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    handleRequest(request, response);
  }

  protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    CreatePostModel _model = new CreatePostModel();


    PostData _blog = new PostData();

    _blog.setId(UUID.randomUUID());

    _blog.setDate(request.getParameter(DataConstants.DATE));

    _blog.setTitle(request.getParameter(DataConstants.TITLE));

    _blog.setBlog(request.getParameter(DataConstants.BODY));

    _blog.setPublished(request.getParameter(DataConstants.PUBLISHED));

    _model.initialize(_blog);

    if (_blog.success) {
      String _redirectURL = UserPagesUtil.getServletHost(request) + "?id=" + _blog.getId();

      response.sendRedirect(_redirectURL);
    } else {
//				request.setAttribute(DataConstants.DEVO_DATA, _post);
//				
//				request.getRequestDispatcher(UserPagesUtil.CREATE_PAGE).forward(request, response);
    }
  }
  //****************************************************************************************************
  //End - Private Methods
  //****************************************************************************************************
}
