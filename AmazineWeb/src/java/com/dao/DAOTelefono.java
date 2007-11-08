/*
 * DAOTelefono.java
 * 
 * Created on 07/11/2007, 16:46:47
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
public class DAOTelefono implements DAOInterface{
private DBManager manager;    

     public DAOTelefono(DBManager m)
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
     * con todos los telefonos de la persona con ese id.
     * @return una colección con todos los telefonos.
     * @param businessId, el id de la persona, del cual quiero obtener los 
     * telefonos
     * */
    public Collection<Telefono> getAll(String businessId) {
        LinkedList <Telefono> telefonos=new LinkedList <Telefono>();
        ResultSet rs=null;
        Telefono tel=null;
        char tipo;
        int id=Integer.parseInt(businessId);//id de la persona
        String query;
        try {
            query="SELECT T.orden, T.tipo, T.telefono"
                   +" FROM libreria.personatelefono T " +
                   "WHERE T.idpersona="+id;
          rs=manager.openQuery(query);        
          while(rs.next())
          { 
              //obtengo el tipo
              tipo=rs.getObject(2).toString().charAt(0);
              //Construyo un teléfono
              tel=new Telefono(rs.getInt(1), tipo,
                       rs.getString(3));
              //lo agrego en la coleccion que luego devolveré
              telefonos.add(tel);          
          }
          
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }        
        return telefonos; 
    }
    
    public Collection getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}