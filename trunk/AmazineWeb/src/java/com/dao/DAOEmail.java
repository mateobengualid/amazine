/*
 * DAOEmail.java
 * 
 * Created on 07/11/2007, 17:32:21
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;
import com.business.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Fernando
 */
public class DAOEmail implements DAOInterface{
private DBManager manager;    

     public DAOEmail(DBManager m)
    {
        manager=m;        
    }

    public Business get(String businessId) {
        return null;
    }

    public void insert(Business b) {
         throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(Business b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(Business b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Sobrecarga del método getAll(), que recibe el id y retorna una colección
     * con todos los emails de la persona con ese id.
     * @return una colección con todos los emails.
     * @param businessId, el id de la persona, del cual quiero obtener los 
     * emails
     * */
    public Collection<Email> getAll(String businessId) {
        LinkedList <Email> emails=new LinkedList <Email>();
        ResultSet rs=null;
        Email e=null;
        char tipo;
        int id=Integer.parseInt(businessId);//id de la persona
        String query;
        try {
            query="SELECT T.orden, T.tipo, T.email"
                   +" FROM libreria.personaemail T " +
                   "WHERE T.idpersona="+id;
          rs=manager.openQuery(query);        
          while(rs.next())
          { 
              //obtengo el tipo
              tipo=rs.getObject(2).toString().charAt(0);
              //Construyo un email.
              e=new Email(rs.getInt(1), tipo,rs.getString(3));
              //lo agrego en la coleccion que luego devolveré
              emails.add(e);          
          }
          
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }        
        return emails; 
    }
    
    public Collection getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
