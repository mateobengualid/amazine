/*
 * Transaccion.java
 * 
 * Created on 06/11/2007, 18:40:55
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Fernando
 */
public class Transaccion extends Business{
    private Usuario usuario;
    private float importe;
    private Date fecha;
    private LinkedList <DetalleDeTransaccion>detalles;

    public Transaccion() {
        
    }
    public Transaccion(Usuario usuario,float importe,Date f,Collection<DetalleDeTransaccion>detalles)
    {
        this.usuario=usuario;
        this.importe=importe;
        fecha=f; 
        this.detalles=new LinkedList<DetalleDeTransaccion>(detalles);
    }

    public Usuario getUsario()
    {
        return usuario;
    }

    public void setUsuario(Usuario u)
    {
        usuario=u;
    }
    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LinkedList<DetalleDeTransaccion> getDetalles() {
        return detalles;
    }

    public void setDetalles(LinkedList<DetalleDeTransaccion> detalles) {
        this.detalles = detalles;
    }
    
     public String toString()
    {
        String aux=super.toString();
        aux+="\nId: "+getId();
        aux+="\nId de usuario: "+usuario;
        aux+="\nImporte: $ "+importe;
        aux+="\n"+fecha;
        for(DetalleDeTransaccion dt: detalles)
            aux+="\n"+dt;
        return aux;
    }
}
