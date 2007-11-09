/*
 * DAOLibro.java
 *
 * Created on 4 de noviembre de 2007, 15:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.dao;

import com.business.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class DAOLibro implements DAOInterface{
    private DBManager manager;
    private DAOProducto daoProducto;
    
    public DAOLibro(DBManager m,DAOProducto dp)
    {
        daoProducto=dp;
        manager=m;
    }
    public Libro get(String businessId)  {
        Libro l=null;
        ResultSet rs=null;
        String query;
        int id=Integer.parseInt(businessId);
        try {
            query="call libreria.get_libro("+id+",@dura,@isbn,@edi,@autor,@idioma);";
            query+="select @dura,@isbn,@edi,@autor,@idioma";
            rs=manager.openCallableQuery(query);        
            rs.first();
          Producto p=daoProducto.get(businessId);
           l=new Libro(p,rs.getInt(1),rs.getObject(2).toString(),rs.getObject(3).toString()
                   ,rs.getObject(4).toString(),rs.getObject(5).toString());                
           
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }
              return l;     
        }        
    

    public void insert(Business b) {
        try {
            Libro l = (Libro) b;
            String query;
            daoProducto.insert((Producto)l);
            query= "select libreria.save_Libro("+l.getId()+","+l.getPaginas()+","+ 
                   "\'"+l.getIsbn()+ "\'"+","+ "\'"+l.getEditorial()+ "\'"
                    +","+ "\'"+l.getAutor()+ "\'"+","+ "\'"+l.getIdioma()+ "\'"+")";
            manager.executePrepared(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Business b) {
    }

    public void delete(Business b) {
    }

    //.idproducto, T.duracion, T.isbn, T.editorial, T.autor, T.idioma
    public Collection<Libro> getAll() {
        LinkedList<Libro> libros = new LinkedList<Libro>();
            Producto p;
            Libro l;
            String id;
            ResultSet rs = null;
            String query = "call libreria.getall_libro()";
        try {
            
            rs = manager.openCallableQuery(query);
            while (rs.next()) {
                id = String.valueOf(rs.getInt(1));
                p = daoProducto.get(id);
                l = get(id);
                libros.add(l);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return libros;
    }

    
}
