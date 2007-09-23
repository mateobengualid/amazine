package persistencia;
/**
 * Una clase para describir en forma genérica un registro hash para ser grabado en disco, implementa
 * la interfaz GrabableHash . Contiene un atributo de estado que indica como se encuentra ese registro
 * Si está abierto implica que se puede grabar, si está cerrado implica que hay un elemento allí y si está muerto
 * indica que fue borrado logicamente.
 * @author Ing. Valerio Frittelli
 * @version Julio de 2005
 * @author Fernando Cargnelutti - Actualización Septiembre 2007
 */

import java.io.*;
public class RegisterHash implements GrabableHash
{
	 public static final int ABIERTO=1;
	 public static final int CERRADO=2;
	 public static final int MUERTO=3;
     private int estado;    // marca de estado del registro. Ocupa 4 bytes en disco
     private GrabableHash datos;    // los datos puros que serán grabados
     public RegisterHash()
     {
        estado=ABIERTO;
     }
     
     /**
      *  Crea un Registro con datos y estado abierto.
      */
     public RegisterHash (GrabableHash d)
     {
    	 estado=ABIERTO;
         datos = d;
     } 
     
     /**
      *  Determina si el registro es activo o no
      *  @return true si es activo, false si no.
      */
     public int getEstado ()
     {
         return estado;
     }
     
     /**
      *  Cambia el estado del registro, en memoria
      *  @param x el nuevo estado
      */
     public void setEstado(int x)
     {
        estado = x;    
     }
     
     /**
      *  Cambia los datos del registro en memoria
      *  @param d los nuevos datos
      */
     public void setData(GrabableHash d)
     {
        datos = d;    
     }
     
     /**
      *  Accede a los datos del registro en memoria
      *  @return una referencia a los datos del registro
      */
     public GrabableHash getData()
     {
         return datos;
     }
     
     /**
      *  Calcula el tamaño en bytes del objeto, tal como será grabado en disco. Pedido por Grabable.
      *  @return el tamaño en bytes del objeto como será grabado.
      */
     public int sizeOf()
     {
        return datos.sizeOf() + 4;   // suma 4 por el atributo "estado", que es int.
     }
     
     /**
      *  Especifica cómo se graba un RegisterHash en disco. Pedido por Grabable.
      *  @param a el manejador del archivo de disco donde se hará la grabación
      */
     public void grabar (RandomAccessFile a)
     {
         try
         {
             a.writeInt(estado);
             datos.grabar(a);
         }
         catch(IOException e)
         {
             System.out.println("Error al grabar el estado del registro: " + e.getMessage());
             System.exit(1);
         }
     }

     /**
      *  Especifica cómo se lee un RegisterHash desde disco. Pedido por Grabable.
      *  @param a el manejador del archivo de disco desde donde se hará la lectura
      */
     public void leer (RandomAccessFile a)
     {
         try
         {
             estado = a.readInt();
             datos.leer(a);
         }
         catch(IOException e)
         {
             System.out.println("Error al leer el estado del registro: " + e.getMessage());
             System.exit(1);
         }
     }    
     
     /**
      * Lee desde un archivo un String de "tam" caracteres. Se declara static para que pueda ser usado en forma global por cualquier
      * clase que requiera leer una cadena de longitud fija desde un archivo.
      * @param  arch el archivo desde el cual se lee
      * @param  tam la cantidad de caracteres a leer
      * @return el String leido
      */
     public static final String readString (RandomAccessFile arch, int tam)
     { 
         String cad = null;
         
         try
         {
             char vector[] = new char[tam];
             for(int i = 0; i<tam; i++)
             {
               vector[i] = arch.readChar();
             }
             cad = new String(vector,0,tam);
         }
         catch(IOException e)
         {
            return cad;
         }
         
         return cad;
     }
    
     /**
      * Graba en un archivo un String de "tam" caracteres. Se declara static para que pueda ser usado forma en global por cualquier
      * clase que requiera grabar una cadena de longitud fija en un archivo.
      * @param  arch el archivo en el cual se graba
      * @param  cad la cadena a a grabar 
      * @param  tam la cantidad de caracteres a grabar
      */
     public static final void writeString (RandomAccessFile arch, String cad, int tam)
     {
         try
         {
             int i;
             char vector[] = new char[tam];
             for(i=0; i<tam; i++)
             {
                vector[i]= ' ';
             }
             cad.getChars(0, cad.length(), vector, 0);
             for (i=0; i<tam; i++)
             {
                arch.writeChar(vector[i]);
             }
         }
         catch(IOException e)
         {
             System.out.println("Error al grabar una cadena: " + e.getMessage());
             System.exit(1);
         }
     } 
     
     /**
      * Compara dos objetos de la clase RegisterHash. Devuelve el resultado de comparar los
      * datos contenidos en el registro.
      * @return 0 si los objetos eran iguales, 1 si el primero era mayor, -1 en caso contrario
      * @param x el objeto contra el cual se compara.
      */
     public int compareTo (GrabableHash x)
     {
         RegisterHash r = (RegisterHash) x;
         return datos.compareTo(r.datos);
     }
     
     public int hashCode()
     {
    	 return datos.hashCode();
     }
}
