package com.csc.web.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author Hector
 * @date Jul 27, 2011
 */

//
abstract public class AbstractData
{
	//-------------------------
	//arrays
	//-------------------------
	private byte[] _idBuffer;
	//-------------------------
	//booleans
	//-------------------------
	public boolean hasUpdate = false;
	
	public Boolean success = false;
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************
	public AbstractData(){}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************
	//****************************************************************************************************
	//Begin  - Get/Set Methods
	//****************************************************************************************************
	private InputStream _idAsInputStream;
	public InputStream getIdAsInputStream()
	{
		_idAsInputStream = null;
		
		try
		{
			_idAsInputStream = new ByteArrayInputStream(Arrays.copyOf(_idBuffer, _idBuffer.length));	//updateInputStream();
		} 
		catch (Exception e){}						
		
		return _idAsInputStream;
	}
	
	public void setIdAsInputStream(InputStream id)
	{
		_idAsInputStream = id;
		
		if(_idAsInputStream!=null)
		{
			updateUUID(); 
			
			updateInputStream();
		}
		
		else _id = null;
	}	
	
	private UUID _id;
	public UUID getId(){return _id;}
	public String getId(Boolean removeHyphens)
	{
		String _value = _id!=null?_id.toString():"";
		
		if(removeHyphens)_value=_value.replace("-", "");
		
		return _value;
	}
	
	public void setId(UUID id)
	{
		_id = id;
		
		if(_id!=null)updateInputStream();
		
		else _idAsInputStream = null;
	}
	//****************************************************************************************************
	//End - Get/Set Methods
	//****************************************************************************************************	
	//****************************************************************************************************
	//Begin  - Private Methods
	//****************************************************************************************************
	private void updateUUID()
	{
		ByteArrayOutputStream _os = new ByteArrayOutputStream();
			
		int _read;
		   		
		try
		{				
			while ((_read = _idAsInputStream.read())>-1)
			{
				_os.write(_read);
			}
				
			_os.flush();
		} 
		catch (IOException e){}	
		
		ByteBuffer _buffer = ByteBuffer.wrap(_os.toByteArray());
		
		_id = new UUID(_buffer.getLong(), _buffer.getLong());
	}
	
	private void updateInputStream()
	{
		ByteBuffer _buffer = ByteBuffer.allocate(16);
		
		if(_id!=null)_buffer.putLong(_id.getMostSignificantBits()).putLong(_id.getLeastSignificantBits());	
		
		_idBuffer = _buffer.array();		
	}
	
	protected Boolean valueUpdated(String oldValue, String newValue, Boolean isUpdate)
	{
		Boolean _value = false;
		
		String _oldValue = oldValue!=null?oldValue:"";
		
		String _newValue = newValue!=null?newValue:"";
		
		if(!_oldValue.equals(_newValue))
		{
			_value = isUpdate;
			
			if(_value)hasUpdate = true; 
		}
		
		return _value;	
	}
	//****************************************************************************************************
	//End - Private Methods
	//****************************************************************************************************
}
