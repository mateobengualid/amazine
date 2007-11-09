/*
 * DAOProductoTest.java
 * JUnit based test
 *
 * Created on 9 de noviembre de 2007, 01:25
 */

package com.dao;

import junit.framework.TestCase;

/**
 *
 * @author Fernando
 */
public class DAOProductoTest extends TestCase {
    
    public DAOProductoTest(String testName) {
        super(testName);
    }

    public void testGet() {
    }

    public void testInsert() {
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }

    public void testGetAll() throws Exception {
        System.out.println(new DAOProducto(new DBManager()).getAll());
    }
    
}
