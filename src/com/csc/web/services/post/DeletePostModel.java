/**************************************************************************************************** 
	#TRIGGER EXECUTED WHEN BLOG ENTRY IS DELETED.
	DELIMITER $$
	CREATE TRIGGER onBlogEntryDelete AFTER DELETE ON inamiki_cscblog_blog_entries.BlogEntries 
	FOR EACH ROW 
	BEGIN 
	DELETE FROM inamiki_cscblog_comments.Comments WHERE OwnerId = OLD.Id; 
	END$$
****************************************************************************************************/
package com.csc.web.services.post;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Hector
 * @date Aug 4, 2011
 */

//
public class DeletePostModel extends AbstractPostModel
{
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public DeletePostModel()
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
		String _query = "DELETE FROM Posts WHERE Id = CAST(0x"+ _post.getId(true)+" AS BINARY); ";
		
	    try
		{					
			Statement _statement = _connection.createStatement();
				    	
			int _updateCount = _statement.executeUpdate(_query);	
			
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
