/*
 * Usuario.java
 * 
 * Created on 07/11/2007, 14:55:58
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 * @author Fernando
 */
public class Usuario extends Persona{
    
    private String login;
    private String password;
    
    public Usuario(Persona p,String login,String password)
    {
        super(p);
        this.login=login;
        this.password=password;    
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString()
    {
        String aux=super.toString();
        aux+="\nlogin: "+login;
        aux+="\npassword: "+password;
        return aux;                
    }
}
