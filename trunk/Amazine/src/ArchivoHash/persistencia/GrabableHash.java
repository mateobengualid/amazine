package persistencia;
import java.io.RandomAccessFile;
/**
 * Interfaz basada en la interfaz Grabable programada por el Ing. Valerio Fritelli, que sirve para 
 * plantear la estructura b�sica que deber� asumir cualquier clase que 
 * quiera grabarse en un Archivo Hash
 * @author Fernando
 *
 */

public interface GrabableHash extends Comparable<GrabableHash>{

    /**
     *  Calcula el tama�o en bytes del objeto, tal como ser� grabado
     *  @return el tama�o en bytes del objeto
     */
    int sizeOf();    
    /**
     *  Indica c�mo grabar un objeto
     *  @param el archivo donde ser� grabado el objeto
     */
    void grabar (RandomAccessFile a);
    
    /**
     *  Indica c�mo leer un objeto
     *  @param a el archivo donde se har� la lectura
     */
    void leer (RandomAccessFile a);
    
    /**
     * Genera un hash Code 
     * @return int con el hash Code generado.
     */
    int hashCode();
    
}
