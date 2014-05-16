package com.csc.web.services.post;

import com.csc.web.constants.DataConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Hector
 * @date Aug 5, 2011
 */

//
public class PostModel extends AbstractPostModel
{
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public PostModel()
	{
	}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************
	//****************************************************************************************************
	//Begin  - Private Methods
	//****************************************************************************************************	
	protected void makeDatabaseQuery()
	{
		try
		{
			findBlog();
		} 
		catch (SQLException e){}
	}
	
	private void findBlog() throws SQLException
	{
		try
		{
	    	PreparedStatement _statement = _connection.prepareStatement
			("SELECT Id,Date,Title,Body,Published "+
			"FROM Posts "+
			"WHERE Id = ?");
			
	    	_statement.setBinaryStream(1, _post.getIdAsInputStream());
			
	    	ResultSet _result = _statement.executeQuery();
	       	         	   
		   while (_result.next())
		   {
			   _post.success = true;
			   
			   _post.setDate(_result.getTimestamp(DataConstants.DATE));
			  		   			   
			   _post.setTitle(_result.getString(DataConstants.TITLE));
			   
			   _post.setBlog(_result.getString(DataConstants.BODY));
			   
			   _post.setPublished(_result.getBoolean(DataConstants.PUBLISHED));
		   }
		   
		   _result.close();
		   
		   _statement.close();			
		} 
		catch (SQLException e){throw new RuntimeException(e);}
	}
	//****************************************************************************************************
	//End - Private Methods
	//****************************************************************************************************	
}
