/*
 * Telefono.java
 * 
 * Created on 07/11/2007, 16:16:18
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 *
 * @author Fernando
 */
public class Telefono extends Business
{
    private char tipo;
    private String telefono;
    
    public Telefono(int id,char tipo,String telefono)
    {
        super(id);
        this.tipo=tipo;
        this.telefono=telefono;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String toString()
    {
        String aux="\nID: "+getId();
        aux+="\nTipo: "+tipo;
        aux="\nNumero: "+telefono;
        return aux;
    }   
}
