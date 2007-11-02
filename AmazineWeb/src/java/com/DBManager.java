package com;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.ConnectionPoolDataSource;


public class DBManager
{
    private Connection con;
    private Statement st;
    private PreparedStatement pst;
    private CallableStatement cst;
    private ResultSet res;

    public DBManager() throws Exception
    {
	try
	{
	    Context ctx = new InitialContext();
	    ConnectionPoolDataSource cpds = (ConnectionPoolDataSource) ctx.lookup("jdbc/MySQLConnectionPool");
	    con = cpds.getPooledConnection().getConnection();
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	    throw new Exception();
	}
    }

    public DBManager(String alias) throws Exception
    {
	try
	{
	    String base = "jdbc:odbc:" + alias;
	    Class.forName("sun.jdbc.obdc.JdbcOdbcDriver");
	    con = DriverManager.getConnection(base);
	}
	catch (Exception e)
	{
	    throw new Exception();
	}
    }

    public void execute(String query)
    {
	res = null;
	try
	{
	    st = con.createStatement();
	    System.out.println(query);
	    st.execute(query);
	}
	catch (Exception ex)
	{
	    ex.printStackTrace();
	}
    }

    public void executeCallable(String query, Map att)
    {
	res = null;
	try
	{
	    cst = con.prepareCall(query);
	    System.out.println(query);

	    Iterator it = att.values().iterator();
	    int i = 1;

	    while (it.hasNext())
	    {
		Object object = it.next();
		cst.setObject(i, object);
		i++;
	    }

	    cst.execute(query);
	}
	catch (Exception ex)
	{
	    ex.printStackTrace();
	}
    }

    public void executePrepared(String query, Map att)
    {
	res = null;
	try
	{
	    pst = con.prepareStatement(query);
	    System.out.println(query);

	    Iterator it = att.values().iterator();
	    int i = 1;

	    while (it.hasNext())
	    {
		Object object = it.next();
		pst.setObject(i, object);
		i++;
	    }

	    pst.execute(query);
	}
	catch (Exception ex)
	{
	    ex.printStackTrace();
	}
    }

    public int getMaxId(String tabla, String campo) throws SQLException
    {
	res = this.openQuery("Select Max(" + campo + ") FROM " + tabla);
	res.next();
	return res.getInt(1);
    }

    public void closeConnection() throws Exception
    {
	try
	{
	    this.res.close();
	    this.st.close();
	    this.pst.close();
	    this.cst.close();
	    this.con.close();
	}
	catch (Exception e)
	{
	    throw new Exception();
	}
    }

    public ResultSet openQuery(String sent)
    {
	res = null;
	try
	{
	    st = con.createStatement();
	    res = st.executeQuery(sent);
	}
	catch (SQLException e)
	{
	    // TODO Bloque catch generado autom�ticamente
	    e.printStackTrace();
	}
	return res;
    }

    public void openCallableQuery(String query, Map att)
    {
	res = null;
	try
	{
	    cst = con.prepareCall(query);
	    System.out.println(query);

	    Iterator it = att.values().iterator();
	    int i = 1;

	    while (it.hasNext())
	    {
		Object object = it.next();
		cst.setObject(i, object);
		i++;
	    }

	    res = cst.executeQuery(query);
	}
	catch (Exception ex)
	{
	    ex.printStackTrace();
	}
    }

    public void openPreparedQuery(String query, Map att)
    {
	res = null;
	try
	{
	    pst = con.prepareStatement(query);
	    System.out.println(query);

	    Iterator it = att.values().iterator();
	    int i = 1;

	    while (it.hasNext())
	    {
		Object object = it.next();
		cst.setObject(i, object);
		i++;
	    }

	    res = pst.executeQuery(query);
	}
	catch (Exception ex)
	{
	    ex.printStackTrace();
	}
    }
}