/*
 * DAOTemaMusical.java
 * 
 * Created on 06/11/2007, 15:48:47
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import com.business.Business;
import com.business.TemaMusical;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Fernando
 */
public class DAOTemaMusical implements DAOInterface{
private DBManager manager;    

     public DAOTemaMusical(DBManager m)
    {
        manager=m;
     }

    public TemaMusical get(String businessId) {
        return null;
    }

    public void insert(Business b) {
        
    }

    public void update(Business b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(Business b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Sobrecarga del método getAll(), que recibe el id y retorna una colección
     * con todos los temas de el cd con ese id.
     * @return una colección con todos los temas.
     * @param businessId, el id del cd, del cual quiero obtener los temas
     * */
    public Collection<TemaMusical> getAll(String businessId) {
        LinkedList <TemaMusical> temas=new LinkedList <TemaMusical>();
        ResultSet rs=null;
        TemaMusical t=null;
        int id=Integer.parseInt(businessId);
        String query;
        try {
            query="SELECT T.idtema, T.nombretema, T.duracion, T.autortema"
                   +" FROM libreria.tema T where T.idproducto="+id;
          rs=manager.openQuery(query);        
          while(rs.next())
          { 
              //Construyo un tema musical
              t=new TemaMusical(rs.getInt(1), rs.getObject(2).toString(),
                       rs.getString(3), rs.getObject(4).toString());
              //lo agrego en la coleccion que luego devolveré
              temas.add(t);          
          }
          
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }        
        return temas; 
    }

    public Collection getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
     
