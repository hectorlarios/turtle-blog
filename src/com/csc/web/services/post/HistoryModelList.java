package com.csc.web.services.post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csc.web.data.PostData;
import com.csc.web.constants.DataConstants;

/**
 * @author Hector
 * @date Aug 5, 2011
 */

//com.csc.turtleblog.services.blog.entry.ListModel
public class HistoryModelList extends AbstractPostModelList
{
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public HistoryModelList(){}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************		
	//****************************************************************************************************
	//Begin  - Private Methods
	//****************************************************************************************************	
	protected void findBlog() throws SQLException
	{
		_query = "SELECT Id,Date,Title "+
				 "FROM Posts "+
				 "WHERE Published = 1 "+
				 "ORDER BY Date DESC "+
				 "LIMIT "+_blogEntryDataList.startRecordIndex+","+pageSize;			
		
		try
		{			
			Statement _statement = _connection.createStatement();
			
			ResultSet _result = _statement.executeQuery(_query);
	       	         	   
		   while (_result.next())
		   {
				PostData _blog = new PostData();
				   
				_blog.setIdAsInputStream(_result.getBinaryStream(DataConstants.ID));
			   
				_blog.setDate(_result.getTimestamp(DataConstants.DATE));
			  		   			   
				_blog.setTitle(_result.getString(DataConstants.TITLE));			   
			   
			   _blogEntryDataList.addBlogEntryData(_blog);
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
