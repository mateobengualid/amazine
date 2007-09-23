package ArchivoHash.persistencia;

/**
 * Hilo que correrá paralelo a la ejecución del programa a fines de controlar
 * que el archivo hash siga siendo eficiente, redimensionando en caso de ser necesario.
 * @author Fernando Cargnelutti.
 */
public class Redimensionador extends Thread {

    private ArchivoHash archivo;

    public Redimensionador(ArchivoHash ar) {
	archivo = ar;
    }

    public void run() {
	while (true) {
	    try {
		sleep(5000);
		if (archivo.isOvercharged()) {
		    long nuevaCapacidad = archivo.getCapacity() * 2; // el nuevo tamaño será un 100% más grande que el original
		    archivo.redimensionar(nuevaCapacidad);
		}
	    } catch (InterruptedException ie) {
		System.out.println("El hilo está interrumpido.");
		interrupted();
		break;
	    }
	}
    }
}