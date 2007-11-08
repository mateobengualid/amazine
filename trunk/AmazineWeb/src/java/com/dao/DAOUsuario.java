/*
 * daoUsuario.java
 * 
 * Created on 07/11/2007, 15:10:30
 * 
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
public class DAOUsuario implements DAOInterface
{private DBManager manager;
private DAOPersona daoPersona;


    
    public DAOUsuario(DBManager m,DAOPersona dp)
    {
        daoPersona=dp;
        manager=m;
        
        
    }
    
    public Usuario get(String businessId)  {
        Usuario u=null;
        ResultSet rs=null;
        String query;
        int id=Integer.parseInt(businessId);
        try {
            query="call libreria.get_usuario("+id+",@log,@pass,@idpersona,@observaciones);";
            query+="select @log,@pass,@observaciones";
            rs=manager.openCallableQuery(query);        
            rs.first();
          Persona p=new Persona(daoPersona.get(businessId));
          u=new Usuario(p,rs.getString(1),rs.getString(2));
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }
          return u;     
        }        
    

    public void insert(Business b) {
        try {
            Usuario u= (Usuario) b;
            String query;
            //inserto la persona.
            daoPersona.insert((Persona)u);
            query= "select libreria.save_Usuario("+u.getId()+","+ 
            "\'"+u.getLogin()+ "\'"+","+ "\'"+u.getPassword()+ "\'"+u.getId()
              +",\'"+u.getObservaciones()+"\')";
            manager.executePrepared(query);
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Business b) {
    }

    public void delete(Business b) {
    }

    public Collection getAll() {
        return null;
    }
}
