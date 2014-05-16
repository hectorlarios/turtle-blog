package com.csc.web.tags;

import java.util.Hashtable;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * @author Hector
 * @date Aug 9, 2011
 */

//com.csc.turtleblog.tags.DevoBody
public class CscHtml extends BodyTagSupport
{
	//-------------------------
	//numbers
	//-------------------------
	private static final long serialVersionUID = 1L;	
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public CscHtml()
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
	//****************************************************************************************************
	//End - Get/Set Methods
	//****************************************************************************************************	
	//****************************************************************************************************
	//Begin  - Private Methods
	//****************************************************************************************************
	protected Hashtable<String, String> getProperties()
	{
		@SuppressWarnings("unchecked")
		Hashtable<String, String> _value = (Hashtable<String, String>)pageContext.getAttribute(CscGet.SIMPLE_TAG_SUPPORT,PageContext.REQUEST_SCOPE);
		
		if(_value==null)
		{
			_value = new Hashtable<String, String>();
			
			pageContext.setAttribute(CscGet.SIMPLE_TAG_SUPPORT,_value,PageContext.REQUEST_SCOPE);
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
		return EVAL_BODY_BUFFERED;
	}
	
	public int doEndTag()
	{
	    Hashtable<String, String> _properties = getProperties();
	    
	    String _body = bodyContent.getString();
	    	    
	    if(_name!=null&&_body!=null)_properties.put(_name, _body);
			    
		return EVAL_PAGE;		
	}	
	//****************************************************************************************************
	//End - Public Methods
	//****************************************************************************************************	
}
