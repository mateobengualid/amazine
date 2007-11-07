/*
 * Persona.java
 * 
 * Created on 07/11/2007, 15:15:14
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

import java.util.*;

/**
 *
 * @author Fernando
 */
public class Persona extends Business{
    
    private String tipoDoc;
    private int numeroDoc;
    private String apellido;
    private String nombre;
    private Date fechaNacimiento;
    private String sexo;
    private String observaciones;
    private LinkedList<Telefono> telefonos;
    private LinkedList<Email> emails;
    private LinkedList<Direccion> direcciones;

    public Persona(int id,String tipoDoc, int numeroDoc, String apellido,
			String nombre, Date fechaNacimiento, String sexo,
			String observaciones, Collection<Telefono> telefonos,
			Collection<Email> emails, Collection<Direccion> direcciones) {
		super(id);
		this.tipoDoc = tipoDoc;
		this.numeroDoc = numeroDoc;
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.observaciones = observaciones;
		this.telefonos = new LinkedList<Telefono>(telefonos);
		this.emails = new LinkedList<Email>(emails);
		this.direcciones = new LinkedList<Direccion>(direcciones);
	}
    
    public Persona(Persona p)
    {
        this(p.getId(),p.getTipoDoc(),p.getNumeroDoc(),p.getApellido(),
        p.getNombre(),p.getFechaNacimiento(),p.getSexo(),p.getObservaciones(),
                p.getTelefonos(),p.getEmails(),p.getDirecciones());
    }
    
    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(int numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
      public String toString()
    {
        String aux="ID: "+getId();
        aux+="\nNombre: "+nombre +" "+ apellido;
        aux+="\nDocumento: "+tipoDoc+": "+numeroDoc;
        aux+="\nFecha de Nacimiento: "+fechaNacimiento;
        aux+="\nSexo: "+sexo;
        aux+="\nObservaciones: "+observaciones;
        aux+="\n Direcciones:\n";
        for(Direccion d: direcciones)
          aux+="\n"+d;  
        aux+="\n Telefonos:\n";
        for(Telefono t: telefonos)
          aux+="\n"+t;
        aux+="\n E-mails:\n";
        for(Email e: emails)
          aux+="\n"+e;
        return aux;       
    }

    public LinkedList<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(LinkedList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public LinkedList<Email> getEmails() {
        return emails;
    }

    public void setEmails(LinkedList<Email> emails) {
        this.emails = emails;
    }

    public LinkedList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(LinkedList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }    
}