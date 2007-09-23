package ArchivoHash.persistencia;

import java.io.EOFException;
import java.io.IOException;

public class AHListasDeDesborde extends ArchivoHash{

	private NodoHash nodo;  // NodoHash para operaciones auxiliares
	private int contNodos;
	public AHListasDeDesborde(String nombre, GrabableHash r, long tam,boolean primo)
	{
		super(nombre,r);
		inicializarArchivo(r,tam,primo);
		
	}

	/**
	 * M�todo privado que inicializa el archivo.
	 * @param r: tipo a grabar.
	 * @param tam: cantidad de registros que contendr� el archivo.
	 * @param primo: si deseo que la cantidad sea prima o no.
	 */
	private void inicializarArchivo(GrabableHash r, long tam, boolean primo) {
		setTipo(r);
		reg = new RegisterHash(r);
		if(primo)
			setCapacity(getPrimoCercano(tam));
		else
			setCapacity(tam);
		long indice=-1L;
		try{
		for(int i=0;i<getCapacity();i++)
			getMasterFile().writeLong(indice);
		}catch(IOException ioe){ioe.printStackTrace(); }
		contNodos=0;
	}
	
	
	/**
	 * M�todo que sirve para desplazarse entre las listas guardadas en el archivo.
	 * @param i: �ndice de la lista.
	 */
	public void seekList(long i)
	{
		try{
			getMasterFile().seek(40+(i*8));
		}catch(IOException ioe){ioe.printStackTrace();}
	}
	
	/**
	 * M�todo que lee un nodo del archivo.
	 * @return
	 */
	public NodoHash leerNodo()
	{
		       
        try
        {
            nodo = new NodoHash();
            nodo.leer(getMasterFile());
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el registro: " + e.getMessage());   
            System.exit(1);
        }

        return nodo;
	}
	
	/**
	 * M�todo para grabar un nodo en el archivo actual.
	 * @param n
	 */
	public void grabarNodo(NodoHash n)
	{
		if(n!=null)
			n.grabar(getMasterFile());
	}
	
	
	/**
	 * Devuelve el long guardado en el �rea de punteros o en el next de algun nodo.
	 * @return
	 */
	public long readNext()
	{
		try{
			return getMasterFile().readLong();
		}catch(IOException ioe){return -2;}
	}
	
	
	/**
	 * Buscar de la clase ArchivoHash.
	 * @param n: nodo a buscar
	 * @return la direcci�n de byte donde se encuentra el nodo o -1 si el nodo no existe.
	 */
	public long buscar(NodoHash n)
	{
		openForRead();
		
		RegisterHash r=n.getInfo();
		if(r == null || getTipo().getClass() != r.getData().getClass()) return -1;
        
		long indice,next,dir;
		indice=h(n);
        seekList(indice);// trata de ubicarse en la lista devuelta por la funci�n h.
        next=readNext();
        if(indice==-1)
       {
        	close();
        	return -1;
       }
        else
        	while(next!=-1)
        	{
        		seekByte(next);
        		dir=bytePos();
        		nodo=leerNodo();
        		if(nodo.isActive()&&nodo.equals(n))
        		{
        			close();
        			return dir;
        		}	
        		next=nodo.getNext();
        	}
        close();
        return -1;
	
    }
	
	
	/**
	 * Funci�n para obtener el indice de la lista donde se va a insertar el registro.
	 * @param n: nodo a insertar
	 * @return el long con el �ndice de la lista.
	 */
	public long h(NodoHash n)
	{
		return n.hashCode()%getCapacity();
	}
	
	/**
	 * M�todo para grabar un nodoHash en el archivo
	 * @param n: el nodo a agregar
	 * @return true o false, seg�n si se pudo o no grabar el nodo.
	 */
	public boolean alta(NodoHash n)
	{
		int cont=-1;
	if(buscar(n)!=-1)
	{	openForReadWrite();
		try{
		long indice,dir,next,actual;
		indice=h(n);
		goFinal();
		dir=getMasterFile().length();
		n.setActive();
		n.grabar(getMasterFile());
		seekList(indice);
		actual=bytePos();
		next=readNext();
		while(next!=-1)
		{
			seekByte(next+n.getInfo().sizeOf());// se ubica justo en el siguiente next.
			actual=bytePos();
			next=readNext();
			contNodos=(++cont>contNodos)?cont:contNodos;
			size++;	
		}
		seekByte(actual);
		getMasterFile().writeLong(dir);
			
		}catch(IOException ioe){}
		close();
		return true;
	}
	close();
	return false;
	}
	
	
	/**
	 * M�todo que de baja (fija como muerto) el registro contenido en el nodo.
	 * @param n: el nodo a dar de baja.
	 * @return: true o false, seg�n si se pudo o no dar de baja el nodo.
	 */
	
	public boolean baja(NodoHash n)
	{
		long dir=buscar(n);
		if(dir==-1)return false;
		else{
			openForReadWrite();
			seekByte(dir);
			nodo=leerNodo();
			nodo.setInactive();
			seekByte(dir);
			nodo.grabar(getMasterFile());
			close();
			return true;
			}
	}
	
	/**
	 * M�todo que sirve para reemplazar un registro existente con el mismo, con datos actualizados.
	 * @param n: registro actualizado
	 * @return true o false, seg�n si se pudo o no modificar el nodo.
	 */
	public boolean modificar(NodoHash n)
	{
		boolean b=false;
		long dir=buscar(n);
		if(dir!=-1)
		{
			openForReadWrite();
			seekByte(dir);
			n.grabar(getMasterFile());
			b=true;
			
		}
		close();
		return b;
	}
	
	public void redimensionar(long newCapacity)
	{
		AHListasDeDesborde temp = new AHListasDeDesborde("temporal.dat",getTipo(),newCapacity,true);
        temp.openForReadWrite();        
        this.openForRead();
        while (!this.eof())
        {
               nodo = this.leerNodo();
               if (nodo.isActive()) 
             	  temp.grabarNodo(nodo);
        }

        this.close();
        temp.close();
        this.delete();
        temp.rename(this);    
	}
	
	
	/**
	 * Redefinici�n del m�todo de la clase ArchivoHash. Controla si hay m�s de 3 nodos por lista. 
	 * @return si el archivo necesita o no ser redimensionado.
	 */
	public boolean isOvercharged()
	{
		return contNodos>3;
	}
}
