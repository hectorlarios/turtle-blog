package com.csc.web.tags;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Hector
 * @date Aug 8, 2011
 */

//com.csc.turtleblog.tags.HistoryTagSupport
public class CscGet extends TagSupport
{
	//-------------------------
	//numbers
	//-------------------------
	private static final long serialVersionUID = 1L;
	//-------------------------
	//strings
	//-------------------------	
	final protected static String SIMPLE_TAG_SUPPORT = "simpleTagSupport";
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public CscGet()
	{
	}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************
	//****************************************************************************************************
	//Begin  - Get/Set Methods
	//****************************************************************************************************
	protected String _name;
	public void setName(String value) {_name=value;}
	
	protected String _id;
	public void setId(String value) {_id=value;}	
	
	protected String _length;
	public void setLength(String value) {_length=value;}
	
	protected String _default;
	public void setDefault(String value) {_default=value;}
	//****************************************************************************************************
	//End - Get/Set Methods
	//****************************************************************************************************	
	//****************************************************************************************************
	//Begin  - Private Methods
	//****************************************************************************************************
	protected Hashtable<String, String> getProperties()
	{
		@SuppressWarnings("unchecked")
		Hashtable<String, String> _value = (Hashtable<String, String>)pageContext.getAttribute(SIMPLE_TAG_SUPPORT,PageContext.REQUEST_SCOPE);
		
		if(_value==null)
		{
			_value = new Hashtable<String, String>();
			
			pageContext.setAttribute(SIMPLE_TAG_SUPPORT,_value,PageContext.REQUEST_SCOPE);
		}
		
		return _value;
	}
	//****************************************************************************************************
	//End - Private Methods
	//****************************************************************************************************		
	//****************************************************************************************************
	//Begin  - Public Methods
	//****************************************************************************************************
	public int doStartTag()
	{
		Hashtable<String, String> _properties = getProperties();
		
		String _value =  null;
		
		if(_id!=null)
		{
			_value = _properties.get(_id);
			
			try {pageContext.include(_value);}
			
			catch(Exception ex){}			
		}
		else 
		{
			JspWriter _out = pageContext.getOut();		
			
			_value = _properties.get(_name);
			
			if(_value==null)_value=_default!=null?_default:"";//_name+": ValueIsNull";
			
			try
			{	
				_out.println(_value);
			} 
			catch (IOException e){}
		}
		
		return SKIP_BODY;
		
	}
	//****************************************************************************************************
	//End - Public Methods
	//****************************************************************************************************	
}
