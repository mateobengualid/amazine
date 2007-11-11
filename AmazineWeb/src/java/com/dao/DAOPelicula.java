package com.dao;

import com.business.Business;
import com.business.Pelicula;
import com.business.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOPelicula implements DAOInterface{
    private DBManager manager;
    private DAOProducto daoProducto;
    
    public DAOPelicula(DBManager m,DAOProducto dp)
    {
        daoProducto=dp;
        manager=m;
    }
    
    public Pelicula get(String businessId)  {
        Pelicula peli=null;
        ResultSet rs=null;
        String query;
        int id=Integer.parseInt(businessId);
        try {
            query="call libreria.get_pelicula("+id+",@dura,@formato);";
            query+="select @dura,@formato";
            rs=manager.openCallableQuery(query);        
            rs.first();
          Producto p=daoProducto.get(businessId);
           peli=new Pelicula(p,rs.getString(1),rs.getString(2));           
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }
          return peli;     
        }        
    

    public void insert(Business b) {
        try {
            Pelicula peli = (Pelicula) b;
            String query;
            daoProducto.insert((Producto)peli);
            query= "select libreria.save_Pelicula("+peli.getId()+","+ 
            "\'"+peli.getDuracion()+ "\'"+","+ "\'"+peli.getFormato()+ "\'"+")";
            manager.executePrepared(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Business b) {
    }

    public void delete(Business b) {
        try {
            daoProducto.delete(b);
            String query = "call libreria.delete_pelicula("+b.getId()+")";
            manager.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Collection<Pelicula> getAll() {
        
            LinkedList<Pelicula> peliculas = new LinkedList<Pelicula>();
            Producto p;
            Pelicula peli;
            String id;
            ResultSet rs = null;
            String query = "call libreria.getall_pelicula()";
            try {
            rs = manager.openCallableQuery(query);
            while (rs.next()) {                
                    id = String.valueOf(rs.getInt(1));
                    p = daoProducto.get(id);
                    peli = get(id);
                    peliculas.add(peli);               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
        return peliculas;
    }

    
}

