package ArchivoHash.persistencia;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;
/** 
 * Modificaci�n de la clase Archivo propuesta por el Ing. Valerio Fritelli
 * a fines de adaptarla a la funcionalidad de un archivo hash.
 * @author Fernando Cargnelutti
 */ 
public abstract class ArchivoHash{
	
	private File fd;                   // descriptor del archivo para acceder a sus propiedades externas.
    private RandomAccessFile maestro;  // objeto para acceder al contenido del archivo.
    private GrabableHash tipo;         // representa al contenido grabado en el archivo.
    		RegisterHash reg;				// RegistroHash para operaciones auxiliares.
    		int size;					//cantidad de Registros en el archivo.
    private static final float TOLERANCIA_PRIMO=0.3f; // tolerancia en el entorno del tama�o sugerido para crear un primo. 
    private long capacity;				// capacidad de almacenamiento del archivo.
    private final int BYTE_BASE = 40;   // El byte real en donde comienzan los registros.
    private static final float FACTOR_CARGA=0.6f; //indica el factor de carga m�xima antes de hacer un redimensionamiento.
    
    /**
     * En el archivo se usaran los primeros 40 bytes para guardar el tipo y asi poder ser usado en mas de una sesi�n.
     * @param nombre :  nombre del archivo.
     * @param r: tipo de dato a grabar en el archivo.
     * @param tam: tama�o de registros para inicializar el archivo.
     * @param primo: indica si se desea asegurar un numero primo cercano a tam para el tama�o.
     */
    public ArchivoHash (String nombre,GrabableHash r) 
    {    	
    	fd = new File(nombre);
    	if(fd.exists()) 
    	{	if(!comprobarTipo(r))
    		{	
    			System.out.println("Error de control de tipos. Imposible Continuar");
    			System.exit(1);
    		}
    	}	
    	else 				
    		grabarTipo(r);
    	size=0;
    }
    

    /**
     * M�todo privado que comprueba el tipo de registro que se intenta grabar con el tipo de registro que 
     * fue previamente grabado en el archivo.
     * @param r  El tipo que se desea grabar
     * @return true o false, seg�n coincidan o no los tipos.
     */
    
    private boolean comprobarTipo(GrabableHash r)
    {
    	String tipoLeido=null;
    	openForRead();
    	this.rewind();
    	tipoLeido=RegisterHash.readString(maestro, 20);//Leo un string de tama�o 20.
    	close();
    	tipoLeido=tipoLeido.trim(); // Quito los espacios en blanco
    	
    	if(r != null && ((RegisterHash)r).getData().getClass().getName().compareTo(tipoLeido)!=0)
    	   		return false;   // los tipos no coinciden
    	
     	return true;   	
    }
    
    
     /**
     * M�todo privado que graba al comienzo del archivo un String que representa 
     * el tipo de registros que va a contener.
     * @param r Registro modelo.
     */
    private void grabarTipo(GrabableHash r)
    {
    	String tipo=new RegisterHash(r).getData().getClass().getName();
    	openForReadWrite();
    	RegisterHash.writeString(maestro, tipo, 20);
    	close();
    }
    
    /**
     * M�todo privado ya que es solo usado por el constructor. Y al no posser llamada a h, no es apropiado que
     * est� disponible para ser llamado desde cualquier lugar.
     * @param r
     * @return
     */
    
