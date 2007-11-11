package com.dao;

import com.sun.xml.ws.api.pipe.NextAction;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;




public class DBManager {
    private Connection con;
    private Statement st;
    private PreparedStatement pst;
    private CallableStatement cst;
    private ResultSet res;
    
    public DBManager()  {
        try{
        /*
        Context ctx = new InitialContext();
        DataSource cpds = (DataSource) ctx.lookup("jdbc/LibreriaPool");
        con = cpds.getConnection();*/
        String base = "jdbc:mysql://localhost:3306/libreria?user=root&password=33762636";
        Class.forName("com.mysql.jdbc.Driver");
        
        con = DriverManager.getConnection(base);
        }catch(Exception e){}
    }
    
    public void execute(String query) throws SQLException {
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        System.out.println(query);
        st.execute(query);
    }
    
    public void executeCallable(String query) throws SQLException {
        res = null;
        cst = con.prepareCall(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        System.out.println(query);
        cst.execute(query);
    }
    
    public void executePrepared(String query) throws SQLException {
        res = null;
        
        pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        System.out.println(query);
        con.setAutoCommit(false);//para que se tome como una transacción.
        StringTokenizer st=new StringTokenizer(query,";");
        while(st.hasMoreTokens())
        {   
            query=st.nextToken();
            pst.execute(query);
        }
        con.commit();       
    }
    
    public int getMaxId(String tabla, String campo) throws SQLException {
        res = this.openQuery("Select Max(" + campo + ") FROM " + tabla);
        res.next();
        return res.getInt(1);
    }
    
    public void closeConnection() throws Exception {
        
        this.res.close();
        this.st.close();
        this.pst.close();
        this.cst.close();
        this.con.close();
    }
    
    public ResultSet openQuery(String sent) throws SQLException {
        res = null;
        
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        res = st.executeQuery(sent);
        
        return res;
    }
    
    public ResultSet openCallableQuery(String query) throws SQLException {
        res = null;
        cst = con.prepareCall(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        System.out.println(query);
        con.setAutoCommit(false);//para que se tome como una transacción.
        StringTokenizer st=new StringTokenizer(query,";");
        while(st.hasMoreTokens())
        {   
            query=st.nextToken();
            res=cst.executeQuery(query);            
        }
        con.commit();
        return res;
    }
    
    public ResultSet openPreparedQuery(String query, Map att) throws SQLException {
        res = null;
        
        pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        System.out.println(query);
        
        Iterator it = att.values().iterator();
        int i = 1;
        
        while (it.hasNext()) {
            Object object = it.next();
            pst.setObject(i, object);
            i++;
        }
        
        res = pst.executeQuery(query);
        return res;
    }
}