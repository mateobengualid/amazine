/*
 * Business.java
 * 
 * Created on 04-nov-2007, 15:43:05
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 *
 * @author Mateo
 */
public class Business {
    int id;

    public Business(){}
    public Business(int id)
    {
        this.id=id;
    }
    public int getId()
    {
	return id;
    }

    public void setId(int id)
    {
	this.id = id;
    }
}
