/*
 * DetalleDeTransaccion.java
 * 
 * Created on 06/11/2007, 18:41:08
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

/**
 *
 * @author Fernando
 */
public class DetalleDeTransaccion  extends Business{
    private int cantidad;
    private float importe;
    private Producto producto;

    public DetalleDeTransaccion()
    {
    }
    
    public DetalleDeTransaccion(int id,int cantidad,float importe,Producto p)
    {
        super(id);
        this.importe=importe;
        this.cantidad=cantidad;
        producto=p;
    }

    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
    
     public String toString()
    {
        String aux="\nDetalle n°: "+getId(); 
        aux+="\nNombre: "+producto.getNombre();
        aux+="\nCantidad: "+cantidad;
        aux+="\nImporte: $ "+importe;
        return aux;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public int getProductoId()
    {
        return producto.getId();
    }

    @Override
    public boolean equals(Object obj)
    {
	if (obj == null)
	{
	    return false;
	}
	if (getClass() != obj.getClass())
	{
	    return false;
	}
	final DetalleDeTransaccion other = (DetalleDeTransaccion) obj;
	return (this.id == other.id);
    }

    @Override
    public int hashCode()
    {
	int hash = 7;
	return hash;
    }

}
