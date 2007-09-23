package ArchivoHash.persistencia;

import java.io.*;

public class NodoHash implements GrabableHash{
	private RegisterHash info;
	private long next;
	
	public NodoHash(){}
	public NodoHash(RegisterHash info)
	{
		this.info=info;
		next=-1;
	}
	public NodoHash(RegisterHash info, long next) {
		this.info = info;
		this.next = next;
	}
	public RegisterHash getInfo() {
		return info;
	}
	public void setInfo(RegisterHash info) {
		this.info = info;
	}
	public long getNext() {
		return next;
	}
	public void setNext(long next) {
		this.next = next;
	}
	
	/**
	 * Redefinici�n del m�todo sizeOf de la interfaz GrabableHash.
	 */
	public int sizeOf()
	{
		return info.sizeOf()+8;
	}

	/**
	 * Redefinici�n del m�todo compareTo de la interfaz GrabableHash.
	 */
	public int compareTo(GrabableHash g)
	{
		return info.compareTo(g);
	}
	
	/**
	 * 
	 * Redefinici�n del m�todo grabar de la interfaz GrabableHash.
	 */
	public void grabar(RandomAccessFile archivo)
	{
		try{
			info.grabar(archivo);
			archivo.writeLong(next);
		}catch(IOException ioe){ioe.printStackTrace();}
	}
	
	/**
	 * Redefinici�n del m�todo leer de la interfaz GrabableHash.
	 */
	public void leer(RandomAccessFile archivo)
	{
		try{
			info.leer(archivo);
			next=archivo.readLong();
		}catch(IOException ioe){ioe.printStackTrace();}
	}
	
	/**
	 * Redefinici�n del m�todo hashCode de la interfaz GrabableHash.
	 */
	public int hashCode()
	{
		return info.hashCode();
	}
	
	/**
	 * Redefinici�n del m�todo equals heredado de Object.
	 */
	public boolean equals(Object o)
	{
		return info.compareTo((NodoHash)o)==0;
	}
	
	/**
	 * M�todo que enmascara el manejo del estado de los registros. Devuelve true si el registro est� cerrado.
	 * @return
	 */
	public boolean isActive()
	{
		return info.getEstado()==RegisterHash.CERRADO;
	}
	
	/**
	 * M�todo que enmascara el manejo del estado de los registros.
	 * @return
	 */
	public void setActive()
	{
		info.setEstado(RegisterHash.CERRADO);
	}
	
	/**
	 * M�todo que enmascara el manejo del estado de los registros.
	 * @return
	 */
	public void setInactive()
	{
		info.setEstado(RegisterHash.MUERTO);
	}
}

