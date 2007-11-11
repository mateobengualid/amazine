/*
 * DAOLibroTest.java
 * JUnit based test
 *
 * Created on 4 de noviembre de 2007, 16:48
 */

package com.dao;

import junit.framework.*;
import com.business.Business;
import com.business.Categoria;
import com.business.Libro;
import com.business.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author Fernando
 */
public class DAOLibroTest extends TestCase {
    
    public DAOLibroTest(String testName) {
        super(testName);
    }

    public void testGet() throws Exception {
        System.out.println("get");
        DBManager m=new DBManager();
        DAOLibro instance = new DAOLibro(m,new DAOProducto(m));        
        System.out.println(instance.get("6"));
    }    
    
    public void testSave() throws Exception {
        System.out.println("save");
        DBManager m=new DBManager();
        DAOLibro instance = new DAOLibro(m,new DAOProducto(m)); 
        int id=m.getMaxId("libreria.producto", "idproducto")+1;
        Producto p=new Producto(id,"Humano demasiado humano","largo...",
                38,new Categoria("nuevacat1"),new Date("07/25/99"));
        Libro libro=new Libro(p,550,"ISBN105","Friedirich Nietchzse","libros tontos","Español");
        instance.insert(libro);
    } 
    
    public void testDelete() throws Exception {
        System.out.println("Delete");
        DBManager m=new DBManager();
        DAOLibro instance = new DAOLibro(m,new DAOProducto(m)); 
        int id=m.getMaxId("libreria.producto", "idproducto");
        Producto p=new Producto();
        p.setId(id);
        instance.delete(p);
    }
}
