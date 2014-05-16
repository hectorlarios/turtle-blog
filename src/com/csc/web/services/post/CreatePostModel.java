package com.csc.web.services.post;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.UUID;

import com.csc.web.utils.DateUtil;

/**
 * @author Hector
 * @date Aug 4, 2011
 */

//
public class CreatePostModel extends AbstractPostModel
{
	//-------------------------
	//numbers
	//-------------------------
	final private int ERROR_CODE_DUPLICATE = 1062;	
	
	final private int MAX_ATTEMPTS = 15;
	
	private int _attemptCount = 0;	
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public CreatePostModel()
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
			createBlogEntry();
		} 
		catch (SQLException e){}
	}	
	
	private void createBlogEntry() throws SQLException
	{
		Calendar _calendar = Calendar.getInstance();
		
		Timestamp _entry = new Timestamp(_calendar.getTimeInMillis());
		
	    try
		{
	    	PreparedStatement _statement = _connection.prepareStatement
			("INSERT INTO Posts(Id,Date,Entry,Title,Body,Published)"+
			"VALUES(?,?,?,?,?,?)");
	  			
			_statement.setBinaryStream(1, _post.getIdAsInputStream());
			
			_statement.setTimestamp(2, _post.getDate());
						
			_statement.setTimestamp(3, _entry);
			
			_statement.setString(4, _post.getTitle());
			
			_statement.setString(5, _post.getBlog());
			
			_statement.setBoolean(6, _post.getPublished());
			
			int _updateCount = _statement.executeUpdate();
									
			_post.success = _updateCount>0;
						
			_statement.close();			
		}
	    catch (SQLException e)
	    {
//	    	throw new RuntimeException(e);
	    	
	    	if(ERROR_CODE_DUPLICATE==e.getErrorCode()&&_attemptCount++<MAX_ATTEMPTS)
	    	{
	    		_post.setId(UUID.randomUUID());
		    		
	    		createBlogEntry();		    		
	    	}
	    }	
	}		
	
//	private void createBlogEntry() throws SQLException
//	{
//		Calendar _calendar = Calendar.getInstance();
//		
//		Timestamp _entry = new Timestamp(_calendar.getTimeInMillis());
//		
//		Hashtable<String, Object> _dmy = DateUtil.getDaysMonthYear(_post.getDate());
//		
//		int i = 0;
//		
//		int _days = (Integer) _dmy.get("days");
//		
//		//String _month = (String) _dmy.get("month");
//		
//		int _month = 0;
//		//String _year = (String) _dmy.get("year");
//		int _year = 1990;
//		
//	    try
//		{
//	    	PreparedStatement _statement = _connection.prepareStatement
//			("INSERT INTO Posts(Id,Date,Entry,Title,Body,Published)"+
//			"VALUES(?,?,?,?,?,?)");
//	    	for(int k=0;k<21;k++)
//	    	{
//	    		_year++;
//	    		_month = 0;
//		    	for(int j=0;j<12;j++)
//		    	{
//		    		_month++;
//		    		Hashtable<String, Object> _gdmy = DateUtil.getDaysMonthYear(DateUtil.getSqlTimestampFromShortDate(_month+"/1/"+_year));
//		    		_days = (Integer) _gdmy.get("days");
//		    		
//					for (i = 0; i <_days; i++)
//					{
//						_post.setId(UUID.randomUUID());
//						
//						_statement.setBinaryStream(1, _post.getIdAsInputStream());
//						
//						_statement.setTimestamp(2, DateUtil.getSqlTimestampFromShortDate(_month+"/"+(i+1)+"/"+_year));
//						
//						_statement.setTimestamp(3, _entry);
//						
//						_statement.setString(4, _post.getTitle()+(i+1));
//						
//						_statement.setString(5, _post.getBlog());
//						
//						_statement.setBoolean(6, true);
//						
//						int _updateCount = _statement.executeUpdate();
//												
//						_post.success = _updateCount>0;
//					}
//		    	}
//	    	}
//			_statement.close();			
//		}
//	    catch (SQLException e)
//	    {
//	    	throw new RuntimeException(e);
////	    	_post.message = e.getMessage();
////	    	
////	    	if(ERROR_CODE_DUPLICATE==e.getErrorCode()&&_attemptCount++<MAX_ATTEMPTS)
////	    	{
////	    		_post.setId(UUID.randomUUID());
////		    		
////		    	createBlogEntry();		    		
////	    	}
//	    }	
//	}	
	//****************************************************************************************************
	//End - Private Methods
	//****************************************************************************************************
}
