/*
 * Producto.java
 * 
 * Created on 04-nov-2007, 15:40:58
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

import java.util.Date;

/**
 *
 * @author Mateo
 */
public class Producto extends Business{
    String nombre;
    String descripcion;
    float precio;
    Categoria categoria;
    Date fechaSalida;
    
    public Producto(int id,String n,String d, float p,Categoria c,Date fs)
    {
        super(id);
        nombre=n;
        descripcion=d;
        precio=p;
        categoria=c;
        fechaSalida=fs;                
    }
    public Producto(Producto p)
    {
        this(p.getId(),p.getNombre(),p.getDescripcion(),p.getPrecio(),
                p.getCategoria(),p.getFechaSalida());
    }
    @Override
    public boolean equals(Object p)
    {
	try
	{
	    return this.id == (((Producto) p).getId());
	}
	catch (ClassCastException exception)
	{
	    return false;
	}
    }

    @Override
    public int hashCode()
    {
	return super.hashCode();
    }

    public Categoria getCategoria()
    {
	return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
	this.categoria = categoria;
    }

    public String getDescripcion()
    {
	return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
	this.descripcion = descripcion;
    }

    public Date getFechaSalida()
    {
	return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida)
    {
	this.fechaSalida = fechaSalida;
    }

    public String getNombre()
    {
	return nombre;
    }

    public void setNombre(String nombre)
    {
	this.nombre = nombre;
    }

    public float getPrecio()
    {
	return precio;
    }

    public void setPrecio(float precio)
    {
	this.precio = precio;
    }
    
    public String toString()
    {
        String aux="ID: "+getId();
        aux+="\nNombre: "+nombre;
        aux+="\nDescripción: "+descripcion;
        aux+="\nPrecio: "+precio;
        aux+="\nCategoria: "+categoria;
        aux+="\nFecha: "+fechaSalida;
        return aux;       
    }
}





