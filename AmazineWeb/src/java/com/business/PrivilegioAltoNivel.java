/*
 * Privilegio.java
 * 
 * Created on 06-nov-2007, 7:15:04
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 *
 * @author Mateo
 * Fijate, esto es solo una guia
 */
public class PrivilegioAltoNivel {
    String persona;
    String pagina;
    String nombre;
    boolean esDeMenu;
    boolean esAccesible;

    public boolean isEsAccesible()
    {
	return esAccesible;
    }

    public void setEsAccesible(boolean esAccesible)
    {
	this.esAccesible = esAccesible;
    }

    public boolean isEsDeMenu()
    {
	return esDeMenu;
    }

    public void setEsDeMenu(boolean esDeMenu)
    {
	this.esDeMenu = esDeMenu;
    }

    public String getPagina()
    {
	return pagina;
    }

    public void setPagina(String pagina)
    {
	this.pagina = pagina;
    }

    public String getNombre()
    {
	return nombre;
    }

    public void setNombre(String nombre)
    {
	this.nombre = nombre;
    }

    public String getPersona()
    {
	return persona;
    }

    public void setPersona(String persona)
    {
	this.persona = persona;
    }
}