    protected boolean altaDirecta (RegisterHash r)
    {
        boolean resp = false;

        if(r != null && tipo.getClass() == r.getData().getClass())
        {
            openForReadWrite();
            
            try
            {
                goFinal();
                grabar(r);
                resp = true;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            close();
        }

        return resp;
    }
    
    
    /**
     * M�todo p�blico y est�tico que analiza si un n�mero es primo o no.
     * @param numero: n�mero a analizar.
     * @return true si es primo, false sino.
     */
    public static boolean isPrime(long numero){
    	
    	if(numero%2==0) return false;
    	if(numero<4) return true;
    	
    	  long contador = 2;
    	  boolean primo=true;
    	  
    	  while ((primo) && (contador!=numero)){
    	    if (numero % contador == 0)
    	      primo= false;
    	    contador++;
    	  }
    	  return primo;
    	}


	/**
	 * M�todo p�blico y est�tico que provee un n�mero el cual es altamente probable que sea primo, "cercano" al especificado por par�metro.
	 * Utiliza el m�todo de BigInteger: probablePrime le pasa un random y la cantidad de bits necesarios para representar tam. Luego controla que no
	 * se exceda de la tolerancia para cercan�a, que el primo sea mayor que tam por razones l�gicas para un Hash y de igual manera ante la probabilidad 
	 * de encontrar las llamadas "lagunas de primos" se controla que el proceso se repita solo 100 veces. 
	 * @param tam: el n�mero "base" para generar el primo
	 * @return un n�mero probablemente primo.
	 */
	  public static long getPrimoCercano(long tam)
	    {
	    	if(isPrime(tam)) return tam;
	    	
	    	else{
	    		long primo;
	    		Random rnd=new Random();
	    		int cantBits=Long.toString(tam).length()*2+1,cont=0;
	    		
	    		do{
	    				primo=BigInteger.probablePrime(cantBits, rnd).longValue();
	    				cont++;
	    		}while((primo<tam)|| primo-tam>TOLERANCIA_PRIMO  * tam||cont==100);
	    		if(cont==100&&primo<tam)
	    			primo=tam;
	    		return primo;    		
	    	}
	    }
    
	  /**
	   * M�todo est�tico que devuelve el tipo de registro grabado en un archivo
	   * enviado por par�metro.
	   * @param arch: archivo en el cual se quiere leer el tipo de registro.
	   * @return: el tipo en una cadena de caracteres.
	   */
	  public static String getRegistroEnArchivo(String dir)
	  {
		  try{
		  RandomAccessFile arch=new RandomAccessFile(dir,"rw");
		  arch.seek(0);
			  return RegisterHash.readString(arch, 20);
		  }catch(IOException e){
			  return null;
		  }
	  }
	  
    /**
     *  Acceso al descriptor del archivo
     *  @return un objeto de tipo File con las propiedades de file system del archivo
     */
    public File getFileDescriptor()
    {
        return fd;   
    }
    
    /**
     * Devuelve un GrabableHash que representa el tipo de dato almacenado en el archivo.
     * @return tipo grabado.
     */
    public GrabableHash getTipo()
    {
    	return tipo;
    }
    
    
    public void setTipo(GrabableHash r)
    {
    	tipo=r;
    }
    
    public void setCapacity(long c)
    {
    	capacity=c;
    }
    /**
     * Funci�n hash que usa el codigo hash del RegisterHash y el tama�o del arreglo para determinar su ubicaci�n.
     * @param r
     * @return
     */
    
    public long h(RegisterHash r)
    {
    	return r.hashCode()%capacity;
    }
    
    /**
     * Retorna si el archivo est� o no sobrecargado.
     * @return true si est� sobrecargado el archivo y false en caso contrario
     */
    public boolean isOvercharged ()
    {
    	return size>capacity*FACTOR_CARGA; //se controla el size contra la carga aceptada. 
    }
    
    /**
     *  Acceso al manejador del archivo binario 
     *  @return un objeto de tipo RandomAccessFile que permite acceder al bloque f�sico de datos en disco, en forma directa
     */
    public RandomAccessFile getMasterFile()
    {
        return maestro;   
    }
    
    /**
     * Retorna la capacidad del archivo para almacenar registros  en el ArchivoHash.
     * @return cantidad de registros.
     */
    public long getCapacity()
    {
    	return capacity;
    }
    
    /**
     *  Acceso al descriptor de la clase del registro que se graba en el archivo 
     *  @return una cadena con el nombre de la clase del registro usado en el archivo
     */
    public String getRegisterType()
    {
        return tipo.getClass().getName();   
    }
    
    /**
     * Borra el Archivo del disco
     */
    public void delete()
    {
        fd.delete();
    }
    
    /**
     * Cambia el nombre del archivo
     * @param nuevo otro Archivo, cuyo nombre (o file descriptor) ser� dado al actual
     */
    public void rename(ArchivoHash nuevo)
    {
        fd.renameTo(nuevo.fd); 
    }
    
    /**
     *  Abre el archivo en modo de s�lo lectura. El archivo en disco debe existir previamente. Queda posicionado al
     *  principio del archivo.
     */
    public void openForRead ()
    {
        try
        {
            maestro = new RandomAccessFile(fd, "r");   
        }
        catch(IOException e)
        {
            System.out.println("Error de apertura archivo " + fd.getName() + ": " + e.getMessage());   
            System.exit(1);
        }
    }
    
    /**
     *  Abre el archivo en modo de lectura y grabaci�n. Si el archivo en disco no exist�a, ser� creado. Si exist�a,
     *  ser� posicionado al principio del archivo. Mueva el puntero de registro activo con el m�todo seekRegister() 
     *  o con seekByte().
     */
    public void openForReadWrite ()
    {
        try
        {
            maestro = new RandomAccessFile(fd, "rw");   
        }
        catch(IOException e)
        {
            System.out.println("Error de apertura archivo " + fd.getName() + ": " + e.getMessage());   
            System.exit(1);
        }
    }
    
    /**
     * Cierra el archivo
     */
    public void close()
    {
        try
        {
            maestro.close();
        }
        catch(IOException e)
        {
            System.out.println("Error al cerrar el archivo " + fd.getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Ubica el puntero de registro activo en la posici�n del registro n�mero i. 
     * Se supone que los registros grabados son del mismo tipo, y que la longitud 
     * de los registros es uniforme. Se tiene en cuenta el BYTE_BASE para llamar a seek.
     * @param i n�mero relativo del registro que se quiere acceder
     * @throws EOFException cuando se llega al final de archivo, est� excepci�n ser� manejada
     * oportunamente por los m�todo que usen este m�todo
     */
    public void seekRegister (long i) throws EOFException
    {
        try
        {
            maestro.seek(i * reg.sizeOf()+BYTE_BASE); //Por el String con la descripci�n de tipo.
        }
        catch(EOFException eof){
        	throw eof;
        }
        catch(IOException e)
        {
            System.out.println("Error al posicionar en el registro n�mero " + i + ": " + e.getMessage());
            System.exit(1);
        }
    }
    
    
    public void seekByte (long b)
    {
        try
        {
            maestro.seek(b);
        }
        catch(IOException e)
        {
            System.out.println("Error al posicionar en el byte n�mero " + b + ": " + e.getMessage());    
            System.exit(1);
        }
    }
    
    /**
     * M�todo que ubica el file pointer en el comienzo de los Registros: BYTE_BASE
     */
    public void rewind()
    {
        try
        {
            maestro.seek(BYTE_BASE);
        }
        catch(IOException e)
        {
            System.out.println("Error al rebobinar el archivo: " + e.getMessage());            
            System.exit(1);
        }
    }
    
    /**
     * Ubica el file pointer en el byte 0. 
     */
    public void totalRewind()
    {
        try
        {
            maestro.seek(0);
        }
        catch(IOException e)
        {
            System.out.println("Error al rebobinar el archivo: " + e.getMessage());            
            System.exit(1);
        }
    }
    
    /**
     * Devuelve el n�mero relativo del registro en el cual esta posicionado el archivo en este momento
     * @return el n�mero del registro actual
     */
    public long registerPos() 
    {
        try
        {
            return maestro.getFilePointer() / reg.sizeOf();
        }
        catch(IOException e)
        {
            System.out.println("Error al intentar devolver el n�mero de registro: " + e.getMessage());
            System.exit(1);
        }
        
        return -1;
    }
    
    /**
     * Devuelve el n�mero de byte en el cual esta posicionado el archivo en este momento
     * @return el n�mero de byte de posicionamiento actual
     */
    public long bytePos () 
    {
        try
        {
            return maestro.getFilePointer();
        }
        catch(IOException e)
        {
            System.out.println("Error al intentar devolver el n�mero de byte: " + e.getMessage());   
            System.exit(1);
        }
        
        return -1;
    }
    
    /**
     * Posiciona el puntero de registro activo al final del archivo
     */
    public void goFinal () 
    {
        try
        {
            maestro.seek(maestro.length());
        }
        catch(IOException e)
        {
            System.out.println("Error al posicionar al final: " + e.getMessage());            
            System.exit(1);
        }
    }
    
    /**
     * Devuelve la cantidad de registros del archivo en este momento
     * @return el n�mero de registros del archivo
     */
    public long countRegisters()
    {
        try
        {
            return maestro.length()-BYTE_BASE / reg.sizeOf();
        }
        catch(IOException e)
        {
            System.out.println("Error al calcular el n�mero de registros: " + e.getMessage()); 
            System.exit(1);
        }
        
        return 0;
    }
    
    /**
     * Determina si se ha llegado al final del archivo o no
     * @return true si se lleg� al final - false en caso contrario
     * @throws IOException si hubo problema en la operaci�n
     */
    public boolean eof ()
    {
        try
        {
            if (maestro.getFilePointer() < maestro.length()) return false;
            else return true;
        }
        catch(IOException e)
        {
            System.out.println("Error al determinar el fin de archivo: " + e.getMessage());  
            System.exit(1);
        }
        
        return true;
    }
    
    
    /**
     * Graba un registro en el archivo
     * @param r el registro a grabar
     */
    public void grabar (RegisterHash r)
    {
        if(r!=null && r.getData().getClass() == tipo.getClass())
        {
            try 
            {
                r.grabar(maestro);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                
            }
        }
    }
    
    /**
     * Lee un registro del archivo
     * @return el registro leido
     * @throws IOException si hubo problema en la operaci�n
     */
    
    public RegisterHash leer ()
    {
        RegisterHash r=null;
        
        try
        {
            r = new RegisterHash((GrabableHash)tipo.getClass().newInstance());
            r.leer(maestro);
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el registro: " + e.getMessage());   
            System.exit(1);
        }

        return r;
    }

      
    /**
     * Redimensiona el archivo actual de acuerdo con newCapacity.
     * @param newCapacity: nueva capacidad del archivo.
     */
    public abstract void redimensionar(long newCapacity);
  
}