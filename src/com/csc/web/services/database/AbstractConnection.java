package com.csc.web.services.database;

import com.csc.web.data.ConnectionData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Hector
 * @date Jul 25, 2011
 */

//
abstract public class AbstractConnection {
  //-------------------------
  //custom classes
  //-------------------------
  protected Connection _connection = null;
  //-------------------------
  //strings
  //-------------------------
  protected ConnectionData _data;

  //****************************************************************************************************
  //Begin - Constructor
  //****************************************************************************************************
  public AbstractConnection() {
    _data = ConnectionData.getGlobalData();

    preInitialize();
  }

  public AbstractConnection(ConnectionData data) {
    _data = data;

    preInitialize();
  }

  //****************************************************************************************************
  //End  - Constructor
  //****************************************************************************************************
  //****************************************************************************************************
  //Begin  - Private Methods
  //****************************************************************************************************
  private void preInitialize() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();

      String _database = getDatabaseName();

      _connection = DriverManager.getConnection(_data.getUrl() + _database, _data.getUser(), _data.getPassword());      
    } catch (Exception e) {
    	throw new RuntimeException(e);//_data.getUrl() + getDatabaseName()+", do _connection is not null:"+(_connection!=null));
    }
  }

  protected void initializeClass() {
    try {
      makeDatabaseQuery();

      _connection.close();
    } catch (SQLException e) {
      //throw new RuntimeException(e);
    }
  }

  abstract protected String getDatabaseName();

  abstract protected void makeDatabaseQuery() throws SQLException;

  //****************************************************************************************************
  //End - Private Methods
  //****************************************************************************************************
  //****************************************************************************************************
  //Begin  - Public Methods
  //****************************************************************************************************
  public void initialize() {
    initializeClass();
  }
  //****************************************************************************************************
  //End - Public Methods
  //****************************************************************************************************
}
