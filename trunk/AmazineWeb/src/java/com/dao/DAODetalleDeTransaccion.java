/*
 * DAODetalleDeTransaccion.java
 * 
 * Created on 06/11/2007, 19:15:18
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
public class DAODetalleDeTransaccion implements DAOInterface{
private DBManager manager;    
private DAOProducto daoProducto;

     public DAODetalleDeTransaccion(DBManager m,DAOProducto daoP)
    {
        manager=m;
        daoProducto=daoP;
    }

    public Business get(String businessId) {
        return null;
    }

    public void insert(Business b) {
         try{
            DetalleDeTransaccion dt=(DetalleDeTransaccion) b;
            String query;
            int idTransaccion = dt.getId();
            int idProducto=dt.getProductoId();//obtengo el id del producto de la transacción.
            query = "select libreria.save_DetalleTransaccion(" + idTransaccion +","+
                    idProducto+"," + dt.getImporte()+ "," + dt.getCantidad()+")";
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
     * Sobrecarga del método getAll(), que recibe el id y retorna una colección
     * con todos los detalles de transacciones de la transacción con ese id.
     * @return una colección con todos los detalles.
     * @param businessId, el id de la transacción, del cual quiero obtener los 
     * detalles
     * */
    public Collection<DetalleDeTransaccion> getAll(String businessId) {
        LinkedList <DetalleDeTransaccion> detalles=new LinkedList <DetalleDeTransaccion>();
        ResultSet rs=null;
        DetalleDeTransaccion dt=null;
        Producto prod=null;
        int id=Integer.parseInt(businessId);
        String query;
        try {
            query="SELECT T.idProducto, T.idtransaccion,  T.cantidad,T.importe," +
                 " FROM libreria.detalletransaccion T" +
                 " WHERE T.idetransaccion="+id;
          rs=manager.openQuery(query);        
          while(rs.next())
          { 
              //Construyo el Producto
              prod=daoProducto.get(String.valueOf(rs.getInt(1)));
              //Construyo el detalle
              dt=new DetalleDeTransaccion(rs.getInt(2), rs.getInt(3),
                       rs.getFloat(4),prod);
              //lo agrego en la coleccion que luego devolveré
              detalles.add(dt);          
          }
          
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }        
        return detalles; 
    }

    
    public void insertAll(Collection <DetalleDeTransaccion> detalles)
    {
         for(DetalleDeTransaccion dt: detalles)
         {
            insert(dt);
         }
    }
    
    public Collection getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
