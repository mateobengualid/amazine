package com;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBManager
{
    private Connection con;
    private Statement st;
    private PreparedStatement pst;
    private CallableStatement cst;
    private ResultSet res;

    public DBManager() throws Exception
    {
	Context ctx = new InitialContext();
	DataSource cpds = (DataSource) ctx.lookup("jdbc/LibreriaPool");
	con = cpds.getConnection();
    }

    public void execute(String query) throws SQLException
    {
	st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	System.out.println(query);
	st.execute(query);
    }

    public void executeCallable(String query, Map att) throws SQLException
    {
	res = null;

	cst = con.prepareCall(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

    public void executePrepared(String query, Map att) throws SQLException
    {
	res = null;

	pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

    public int getMaxId(String tabla, String campo) throws SQLException
    {
	res = this.openQuery("Select Max(" + campo + ") FROM " + tabla);
	res.next();
	return res.getInt(1);
    }

    public void closeConnection() throws Exception
    {

	this.res.close();
	this.st.close();
	this.pst.close();
	this.cst.close();
	this.con.close();
    }

    public ResultSet openQuery(String sent) throws SQLException
    {
	res = null;

	st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	res = st.executeQuery(sent);

	return res;
    }

    public ResultSet openCallableQuery(String query, Map att) throws SQLException
    {
	res = null;

	cst = con.prepareCall(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

	return res;
    }

    public ResultSet openPreparedQuery(String query, Map att) throws SQLException
    {
	res = null;

	pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	System.out.println(query);

	Iterator it = att.values().iterator();
	int i = 1;

	while (it.hasNext())
	{
	    Object object = it.next();
	    pst.setObject(i, object);
	    i++;
	}

	res = pst.executeQuery(query);
	return res;
    }
}