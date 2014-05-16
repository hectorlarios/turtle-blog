package com.csc.web.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.csc.web.data.ConnectionData;

/**
 * Application Lifecycle Listener implementation class ConnDataListener
 *
 */
public class ConnDataListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ConnDataListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent e) 
    {
  		ServletContext _sc = e.getServletContext();
		
		ConnectionData.user = _sc.getInitParameter("connection-data-user");
		
		ConnectionData.password = _sc.getInitParameter("connection-data-password");
		
		ConnectionData.url = _sc.getInitParameter("connection-data-url");
		
		ConnectionData.dbNamePrefix = _sc.getInitParameter("connection-data-db-name-prefix");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
