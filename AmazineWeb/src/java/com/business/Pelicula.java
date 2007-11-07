/*
 * Pelicula.java
 * 
 * Created on 06/11/2007, 14:06:17
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 * @author Fernando
 */
public class Pelicula extends Producto{

    private String duracion;
    private String formato;

    public Pelicula(Producto p,String duracion,String formato){
        super(p);
        this.duracion=duracion;
        this.formato=formato;
    }
    
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    public String toString()
    {
        String aux=super.toString();
        aux+="\nDuracion: "+duracion;
        aux+="\nFormato: "+formato;
        return aux;
    }

}
