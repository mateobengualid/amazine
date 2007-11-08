/*
 * DAOPersona.java
 * 
 * Created on 07/11/2007, 17:42:04
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;
import com.business.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
/**
 *
 * @author Fernando
 */
public class DAOPersona implements DAOInterface{
private DBManager manager;
private DAODireccion daoDireccion;
private DAOTelefono daoTelefono;
private DAOEmail daoEmail;
private DAOPrivilegio daoPrivilegio;
private DateFormat dateFormat;
    
   // public DAOPersona(DBManager m,DAOTelefono daoTelefono,DAODireccion daoDireccion
     //       ,DAOEmail daoEmail)
     public DAOPersona(DBManager m)
    {
         manager=m;
        this.daoDireccion=new DAODireccion(m);
        this.daoEmail=new DAOEmail(m);
        this.daoTelefono=new DAOTelefono(m);
        daoPrivilegio=new DAOPrivilegio(m);
        dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
               
    }
    
    public Persona get(String businessId)  {
        Persona p=null;
        ResultSet rs=null;
        String query,observaciones;
        int id=Integer.parseInt(businessId);
        try {
            query="call libreria.get_persona("+id+",@tipodoc,@nrodoc,@apellido," +
                    "@apellido,@nombre,@sexo,@fechanac,@observaciones);";
            query+="select @tipodoc,@nrodoc,@apellido,@nombre,@sexo" +
                    ",@fechanac,@observaciones";
            
            rs=manager.openCallableQuery(query);        
            rs.first();           
            java.util.Date fecha=dateFormat.parse(rs.getString(6));
            observaciones=(String) rs.getObject("observaciones");     
            
            p=new Persona(id,rs.getString(1),Integer.parseInt(rs.getString(2))
            ,rs.getObject(3).toString(),rs.getObject(4).toString(),fecha,
            rs.getString(5),observaciones,daoTelefono.getAll(businessId),
            daoEmail.getAll(businessId),daoDireccion.getAll(businessId),
                    daoPrivilegio.getAll(businessId));
            
            } catch (ParseException ex) {
            ex.printStackTrace();
            }catch (SQLException exc) {
                  exc.printStackTrace();                    
                }
          return p;     
        }        
    

    public void insert(Business b) {
        try {
            Persona p= (Persona) b;
            String query,fecha;
            fecha=dateFormat.format(p.getFechaNacimiento());
            query= "select libreria.save_Persona("+p.getId()+","+ 
            "\'"+p.getTipoDoc()+ "\'"+","+ "\'"+p.getNumeroDoc()+ "\'"+
              ",\'"+p.getApellido()+"\',\'"+p.getNombre()+"\',\'"+p.getSexo()+
              "\',\'"+fecha+"\',\'"+p.getObservaciones()+"\')";
            manager.executePrepared(query);
            insertDirecciones(p);
            insertEmails(p);
            insertTelefonos(p);
            insertPrivilegios(p);
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
    
    /**
     * Método privado que mapea a la base de datos todas las direcciones de la 
     * persona pasada como parámetro.
     * */
    private void insertDirecciones(Persona p) throws SQLException
    {
            String query="";
            int idPersona=p.getId();
            for(Direccion dir: p.getDirecciones())
            {   
                query="select libreria.save_PersonaDireccion("+idPersona+","+dir.getId()
                        +",\'"+dir.getTipo()+"\',\'"+dir.getCp()+"\',\'"+dir.getLocalidad()
                        +"\',\'"+dir.getCalle()+"\',\'"+dir.getNumero()+"\',\'"+dir.getDireccionPuerta()
                        +"\',\'"+dir.getObservaciones()+");";
            }
            manager.executePrepared(query);//el manager los separa y los ejecuta por separado
    }
    
    private void insertTelefonos(Persona p) throws SQLException
    {
       
            String query="";
            int idPersona = p.getId();
            for (Telefono tel : p.getTelefonos()) {
                query = "select libreria.save_PersonaTelefono(" + idPersona + "," + tel.getId() + ",\'" + tel.getTipo() + "\',\'" + tel.getTelefono() + "\');";
            }
            manager.executePrepared(query); //el manager los separa y los ejecuta por separado
       
    }
    
     private void insertEmails(Persona p) throws SQLException
    {
            String query="";
            int idPersona=p.getId();
            for(Email e: p.getEmails())
            {   
                query="select libreria.save_PersonaTelefono("+idPersona+","+e.getId()
                        +",\'"+e.getTipo()+"\',\'"+e.getEmail()+"\');";

            }
            manager.executePrepared(query);//el manager los separa y los ejecuta por separado
    }
    
     private void insertPrivilegios(Persona p) throws SQLException
    {
            String query="";
            int idPersona=p.getId();
            for(Privilegio priv: p.getPrivilegios())
            {   
                query="select libreria.save_PersonaPrivilegio("+idPersona+","+priv.getId()
                        +",\'"+priv.getCodigoPrivilegio()+"\',\'"+priv.getDescripcion()+"\',\'" +
                          priv.getObservaciones()+"\');";

            }
            manager.executePrepared(query);//el manager los separa y los ejecuta por separado
    }
}
