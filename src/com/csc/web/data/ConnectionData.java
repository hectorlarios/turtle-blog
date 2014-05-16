package com.csc.web.data;

/**
 @author Gabriela
 @data   May 13, 2012
 **/

public class ConnectionData 
{
	//-------------------------
	//strings
	//-------------------------
	public static String user;
	
	public static String password;
	
	public static String url; 
	
	public static String dbNamePrefix;
	//****************************************************************************************************
	//Begin - Constructor
	//****************************************************************************************************    
	public ConnectionData(String user, String password, String url, String dbNamePrefix) 
	{
		_user = user;
		
		_password = password;
		
		_url = url;
		
		_dbNamePrefix = dbNamePrefix;
	}
	//****************************************************************************************************
	//End  - Constructor
	//****************************************************************************************************	
	//****************************************************************************************************
	//Begin  - Get/Set Methods
	//****************************************************************************************************
	private String _user;// = "inamiki_admin";
	public String getUser(){return _user;}

    private String _password;// = "$@f3P@$$W0rd";//$@f3P@$$W0rd
	public String getPassword(){return _password;}
	
    private String _url;// = "jdbc:mysql://eland.arvixe.com:3306/";
	public String getUrl(){return _url;}	
	
	private String _dbNamePrefix;
	public String getDbNamePrefix(){return _dbNamePrefix;}
	
	public static ConnectionData getGlobalData()
	{
		ConnectionData _value = new ConnectionData(user, password, url, dbNamePrefix);
		
		return _value;
	}
	//****************************************************************************************************
	//End - Get/Set Methods
	//****************************************************************************************************		
}