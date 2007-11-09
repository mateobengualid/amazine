/*
 * DAOTransaccionTest.java
 * JUnit based test
 *
 * Created on 8 de noviembre de 2007, 14:35
 */

package com.dao;

import com.business.*;
import java.util.*;
import junit.framework.TestCase;

/**
 *
 * @author Fernando
 */
public class DAOTransaccionTest extends TestCase {
    
    public DAOTransaccionTest(String testName) {
        super(testName);
    }

    public void testGet() {
    }

    public void testInsert() throws Exception {
        Transaccion t;
        LinkedList<DetalleDeTransaccion> detalles=new LinkedList<DetalleDeTransaccion>();
        DBManager m=new DBManager();
        LinkedList<Direccion> direcciones=new LinkedList<Direccion>();
        LinkedList <Email> emails=new LinkedList <Email>();
        LinkedList <Privilegio> privilegios=new LinkedList <Privilegio>();
        LinkedList <Telefono> telefonos=new LinkedList <Telefono>();
        direcciones.add(new Direccion(1, 'p', "5000", "Cordoba", "Obispo Oro", 445, "13 C", "NVA CBA"));
        emails.add(new Email(1, 'p', "ferfx@arnet.com.ar"));
        telefonos.add(new Telefono(1, 'p', "3825673200"));
        privilegios.add(new Privilegio(1, "administrador", "root", "root"));
        DAOLibro instance = new DAOLibro(m,new DAOProducto(m));        
        detalles.add(new DetalleDeTransaccion(1, 10, 15, instance.get("6")));
        
        Persona p= new Persona(1, "DNI", 31686715, "Cargnelutti", "Pablo Fernando", new Date(), "Masculino", "nada", telefonos, emails, direcciones, privilegios);
        Usuario u=new Usuario(p, "fer", "12345");
        Date fecha= new Date();
        t=new Transaccion(u, 5000.10f,fecha , detalles);
        DAOUsuario du=new DAOUsuario(m, new DAOPersona(m));
        //du.insert(u);
        new DAOTransaccion(m, new DAOProducto(m), 
                du).insert(t);
        
        System.out.println(t.toString());
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }

    public void testGetAll() {
    }
    
}
