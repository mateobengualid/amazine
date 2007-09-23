package persistencia;
/**
 * Hilo que correr� paralelo a la ejecuci�n del programa a fines de controlar
 * que el archivo hash siga siendo eficiente, redimensionando en caso de ser necesario.
 * @author Fernando Cargnelutti.
 */
public class Redimensionador extends Thread {
	
	private ArchivoHash archivo;
	public Redimensionador(ArchivoHash ar)
	{
		archivo=ar;
	}
	
	public void run()
	{
		while(true)
		{
			try{					
					sleep(5000);
					if(archivo.isOvercharged())
					{					
						long nuevaCapacidad=archivo.getCapacity()*2; // el nuevo tama�o ser� un 100% m�s grande que el original
					    archivo.redimensionar(nuevaCapacidad);					       
					}				
			}catch(InterruptedException ie)
			{
				System.out.println("El hilo est� interrumpido.");
				interrupted();
				break;
			}
		}
	}
}