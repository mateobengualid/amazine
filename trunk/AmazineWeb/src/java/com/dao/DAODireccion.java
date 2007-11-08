/*
 * DAODireccion.java
 * 
 * Created on 07/11/2007, 17:20:28
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
public class DAODireccion implements DAOInterface{
private DBManager manager;    

     public DAODireccion(DBManager m)
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
     * con todos las direcciones de la persona con ese id.
     * @return una colección con todos las direcciones.
     * @param businessId, el id de la persona, del cual quiero obtener las 
     * direcciones.
     * */
    public Collection<Direccion> getAll(String businessId) {
        LinkedList <Direccion> direcciones=new LinkedList <Direccion>();
        ResultSet rs=null;
        Direccion dir=null;
        char tipo;
        int id=Integer.parseInt(businessId);//id de la persona
        String query;
        try {
            query="SELECT T.orden,T.tipo,T.direccioncp,T.direccionlocalidad," +
                    "T.direccioncalle,T.direccionnro,T.direccionpuerta,T.observaciones"
                   +" FROM libreria.personadireccion T " +
                   "WHERE T.idpersona="+id;
          rs=manager.openQuery(query);        
          while(rs.next())
          { 
              //obtengo el tipo
              tipo=rs.getObject(2).toString().charAt(0);
              //Construyo una dirección
              dir=new Direccion(rs.getInt(1),tipo,rs.getString(2),rs.getString(3),
                      rs.getString(4),Integer.parseInt(rs.getString(5)),
                      rs.getString(6),rs.getString(7));
              //lo agrego en la coleccion que luego devolveré
              direcciones.add(dir);          
          }
          
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }        
        return direcciones; 
    }
    
    public Collection getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
