package com.csc.web.services.post;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Hector
 * @date Aug 4, 2011
 */

//
public class UpdatePostModel extends AbstractPostModel
{
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public UpdatePostModel()
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
			updateBlogData();
		} 
		catch (SQLException e){}
	}	
	
	private void updateBlogData() throws SQLException
	{		
	    try
		{						
			PreparedStatement _statement = _connection.prepareStatement
			("UPDATE Posts SET Date= ?, Title= ?, Body= ?, Published=? WHERE Id = ?");
			
			_statement.setTimestamp(1, _post.getDate());
			
			_statement.setString(2, _post.getTitle());
			
			_statement.setString(3, _post.getBlog());
			
			_statement.setBoolean(4, _post.getPublished());
			
			_statement.setBinaryStream(5, _post.getIdAsInputStream());
	    	
			int _updateCount = _statement.executeUpdate();	
			
			_post.success = _updateCount>0;
			
			_statement.close();			
		}
	    catch (SQLException e)
	    {	    		    		    	
	    	throw new RuntimeException(e);
	    }	
	}
	//****************************************************************************************************
	//End - Private Methods
	//****************************************************************************************************
}
