/*
 * DAOInterface.java
 *
 * Created on 4 de noviembre de 2007, 15:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.dao;

import com.business.Business;
import java.util.Collection;

/**
 *
 * @author Fernando
 */
public interface DAOInterface {
    
    public Business get(String businessId);
    public void insert(Business b);
    public void update(Business b);
    public void delete(Business b);
    public Collection getAll();
}
