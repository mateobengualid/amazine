/*
 * ChartBean.java
 *
 * Created on 14 de septiembre de 2006, 19:02
 */

package ArchivoHash.gui;
import java.awt.Color;
import java.io.Serializable;
import java.awt.Graphics;
import java.util.*;
/**
 *
 * @author  user
 */
public class ChartBean extends javax.swing.JComponent implements Serializable{

    class Value implements Serializable
    {
        String name;
        double value;
    }
    
    private ArrayList valores;
    /** Creates new form BeanForm */
    public ChartBean() {
        initComponents();
        setValores(new ArrayList());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        
    }
    // </editor-fold>//GEN-END:initComponents
    
    public void addItem(String name, double value)
    {
        Value aux = new Value();
        aux.name = name;
        aux.value = value;
        getValores().add(aux);
    }
    
    public void paintComponent(Graphics g)
    {   
        int pasoX = (int)((getWidth()-5)/getValores().size());
        int pasoY = (int)((getHeight()-5)/4);
        
        Iterator it = getValores().iterator();
        
        double max = 0;
        
        while(it.hasNext())
        {
            Value aux = (Value)(it.next());
            if(aux.value>max) max = aux.value;
        }
        
        //Columnas
        for(int i=0;i<getValores().size();i++)
        {
            Value aux = (Value)(getValores().get(i));
            int colorR = (i+1)*255/(getValores().size());//Default 255
            int colorG = ((i+1)*765/(getValores().size()))%256;//Default 510
            int colorB = ((i+1)*510/(getValores().size()))%256;//Default 765
            int supCol = (int)((getHeight()-5)*(1-aux.value/max));
            int izqCol = 5+pasoX/2+pasoX*i-pasoX/4;
            int derCol = pasoX/2;            
            
            g.draw3DRect(izqCol,supCol,pasoX/2,getHeight()-5-supCol,true);
            g.setColor(new Color(colorR,colorG,colorB));
            g.fillRect(izqCol+1,supCol+1,pasoX/2-1,getHeight()-6-supCol);
            g.setColor(Color.BLACK);
            if(getHeight()-30-5>supCol)
            {
                g.drawString(aux.name,izqCol+5,supCol+14);
                g.drawString(Double.toString(aux.value),izqCol+5,supCol+28);    
            }            
            else
            {
                g.drawString(aux.name,izqCol+5,supCol-24);
                g.drawString(Double.toString(aux.value),izqCol+5,supCol-10); 
            }
        }
        
        //Linea Vertical
        g.drawLine(5,5,5,getHeight()-5);
        for(int i=0;i<4;i++)
        {
            g.drawLine(2,5+pasoY*i,7,5+pasoY*i);//Peque�as lineas horizontales
            String auxStr = Double.toString(max*(4-i)/4);
            g.drawString(auxStr,10,10+pasoY*i);
        }
        
        //Linea Horizontal
        g.drawLine(5,getHeight()-5,getWidth()-5,getHeight()-5);
        for(int i=0;i<getValores().size();i++)
        {   
            g.drawLine(getWidth()-5-pasoX*i,getHeight()-2,getWidth()-5-pasoX*i,getHeight()-7);//Peque�as lineas verticales
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public ArrayList getValores() {
        return valores;
    }

    public void setValores(ArrayList valores) {
        this.valores = valores;
    }
    
}