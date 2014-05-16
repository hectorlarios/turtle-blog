package com.csc.web.utils;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

/**
 * @author Hector
 * @date Aug 21, 2011
 */

//com.csc.web.utils.DateUtil
public class DateUtil
{
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public DateUtil()
	{
	}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************		
	//****************************************************************************************************
	//Begin  - Public Methods
	//****************************************************************************************************
	public static String getShortDate()
	{
		String _value = getShortDate(new Date());
		
		return _value;
	}
	public static String getShortDate(Date date)
	{
		if(date==null)date = new Date();
		
		SimpleDateFormat _sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		String _value = _sdf.format(date);
		
		return _value;
	}
	
	public static Date getShortDate(String date)
	{		
		SimpleDateFormat _sdf = new SimpleDateFormat("MM/dd/yyyy");
				
		Date _value = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		try
		{			
			_value = new Date(_sdf.parse(date).getTime());
		} 
		catch (Exception e){}
		
		return _value;		
	}	
	
	public static Timestamp getSqlTimestampFromShortDate(String date)
	{
		Date _date = getShortDate(date);
		
		int _year = Integer.parseInt(getYear(_date));
		
		int _month = Integer.parseInt(getMonth(_date))-1;
		
		int _day = Integer.parseInt(getDay(_date));
		
		Calendar _cal = Calendar.getInstance();
				
		Timestamp _value = new Timestamp(_cal.getTimeInMillis());
		
		try
		{		
			_cal.set(_year, _month, _day);
			
			_value = new Timestamp(_cal.getTimeInMillis());
		} 
		catch (Exception e){}
		
		return _value;		
	}
	
	public static Hashtable<String,Object> getDaysMonthYear(Date date)
	{
		Hashtable<String, Object> _value = new Hashtable<String, Object>();
		
		Calendar _calendar = Calendar.getInstance();
		
		_calendar.setTime(date);
		
		int _days = _calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		String _month = getMonth(date);
		
		String _year = getYear(date);
		
		_value.put("days", _days);
		
		_value.put("month", _month);
		
		_value.put("year", _year);
		
		return _value;
	}
	
	public static String getYear(Date date)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat("yyyy");
		
		String _value = _sdf.format(date);
		
		return _value;
	}
	
	public static String getMonth(Date date)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat("MM");
		
		String _value = _sdf.format(date);
		
		return _value;
	}	
	
	public static String getMonthName(Date date)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat("MMMM");
		
		String _value = _sdf.format(date);
		
		return _value;
	}	
	
	public static String getDay(Date date)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat("dd");
		
		String _value = _sdf.format(date);
		
		return _value;
	}	
	
	public static String getDayName(Date date)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat("EEEE");
		
		String _value = _sdf.format(date);
		
		return _value;
	}		
	
	public static String getHour(Date date)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat("hh");
		
		String _value = _sdf.format(date);
		
		return _value;		
	}		
	
	public static String getMinute(Date date)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat("mm");
		
		String _value = _sdf.format(date);
		
		return _value;		
	}		
	
	public static String getTimePeriod(Date date)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat("aa");
		
		String _value = _sdf.format(date);
		
		return _value;		
	}
	
	public static String getTime(Date date)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat("hh:mm aa");
		
		String _value = _sdf.format(date);
		
		return _value;			
	}
	//****************************************************************************************************
	//End - Public Methods
	//****************************************************************************************************	
}
