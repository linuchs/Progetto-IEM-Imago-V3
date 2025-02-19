/*============================================
package da importare
============================================*/
import java.awt.*;
import javax.swing.*;


/////////////////////////////////

class PannelloIsto extends JPanel
    {

     private JLabel label;
     private double [] freq;
     private int fattoreVerticale; // serve per una buona visualizzazione

     public PannelloIsto(String s, double [] F, Color c, int fattore)
         {
          label = new JLabel(s);
          this.add(label);
          freq=F;
         setBackground(c);
          fattoreVerticale=fattore;
         }

     public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Dimension dimensioniPanel = getSize();
    int altezza=dimensioniPanel.height;
    for(int i=0; i<freq.length; i++)
     g.drawLine(i,altezza,i,(int)(altezza-altezza*fattoreVerticale*freq[i]));
    // attenzione si deve "esagerare" l'altezza delle barre dell'istogramma
    // altrimenti esso risluterebbe troppo "piatto"
  }

    }