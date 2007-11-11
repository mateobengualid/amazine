/*
 * DAOCDTest.java
 * JUnit based test
 *
 * Created on 6 de noviembre de 2007, 16:56
 */

package com.dao;

import com.business.Business;
import com.business.CD;
import com.business.Categoria;
import com.business.Producto;
import com.business.TemaMusical;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Fernando
 */
public class DAOCDTest extends TestCase {
    
    public DAOCDTest(String testName) {
        super(testName);
    }
    public void testGet() {
        try {
            System.out.println("get");
            String businessId = "7";//id para el ejeplo
            DBManager m = new DBManager();
            DAOCD instance = new DAOCD(m, new DAOProducto(m));
            CD result = instance.get(businessId);
            System.out.println(result);
            
        } catch (Exception ex) {
            Logger.getLogger(DAOCDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void testInsert() {
        try {
            System.out.println("insert");
            DBManager m = new DBManager();
            int id=m.getMaxId("libreria.producto", "idproducto")+1;
            Producto p = new Producto(id, "Lio musical", "variadisimo", 23.50f, new Categoria("nuevacat1"), new Date("07/25/99"));
            LinkedList<TemaMusical> l = new LinkedList<TemaMusical>();
            l.add(new TemaMusical(1, "no me ames", "5:30", "JenniferLopez y Marc Anthony"));
            l.add(new TemaMusical(2, "dime que no", "4:30", "Ricardo Arjona"));
            l.add(new TemaMusical(3, "se te ve la tanga", "3:40", "Damas Gratis"));
            CD cd = new CD(p, "65:01", "Varios", "BMG", l);
            
            DAOCD instance = new DAOCD(m, new DAOProducto(m));
            instance.insert(cd);
            //duracion autor sello
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }
    
     public void testDelete() throws Exception {
        System.out.println("Delete");
        DBManager m=new DBManager();
        DAOCD instance = new DAOCD(m,new DAOProducto(m)); 
        int id=m.getMaxId("libreria.producto", "idproducto");
        Producto p=new Producto();
        p.setId(id);
        instance.delete(p);
    }
          
}
