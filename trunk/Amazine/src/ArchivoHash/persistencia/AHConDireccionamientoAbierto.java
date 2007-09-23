package ArchivoHash.persistencia;

import java.io.EOFException;
/**
 * Clase que hereda de ArchivoHash implementando los m�todos abstractos de esta, a fines de implementar un 
 * archivo hash con direccionamiento abierto.
 * @author Fernando Cargnelutti 
 *
 */
public class AHConDireccionamientoAbierto extends ArchivoHash{
	
	
	/**
	 * Llama al constructor de ArchivoHash y al m�todo inicializar para inicializar la tabla en disco.
	 * @param nombre: del archivo en donde voy a almacenar la tabla.
	 * @param r : representa el tipo de dato que va a contener la tabla. 
	 * @param tam: tamaño inicial para la tabla
	 */
	public AHConDireccionamientoAbierto (String nombre, GrabableHash r, long tam,boolean primo)
	{
		super(nombre,r);
		inicializarArchivo(r,tam,primo);
	}
	

	/**
     * Inicializa el archivo.
     */
    
    private void inicializarArchivo( GrabableHash r, long tam,boolean primo)
    {
    	try{
    		setTipo(r);
    		reg = new RegisterHash(r);
    		if(primo)
    		setCapacity(getPrimoCercano(tam));
    		else
    			setCapacity(tam);
    		for(long i=0;i<getCapacity();i++)
    			altaDirecta(new RegisterHash(getTipo().getClass().newInstance()));// crea y agrega registros abiertos.
    		
        }catch(ClassCastException cce)
		{cce.printStackTrace();}
		catch(IllegalAccessException iae)
		{iae.printStackTrace();}
		catch(InstantiationException ie)
		{ie.printStackTrace();}
    }
    
  
	
    /**
     * Busca un registro en el archivo. Si la clase del registro que se busca no coincide con la clase de los registros grabados 
     * en el archivo, retorna -1. En general, el retorno de -1 significa que el registro no fue encontrado.
     * @param r objeto a buscar en el archivo
     * @return la posici�n de byte del registro en el archivo, si existe, o -1 si no existe.
     */
    
    public long buscar (RegisterHash r)
    {
        if(r == null || getTipo().getClass() != r.getData().getClass()) return -1;
        
        openForRead();
        long pos = h(r), actual = registerPos(); // se asigna el indice de registro donde estar�a ubicado el registro.
        do{
        try{
        seekRegister(pos);  // trata de ubicarse en el lugar devuelto por la funci�n h.
        reg=leer();             	
        	if(reg.getData().equals(r.getData()))
        		return pos; // retorna el numero de registro donde est� el registro buscado.
        	else
        		pos++;        	
           }catch(EOFException finArch){
        	   if(reg!=null)
        		{
        		   pos=0;
        		}
        	   else break;// el archivo estaba vacio.
        	}
           finally{close();}
        }while(reg.getEstado()!=RegisterHash.ABIERTO && pos!=actual);   //trabaja mientras el registro no este vacio y     
        															 // no haya dado un vuelta completa	
        return -1;
    }
    
    
    /**
     * Agrega un registro en el archivo, controlando que no haya repetici�n y que la clase del nuevo registro coincida con la 
     * clase indicada para el archivo al invocar al constructor. El archivo debe estar abierto en modo de grabaci�n.
     * El m�todo es quien se encarga de marcar como cerrado el registro que va a ser grabado en el archivo.
     * @param r registro a agregar
     * @return true si fue posible agregar el registro - false si no fue posible
     */
    public boolean alta (RegisterHash r)
    {
        boolean resp = false;
        long pos;
                
        if(r != null && getTipo().getClass() == r.getData().getClass())
        {
            openForReadWrite();
           try
            {
                pos = buscar(r);
                if (pos == -1)
                {
                	r.setEstado(RegisterHash.CERRADO);     //seteo el estado en cerrado del registro a guardar
                	pos=h(r);													  //obtengo la ubicaci�n donde deber�a ser guardado.
                    
                	do{
                		try{      
                			seekRegister(pos);			// Me paro en esa ubicaci�n
                			reg=leer();						//leo
                			pos++;                    
                		}catch(EOFException eof)
                		{ 	pos=0;        	}
                    }while(reg.getEstado()!=RegisterHash.ABIERTO);
                    seekRegister(pos-1);// debo volver para grabar sobre el registro que cumple con tener su estado Abierto o muerto.
                	grabar(r);//grabo
                	size++;
                    resp =  true;                    
                }
            }
            catch(Exception e)
            {
                System.out.println("Error al grabar el registro: " + e.getMessage());
                System.exit(1);
            }            
            close();
        }

        return resp;
      }
    
    /**
     * Borra un registro del archivo. La clase del registro buscado debe coincidir con la clase indicada para el archivo 
     * al invocar al constructor. El archivo debe estar abierto en modo de grabaci�n. El registro se marca como borrado, 
     * aunque sigue f�sicamente ocupando lugar en el archivo
     * @param r registro a buscar y borrar
     * @return true si fue posible borrar el registro - false si no fue posible
     */
    public boolean baja (RegisterHash r)
    {        
        boolean resp = false;
        long pos;
        
        if(r != null && getTipo().getClass() == r.getData().getClass())
        {
            openForReadWrite();
            
            try
            {
                pos = buscar(r);
                if (pos != -1)
                {
                     seekRegister(pos);
                     reg = leer();
                     reg.setEstado(RegisterHash.MUERTO);
                     
                     seekRegister(pos);
                     grabar(reg);
                     resp = true;
                }   
            }
            catch(Exception e)
            {
                System.out.println("Error al eliminar el registro: " + e.getMessage());   
                System.exit(1);
            }
            
            close();
        }

        return resp;
    }   
     
    /**
     * Modifica un registro en el archivo. Reemplaza el registro en una posici�n dada, por otro tomado como par�metro.
     * La clase del registro buscado debe coincidir con la clase indicada para el archivo al invocar al constructor. El 
     * archivo debe estar abierto en modo de grabaci�n. 
     * @param r registro con los nuevos datos
     * @return true si fue posible modificar el registro - false si no fue posible
     */
    public boolean modificar (RegisterHash r)
    {
        boolean resp = false;
        long pos;
        
        if(r != null && getTipo().getClass() == r.getData().getClass())
        {
            openForReadWrite();
            
            try
            {
                pos = buscar(r);
                if (pos != -1)
                {
                     seekRegister(pos);
                     grabar(r); // graba el nuevo registro encima del anterior
                     resp = true;
                }   
            }
            catch(Exception e)
            {
                System.out.println("Error al modificar el registro: " + e.getMessage());   
                System.exit(1);
            }
            
            close();
        }

        return resp;
    }      
    
    
    /**
     * El M�todo es llamado por el hilo encargado de cuidar que el factor de carga no sea excedido, adem�s depura el archivo.
     * Redimensiona el Archivo de acuerdo con el tama�o de newCapacity.
     * @param newCapacity: tama�o del nuevo archivo.
     */
    public void redimensionar(long newCapacity)
    {
    	AHConDireccionamientoAbierto temp = new AHConDireccionamientoAbierto("temporal.dat",getTipo(),newCapacity,true);
        temp.openForReadWrite();        
        this.openForRead();
        while (!this.eof())
        {
               reg = this.leer();
               if (reg.getEstado()==RegisterHash.CERRADO) 
             	  temp.grabar(reg);
        }

        this.close();
        temp.close();
        this.delete();
        temp.rename(this);       	
    }
}