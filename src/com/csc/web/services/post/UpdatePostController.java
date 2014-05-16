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

/**
 * @author Hector
 * @date Aug 4, 2011
 */

//
public class UpdatePostController extends HttpServlet {
  //-------------------------
  //numbers
  //-------------------------
  private static final long serialVersionUID = 1L;

  //****************************************************************************************************
  //Begin - Constructor
  //****************************************************************************************************
  public UpdatePostController() {
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

  protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession _session = request.getSession(true);

    UpdatePostModel _model = new UpdatePostModel();

    PostData _post = (PostData) _session.getAttribute(DataConstants.POST_DATA_EDIT);

    String _postId = request.getParameter(DataConstants.ID);


    if (_post != null && _post.getId(false).equals(_postId)) {
      _post.setDate(request.getParameter(DataConstants.DATE), true);

      _post.setTitle(request.getParameter(DataConstants.TITLE), true);

      _post.setBlog(request.getParameter(DataConstants.BODY), true);

      _post.setPublished(request.getParameter(DataConstants.PUBLISHED));

      _model.initialize(_post);
    }

    if (_post.success) {
      String _successURL = UserPagesUtil.getServletHost(request) + "/?id=" + _post.getId();

      _session.removeAttribute(DataConstants.POST_DATA_EDIT);

      response.sendRedirect(_successURL);
    } else {
      String _failedURL = UserPagesUtil.getPostEditPage(request) + "?id=" + _post.getId() + "&error=true";

      response.sendRedirect(_failedURL);
    }
  }
  //****************************************************************************************************
  //End - Private Methods
  //****************************************************************************************************
}
