package com.csc.web.data;

import java.util.ArrayList;

/**
 * @author Hector
 * @date Aug 6, 2011
 */

public class PostDataList
{	
	//-------------------------
	//strings
	//-------------------------
	public int page = 1;
	
	public int pageSize = 10;
	
	public int totalPages = 1;
	
	public int totalRecords = 0;
	
	public int startRecordIndex = 0;
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public PostDataList()
	{
		preInitialize();
	}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************
	//****************************************************************************************************
	//Begin  - Get/Set Methods
	//****************************************************************************************************
	private ArrayList<PostData> _list;
	public ArrayList<PostData> getList(){return _list;}
	
	public ArrayList<String> getYearList()
	{
		ArrayList<String> _value = new ArrayList<String>();
		
		int l = _list.size();
		
		for(int i=0;i<l;i++)
		{
			PostData _blog = _list.get(i);
 
		    if(!_value.contains(_blog.getYear())) 
		    {
		    	_value.add(_blog.getYear());		    	
		    }
		}	
		
		return _value;
	}
	
	public ArrayList<String> getMonthListByYear(String year)
	{
		ArrayList<String> _value = new ArrayList<String>();
		
		int l = _list.size();
		
		for(int i=0;i<l;i++)
		{
			PostData _blog = _list.get(i);
 
		    if(year.equals(_blog.getYear())&&!_value.contains(_blog.getMonthName()))
		    {
		    	_value.add(_blog.getMonthName());		    	
		    }
		}	
		
		return _value;		
	}
	
	public ArrayList<PostData> getBlogEntryDataListByMonthYear(String month,String year)
	{
		ArrayList<PostData> _value = new ArrayList<PostData>();
		
		int l = _list.size();
		
		for(int i=0;i<l;i++)
		{
			PostData _blog = _list.get(i);
 
		    if(month.equals(_blog.getMonthName())&&year.equals(_blog.getYear())) 
		    {
		    	_value.add(_blog);		    	
		    }
		}	
		
		return _value;			
	}
	
	public Boolean getHasSelectedId(String selectedId, ArrayList<PostData> list)
	{
		Boolean _value = false;
		
		PostData _blog = null;
		
		int i = 0;
		
		int l = list.size();
		
		while(i<l)
		{
			_blog = list.get(i++);
			
			if(selectedId.equals(_blog.getId(false)))
			{
				_value = true;
				
				break;
			}
		}
		
		return _value;
	}
	
	public PaginatorData getPaginatorData()
	{
		PaginatorData _value = new PaginatorData();
		
		_value.page = page;
		
		_value.totalPages = totalPages;
		
		return _value;
	}
	//****************************************************************************************************
	//End - Get/Set Methods
	//****************************************************************************************************	
	//****************************************************************************************************
	//Begin  - Private Methods
	//****************************************************************************************************
	private void preInitialize()
	{
		_list = new ArrayList<PostData>();
	}	
	//****************************************************************************************************
	//End - Private Methods
	//****************************************************************************************************	
	//****************************************************************************************************
	//Begin  - Public Methods
	//****************************************************************************************************
	public void addBlogEntryData(PostData blogData)
	{
		_list.add(blogData);		
	}
	//****************************************************************************************************
	//End - Public Methods
	//****************************************************************************************************	
}
