/*
 * DAOCategoria.java
 * 
 * Created on 05/11/2007, 15:47:04
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import com.business.Business;
import com.business.CD;
import com.business.Categoria;
import com.business.Libro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Fernando
 */
public class DAOCategoria implements DAOInterface{

    private DBManager manager;
    public DAOCategoria(DBManager m)
    {
        manager=m;
    }
    public Categoria get(String businessId) {
        Categoria c=null;
        try {            
            int id = Integer.parseInt(businessId);
            ResultSet rs = manager.openCallableQuery("call libreria.get_categoria(" + id + ",@desc)");
            rs.first();
            c = new Categoria(rs.getObject(1).toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public int getId(Categoria c)
    {
        ResultSet rs;
        int id=-1;
        try {
            rs=manager.openQuery("select idcategoria from categoria where" +
                    " descripcion=" + "\'"+c.getDescripcion()+ "\'");
            rs.first();
            id=rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }
    public void insert(Business b) {
        try{
            Categoria c=(Categoria) b;
            String query;
            int id = manager.getMaxId("transaccion", "idtransaccion") + 1;
            query = "select libreria.save_Categoria(" + id +","+ "\'"+
                    c.getDescripcion()+"\')";                  
            manager.executePrepared(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Business b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(Business b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
    Método que recibe un business y dependiendo de que tipo de producto sea
     * me retorna todas las caterias disponibles para ese tipo de producto.
    **/
    public Collection<Categoria> getAll(Business b) {
      LinkedList<Categoria> categorias = new LinkedList<Categoria>();
        try {
            ResultSet rs = null;
            String query = "";
            String entidad;
            if (b instanceof Libro) {
                entidad = "libro";
            } else if (b instanceof CD) {
                entidad = "cd";
            } else {
                entidad = "pelicula";
            }
            query = "select DISTINCT T.descripcion " + "FROM libreria.categoria T, libreria." + entidad + " L,libreria.producto P" + "WHERE P.idproducto=L.idproducto AND T.idcategoria=P.idcategoria";
            rs = manager.openQuery(query);
            while(rs.next())
                categorias.add(new Categoria(rs.getString(1)));            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categorias;
    }
    
    public Collection getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
