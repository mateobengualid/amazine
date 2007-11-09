/*
 * Permiso.java
 * 
 * Created on 08/11/2007, 10:20:28
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 *
 * @author Fernando
 */
public class Privilegio extends Business{
    private String codigoPrivilegio;
    private String descripcion;
    private String observaciones;

    public Privilegio()
    {
	
    }
    
    public Privilegio(int id,String codigoPrivilegio,String descripcion,String observaciones)
    {
        super(id);
        this.codigoPrivilegio=codigoPrivilegio;
        this.descripcion=descripcion;
        this.observaciones=observaciones;
    }

    public String getCodigoPrivilegio() {
        return codigoPrivilegio;
    }

    public void setCodigoPrivilegio(String codigoPrivilegio) {
        this.codigoPrivilegio = codigoPrivilegio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String toString()
    {
        String aux="\nID: "+getId();
        aux+="\nCodigo de Privilegio: "+codigoPrivilegio;
        aux+="\nDescripción: "+descripcion;
        aux+="\nObservaciones: "+observaciones;
        return aux;
    }
}
