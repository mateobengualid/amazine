/*
 * UIMostrarEstadistica.java
 *
 * Created on 3 de septiembre de 2006, 11:23
 */

package ArchivoHash.gui;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author  Intel
 */
public class UIMostrarEstadistica extends javax.swing.JPanel implements Serializable{
    
    /** Crea una instancia de informe estadistico */
    public UIMostrarEstadistica(boolean[] seleccionMetodos,int cantElementos,int modo,boolean repetidos, boolean sonEnteros) {
        initComponents();
        
        String cadAux = new String();
        
        //Carga la cantidad de elementos
        labelCantidad.setText(labelCantidad.getText()+cantElementos+"");
        
        //Carga el modo de ordenamiento
        switch(modo){
            case(GestorCalcularEstadistica.ORDENADO): cadAux = " Ordenada";break;
            case(GestorCalcularEstadistica.INV_ORDENADO): cadAux = " Inversamente Ordenada";break;
            case(GestorCalcularEstadistica.ALEATORIO): cadAux = " Aleatoria";break;
        }
        labelOrdenamiento.setText(labelOrdenamiento.getText()+cadAux);
        
        //Carga la repeticion
        if(repetidos)
        {
            cadAux=" Si";
        }
        else
        {
            cadAux=" No";
        }
        labelRepeticion.setText(labelRepeticion.getText()+cadAux);
        
        //Carga los algoritmos usados
        cadAux="";
        if(seleccionMetodos[GestorCalcularEstadistica.SELECCION])
        {            
            cadAux+=" Seleccion,";
        }
        if(seleccionMetodos[GestorCalcularEstadistica.BURBUJA])
        {            
            cadAux+=" Burbuja,";
        }                
        if(seleccionMetodos[GestorCalcularEstadistica.INSERCION])
        {            
            cadAux+=" Insercion,";
        }                
        if(seleccionMetodos[GestorCalcularEstadistica.COMBSORT])
        {            
            cadAux+=" CombSort,";
        }               
        if(seleccionMetodos[GestorCalcularEstadistica.SHELLSORT])
        {            
            cadAux+=" ShellSort,";
        }                
        if(seleccionMetodos[GestorCalcularEstadistica.HEAPSORT])
        {
            cadAux+=" HeapSort,";
        }                
        if(seleccionMetodos[GestorCalcularEstadistica.QUICKSORT])
        {            
            cadAux+=" QuickSort,";
        }
        cadAux = cadAux.substring(0, cadAux.length()-1);
        labelMetodos.setText(labelMetodos.getText()+cadAux);
        
        //carga la naturaleza de los elementos
        if(sonEnteros)
        {
            cadAux=" Enteros";
        }
        else
        {
            cadAux=" Objetos";
        }
        labelElementos.setText(labelElementos.getText()+cadAux);        
    }

    /** A�ade un bean ChartBean al panel correspondiente*/
    public void addChart(ChartBean chart) {
        jPanel2.add(chart);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        labelMetodos = new javax.swing.JLabel();
        labelCantidad = new javax.swing.JLabel();
        labelOrdenamiento = new javax.swing.JLabel();
        labelRepeticion = new javax.swing.JLabel();
        labelElementos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botonGuardar = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(5, 1));

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Parametros"));
        jPanel1.setMinimumSize(new java.awt.Dimension(371, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(371, 148));
        labelMetodos.setText("Metodos usados: ");
        jPanel1.add(labelMetodos);

        labelCantidad.setText("Cantidad de elementos: ");
        jPanel1.add(labelCantidad);

        labelOrdenamiento.setText("Orden original de la cadena: ");
        jPanel1.add(labelOrdenamiento);

        labelRepeticion.setText("Existencia de repetidos:");
        jPanel1.add(labelRepeticion);

        labelElementos.setText("Elementos a comparar:");
        jPanel1.add(labelElementos);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Grafico"));
        add(jPanel2, java.awt.BorderLayout.CENTER);

        botonGuardar.setText("Guardar Estadistica");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        add(botonGuardar, java.awt.BorderLayout.SOUTH);

    }//GEN-END:initComponents
    
    /** A traves de una ventana, permite guardar el informe generado*/
    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        try
        {            
            JFileChooser aa = new JFileChooser(); 
            int returnVal = aa.showSaveDialog(null);
            //Si selecciona un archivo
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File archivo = aa.getSelectedFile();
                ObjectOutputStream out =
                new ObjectOutputStream(
                new FileOutputStream(archivo));
                out.writeObject(this);                                
            }
        }
        catch(FileNotFoundException FNFE)
        {
            JOptionPane.showMessageDialog(this, new String("Error en la carga"));
        }
        catch(IOException IOE)
        {
            JOptionPane.showMessageDialog(this, new String("Error en la carga"));
        }
    }//GEN-LAST:event_botonGuardarActionPerformed
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelCantidad;
    private javax.swing.JLabel labelElementos;
    private javax.swing.JLabel labelMetodos;
    private javax.swing.JLabel labelOrdenamiento;
    private javax.swing.JLabel labelRepeticion;
    // End of variables declaration//GEN-END:variables
}