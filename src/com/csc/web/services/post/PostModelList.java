package com.csc.web.services.post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csc.web.data.PostData;
import com.csc.web.data.PostDataList;
import com.csc.web.constants.DataConstants;

/**
 * @author Hector
 * @date Aug 5, 2011
 */

//com.csc.turtleblog.services.blog.entry.ListModel
public class PostModelList extends AbstractPostModelList
{
	//-------------------------
	//custom classes
	//-------------------------
	PostDataList _historyList;
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public PostModelList(){}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************		
	//****************************************************************************************************
	//Begin  - Private Methods
	//****************************************************************************************************	
	protected void getBlogCount()
	{
		if(_post ==null)
		{
			super.getBlogCount();
			
			_blogEntryDataList.startRecordIndex+=_historyList.startRecordIndex;
		}
	}

/*
	SELECT b.*
	FROM (SELECT Id,OwnerId,Title,Date,Blog,Published,(SELECT COUNT(*)
			FROM inamiki_cscblog_comments.Comments
			WHERE OwnerId = b.Id) AS CommentCount
		FROM inamiki_cscblog_blog_entries.BlogEntries b
		WHERE OwnerId = CAST(0xf172f464b7b643bbb5274a681a58543b AS BINARY)  
		ORDER BY Date DESC
		LIMIT 0,10) b
 */
	protected void findBlog() throws SQLException
	{		
		_query =  "SELECT b.* ";		
		_query += "FROM(SELECT Id,Title,Date,Body,Published,(SELECT COUNT(*) ";		
		_query += "   FROM inamicus_blog.Comments ";
		_query += "   WHERE OwnerId = b.Id) AS CommentCount ";		
		_query += "  FROM inamicus_blog.Posts b ";		
		
		if(_post !=null)
		{			
			_query += "  WHERE Id = CAST(0x"+ _post.getId(true)+" AS BINARY)) b";
		}
		else
		{
			if(publishedOnly)_query += "  WHERE Published = 1 ";
			_query += "  ORDER BY Date DESC ";			
			_query += "  LIMIT "+_blogEntryDataList.startRecordIndex+","+pageSize+")b";
		}		
		
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
				_blog.setBlog(_result.getString(DataConstants.BODY));				
				_blog.setPublished(_result.getBoolean(DataConstants.PUBLISHED));				   
				_blog.commentCount = _result.getInt(DataConstants.COMMENT_COUNT);			   
			   
			   _blogEntryDataList.addBlogEntryData(_blog);
		   }
		   
		   _result.close();
		   
		   _statement.close();			
		} 
		catch (SQLException e){throw new RuntimeException(_query);}
	}	
	//****************************************************************************************************
	//End - Private Methods
	//****************************************************************************************************
	//****************************************************************************************************
	//Begin  - Public Methods
	//****************************************************************************************************	
	public void initialize(PostDataList historyList)
	{
		_historyList = historyList;
		
		initialize();		
	}	
	//****************************************************************************************************
	//End - Public Methods
	//****************************************************************************************************	
}
