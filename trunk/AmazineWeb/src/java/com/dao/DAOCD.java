/*
 * DAOCD.java
 * 
 * Created on 06/11/2007, 14:34:25
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
public class DAOCD implements DAOInterface{
private DBManager manager;
private DAOProducto daoProducto;
private DAOTemaMusical daoTemas;
    
    public DAOCD(DBManager m,DAOProducto dp)
    {
        daoProducto=dp;
        manager=m;
        daoTemas=new DAOTemaMusical(m);
    }
    
    public CD get(String businessId)  {
        CD cd=null;
        ResultSet rs=null;
        String query;
        int id=Integer.parseInt(businessId);
        try {
            query="call libreria.get_cd("+id+",@dura,@autor,@sello);";
            query+="select @dura,@autor,@sello";
            rs=manager.openCallableQuery(query);        
            rs.first();
          Producto p=daoProducto.get(businessId);//obtengo la parte del producto
                  
           cd=new CD(p,rs.getString(1),rs.getString(2),rs.getString(3),
                   daoTemas.getAll(businessId));
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }
          return cd;     
        }        
    

    public void insert(Business b) {
        try {
            CD cd= (CD) b;
            String query;
            Collection <TemaMusical>temas=cd.getTemas();
            daoProducto.insert((Producto)cd);
            query= "select libreria.save_CD("+cd.getId()+","+ 
            "\'"+cd.getDuracion()+ "\'"+","+ "\'"+cd.getAutor()+ "\'"+
              ",\'"+cd.getSelloDiscografico()+"\')";
            manager.executePrepared(query);
            query="";
            //Grabo los temas del cd.
            for(TemaMusical tm: temas)
            {   
                query+="select libreria.save_Tema("+cd.getId()+","+tm.getId()+",\'"+
                        tm.getNombre()+"\',\'"+tm.getDuracion()+"\',\'"+tm.getAutor()+"\');";
            }
            manager.executePrepared(query);//el manager los separa y los ejecuta por separado
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Business b) {
    }

    public void delete(Business b) {
    }

    public Collection<CD> getAll() {
        LinkedList<CD> cds = new LinkedList<CD>();
            Producto p;
            CD cd;
            String id;
            ResultSet rs = null;
            String query = "call libreria.getall_cd()";
            try {
            rs = manager.openCallableQuery(query);
            while (rs.next()) {                
                    id = String.valueOf(rs.getInt(1));
                    p = daoProducto.get(id);
                    cd = get(id);
                    cds.add(cd);
                            
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cds;
    }
}
