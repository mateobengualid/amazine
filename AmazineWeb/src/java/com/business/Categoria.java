/*
 * Categoria.java
 * 
 * Created on 04-nov-2007, 15:54:31
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 *
 * @author Mateo
 */
public class Categoria extends Business{
    String descripcion;

    public Categoria()
    {
    }    
    
    public Categoria(String c) {
        descripcion=c;
    }

    public String getDescripcion()
    {
	return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
	this.descripcion = descripcion;
    }
    
   public String toString()
   {
       return getDescripcion();
   }
}
