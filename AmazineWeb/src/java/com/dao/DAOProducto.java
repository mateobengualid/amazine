/*
 * DAOProducto.java
 * 
 * Created on 05/11/2007, 18:25:34
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import com.business.Business;
import com.business.Categoria;
import com.business.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class DAOProducto implements DAOInterface{
private DBManager manager;
private DAOCategoria daoCat;
private DateFormat dateFormat;
public DAOProducto(DBManager m)
{
    dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
    manager=m;
    daoCat=new DAOCategoria(m);
}

    public Producto get(String businessId) {
        Producto p=null;
        ResultSet rs=null;
        String query;
        int id=Integer.parseInt(businessId);
        try {
            query="call libreria.get_producto("+id+",@nombre,@descripcion,@precio,@idcategoria,@fecha);";
            query+="call libreria.get_categoria(@idcategoria,@categoria);";
            query+="select @nombre,@descripcion,@precio,@categoria,@fecha";
            rs=manager.openCallableQuery(query);        
            rs.first();
            Date fecha=dateFormat.parse(rs.getString(5));
            p=new Producto(id,rs.getObject(1).toString(),rs.getObject(2).toString(),
                  rs.getFloat(3),new Categoria(rs.getString(4)),fecha);
           } catch (ParseException ex) {
            Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }
          return p; 
    }

    public void insert(Business b) {
        try{
            Producto p=(Producto) b;
            String query,fecha;
            int idCategoria = daoCat.getId(p.getCategoria());
            int id = manager.getMaxId("producto", "idproducto") + 1;
            p.setId(id);//para quedarme con  la clave.
            fecha=dateFormat.format(p.getFechaSalida());
            query = "select libreria.save_Producto(" + id +","+ "\'"+p.getNombre()+ 
                    "\'"+"," + "\'"+ p.getDescripcion()+ "\'"+"," + p.getPrecio()+
                    ","+idCategoria +"," + "\'"+fecha + "\'"+ ")";
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

    public Collection getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
