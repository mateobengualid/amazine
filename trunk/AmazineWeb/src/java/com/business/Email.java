/*
 * Email.java
 * 
 * Created on 07/11/2007, 16:26:43
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 *
 * @author Fernando
 */
public class Email extends Business{

    private char tipo;
    private String email;
    
    public Email(int id,char tipo,String email)
    {
        super(id);
        this.tipo=tipo;
        this.email=email;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String telefono) {
        this.email=email;
    }
    
    public String toString()
    {
        String aux="\nID: "+getId();
        aux+="\nTipo: "+tipo;
        aux="\nEmail: "+email;
        return aux;
    }   
}

