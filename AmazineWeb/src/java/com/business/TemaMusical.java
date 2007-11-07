/*
 * TemaMusical.java
 * 
 * Created on 06/11/2007, 14:55:00
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 *
 * @author Fernando
 */
public class TemaMusical extends Business{
private String nombre;
private String duracion;
private String autor;

public TemaMusical(int numeroTema,String n,String d,String a)
{ 
    super(numeroTema);
    nombre=n;
    duracion=d;
    autor=a;
}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public String toString()
    {
        String aux="\nNúmero: "+getId();
        aux+="\nNombre: "+nombre+" - "+autor;
        aux+="\nDuración: "+duracion;
        return aux;
    }
}
