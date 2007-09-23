package ArchivoHash.persistencia;
import java.io.RandomAccessFile;
/**
 * Interfaz basada en la interfaz Grabable programada por el Ing. Valerio Fritelli, que sirve para 
 * plantear la estructura básica que deberá asumir cualquier clase que 
 * quiera grabarse en un Archivo Hash
 * @author Fernando
 *
 */

public interface GrabableHash extends Comparable<GrabableHash>{

    /**
     *  Calcula el tamaño en bytes del objeto, tal como será grabado
     *  @return el tamaño en bytes del objeto
     */
    int sizeOf();    
    /**
     *  Indica cómo grabar un objeto
     *  @param el archivo donde será grabado el objeto
     */
    void grabar (RandomAccessFile a);
    
    /**
     *  Indica cómo leer un objeto
     *  @param a el archivo donde se hará la lectura
     */
    void leer (RandomAccessFile a);
    
    /**
     * Genera un hash Code 
     * @return int con el hash Code generado.
     */
    int hashCode();
    
}
