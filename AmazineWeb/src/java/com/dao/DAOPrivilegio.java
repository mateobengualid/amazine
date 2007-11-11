/*
 * DAOPrivilegio.java
 *
 * Created on 08/11/2007, 10:38:34
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import com.business.*;
import java.sql.*;
import java.util.*;

/**
 * @author Fernando
 */
public class DAOPrivilegio implements DAOInterface
{
    private DBManager manager;

    public DAOPrivilegio(DBManager m)
    {
	manager = m;
    }

    public Business get(String businessId)
    {
	return null;
    }

    public void insert(Business b)
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(Business b)
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(Business b)
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Sobrecarga del método getAll(), que recibe el id y retorna una colección
     * con todos los privilegios de la persona con ese id.
     * @return una colección con todos los privilegios.
     * @param businessId, el id de la persona, del cual quiero obtener los
     * privilegios.
     * */
    public Collection<Privilegio> getAll(String businessId)
    {
	LinkedList<Privilegio> privilegios = new LinkedList<Privilegio>();
	ResultSet rs = null;
	Privilegio priv = null;
	int id = Integer.parseInt(businessId); //id de la persona
	String query;
	try
	{
	    query = "SELECT T.idprivilegio, P.cgoprivilegio, P.descripcion,P.observaciones" +
		    " FROM libreria.personaprivilegio T INNER JOIN libreria.privilegio P ON T.idprivilegio = P.idprivilegio" +
		    " WHERE T.idpersona=" + id;
	    rs = manager.openQuery(query);
	    while (rs.next())
	    {
		//Construyo un privilegio.
		priv = new Privilegio(rs.getInt(1), rs.getString(2), rs.getObject(3).toString(), rs.getObject(4).toString());
		//lo agrego en la coleccion que luego devolveré
		privilegios.add(priv);
	    }
	}
	catch (SQLException exc)
	{
	    exc.printStackTrace();
	}
	return privilegios;
    }

    public Collection getAll()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}