/*
 * Direccion.java
 * 
 * Created on 07/11/2007, 16:03:14
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 * @author Fernando
 */
public class Direccion extends Business{
    private String cp;
    private String localidad;
    private String calle;
    private int numero;
    private char tipo;
    private String direccionPuerta;
    private String observaciones;

    public Direccion(int id,char tipo,String cp, String localidad, String calle, int numero,
			String direccionPuerta, String observaciones) {
		super(id);
		this.cp = cp;
                this.tipo=tipo;
		this.localidad = localidad;
		this.calle = calle;
		this.numero = numero;
		this.direccionPuerta = direccionPuerta;
		this.observaciones = observaciones;
	}
    
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDireccionPuerta() {
        return direccionPuerta;
    }

    public void setDireccionPuerta(String direccionPuerta) {
        this.direccionPuerta = direccionPuerta;
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
        aux+="\nDireccion: "+calle+" "+numero+" "+direccionPuerta;
        aux+="\nLocalidad: "+localidad+" - CP: "+cp;      
        return aux;       
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
