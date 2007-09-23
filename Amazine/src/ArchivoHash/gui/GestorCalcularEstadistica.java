/*
 * GestorCalcularEstadistica.java
 *
 * Created on 3 de septiembre de 2006, 11:37
 */

package ArchivoHash.gui;
import sorters.*;
/**
 *
 * @author Intel
 */
public class GestorCalcularEstadistica {
    public static final int DESBORDE = 0;
    public static final int ABIERTO = 1;
    
    private int cantElementos;
    private int modo;
    private int[] vectorElementos;
    private Integer[] vectorObjetos;
    private boolean hayRepetidos;
    private boolean sonEnteros;
    
    /** Crea una instancia de  <code>GestorCalcularEstadistica</code>*/
    public GestorCalcularEstadistica(int cantidad, int modoOrd, boolean repeticion, boolean sonEnteros) {
        this.cantElementos = cantidad;
        this.modo = modoOrd;
        this.hayRepetidos = repeticion;
        this.sonEnteros = sonEnteros;
    }
    
    private void generarAleatorio(int[] vector, int inf, int sup) {
        if(inf<sup) {
            for(int i=inf; i<sup; i++) {
                if(Math.random()<0.34) {
                    int aux = vector[i];
                    vector[i] = vector[sup-i];
                    vector[sup-i] = aux;
                }
                if(hayRepetidos && Math.random()<0.34) {
                    vector[i] = vector[sup-i];
                }
            }
            generarAleatorio(vector, inf, (sup+inf)/2);
            generarAleatorio(vector, (sup+inf)/2+1, sup);
        }
    }
    
    /** Genera un vector en base a los par�metros de construcci�n deseados*/
    private void generarVectorEnteros() {
        vectorElementos = new int[cantElementos];
        switch(modo){
            case(ORDENADO):for(int i=0; i<cantElementos;i++)
                vectorElementos[i] = i;
            break;
            case(INV_ORDENADO):for(int i=0; i<cantElementos;i++)
                vectorElementos[i] = cantElementos-i;
            break;
            case(ALEATORIO):for(int i=0; i<cantElementos;i++)
                vectorElementos[i]= i;
            generarAleatorio(vectorElementos,0,cantElementos-1);
            break;
        }
    }
    
    private void generarAleatorio(Integer[] vector, int inf, int sup) {
        if(inf<sup) {
            for(int i=inf; i<sup; i++){
                if(Math.random()<0.34) {
                    Integer aux = vector[i];
                    vector[i] = vector[sup-i];
                    vector[sup-i] = aux;
                }
                if(hayRepetidos && Math.random()<0.34) {
                    vector[i] = new Integer(vector[sup-i].intValue());
                }
            }
            generarAleatorio(vector, inf, (sup+inf)/2);
            generarAleatorio(vector, (sup+inf)/2+1, sup);
        }
    }
    
    private void generarVectorObjetos() {
        vectorObjetos = new Integer[cantElementos];
        switch(modo){
            case(ORDENADO):for(int i=0; i<cantElementos;i++)
                vectorObjetos[i] = new Integer(i);
            break;
            case(INV_ORDENADO):for(int i=0; i<cantElementos;i++)
                vectorObjetos[i] = new Integer(cantElementos-i);
            break;
            case(ALEATORIO):for(int i=0; i<cantElementos;i++)
                vectorObjetos[i]= new Integer(i);
            generarAleatorio(vectorObjetos,0,cantElementos-1);
            break;
        }
    }
    
    /** Genera un informe estad�stico*/
    public void generarInforme(boolean[] seleccionMetodos, Principal p) {
        ArchivoHash.gui.ChartBean chart = new ChartBean();
        Ordenador ord = new Ordenador();
        
        if(sonEnteros) {
            generarVectorEnteros();
        } else {
            generarVectorObjetos();
        }
        
        if(seleccionMetodos[SELECCION]) {
            ord.setAlgoritmo(SELECCION);
            long time = System.currentTimeMillis();
            if(sonEnteros) {
                ord.getAlgoritmo().ordenar((int[])(vectorElementos.clone()));
            } else {
                ord.getAlgoritmo().ordenar((Integer[])(vectorObjetos.clone()));
            }
            time = System.currentTimeMillis()-time;
            chart.addItem("Seleccion", time);
        }
        if(seleccionMetodos[BURBUJA]) {
            ord.setAlgoritmo(BURBUJA);
            long time = System.currentTimeMillis();
            if(sonEnteros) {
                ord.getAlgoritmo().ordenar((int[])(vectorElementos.clone()));
            } else {
                ord.getAlgoritmo().ordenar((Integer[])(vectorObjetos.clone()));
            }
            time = System.currentTimeMillis()-time;
            chart.addItem("Burbuja", time);
        }
        if(seleccionMetodos[INSERCION]) {
            ord.setAlgoritmo(INSERCION);
            long time = System.currentTimeMillis();
            if(sonEnteros) {
                ord.getAlgoritmo().ordenar((int[])(vectorElementos.clone()));
            } else {
                ord.getAlgoritmo().ordenar((Integer[])(vectorObjetos.clone()));
            }
            time = System.currentTimeMillis()-time;
            chart.addItem("Insercion", time);
        }
        if(seleccionMetodos[COMBSORT]) {
            ord.setAlgoritmo(COMBSORT);
            long time = System.currentTimeMillis();
            if(sonEnteros) {
                ord.getAlgoritmo().ordenar((int[])(vectorElementos.clone()));
            } else {
                ord.getAlgoritmo().ordenar((Integer[])(vectorObjetos.clone()));
            }
            time = System.currentTimeMillis()-time;
            chart.addItem("CombSort", time);
        }
        if(seleccionMetodos[SHELLSORT]) {
            ord.setAlgoritmo(SHELLSORT);
            long time = System.currentTimeMillis();
            if(sonEnteros) {
                ord.getAlgoritmo().ordenar((int[])(vectorElementos.clone()));
            } else {
                ord.getAlgoritmo().ordenar((Integer[])(vectorObjetos.clone()));
            }
            time = System.currentTimeMillis()-time;
            chart.addItem("ShellSort", time);
        }
        if(seleccionMetodos[HEAPSORT]){
            ord.setAlgoritmo(HEAPSORT);
            long time = System.currentTimeMillis();
            if(sonEnteros) {
                ord.getAlgoritmo().ordenar((int[])(vectorElementos.clone()));
            } else {
                ord.getAlgoritmo().ordenar((Integer[])(vectorObjetos.clone()));
            }
            time = System.currentTimeMillis()-time;
            chart.addItem("HeapSort", time);
        }
        if(seleccionMetodos[QUICKSORT]) {
            ord.setAlgoritmo(QUICKSORT);
            long time = System.currentTimeMillis();
            if(sonEnteros) {
                ord.getAlgoritmo().ordenar((int[])(vectorElementos.clone()));
            } else {
                ord.getAlgoritmo().ordenar((Integer[])(vectorObjetos.clone()));
            }
            time = System.currentTimeMillis()-time;
            chart.addItem("QuickSort", time);
        }
        UIMostrarEstadistica uiMostrar = new UIMostrarEstadistica(seleccionMetodos,cantElementos,modo,hayRepetidos, sonEnteros);
        uiMostrar.addChart(chart);
        
        p.addPesta�a(uiMostrar);
    }
}
