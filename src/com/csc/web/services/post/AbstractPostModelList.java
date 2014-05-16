package com.csc.web.services.post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csc.web.data.PostDataList;

/**
 * @author Hector
 * @date Aug 5, 2011
 */

//com.csc.turtleblog.services.blog.entry.ListModel
abstract public class AbstractPostModelList extends AbstractPostModel
{
	//-------------------------
	//booleans
	//-------------------------
	public Boolean publishedOnly = true;
	//-------------------------
	//numbers
	//-------------------------	
	public int page = 1;
	public int pageSize = 125;	
	//-------------------------
	//strings
	//-------------------------	
	protected String _query = "";
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public AbstractPostModelList()
	{
		preInitialize();
	}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************
	//****************************************************************************************************
	//Begin  - Get/Set Methods
	//****************************************************************************************************
	protected PostDataList _blogEntryDataList;
	public PostDataList getBlogEntryDataList() {return _blogEntryDataList;}
	//****************************************************************************************************
	//End - Get/Set Methods
	//****************************************************************************************************		
	//****************************************************************************************************
	//Begin  - Private Methods
	//****************************************************************************************************	
	protected void makeDatabaseQuery()
	{
		try
		{
			getBlogCount();
						
			findBlog();
		} 
		catch (SQLException e){throw new RuntimeException(e);}
	}
	
	protected void getBlogCount()
	{
		try
		{
			String _queryCount = "SELECT COUNT(*) "+
					"FROM Posts "/*+
					"WHERE OwnerId = CAST(0x"+_user.blog.getId(true)+" AS BINARY) "*/;
			
			if(publishedOnly)_queryCount+="WHERE Published = 1 ";
			
			Statement _statement = _connection.createStatement();
			
			ResultSet _result = _statement.executeQuery(_queryCount);
			
			_result.next();
			
			_blogEntryDataList.totalRecords = _result.getInt(1);
			
			_blogEntryDataList.totalPages = _blogEntryDataList.totalRecords/pageSize;
			
			if(_blogEntryDataList.totalRecords%pageSize>0)_blogEntryDataList.totalPages+=1;
			
			_result.close();
		   
		   _statement.close();
			
			_blogEntryDataList.page = Math.min(_blogEntryDataList.totalPages , page);
			
			_blogEntryDataList.pageSize = pageSize;	
			
			_blogEntryDataList.startRecordIndex = Math.max(0,(_blogEntryDataList.page-1)*pageSize);			
		} 
		catch (SQLException e){throw new RuntimeException(e);}
	}	
	
	protected void findBlog() throws SQLException{}
		
	private void preInitialize()
	{
		_blogEntryDataList = new PostDataList();
	}
	//****************************************************************************************************
	//End - Private Methods
	//****************************************************************************************************		
}
