package com.csc.web.services.post;

import com.csc.web.constants.DataConstants;
import com.csc.web.data.PostData;
import com.csc.web.utils.UserPagesUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Hector
 * @date Oct 22, 2011
 */
public class DeletePostController extends HttpServlet {
  //-------------------------
  //numbers
  //-------------------------
  private static final long serialVersionUID = 1L;

  //****************************************************************************************************
  //Begin - Constructor
  //****************************************************************************************************
  public DeletePostController() {
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
    DeletePostModel _model = new DeletePostModel();

    PostData _blog = new PostData();

    _blog.setId(UUID.fromString(request.getParameter(DataConstants.ID)));

    _model.initialize(_blog);

    if (_blog.success) {
      String _successURL = request.getHeader("Referer");//UserPagesUtil.getEditListPage(request);//+request.get;

      response.sendRedirect(_successURL);
    } else {
      String _failedURL = UserPagesUtil.getPostEditListPage(request);

      response.sendRedirect(_failedURL);
    }
  }
//****************************************************************************************************
//End - Private Methods
//****************************************************************************************************
}




























