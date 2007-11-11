/*
 * DAOTransaccion.java
 * 
 * Created on 06/11/2007, 19:14:55
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;
import com.business.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Fernando
 */
public class DAOTransaccion implements DAOInterface{
private DBManager manager;
private DAOUsuario daoUsuario;
private DAODetalleDeTransaccion daoDetalles;
private DateFormat dateFormat;
    
    public DAOTransaccion(DBManager m,DAOProducto dp,DAOUsuario du)
    {
        daoUsuario=du;
        manager=m;
        daoDetalles=new DAODetalleDeTransaccion(m,dp);
        dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
    }
    
    public Transaccion get(String businessId) {
        Transaccion t=null;
        ResultSet rs=null;
        String query;
        int id=Integer.parseInt(businessId);
        try {
            query="call libreria.get_transaccion("+id+",@idusuario,@importe,@fecha)";
            query+="select @idusuario,@importe,@fecha";
            rs=manager.openCallableQuery(query);        
            rs.first();
            java.util.Date fecha=dateFormat.parse(rs.getString(3));
            Usuario u=daoUsuario.get(String.valueOf(rs.getInt(1)));//obtengo el usuario asociado a la transacción.
            t=new Transaccion(u,rs.getFloat(2),fecha,daoDetalles.getAll(businessId));
            } catch (ParseException ex) {
            ex.printStackTrace();
        }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }
          return t;     
        }        
    

    public void insert(Business b) {
        try {
            Transaccion t= (Transaccion) b;
            String query,fecha;
            daoDetalles.insertAll(t.getDetalles());
            //ACA IRÍA ALGO COMO CONTROL DEL USUARIO.
            fecha=dateFormat.format(t.getFecha()); 
            query= "select libreria.save_Transaccion("+t.getId()+","+ 
            t.getUsario().getId()+","+ t.getImporte()+",\'"+fecha+"\')";              
            manager.executePrepared(query);
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Business b) {
    }

    public void delete(Business b) {
        try {
            String query="delete from libreria.detalletransaccion where idtransaccion="+b.getId()+";";
            query += "call libreria.delete_transaccion("+b.getId()+")";
            manager.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Collection getAll() {
        return null;
    }
}