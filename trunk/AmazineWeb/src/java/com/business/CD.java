/*
 * CD.java
 * 
 * Created on 06/11/2007, 14:27:03
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Fernando
 */
public class CD extends Producto{
    private String duracion;
    private String autor;
    private String selloDiscografico;
    private LinkedList<TemaMusical> temas;
    
    public CD(Producto p,String duracion,String autor,String selloDiscografico,
            Collection <TemaMusical>temas){
        super(p);
        this.duracion=duracion;
        this.autor=autor;
        this.selloDiscografico=selloDiscografico;
        this.temas=new LinkedList<TemaMusical>(temas);
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

    public String getSelloDiscografico() {
        return selloDiscografico;
    }

    public void setSelloDiscografico(String selloDiscografico) {
        this.selloDiscografico = selloDiscografico;
    }
    
    public LinkedList<TemaMusical> getTemas() {
        return temas;
    }

    public void setTemas(LinkedList<TemaMusical> temas) {
        this.temas = temas;
    }
    
    public void setTema(TemaMusical tema)
    {
        temas.add(tema);
    }
    
    public String toString()
    {
        String aux=super.toString();
        aux+="\nAutor: "+autor;
        aux+="\nDuración: "+duracion;
        aux+="\nSello Discográfico: "+selloDiscografico;
        for(TemaMusical tm: temas)
            aux+="\n"+tm;
        return aux;
    }


}
