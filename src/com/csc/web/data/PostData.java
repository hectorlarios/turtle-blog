package com.csc.web.data;

import java.sql.Timestamp;

import com.csc.web.utils.DateUtil;
import com.csc.web.data.AbstractData;

/**
 * @author Hector
 * @date Jul 27, 2011
 */

//
public class PostData extends AbstractData
{		
	//-------------------------
	//booleans
	//-------------------------
	public Boolean blogUpdated = false;
	
	public Boolean dateUpdated = false;
	
	public Boolean titleUpdated = false;
	
	public Boolean publishedUpdated = false;
	//-------------------------
	//numbers
	//-------------------------
	public int commentCount = 0;
	//-------------------------
	//strings
	//-------------------------
	final private String TRUE = "true";
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public PostData(){}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************
	//****************************************************************************************************
	//Begin  - Get/Set Methods
	//****************************************************************************************************
	private String _blog;
	public String getBlog() {return _blog;}
	public void setBlog(String value) {setBlog(value,false);}
	public void setBlog(String value, Boolean isUpdate)
	{
		blogUpdated = valueUpdated(_blog, value, isUpdate);;
		
		_blog = value;
	}	
	protected Timestamp _date;	
	public Timestamp getDate(){return _date;}
	public void setDate(Timestamp value)
	{		
		setDate(value, false);
	}
	public void setDate(String value)
	{
		setDate(value, false);
	}
	
	public void setDate(String value,Boolean isUpdate)
	{
		Timestamp _newDate = DateUtil.getSqlTimestampFromShortDate(value);
		
		setDate(_newDate, isUpdate);
	}
	
	public void setDate(Timestamp value,Boolean isUpdate)
	{
		String _oldValue = DateUtil.getShortDate(_date);
		
		String _newValue = DateUtil.getShortDate(value);
		
		dateUpdated = valueUpdated(_oldValue, _newValue, isUpdate);
		
		if(!isUpdate||(isUpdate&&dateUpdated))_date = value;
	}
	
	protected String _title;
	public String getTitle() {return _title;}
	public void setTitle(String value){setTitle(value, false);}
	
	public void setTitle(String value, Boolean isUpdate)
	{	
		titleUpdated = valueUpdated(_title, value, isUpdate);
				
		_title = value;
	}
	
	protected Boolean _published = false;
	public Boolean getPublished() {return _published;}	
	public void setPublished(String value){setPublished(value, false);}
	
	public void setPublished(String value, Boolean isUpdate)
	{
		Boolean _value = TRUE.equals(value);
		
		setPublished(_value,isUpdate);
	}
	
	public void setPublished(Boolean value){setPublished(value, false);}
	
	public void setPublished(Boolean value, Boolean isUpdate)
	{
		String _oldValue = _published.toString();
		
		String _newValue = value.toString();
		
		publishedUpdated = valueUpdated(_oldValue, _newValue, isUpdate);
		
		_published = value;
	}	
	
	public String getDateString()
	{		
		String _value = DateUtil.getShortDate(_date);
		
		return _value;
	}
	
	public String getYear()
	{		
		String _value = DateUtil.getYear(_date);
		
		return _value;
	}
	
	public String getMonthName()
	{
		String _value = DateUtil.getMonthName(_date);
		
		return _value;
	}
	
	public String getDay()
	{
		String _value = DateUtil.getDay(_date);
		
		return _value;		
	}
	public String getDayName()
	{
		String _value = DateUtil.getDayName(_date);
		
		return _value;		
	}		
	//****************************************************************************************************
	//End - Get/Set Methods
	//****************************************************************************************************	
}
